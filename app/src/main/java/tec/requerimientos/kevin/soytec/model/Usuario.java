package tec.requerimientos.kevin.soytec.model;


import java.util.ArrayList;

public class Usuario {
    private int id;
    private String correo;
    private String carnet;
    private String contrasena;
    private String tipoUsuario;

    public Usuario(int id, String correo, String carnet, String contrasena, String tipoUsuario) {
        this.id = id;
        this.correo = correo;
        this.carnet = carnet;
        this.contrasena = contrasena;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}
