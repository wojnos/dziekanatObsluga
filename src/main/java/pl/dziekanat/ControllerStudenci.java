package pl.dziekanat;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ControllerStudenci implements Initializable {

    Connection c = null;
    Statement stmt = null;


    @FXML
    private TextField textId;

    @FXML
    private TextField textImie;

    @FXML
    private TextField textNazwisko;

    @FXML
    private TextField textCzyZaplacone;

    @FXML
    private TextField textCzyLegitymacja;

    @FXML
    private TextField textNumerGrupy;

    @FXML
    private TextField textRokStudiow;

    @FXML
    private CheckBox checkCzyZaplacone;

    @FXML
    private CheckBox checkCzyLegitymacjaPodbita;

    private Connection conn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void dodajStudenta() {
        conn = SQLiteSterowanie.polaczZBaza();
        String id_studenta = null;
        boolean czyZaplacone;
        boolean czyLegitymacjaPodbita;

        if(checkCzyZaplacone.isSelected()){
            czyZaplacone = true;
        } else {
            czyZaplacone = false;
        }

        if(checkCzyLegitymacjaPodbita.isSelected()){
            czyLegitymacjaPodbita = true;
        } else {
            czyLegitymacjaPodbita = false;
        }

        SQLiteSterowanie.dodajStudentaSQL(conn, id_studenta, textImie.getText(), textNazwisko.getText(), czyZaplacone, czyLegitymacjaPodbita, Integer.parseInt(textNumerGrupy.getText()), Integer.parseInt(textRokStudiow.getText()) );
    }



    public int policzStudentow() throws SQLException {
        int ileStudentow = 0;
        try {
            this.stmt = c.createStatement();
            System.out.println(" ");

            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS policzStudentow FROM Student");
            ileStudentow = rs.getInt("policzStudentow");
            System.out.println("Aktualna liczba studentow: " + ileStudentow + ".");
        } catch (Exception e) {
            System.out.println("Blad przy liczeniu studentow" + e.getMessage());
        }
        return ileStudentow;
    }
}
