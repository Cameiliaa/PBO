package codeleb2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class codelab{

    private static final Map<String, Integer> mahasiswaCredentials;
    private static final Map<String, String> adminCredentials;

    static {
        mahasiswaCredentials = new HashMap<>();
        mahasiswaCredentials.put("202310370311168", 123);

        adminCredentials = new HashMap<>();
        adminCredentials.put("admin", "admin123");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("======Liblary Sistem=======");
            System.out.println("1. Login sebagai mahasiswa");
            System.out.println("2. Login sebagai admin");
            System.out.println("3. Keluar");
            System.out.print("Masukkan pilihan (1/2/3): ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    loginMahasiswa(scanner);
                    break;
                case 2:
                    loginAdmin(scanner);
                    break;
                case 3:
                    System.out.println("Terima kasih, keluar dari program.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (choice != 3);
    }

    private static void loginMahasiswa(Scanner scanner) {
        System.out.print("Masukkan NIM: ");
        String nim = scanner.next();

        if (mahasiswaCredentials.containsKey(nim) && nim.length() == 15) {
            System.out.println("Berhasil masuk sebagai mahasiswa!");
        } else {
            System.out.println("Tugas2.Mahasiswa tidak ditemukan atau NIM tidak valid.");
        }
    }

    private static void loginAdmin(Scanner scanner) {
        System.out.print("Masukkan username admin: ");
        String adminUsername = scanner.next();
        System.out.print("Masukkan password admin: ");
        String adminPassword = scanner.next();

        if (adminCredentials.containsKey(adminUsername) && adminCredentials.get(adminUsername).equals(adminPassword)) {
            System.out.println("Berhasil masuk sebagai admin!");
        } else {
            System.out.println("Username atau password admin tidak valid.");
        }
    }
}
