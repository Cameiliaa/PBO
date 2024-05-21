package data;

import Util.iMenu;
import books.Book;
import books.HistoryBook;
import books.StoryBook;
import books.TextBook;
import com.main.LibrarySystem;
import exception.custom.exception.IllegalAdminAccess;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User implements iMenu {
    private final Scanner scanner;
    private final ArrayList<Student> studentList;
    private ArrayList<Book> bookList;

    public Admin() {
        super("admin");
        this.scanner = LibrarySystem.scanner;
        this.studentList = new ArrayList<>();
        this.bookList = LibrarySystem.bookList;
    }

    public void login() {
        System.out.print("Masukkan Username (admin): ");
        String username = scanner.next();
        System.out.print("Masukkan Password (admin): ");
        String password = scanner.next();
        if (super.login(username, password)) {
            System.out.println("Login berhasil sebagai Admin");
            menu();
        } else {
            System.out.println("User Admin tidak ditemukan");
        }
    }

    @Override
    public void menu() {
        try {
            while (true) {
                System.out.println("Dashboard Admin");
                System.out.println("1. Tambah Mahasiswa");
                System.out.println("2. Tampilkan Mahasiswa");
                System.out.println("3. Input Buku");
                System.out.println("4. Tampilkan Daftar Buku");
                System.out.println("5. Logout");
                System.out.print("Pilih antara (1-5): ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        displayStudents();
                        break;
                    case 3:
                        inputBook();
                        break;
                    case 4:
                        displayBooks();
                        break;
                    case 5:
                        System.out.println("Logout berhasil.");
                        return;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            }
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan di menu Admin: " + e.getMessage());
        }
    }

    private void inputBook() {
        try {
            System.out.println("Memasukkan buku...");
            System.out.println("Pilih jenis buku:");
            System.out.println("1. History Book");
            System.out.println("2. Story Book");
            System.out.println("3. Text Book");
            System.out.print("Pilih jenis buku (1-3): ");
            int bookType = scanner.nextInt();
            scanner.nextLine(); // membersihkan newline

            String idBuku, judul, author;
            int stok;
            System.out.print("Masukkan judul buku: ");
            judul = scanner.nextLine();
            System.out.print("Masukkan author buku: ");
            author = scanner.nextLine();
            System.out.print("Masukkan stok buku: ");
            stok = scanner.nextInt();
            scanner.nextLine(); // membersihkan newline

            switch (bookType) {
                case 1:
                    idBuku = generateId("HB");
                    bookList.add(new HistoryBook(idBuku, judul, stok, author));
                    break;
                case 2:
                    idBuku = generateId("SB");
                    bookList.add(new StoryBook(idBuku, judul, stok, author));
                    break;
                case 3:
                    idBuku = generateId("TB");
                    bookList.add(new TextBook(idBuku, judul, stok, author));
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
                    return;
            }
            System.out.println("Buku berhasil ditambahkan.");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat memasukkan buku: " + e.getMessage());
        }
    }

    private void displayStudents() {
        try {
            System.out.println("Daftar Mahasiswa yang terdaftar:");
            for (Student student : studentList) {
                System.out.println("Nama: " + student.getName());
                System.out.println("NIM: " + student.getNim());
                System.out.println("Fakultas: " + student.getFaculty());
                System.out.println("Program Studi: " + student.getStudyProgram());
                if (!student.getBorrowedBooks().isEmpty()) {
                    System.out.println("  Meminjam Buku:");
                    for (Book book : student.getBorrowedBooks()) {
                        System.out.println("    - " + book.getJudul());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menampilkan mahasiswa: " + e.getMessage());
        }
    }

    private void addStudent() {
        try {
            System.out.println("Menambahkan mahasiswa...");
            System.out.print("Masukkan Nama: ");
            String name = scanner.next();
            System.out.print("Masukkan NIM: ");
            String nim = scanner.next();
            if (nim.length() != 15) {
                System.out.println("NIM tidak valid! Harus 15 karakter.");
                return;
            }
            System.out.print("Masukkan Fakultas: ");
            String faculty = scanner.next();
            System.out.print("Masukkan Program Studi: ");
            String studyProgram = scanner.next();
            studentList.add(new Student(nim, name, faculty, studyProgram));
            System.out.println("Mahasiswa dengan NIM " + nim + " berhasil ditambahkan.");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menambahkan mahasiswa: " + e.getMessage());
        }
    }

    @Override
    public void displayBooks() {
        try {
            System.out.println("Daftar Buku Tersedia:");
            for (Book book : bookList) {
                if (book != null) {
                    System.out.println("- " + book.getJudul() + " oleh " + book.getAuthor() + " (Stok: " + book.getStok() + ")");
                }
            }
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menampilkan buku: " + e.getMessage());
        }
    }

    private String generateId(String prefix) {
        int nextId = bookList.size() + 1;
        return prefix + String.format("%03d", nextId);
    }

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    public void isAdmin() throws IllegalAdminAccess {
        throw new IllegalAdminAccess("Invalid credentials");
    }
}
