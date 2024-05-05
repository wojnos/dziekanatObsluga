package pl.dziekanat;

import java.sql.*;

public class SQLiteSterowanie {

    public static Connection polaczZBaza(){

        String url = "jdbc:sqlite:bazaDziekanat.db";
        Connection conn = null;

        try{
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
          //  System.out.println("Polaczenie z baza: poprawne");
            conn.setAutoCommit(true);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Polaczenie z baza: niepoprawne: " + e.getMessage());
        }

        return conn;
    }



    public static void dodajStudentaSQL(Connection conn, String id_studenta, String imie, String nazwisko, boolean czyZaplacone, boolean czyLegitymacjaPodbita, int numer_grupy, int rok_studiow)
    {
        String sql = "INSERT INTO Student(id_studenta, imie, nazwisko, czyZaplacone, czyLegitymacjaPodbita, numer_grupy, rok_studiow) VALUES (?,?,?,?,?,?,?)";

        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id_studenta);
            pstmt.setString(2, imie);
            pstmt.setString(3, nazwisko);
            pstmt.setBoolean(4, czyZaplacone);
            pstmt.setBoolean(5, czyLegitymacjaPodbita);
            pstmt.setInt(6, numer_grupy);
            pstmt.setInt(7, rok_studiow);
            pstmt.executeUpdate();

            System.out.println("Poprawnie dodano studenta");  ;
        } catch (SQLException e) {
            System.out.println("Blad: " + e.getMessage());
        }

    }

    public static void podbijLegitymacjeSQL(Connection conn, String id_studenta) {
        String sql = "UPDATE Student SET czyLegitymacjaPodbita = 1 WHERE id_studenta = ?";

        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id_studenta);

            pstmt.executeUpdate();
            System.out.println("Poprawnie podbito legitymacje studenta");
        } catch (SQLException e) {
            System.out.println("Blad: " + e.getMessage());
        }
    }

    public static void zglosOplacenieSQL(Connection conn, String id_studenta) {
        String sql = "UPDATE Student SET czyZaplacone = 1 WHERE id_studenta = ?";

        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id_studenta);

            pstmt.executeUpdate();
            System.out.println("Poprawnie dokonano oplaty");
        } catch (SQLException e) {
            System.out.println("Blad: " + e.getMessage());
        }
    }

    public static void usunStudentaSQL(Connection conn, String id_studenta) {
        String sql = ("DELETE FROM Student WHERE id_studenta = ?");

        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id_studenta);

            pstmt.executeUpdate();
            System.out.println("Poprawnie usunieto studenta o id: " + id_studenta);
        } catch (SQLException e) {
            System.out.println("Blad: " + e.getMessage());
        }


    }








/*

    //zamykanie polaczenia z baza
    public static void zakonczPolaczenieZBaza() {
        try {
            c.close();
        } catch (Exception e) {
            System.out.println("Blad przy zamykaniu: " + e.getMessage());
        }
    }



    //wyswietlanie listy studentow: id, imie, nazwisko
    public void listaStudentow() {
        try {
            this.stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Student");

            while (rs.next()) {
                int id_studenta = rs.getInt("id_studenta");
                String imie = rs.getString("imie");
                String nazwisko = rs.getString("nazwisko");


                System.out.println(id_studenta + ". " + imie + " " + nazwisko);
            }
        } catch (Exception e) {
            System.out.println("Blad: " + e.getMessage());
        }
    }

    //wyswietlanie pelnych danych
    public void pelnaInformacjaStudentow() {
        try {
            this.stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Student");
            System.out.println("id. Imie Nazwisko    Nr Grupy    Rok Studiow     Czy zaplacone       Czy Legitymacja podbita");
            while (rs.next()) {
                int id_studenta = rs.getInt("id_studenta");
                String imie = rs.getString("imie");
                String nazwisko = rs.getString("nazwisko");
                int numer_grupy = rs.getInt("numer_grupy");
                int rok_studiow = rs.getInt("rok_studiow");
                Boolean czyZaplacone = rs.getBoolean("czyZaplacone");
                Boolean czyLegitymacjaPodbita = rs.getBoolean("czyLegitymacjaPodbita");


                System.out.println(id_studenta + ". " + imie + " " + nazwisko + "                " + numer_grupy + "              " + rok_studiow
                        + "      " + czyZaplacone + "                " + czyLegitymacjaPodbita);
            }
        } catch (Exception e) {
            System.out.println("Blad: " + e.getMessage());
        }
    }



    //dodawanie studenta bez GUI
    public void StudentDodaj() {
        try {
            this.stmt = c.createStatement();
            String sql = ("INSERT INTO Student (id_studenta, imie, nazwisko, numer_grupy, rok_studiow, czyZaplacone, czyLegitymacjaPodbita)" +
                    "        VALUES (null, 'test1', 'testowy1', 1, 1, true, true);");
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Blad przy dodawaniu Studenta" + e.getMessage());
        }
    }

    //Usuwanie studenta bez GUI
    public void StudentUsun() {
        try {
            this.stmt = c.createStatement();
            String sql = ("DELETE FROM Student WHERE id_studenta = 26");
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Blad przy dodawaniu Studenta" + e.getMessage());
        }
    }

    public void StudenBezOplaty() {
        try {
            this.stmt = c.createStatement();
            System.out.println("id. Imie Nazwisko    Studentow, ktorzy nie oplacili studiow.");

            ResultSet rs = stmt.executeQuery("SELECT id_studenta, imie, nazwisko FROM Student WHERE czyZaplacone = 'false'");

            while (rs.next()) {
                int id_studenta = rs.getInt("id_studenta");
                String imie = rs.getString("imie");
                String nazwisko = rs.getString("nazwisko");

                System.out.println(id_studenta + ". " + imie + " " + nazwisko);
            }
        } catch (Exception e) {
            System.out.println("Blad przy dodawaniu Studenta" + e.getMessage());
        }
    }

    public void NauczycielOdMatematyki() {
        try {
            this.stmt = c.createStatement();
            System.out.println(" ");

            ResultSet rs = stmt.executeQuery("SELECT stopien, imie, nazwisko FROM Prowadzacy WHERE id_prowadzacego IN (SELECT id_prowadzacego FROM Przydzial_zajec WHERE id_przedmiotu IN (SELECT id_przedmiotu FROM Przedmioty WHERE nazwa = 'matematyka'))");

            while (rs.next()) {
                String stopien = rs.getString("stopien");
                String imie = rs.getString("imie");
                String nazwisko = rs.getString("nazwisko");

                System.out.println(stopien + ". " + imie + " " + nazwisko);
            }
        } catch (Exception e) {
            System.out.println("Blad przy dodawaniu Studenta" + e.getMessage());
        }
    }

*/

}