package pl.dziekanat;

public class ModelTabeli_IDImieNazwisko {

    private int id_studenta;
    private String imie, nazwisko;

    public int getId_studenta() {
        return id_studenta;
    }

    public void setId_studenta(int id_studenta) {
        this.id_studenta = id_studenta;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public ModelTabeli_IDImieNazwisko(int id_studenta, String imie, String nazwisko) {
        this.id_studenta = id_studenta;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }
}
