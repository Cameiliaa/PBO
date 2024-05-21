package data;

import books.Book;
import com.main.LibrarySystem;

import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User {
    private String name;
    private String faculty;
    private String studyProgram;
    private ArrayList<Book> borrowedBooks = new ArrayList<>();
    private Scanner scanner; // Menambahkan variabel scanner

    public Student(String nim) {
        super();
    }

    public Student(String nim, String name, String faculty, String studyProgram) {
        super(nim);
        this.name = name;
        this.faculty = faculty;
        this.studyProgram = studyProgram;
    }

    public Student() {

    }


    public void login() {
        if (checkNim(getNim())) {
            System.out.println("Login berhasil sebagai Mahasiswa");
            menuStudent();
        } else {
            System.out.println("NIM Mahasiswa tidak valid atau tidak ditemukan");
        }
    }

    private boolean checkNim(String nim) {
        return nim.length() == 15;
    }

    private void menuStudent() {
        scanner = new Scanner(System.in); // Inisialisasi scanner di dalam method
        while (true) {
            System.out.println("Dashboard Mahasiswa");
            System.out.println("1. Tampilkan Buku");
            System.out.println("2. Pinjam Buku");
            System.out.println("3. Kembalikan Buku");
            System.out.println("4. Logout");
            System.out.print("Pilih antara (1-4): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayBooks();
                    break;
                case 2:
                    borrowBook();
                    break;
                case 3:
                    returnBook();
                    break;
                case 4:
                    logout();
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    @Override
    public void displayBooks() {
        ArrayList<Book> bookList = LibrarySystem.getBookList(); // Mengakses bookList dari LibrarySystem
        System.out.println("Daftar Buku Tersedia:");
        for (Book book : bookList) { // Menggunakan bookList yang diambil dari LibrarySystem
            if (book != null) {
                System.out.println("- " + book.getJudul() + " oleh " + book.getAuthor() + " (Stok: " + book.getStok() + ")");
            }
        }
    }

    private void borrowBook() {
        ArrayList<Book> bookList = LibrarySystem.getBookList(); // Mengakses bookList dari LibrarySystem
        System.out.print("Masukkan ID Buku yang ingin dipinjam: ");
        String bookId = scanner.next();
        Book selectedBook = findBookById(bookId, bookList); // Menggunakan bookList yang diambil dari LibrarySystem
        if (selectedBook != null && selectedBook.getStok() > 0) {
            selectedBook.setStok(selectedBook.getStok() - 1);
            borrowedBooks.add(selectedBook);
            System.out.println("Berhasil meminjam buku: " + selectedBook.getJudul());
        } else {
            System.out.println("Buku tidak tersedia atau ID buku tidak ditemukan");
        }
    }

    private void returnBook() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("Anda belum meminjam buku.");
            return;
        }
        System.out.println("Buku yang Anda pinjam:");
        for (int i = 0; i < borrowedBooks.size(); i++) {
            System.out.println((i + 1) + ". " + borrowedBooks.get(i).getJudul());
        }
        System.out.print("Pilih buku yang akan dikembalikan (nomor): ");
        int choice = scanner.nextInt();
        if (choice > 0 && choice <= borrowedBooks.size()) {
            Book returnedBook = borrowedBooks.remove(choice - 1);
            returnedBook.setStok(returnedBook.getStok() + 1);
            System.out.println("Buku " + returnedBook.getJudul() + " berhasil dikembalikan.");
        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }

    private Book findBookById(String id, ArrayList<Book> bookList) { // Mengubah parameter untuk menerima bookList
        for (Book book : bookList) { // Menggunakan bookList yang diberikan
            if (book != null && book.getIdBuku().equals(id)) {
                return book;
            }
        }
        return null;
    }

    private void logout() {
        System.out.println("Logout berhasil.");
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
