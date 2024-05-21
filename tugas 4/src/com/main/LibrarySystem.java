package com.main;

import books.Book;
import data.Admin;
import data.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class LibrarySystem {
    public static ArrayList<Book> bookList = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Mengisi stok buku
        bookList.add(new Book("999", "tere liye", 9, "naruto"));
        bookList.add(new Book("889", "chatgpt ajah", 30, "sasuke"));
        bookList.add(new Book("765", "codingan error", 8, "Hinata"));

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Library System Login");
            System.out.println("1. Login sebagai Mahasiswa");
            System.out.println("2. Login sebagai Admin");
            System.out.println("3. Keluar");
            System.out.print("Pilih antara (1-3): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Masukkan NIM : ");
                    String nimStudent = scanner.next();
                    if (nimStudent.length() != 15) {
                        System.out.println("NIM tidak valid! Harus 15 karakter.");
                        break;
                    }
                    Student student = new Student();
                    student.login();
                    break;
                case 2:
                    Admin admin = new Admin();
                    admin.setBookList(bookList);
                    admin.login();
                    break;
                case 3:
                    System.out.println("Terima kasih semoga puas dengan pelayanan kami");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    public static ArrayList<Book> getBookList() {

        return null;
    }
}
