package tec.requerimientos.kevin.soytec.database;

import tec.requerimientos.kevin.soytec.database.EventosTec.Mensajes;
import tec.requerimientos.kevin.soytec.database.EventosTec.Evento;
import tec.requerimientos.kevin.soytec.database.EventosTec.Usuario;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

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

        String idEvento = String.format("REFERENCES %s(%s)",
                Tablas.evento, Evento.id);

        String idUsuario = String.format("REFERENCES %s(%s)",
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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
