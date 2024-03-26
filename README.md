

import java.util.ArrayList;
import java.util.Scanner;

class main {
    private ArrayList<Book> bookList;
    private ArrayList<User> userStudent;

    public main() {
        this.bookList = new ArrayList<>();
        this.userStudent = new ArrayList<>();
        // Inisialisasi data buku dan user (contoh)
        bookList.add(new Book("B001", "Harry Potter", "J.K. Rowling", 10));
        bookList.add(new Book("B002", "Lord of the Rings", "J.R.R. Tolkien", 15));
        bookList.add(new Book("B003", "To Kill a Mockingbird", "Harper Lee", 5));
        userStudent.add(new User("123456789", "John Doe"));
        userStudent.add(new User("987654321", "Jane Smith"));
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("===== Library System =====");
            System.out.println("1. Login as Student");
            System.out.println("2. Login as Admin");
            System.out.println("3. Exit");
            System.out.print("Choose option (1-3): ");
            int option = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (option) {
                case 1:
                    inputNim();
                    break;
                case 2:
                    menuAdmin();
                    break;
                case 3:
                    System.out.println("Thank you. Exiting program.");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    public void inputNim() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your NIM: ");
        String nim = scanner.nextLine();
        if (checkNim(nim)) {
            menuStudent();
        } else {
            System.out.println("NIM not found. Please try again.");
        }
    }

    public boolean checkNim(String nim) {
        for (User user : userStudent) {
            if (user.getNim().equals(nim)) {
                return true;
            }
        }
        return false;
    }

    public void menuAdmin() {
        // Implementasi dashboard admin
        System.out.println("===== Admin Dashboard =====");
        // Misalnya tampilkan opsi untuk tambah buku, lihat daftar buku, dll.
    }

    public void menuStudent() {
        // Implementasi dashboard student
        System.out.println("===== Student Dashboard =====");
        // Misalnya tampilkan daftar buku yang dapat dipinjam, buku yang sedang dipinjam, dll.
    }

    public static void main(String[] args) {
        main librarySystem = new main();
        librarySystem.menu();
    }
}

class Book {
    private String id;
    private String title;
    private String author;
    private int stock;

    public Book(String id, String title, String author, int stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.stock = stock;
    }

    // Getter and setter methods
}

class User {
    private String nim;
    private String name;

    public User(String nim, String name) {
        this.nim = nim;
        this.name = name;
    }

    public String getNim() {
        return nim;
    }

    // Getter and setter methods for name
}

public class Mahasiswa {
    // Deklarasi variabel
    String nama;
    String nim;
    String jurusan;

    // Static method
    static void tampilUniversitas(String universitas){
        System.out.println("Universitas: " + universitas);
    }

    // Method untu mechekt dan display panjang nim
    void setNIM(String nim) {
        if(nim.length() > 15){
            System.out.println("NIM yang Anda masukan lebih dari 15 Angka");
        } else if(nim.length() < 15){
            System.out.println("NIM yang Anda masukan kurang dari 15 Angka");
        } else {
            this.nim = nim;
        }
    }

    // Method untuk menampilkan data mahasiswa
    void tampilDataMahasiswe(){
        System.out.println("Nama: " + nama);
        System.out.println("NIM: " + nim);
        System.out.println("Jurusan: "+ jurusan);
    }
}



class Main {
    // Void method menu
    public static void menu(){

    }
    public static void main(String[] args){
    }
}
