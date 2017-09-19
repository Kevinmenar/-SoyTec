package tec.requerimientos.kevin.soytec.model;


public class Mensaje {
    private int id;
    private int idUsuario;
    private String mensaje;

    public Mensaje(int id, int idUsuario, String mensaje) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.mensaje = mensaje;
    }

    public Mensaje() {
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
