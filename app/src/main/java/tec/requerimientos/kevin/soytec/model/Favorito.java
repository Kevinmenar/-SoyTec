package tec.requerimientos.kevin.soytec.model;


public class Favorito {
    private int userId;
    private int eventoId;

    public Favorito(int userId, int eventoId) {
        this.userId = userId;
        this.eventoId = eventoId;
    }

    public Favorito() {
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
