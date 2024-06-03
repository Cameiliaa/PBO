package data;

public abstract class User {
    private String nim;

    // Konstruktor dengan parameter NIM
    public User(String nim) {
        this.nim = nim;
    }

    // Konstruktor tanpa parameter
    public User() {
    }

    // Getter untuk NIM
    public String getNim() {
        return nim;
    }

    // Setter untuk NIM, jika diperlukan
    public void setNim(String nim) {
        this.nim = nim;
    }

    // Metode abstrak login yang harus diimplementasikan oleh subkelas
    public abstract void login();
}
