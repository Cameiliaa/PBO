package data;

import Util.iMenu;
import books.Book;
import books.HistoryBook;
import books.StoryBook;
import books.TextBook;
import com.main.LibrarySystem;

import java.util.ArrayList;
import java.util.Scanner; 

public class Admin extends User implements iMenu {
    private final Scanner scanner;
    private final ArrayList<Student> studentList; // Menggunakan final untuk field studentList
    private ArrayList<Book> bookList;

    public Admin() {
        super("admin");
        this.scanner = LibrarySystem.scanner;
        this.studentList = new ArrayList<>(); // Inisialisasi studentList
        this.bookList = LibrarySystem.bookList; // Mendapatkan referensi ke bookList dari LibrarySystem
    }

    public void login() { 
        System.out.print("Masukkan Username (admin): ");
        String username = scanner.next();
        System.out.print("Masukkan Password (admin): ");
        String password = scanner.next();
        if (super.login(username, password)) {
            System.out.println("Login berhasil sebagai Admin ");
            menu();
        } else {
            System.out.println("User Admin tidak ditemukan");
        }
    }

    @Override
    public void menu() {
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
    }

    private void inputBook() {
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
                bookList.add((Book) new StoryBook(idBuku, judul, stok, author));
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
    }

    private void displayStudents() {
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
    }

    private void addStudent() {
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
    }

    @Override
    public void displayBooks() {
        System.out.println("Daftar Buku Tersedia:");
        for (Book book : bookList) {
            if (book != null) {
                System.out.println("- " + book.getJudul() + " oleh " + book.getAuthor() + " (Stok: " + book.getStok() + ")");
            }
        }
    }

    private String generateId(String prefix) {
        int nextId = bookList.size() + 1;
        return prefix + String.format("%03d", nextId);
    }

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }
}
