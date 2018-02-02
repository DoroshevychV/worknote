package net.worknote.request;

public class IdRequest {

    private Long id;

    public IdRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "IdRequest{" +
                "id=" + id +
                '}';
    }

}
