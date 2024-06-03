package com.main;

import books.Book;
import books.HistoryBook;
import books.StoryBook;
import books.TextBook;
import data.Student;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class LibraryApp extends Application {

    private ArrayList<Student> studentList = new ArrayList<>();
    private ArrayList<Book> bookList = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sistem Perpustakaan");

        // Main login screen
        GridPane mainGrid = createMainLoginScreen(primaryStage);
        Scene mainScene = new Scene(mainGrid, 400, 300);

        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private GridPane createMainLoginScreen(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label titleLabel = new Label("Library System Login");
        GridPane.setConstraints(titleLabel, 0, 0);

        Button studentLoginButton = new Button("Login sebagai Mahasiswa");
        GridPane.setConstraints(studentLoginButton, 0, 1);
        studentLoginButton.setOnAction(e -> primaryStage.setScene(new Scene(createStudentLoginScreen(primaryStage), 400, 200)));

        Button adminLoginButton = new Button("Login sebagai Admin");
        GridPane.setConstraints(adminLoginButton, 0, 2);
        adminLoginButton.setOnAction(e -> primaryStage.setScene(new Scene(createAdminLoginScreen(primaryStage), 400, 200)));

        grid.getChildren().addAll(titleLabel, studentLoginButton, adminLoginButton);

        return grid;
    }

    private GridPane createStudentLoginScreen(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label nimLabel = new Label("NIM:");
        GridPane.setConstraints(nimLabel, 0, 0);
        TextField nimField = new TextField();
        nimField.setPromptText("Masukkan NIM");
        GridPane.setConstraints(nimField, 1, 0);

        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 1, 1);
        loginButton.setOnAction(e -> {
            String nim = nimField.getText();
            if (nim.length() != 15) {
                showAlert("NIM Tidak Valid", "NIM harus terdiri dari 15 angka.");
            } else {
                boolean isRegistered = false;
                for (Student student : studentList) {
                    if (student.getNim().equals(nim)) {
                        isRegistered = true;
                        break;
                    }
                }
                if (isRegistered) {
                    showAlert("Login Berhasil", "Selamat datang Mahasiswa!");
                    primaryStage.setScene(new Scene(createStudentDashboard(primaryStage), 400, 300));
                } else {
                    showAlert("Login Gagal", "Mahasiswa dengan NIM tersebut tidak terdaftar.");
                }
            }
        });

        Button backButton = new Button("Kembali");
        GridPane.setConstraints(backButton, 1, 2);
        backButton.setOnAction(e -> primaryStage.setScene(new Scene(createMainLoginScreen(primaryStage), 400, 300)));

        grid.getChildren().addAll(nimLabel, nimField, loginButton, backButton);
        return grid;
    }

    private VBox createStudentDashboard(Stage primaryStage) {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Label studentLabel = new Label("Dashboard Mahasiswa");

        Button borrowBookButton = new Button("Pinjam Buku");
        borrowBookButton.setOnAction(e -> primaryStage.setScene(new Scene(createBorrowBookScreen(primaryStage), 400, 300)));

        Button returnBookButton = new Button("Kembalikan Buku");
        returnBookButton.setOnAction(e -> primaryStage.setScene(new Scene(createReturnBookScreen(primaryStage), 400, 300)));

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> primaryStage.setScene(new Scene(createMainLoginScreen(primaryStage), 400, 300)));

        vbox.getChildren().addAll(studentLabel, borrowBookButton, returnBookButton, logoutButton);
        return vbox;
    }

    private GridPane createBorrowBookScreen(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Mengisi stok buku
        try {
            bookList.add(new Book("999", "tere liye", 9, "naruto"));
            bookList.add(new Book("889", "chatgpt ajah", 30, "sasuke"));
            bookList.add(new Book("765", "codingan error", 8, "Hinata"));
        } catch (Exception e) {
            showAlert("Error", "Gagal menambahkan buku: " + e.getMessage());
        }

        Label booksLabel = new Label("Pilih Buku untuk Dipinjam:");
        GridPane.setConstraints(booksLabel, 0, 0);

        ListView<String> booksListView = new ListView<>();
        for (Book book : bookList) {
            booksListView.getItems().add(book.getJudul() + " oleh " + book.getAuthor() + " (Stok: " + book.getStok() + ")");
        }
        GridPane.setConstraints(booksListView, 0, 1);

        Button borrowButton = new Button("Pinjam");
        GridPane.setConstraints(borrowButton, 0, 2);
        borrowButton.setOnAction(e -> {
            String selectedBook = booksListView.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                try {
                    String[] bookInfo = selectedBook.split(" oleh ");
                    String bookTitle = bookInfo[0];
                    String bookAuthor = bookInfo[1].split(" \\(")[0];
                    for (Book book : bookList) {
                        if (book.getJudul().equals(bookTitle) && book.getAuthor().equals(bookAuthor)) {
                            if (book.getStok() > 0) {
                                book.setStok(book.getStok() - 1);
                                showAlert("Sukses", "Buku \"" + selectedBook + "\" berhasil dipinjam.");
                            } else {
                                showAlert("Gagal", "Stok buku \"" + selectedBook + "\" habis.");
                            }
                            break;
                        }
                    }
                    primaryStage.setScene(new Scene(createStudentDashboard(primaryStage), 400, 300));
                } catch (Exception ex) {
                    showAlert("Error", "Gagal meminjam buku: " + ex.getMessage());
                }
            } else {
                showAlert("Gagal", "Pilih buku terlebih dahulu.");
            }
        });

        Button backButton = new Button("Kembali");
        GridPane.setConstraints(backButton, 1, 2);
        backButton.setOnAction(e -> primaryStage.setScene(new Scene(createStudentDashboard(primaryStage), 400, 300)));

        grid.getChildren().addAll(booksLabel, booksListView, borrowButton, backButton);
        return grid;
    }

    private GridPane createReturnBookScreen(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        try {
            // Mengecek apakah ada buku yang dipinjam
            boolean anyBorrowedBook = false;
            for (Book book : bookList) {
                if (book.getStok() < 10) {
                    anyBorrowedBook = true;
                    break;
                }
            }

            if (!anyBorrowedBook) {
                Label noBorrowedBookLabel = new Label("Anda tidak meminjam buku.");
                GridPane.setConstraints(noBorrowedBookLabel, 0, 0);
                grid.getChildren().add(noBorrowedBookLabel);
            } else {
                Label booksLabel = new Label("Pilih Buku untuk Dikembalikan:");
                GridPane.setConstraints(booksLabel, 0, 0);

                ListView<String> borrowedBooksListView = new ListView<>();
                for (Book book : bookList) {
                    if (book.getStok() < 10) { // Hanya menampilkan buku yang dipinjam
                        borrowedBooksListView.getItems().add(book.getJudul() + " oleh " + book.getAuthor());
                    }
                }
                GridPane.setConstraints(borrowedBooksListView, 0, 1);

                Button returnButton = new Button("Kembalikan");
                GridPane.setConstraints(returnButton, 0, 2);
                returnButton.setOnAction(e -> {
                    String selectedBook = borrowedBooksListView.getSelectionModel().getSelectedItem();
                    if (selectedBook != null) {
                        // Kembalikan buku
                        try {
                            for (Book book : bookList) {
                                if ((book.getJudul() + " oleh " + book.getAuthor()).equals(selectedBook)) {
                                    book.setStok(book.getStok() + 1); // Tambahkan stok buku yang dikembalikan
                                    break;
                                }
                            }
                            showAlert("Sukses", "Buku \"" + selectedBook + "\" berhasil dikembalikan.");
                            primaryStage.setScene(new Scene(createStudentDashboard(primaryStage), 400, 300));
                        } catch (Exception ex) {
                            showAlert("Error", "Gagal mengembalikan buku: " + ex.getMessage());
                        }
                    } else {
                        showAlert("Gagal", "Pilih buku terlebih dahulu.");
                    }
                });

                Button backButton = new Button("Kembali");
                GridPane.setConstraints(backButton, 1, 2);
                backButton.setOnAction(e -> primaryStage.setScene(new Scene(createStudentDashboard(primaryStage), 400, 300)));

                grid.getChildren().addAll(booksLabel, borrowedBooksListView, returnButton, backButton);
            }
        } catch (Exception e) {
            showAlert("Error", "Terjadi kesalahan: " + e.getMessage());
        }

        return grid;
    }


    private GridPane createAdminLoginScreen(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label usernameLabel = new Label("Username:");
        GridPane.setConstraints(usernameLabel, 0, 0);
        TextField usernameField = new TextField();
        usernameField.setPromptText("Masukkan Username");
        GridPane.setConstraints(usernameField, 1, 0);

        Label passwordLabel = new Label("Password:");
        GridPane.setConstraints(passwordLabel, 0, 1);
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Masukkan Password");
        GridPane.setConstraints(passwordField, 1, 1);

        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 1, 2);
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (username.equals("admin") && password.equals("admin")) {
                showAlert("Login Berhasil", "Selamat datang Admin!");
                primaryStage.setScene(new Scene(createAdminDashboard(primaryStage), 600, 400));
            } else {
                showAlert("Login Gagal", "Username atau password salah.");
            }
        });

        Button backButton = new Button("Kembali");
        GridPane.setConstraints(backButton, 1, 3);
        backButton.setOnAction(e -> primaryStage.setScene(new Scene(createMainLoginScreen(primaryStage), 400, 300)));

        grid.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton, backButton);
        return grid;
    }


    private VBox createAdminDashboard(Stage primaryStage) {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Label adminLabel = new Label("Dashboard Admin");

        Button addStudentButton = new Button("Tambah Mahasiswa");
        addStudentButton.setOnAction(e -> primaryStage.setScene(new Scene(createInputStudentScreen(primaryStage), 600, 400)));

        Button viewStudentsButton = new Button("Lihat Mahasiswa");
        viewStudentsButton.setOnAction(e -> primaryStage.setScene(new Scene(createDisplayStudentsScreen(primaryStage), 600, 400)));

        Button addBookButton = new Button("Tambah Buku");
        addBookButton.setOnAction(e -> primaryStage.setScene(new Scene(createInputBookScreen(primaryStage), 600, 400)));

        Button viewBooksButton = new Button("Lihat Buku");
        viewBooksButton.setOnAction(e -> primaryStage.setScene(new Scene(createDisplayBooksScreen(primaryStage), 600, 400)));

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> primaryStage.setScene(new Scene(createMainLoginScreen(primaryStage), 400, 300)));

        vbox.getChildren().addAll(adminLabel, addStudentButton, viewStudentsButton, addBookButton, viewBooksButton, logoutButton);
        return vbox;
    }

    private GridPane createInputStudentScreen(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label nameLabel = new Label("Nama:");
        GridPane.setConstraints(nameLabel, 0, 0);
        TextField nameField = new TextField();
        nameField.setPromptText("Masukkan Nama");
        GridPane.setConstraints(nameField, 1, 0);

        Label nimLabel = new Label("NIM:");
        GridPane.setConstraints(nimLabel, 0, 1);
        TextField nimField = new TextField();
        nimField.setPromptText("Masukkan NIM");
        GridPane.setConstraints(nimField, 1, 1);

        Label facultyLabel = new Label("Fakultas:");
        GridPane.setConstraints(facultyLabel, 0, 2);
        TextField facultyField = new TextField();
        facultyField.setPromptText("Masukkan Fakultas");
        GridPane.setConstraints(facultyField, 1, 2);

        Label studyProgramLabel = new Label("Program Studi:");
        GridPane.setConstraints(studyProgramLabel, 0, 3);
        TextField studyProgramField = new TextField();
        studyProgramField.setPromptText("Masukkan Program Studi");
        GridPane.setConstraints(studyProgramField, 1, 3);

        Button addButton = new Button("Tambah");
        GridPane.setConstraints(addButton, 1, 4);
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String nim = nimField.getText();
            String faculty = facultyField.getText();
            String studyProgram = studyProgramField.getText();
            studentList.add(new Student(nim, name, faculty, studyProgram));
            showAlert("Sukses", "Mahasiswa berhasil ditambahkan.");
            primaryStage.setScene(new Scene(createAdminDashboard(primaryStage), 600, 400));
        });

        grid.getChildren().addAll(nameLabel, nameField, nimLabel, nimField, facultyLabel, facultyField, studyProgramLabel, studyProgramField, addButton);
        return grid;
    }

    private GridPane createDisplayStudentsScreen(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label studentsLabel = new Label("Daftar Mahasiswa:");
        GridPane.setConstraints(studentsLabel, 0, 0);

        ListView<String> studentsListView = new ListView<>();
        for (Student student : studentList) {
            studentsListView.getItems().add(student.getName() + " - " + student.getNim());
        }
        GridPane.setConstraints(studentsListView, 0, 1);

        Button backButton = new Button("Kembali");
        GridPane.setConstraints(backButton, 0, 2);
        backButton.setOnAction(e -> primaryStage.setScene(new Scene(createAdminDashboard(primaryStage), 600, 400)));

        grid.getChildren().addAll(studentsLabel, studentsListView, backButton);
        return grid;
    }


    private GridPane createInputBookScreen(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label titleLabel = new Label("Judul:");
        GridPane.setConstraints(titleLabel, 0, 0);
        TextField titleField = new TextField();
        titleField.setPromptText("Masukkan Judul Buku");
        GridPane.setConstraints(titleField, 1, 0);

        Label authorLabel = new Label("Penulis:");
        GridPane.setConstraints(authorLabel, 0, 1);
        TextField authorField = new TextField();
        authorField.setPromptText("Masukkan Nama Penulis");
        GridPane.setConstraints(authorField, 1, 1);

        Label typeLabel = new Label("Jenis Buku:");
        GridPane.setConstraints(typeLabel, 0, 2);
        ComboBox<String> typeComboBox = new ComboBox<>();
        typeComboBox.getItems().addAll("HistoryBook", "StoryBook", "TextBook");
        GridPane.setConstraints(typeComboBox, 1, 2);

        Button addButton = new Button("Tambah");
        GridPane.setConstraints(addButton, 1, 3);
        addButton.setOnAction(e -> {
            String title = titleField.getText();
            String author = authorField.getText();
            String type = typeComboBox.getValue();

            if (type.equals("HistoryBook")) {
                bookList.add(new HistoryBook(title, author));
            } else if (type.equals("StoryBook")) {
                bookList.add(new StoryBook(title, author));
            } else if (type.equals("TextBook")) {
                bookList.add(new TextBook(title, author));
            }
            showAlert("Sukses", "Buku berhasil ditambahkan.");
            primaryStage.setScene(new Scene(createAdminDashboard(primaryStage), 600, 400));
        });

        grid.getChildren().addAll(titleLabel, titleField, authorLabel, authorField, typeLabel, typeComboBox, addButton);
        return grid;
    }

    private GridPane createDisplayBooksScreen(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label booksLabel = new Label("Daftar Buku:");
        GridPane.setConstraints(booksLabel, 0, 0);

        ListView<String> booksListView = new ListView<>();
        for (Book book : bookList) {
            booksListView.getItems().add(book.getJudul() + " oleh " + book.getAuthor());
        }
        GridPane.setConstraints(booksListView, 0, 1);

        Button backButton = new Button("Kembali");
        GridPane.setConstraints(backButton, 0, 2);
        backButton.setOnAction(e -> primaryStage.setScene(new Scene(createAdminDashboard(primaryStage), 600, 400)));

        grid.getChildren().addAll(booksLabel, booksListView, backButton);
        return grid;
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}