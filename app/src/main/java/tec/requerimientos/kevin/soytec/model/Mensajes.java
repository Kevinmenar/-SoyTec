package tec.requerimientos.kevin.soytec.model;


public class Mensajes {
    private int id;
    private int idUsuario;
    private String mensaje;

    public Mensajes(int id, int idUsuario, String mensaje) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.mensaje = mensaje;
    }

    public Mensajes() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
