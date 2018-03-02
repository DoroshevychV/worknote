package net.worknote.dto;

public class TokenModel {

    private Long id;

    private String key;

    private String value;

    public TokenModel() {
    }

    public TokenModel(String key, String value,Long id) {
        this.key = key;
        this.value = value;
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TokenModel{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
