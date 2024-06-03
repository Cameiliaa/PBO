package data;

import books.Book;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Student extends User implements util.IMenu {
    private String name;
    private String faculty;
    private String studyProgram;
    private ArrayList<Book> borrowedBooks = new ArrayList<>();

    public Student(String nim) {
        super(nim);
    }

    public Student(String nim, String name, String faculty, String studyProgram) {
        super(nim);
        this.name = name;
        this.faculty = faculty;
        this.studyProgram = studyProgram;
    }

    public Student() {
    }

    @Override
    public void displayBooks() {
        // Implementasi tampilan buku
    }

    @Override
    public void login() {
        try {
            if (checkNim(getNim())) {
                System.out.println("Login berhasil sebagai Mahasiswa");
                Platform.runLater(() -> menu());
            } else {
                System.out.println("NIM tidak ditemukan");
            }
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat login: " + e.getMessage());
        }
    }

    private boolean checkNim(String nim) {
        // Implementasi cek NIM dalam database atau daftar mahasiswa
        return true; // untuk saat ini, kita asumsikan selalu true
    }

    @Override
    public void menu() {
        // Menggunakan JavaFX untuk menampilkan grid informasi mahasiswa
        new Thread(() -> Application.launch(StudentMenuApp.class, getNim(), name, faculty, studyProgram)).start();
    }

    public void borrowBook() {
        // Implementasi peminjaman buku
    }

    public void returnBook() {
        // Implementasi pengembalian buku
    }

    public String getName() {
        return name;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getStudyProgram() {
        return studyProgram;
    }

    public static class StudentMenuApp extends Application {

        @Override
        public void start(Stage primaryStage) {
            Parameters params = getParameters();
            String nim = params.getUnnamed().get(0);
            String name = params.getUnnamed().get(1);
            String faculty = params.getUnnamed().get(2);
            String studyProgram = params.getUnnamed().get(3);

            // Create a GridPane
            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(10));
            gridPane.setHgap(10);
            gridPane.setVgap(10);

            // Add labels and values to the GridPane
            gridPane.add(new Label("Name:"), 0, 0);
            gridPane.add(new Label(name), 1, 0);
            gridPane.add(new Label("NIM:"), 0, 1);
            gridPane.add(new Label(nim), 1, 1);
            gridPane.add(new Label("Faculty:"), 0, 2);
            gridPane.add(new Label(faculty), 1, 2);
            gridPane.add(new Label("Study Program:"), 0, 3);
            gridPane.add(new Label(studyProgram), 1, 3);

            // Add logout button
            Button logoutButton = new Button("Logout");
            logoutButton.setOnAction(e -> {
                primaryStage.close();
                System.out.println("Logout berhasil.");
            });
            gridPane.add(logoutButton, 1, 4);

            // Set scene and show stage
            Scene scene = new Scene(gridPane, 300, 200);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Student Information");
            primaryStage.show();
        }
    }
}
