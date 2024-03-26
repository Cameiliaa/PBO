

public class BangunRuang {
    private String name;

    BangunRuang(String namee){
        this.name = namee;
    }

    public void inputNilai(){
        System.out.println("Maukan Nilai ");
    }

    public void luasPermukaan(){
        System.out.println("\nMenghitung Luas permukaan bangun" + name);
    }

    public void volume(){
        System.out.println("Menghitung Volume bangun" + name);
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

}
