package pl.dziekanat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerOknoWyswietlania implements Initializable {
    Connection c = null;
    Statement stmt = null;

    @FXML
    private TableView<ModelTabeli> tabelaListyStudentow;
    @FXML
    private TableColumn<ModelTabeli, Integer> kolumnaId;
    @FXML
    private TableColumn<ModelTabeli, String> kolumnaImie;
    @FXML
    private TableColumn<ModelTabeli, String> kolumnaNazwisko;

    @FXML
    private TableColumn<ModelTabeli, Boolean> kolumnaCzyOplacone;

    @FXML
    private TableColumn<ModelTabeli, Boolean> kolumnaCzyLegitymacja;

    @FXML
    private TableColumn<ModelTabeli, Integer> kolumnaNrGrupy;

    @FXML
    private TableColumn<ModelTabeli, Integer> kolumnaRokStudiow;


    ObservableList<ModelTabeli> listaDoTabeli = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:bazaDziekanat.db");
            System.out.println("Polaczenie z baza: poprawne");
        } catch (Exception e) {
            System.out.println("Polaczenie z baza: niepoprawne: " + e.getMessage());
        }
        try {
            this.stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Student");

            while(rs.next()) {
                listaDoTabeli.add(new ModelTabeli(rs.getInt("id_studenta"), rs.getString("imie"), rs.getString("nazwisko"),
                        rs.getBoolean("czyZaplacone"), rs.getBoolean("czyLegitymacjaPodbita"), rs.getInt("numer_grupy"),
                        rs.getInt("rok_studiow")));

            }
        } catch (Exception e) {
            System.out.println("Blad: " + e.getMessage());
        }

        kolumnaId.setCellValueFactory(new PropertyValueFactory<>("id_studenta"));
        kolumnaImie.setCellValueFactory(new PropertyValueFactory<>("imie"));
        kolumnaNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        kolumnaCzyOplacone.setCellValueFactory(new PropertyValueFactory<>("czyZaplacone"));
        kolumnaCzyLegitymacja.setCellValueFactory(new PropertyValueFactory<>("czyLegitymacjaPodbita"));
        kolumnaNrGrupy.setCellValueFactory(new PropertyValueFactory<>("numer_grupy"));
        kolumnaRokStudiow.setCellValueFactory(new PropertyValueFactory<>("rok_studiow"));
        tabelaListyStudentow.setItems(listaDoTabeli);
    }

}
