package tec.requerimientos.kevin.soytec.model;


public class Favoritos {
    private int userId;
    private int eventoId;

    public Favoritos(int userId, int eventoId) {
        this.userId = userId;
        this.eventoId = eventoId;
    }

    public Favoritos() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEventoId() {
        return eventoId;
    }

    public void setEventoId(int eventoId) {
        this.eventoId = eventoId;
    }
}
