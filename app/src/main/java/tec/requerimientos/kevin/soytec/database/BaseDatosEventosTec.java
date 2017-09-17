package tec.requerimientos.kevin.soytec.database;

import tec.requerimientos.kevin.soytec.database.EventosTec.Mensajes;
import tec.requerimientos.kevin.soytec.database.EventosTec.Evento;
import tec.requerimientos.kevin.soytec.database.EventosTec.Usuario;
import tec.requerimientos.kevin.soytec.database.EventosTec.Asistencia;
import tec.requerimientos.kevin.soytec.database.EventosTec.Favoritos;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.BaseColumns;

public class BaseDatosEventosTec extends SQLiteOpenHelper {

    private static final String nombreBaseDatos = "eventos_tec.db";
    private static final int versionActual = 1;
    private final Context contexto;

    interface Tablas {
        String asistencia = "asistencia";
        String evento = "evento";
        String favoritos = "favoritos";
        String mensaje = "mensaje";
        String usuario = "usuario";
    }

    interface Referencias {

        String idMensaje = String.format("REFERENCES %s(%s) ON DELETE CASCADE",
                Tablas.mensaje, Mensajes.id);

        String idEvento = String.format("REFERENCES %s(%s) ON DELETE CASCADE",
                Tablas.evento, Evento.id);

        String idUsuario = String.format("REFERENCES %s(%s) ON DELETE CASCADE",
                Tablas.usuario, Usuario.id);
    }

    public BaseDatosEventosTec(Context contexto) {
        super(contexto, nombreBaseDatos, null, versionActual);
        this.contexto = contexto;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT UNIQUE NOT NULL,%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL %s)",
                Tablas.mensaje, BaseColumns._ID,
                Mensajes.id, Mensajes.mensaje,
                Mensajes.idUsuario, Referencias.idUsuario));

        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL %s,%s TEXT NOT NULL %s)",
                Tablas.asistencia, BaseColumns._ID,
                Asistencia.idUsuario, Referencias.idUsuario,
                Asistencia.idEvento, Referencias.idEvento));

        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL %s,%s TEXT NOT NULL %s)",
                Tablas.favoritos, BaseColumns._ID,
                Favoritos.idUsuario, Referencias.idUsuario,
                Favoritos.idEvento, Referencias.idEvento));

        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT UNIQUE NOT NULL,%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL, %s TEXT NOT NULL," +
                        "%s TEXT NOT NULL)",
                Tablas.usuario, BaseColumns._ID,
                Usuario.id, Usuario.correo,
                Usuario.carnet, Usuario.contrasena,
                Usuario.tipoUsuario
                ));

        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT UNIQUE NOT NULL,%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL, %s TEXT NOT NULL," +
                        "%s TEXT NOT NULL)",
                Tablas.usuario, BaseColumns._ID,
                Usuario.id, Usuario.correo,
                Usuario.carnet, Usuario.contrasena,
                Usuario.tipoUsuario
        ));

        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT UNIQUE NOT NULL,%s TEXT NOT NULL," +
                        "%s DATETIME NOT NULL, %s TEXT NOT NULL," +
                        "%s TEXT NOT NULL, %s TEXT NOT NULL" +
                        "%s INTEGER NOT NULL)",
                Tablas.evento, BaseColumns._ID,
                Evento.id, Evento.nombre,
                Evento.fecha, Evento.lugar,
                Evento.categoria, Evento.organizador,
                Evento.costo
        ));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.asistencia);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.evento);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.favoritos);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.mensaje);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.usuario);

        onCreate(db);
    }
}
