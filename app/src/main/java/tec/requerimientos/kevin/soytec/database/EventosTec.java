package tec.requerimientos.kevin.soytec.database;
import java.util.UUID;

public class EventosTec {

    public EventosTec() {
    }

    interface ColumnasAsistencia {
        String idUsuario = "id_usuario";
        String idEvento = "id_evento";
    }

    interface ColumnasFavoritos {
        String idUsuario = "id_usuario";
        String idEvento = "id_evento";
    }

    interface ColumnasEvento {
        String id = "id";
        String nombre = "nombre";
        String fecha = "fecha";
        String lugar = "lugar";
        String categoria = "categoria";
        String organizador = "organizador";
        String costo = "costo";
    }

    interface ColumnasMensajes {
        String id = "id";
        String idUsuario = "id_usuario";
        String mensaje = "mensaje";
    }

    interface ColumnasUsuario {
        String id = "id";
        String correo = "correo";
        String carnet = "carnet";
        String contrasena = "contrasena";
        String tipoUsuario = "tipo_usuario";
    }

    public static class Evento implements ColumnasEvento {
        public static String generarIdEvento() {
            return "E-" + UUID.randomUUID().toString();
        }
    }

    public static class Mensajes implements ColumnasMensajes {
        public static String generarIdMensaje() {
            return "M-" + UUID.randomUUID().toString();
        }
    }

    public static class Usuario implements ColumnasUsuario {
        public static String generarIdUsuario() {
            return "U-" + UUID.randomUUID().toString();
        }
    }

    public static class Asistencia implements ColumnasAsistencia {

    }

    public static class Favoritos implements ColumnasFavoritos {

    }
}
