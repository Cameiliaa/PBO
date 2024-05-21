package data;

import books.Book;

import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User {
    private String name;
    private String faculty;
    private String studyProgram;
    private ArrayList<Book> borrowedBooks = new ArrayList<>();
    private Scanner scanner; // Menambahkan variabel scanner

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

    }

    public void login() {
        if (checkNim(getNim())) {
            System.out.println("Login berhasil sebagai Mahasiswa ");
            menu();
        } else {
            System.out.println("NIM tidak ditemukan");
        }
    }

    private boolean checkNim(String nim) {
        // Implementasi cek NIM dalam database atau daftar mahasiswa
        return true; // untuk saat ini, kita asumsikan selalu true
    }

    public void menu() {
        try {
            while (true) {
                System.out.println("Dashboard Mahasiswa");
                System.out.println("1. Pinjam Buku");
                System.out.println("2. Kembalikan Buku");
                System.out.println("3. Logout");
                System.out.print("Pilih antara (1-3): ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        borrowBook();
                        break;
                    case 2:
                        returnBook();
                        break;
                    case 3:
                        System.out.println("Logout berhasil.");
                        return;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            }
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan di menu Mahasiswa: " + e.getMessage());
        }
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

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}
