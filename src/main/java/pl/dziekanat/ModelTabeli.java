package pl.dziekanat;

public class ModelTabeli {

    private int id_studenta, numer_grupy, rok_studiow;
    private String imie, nazwisko;
    private boolean czyZaplacone, czyLegitymacjaPodbita;


    public ModelTabeli(int id_studenta, String imie, String nazwisko, boolean czyZaplacone, boolean czyLegitymacjaPodbita, int numer_grupy, int rok_studiow) {
        this.id_studenta = id_studenta;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.czyZaplacone = czyZaplacone;
        this.czyLegitymacjaPodbita = czyLegitymacjaPodbita;
        this.numer_grupy = numer_grupy;
        this.rok_studiow = rok_studiow;
    }


    public int getId_studenta() {
        return id_studenta;
    }

    public void setId_studenta(int id_studenta) {
        this.id_studenta = id_studenta;
    }

    public int getNumer_grupy() {
        return numer_grupy;
    }

    public void setNumer_grupy(int numer_grupy) {
        this.numer_grupy = numer_grupy;
    }

    public int getRok_studiow() {
        return rok_studiow;
    }

    public void setRok_studiow(int rok_studiow) {
        this.rok_studiow = rok_studiow;
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

    public boolean isCzyZaplacone() {
        return czyZaplacone;
    }

    public void setCzyZaplacone(boolean czyZaplacone) {
        this.czyZaplacone = czyZaplacone;
    }

    public boolean isCzyLegitymacjaPodbita() {
        return czyLegitymacjaPodbita;
    }

    public void setCzyLegitymacjaPodbita(boolean czyLegitymacjaPodbita) {
        this.czyLegitymacjaPodbita = czyLegitymacjaPodbita;
    }
}
