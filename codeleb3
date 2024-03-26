import java.util.Scanner;

public class BangunRuang {
    private final String name;

    BangunRuang(String name) {
        this.name = name;
    }

    public void inputNilai() {
        System.out.println("Masukkan Nilai ");
    }

    public void luasPermukaan() {
        System.out.println("\nMenghitung Luas permukaan bangun" + name);
    }

    public void volume() {
        System.out.println("Menghitung Volume bangun" + name);
    }

}

class Tabung extends BangunRuang {
    Scanner scanner = new Scanner(System.in);
    private double tinggi;
    private double jari_jari;

    Tabung(String name) {
        super(name);
    }

    @Override
    public void inputNilai() {
        super.luasPermukaan();
        super.inputNilai();
        System.out.print("Masukkan tinggi: ");
        tinggi = scanner.nextDouble();
        System.out.print("Masukkan jari-jari: ");
        jari_jari = scanner.nextDouble();
    }

    @Override
    public void luasPermukaan() {
        double hasil = 2 * Math.PI * jari_jari * (jari_jari + tinggi);
        System.out.println("Hasil luas permukaan: " + hasil);
    }

    @Override
    public void volume() {
        double hasil = Math.PI * Math.pow(jari_jari, 2) * tinggi;
        super.volume();
        System.out.println("Hasil volume: " + hasil);
    }
}

class Kubus extends BangunRuang {
    Scanner scanner = new Scanner(System.in);
    private double sisi;

    public Kubus(String name) {
        super(name);
    }

    @Override
    public void inputNilai() {
        super.luasPermukaan();
        super.inputNilai();
        System.out.print("Masukkan sisi: ");
        sisi = scanner.nextDouble();
    }

    @Override
    public void luasPermukaan() {
        double hasil = 6 * Math.pow(sisi, 2);
        System.out.println("Hasil luas permukaan: " + hasil);
    }

    @Override
    public void volume() {
        double hasil = Math.pow(sisi, 3);
        super.volume();
        System.out.println("Hasil volume: " + hasil);
    }
}

class Balok extends BangunRuang {
    Scanner scanner = new Scanner(System.in);
    private double panjang;
    private double lebar;
    private double tinggi;

    public Balok(String name) {
        super(name);
    }

    @Override
    public void inputNilai() {
        super.luasPermukaan();
        super.inputNilai();
        System.out.print("Masukkan panjang: ");
        panjang = scanner.nextDouble();
        System.out.print("Masukkan lebar: ");
        lebar = scanner.nextDouble();
        System.out.print("Masukkan tinggi: ");
        tinggi = scanner.nextDouble();
    }

    @Override
    public void luasPermukaan() {
        double hasil = 2 * ((panjang * lebar) + (panjang * tinggi) + (lebar * tinggi));

        System.out.println("Hasil luas permukaan: " + hasil);
    }

    @Override
    public void volume() {
        double hasil = panjang * lebar * tinggi;
        super.volume();
        System.out.println("Hasil volume: " + hasil);
    }
}

class App {
    public static void main(String[] args) {
        Tabung tabung = new Tabung("Tabung");
        Kubus kubus = new Kubus("Kubus");
        Balok balok = new Balok("Balok");

        balok.inputNilai();
        balok.luasPermukaan();
        balok.volume();

        kubus.inputNilai();
        kubus.luasPermukaan();
        kubus.volume();

        tabung.inputNilai();
        tabung.luasPermukaan();
        tabung.volume();
    }
}
