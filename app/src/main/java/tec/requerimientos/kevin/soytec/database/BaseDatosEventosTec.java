package tec.requerimientos.kevin.soytec.database;

import tec.requerimientos.kevin.soytec.database.EventosTec.Mensajes;
import tec.requerimientos.kevin.soytec.database.EventosTec.Eventos;
import tec.requerimientos.kevin.soytec.database.EventosTec.Usuarios;
import tec.requerimientos.kevin.soytec.database.EventosTec.Asistencias;
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
                Tablas.evento, Eventos.id);

        String idUsuario = String.format("REFERENCES %s(%s) ON DELETE CASCADE",
                Tablas.usuario, Usuarios.id);
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
                Asistencias.idUsuario, Referencias.idUsuario,
                Asistencias.idEvento, Referencias.idEvento));

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
                Usuarios.id, Usuarios.correo,
                Usuarios.carnet, Usuarios.contrasena,
                Usuarios.tipoUsuario
                ));

        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT UNIQUE NOT NULL,%s TEXT NOT NULL," +
                        "%s DATETIME NOT NULL, %s TEXT NOT NULL," +
                        "%s TEXT NOT NULL, %s TEXT NOT NULL" +
                        "%s INTEGER NOT NULL)",
                Tablas.evento, BaseColumns._ID,
                Eventos.id, Eventos.nombre,
                Eventos.fecha, Eventos.lugar,
                Eventos.categoria, Eventos.organizador,
                Eventos.costo
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
