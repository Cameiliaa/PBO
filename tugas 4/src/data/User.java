package data;

import books.Book;

import java.util.ArrayList;

public abstract class User {
    private String nim;
    private ArrayList<Book> bookList;

    public User(String nim) {
        this.nim = nim;
    }

    public User() {

    }

    public String getNim() {
        return nim;
    }

    public abstract void displayBooks();

    public abstract void login();

    protected boolean login(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }
}
