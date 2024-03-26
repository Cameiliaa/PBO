package codeleb1;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

class main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input data diri
        System.out.print("Masukkan nama: ");
        String nama = scanner.nextLine();

        System.out.print("Masukkan jenis kelamin (P/L): ");
        char jenisKelamin = scanner.next().charAt(0);

        System.out.print("Masukkan tanggal lahir (YYYY-MM-DD): ");
        String tanggalLahirStr = scanner.next();

        // Konversi string tanggal lahir ke LocalDate
        LocalDate tanggalLahir = LocalDate.parse(tanggalLahirStr);

        // Hitung umur
        LocalDate sekarang = LocalDate.now();
        Period usia = Period.between(tanggalLahir, sekarang);

        // Output data diri
        System.out.println("\nData Diri:");
        System.out.println("Nama: " + nama);
        System.out.println("Jenis Kelamin: " + (jenisKelamin == 'L' ? "Laki-laki" : "Perempuan"));
        System.out.println("Tanggal Lahir: " + tanggalLahir);
        System.out.println("Umur: " + usia.getYears() + " tahun " + usia.getMonths() + " bulan " + usia.getDays() + " hari");

        // Tutup scanner
        scanner.close();
    }
}


