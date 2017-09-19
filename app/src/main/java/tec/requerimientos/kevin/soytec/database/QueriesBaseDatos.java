package tec.requerimientos.kevin.soytec.database;

import tec.requerimientos.kevin.soytec.database.BaseDatosEventosTec.Tablas;
import tec.requerimientos.kevin.soytec.model.Asistencia;
import tec.requerimientos.kevin.soytec.model.Favorito;
import tec.requerimientos.kevin.soytec.model.Mensaje;
import tec.requerimientos.kevin.soytec.model.Usuario;
import tec.requerimientos.kevin.soytec.model.Evento;
import tec.requerimientos.kevin.soytec.database.EventosTec.Eventos;
import tec.requerimientos.kevin.soytec.database.EventosTec.Usuarios;
import tec.requerimientos.kevin.soytec.database.EventosTec.Mensajes;
import tec.requerimientos.kevin.soytec.database.EventosTec.Favoritos;
import tec.requerimientos.kevin.soytec.database.EventosTec.Asistencias;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

public class QueriesBaseDatos {
    private static BaseDatosEventosTec baseDatos;

    private static QueriesBaseDatos instancia = new QueriesBaseDatos();

    private QueriesBaseDatos() {
    }

    public static QueriesBaseDatos obtenerInstancia(Context contexto) {
        if (baseDatos == null) {
            baseDatos = new BaseDatosEventosTec(contexto);
        }
        return instancia;
    }

    public Cursor obtenerFavortosUsuario(int idUsuario) {

        String favoritos_join_evento_usuario = "favoritos " +
                "INNER JOIN usuario " +
                "ON favoritos.id_usuario = usuario.id " +
                "INNER JOIN evento " +
                "ON favoritos.id_evento = evento.id";

         String[] proyCabeceraPedido = new String[]{
                Eventos.id, Eventos.nombre,
                Eventos.categoria, Eventos.costo,
                Eventos.fecha, Eventos.lugar,
                Eventos.organizador };

        SQLiteDatabase db = baseDatos.getReadableDatabase();

        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();

        builder.setTables(favoritos_join_evento_usuario);

        String selection = Usuarios.id + " LIKE ?";
        String selectionArgs[] = new String[]{String.valueOf(idUsuario)};

        return builder.query(db, proyCabeceraPedido, selection, selectionArgs, null, null, null);
    }

    public Cursor obtenerAsistenciaEvento(int idUsuario) {

        String favoritos_join_evento_usuario = "asistencia " +
                "INNER JOIN usuario " +
                "ON asistencia.id_usuario = usuario.id " +
                "INNER JOIN evento " +
                "ON asistencia.id_evento = evento.id";

        String[] proyCabeceraPedido = new String[]{
                Eventos.id, Eventos.nombre,
                Eventos.categoria, Eventos.costo,
                Eventos.fecha, Eventos.lugar,
                Eventos.organizador };

        SQLiteDatabase db = baseDatos.getReadableDatabase();

        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();

        builder.setTables(favoritos_join_evento_usuario);

        String selection = String.format("%s=?", Usuarios.id);;
        String selectionArgs[] = new String[]{String.valueOf(idUsuario)};

        return builder.query(db, proyCabeceraPedido, selection, selectionArgs, null, null, null);
    }

    public void insertarUsuario(Usuario usuario) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        // Generar Pk
        String idUsuario = Usuarios.generarIdUsuario();

        ContentValues valores = new ContentValues();
        valores.put(Usuarios.id, idUsuario);
        valores.put(Usuarios.carnet, usuario.getCarnet());
        valores.put(Usuarios.contrasena, usuario.getContrasena());
        valores.put(Usuarios.correo, usuario.getCorreo());
        valores.put(Usuarios.tipoUsuario, usuario.getTipoUsuario());

        // Insertar cabecera
        db.insertOrThrow(Tablas.usuario, null, valores);
    }

    public void insertarEvento(Evento evento) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        // Generar Pk
        String idEvento = Eventos.generarIdEvento();

        ContentValues valores = new ContentValues();
        valores.put(Eventos.id, idEvento);
        valores.put(Eventos.nombre, evento.getNombre());
        valores.put(Eventos.fecha, String.valueOf(evento.getFecha()));
        valores.put(Eventos.organizador, evento.getOrganizador());
        valores.put(Eventos.lugar, evento.getLugar());
        valores.put(Eventos.categoria, evento.getCategoria());
        valores.put(Eventos.costo, evento.getCosto());


        // Insertar cabecera
        db.insertOrThrow(Tablas.evento, null, valores);
    }

    public void insertarMensaje(Mensaje mensaje) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        // Generar Pk
        String idMensaje = Mensajes.generarIdMensaje();

        ContentValues valores = new ContentValues();
        valores.put(Mensajes.id, idMensaje);
        valores.put(Mensajes.idUsuario, mensaje.getIdUsuario());
        valores.put(Mensajes.mensaje, mensaje.getMensaje());

        // Insertar cabecera
        db.insertOrThrow(Tablas.mensaje, null, valores);
    }


    public void insertarFavorito(Favorito favorito) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        // Generar Pk

        ContentValues valores = new ContentValues();
        valores.put(Favoritos.idEvento, favorito.getEventoId());
        valores.put(Favoritos.idUsuario, favorito.getUserId());

        // Insertar cabecera
        db.insertOrThrow(Tablas.favoritos, null, valores);
    }

    public void insertarFavorito(Asistencia asistencia) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        // Generar Pk

        ContentValues valores = new ContentValues();
        valores.put(Asistencias.idEvento, asistencia.getIdEvento());
        valores.put(Asistencias.idUsuario, asistencia.getIdUsuario());

        // Insertar cabecera
        db.insertOrThrow(Tablas.asistencia, null, valores);
    }

    public Cursor obtenerEventos() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();

        String sql = String.format("SELECT * FROM %s", Tablas.evento);

        return db.rawQuery(sql, null);
    }

    public Cursor obtenerUsuarios() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();

        String sql = String.format("SELECT * FROM %s", Tablas.usuario);

        return db.rawQuery(sql, null);
    }

    public Cursor obtenerMensajes() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();

        String sql = String.format("SELECT * FROM %s", Tablas.mensaje);

        return db.rawQuery(sql, null);
    }

    public Cursor Login(String carnet, String pass) {
        SQLiteDatabase db = baseDatos.getReadableDatabase();

        String sql = String.format("SELECT * FROM %s WHERE %s=? AND %s=?",
                Tablas.usuario, Usuarios.carnet, Usuarios.contrasena);

        String[] selectionArgs = {carnet, pass};

        return db.rawQuery(sql, selectionArgs);

    }

}
