package pl.dziekanat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerZaleglosci implements Initializable {
    Connection c = null;
    Statement stmt = null;

    @FXML
    private TableView<ModelTabeli_IDImieNazwisko> tabelaListyZaleglosci;
    @FXML
    private TableColumn<ModelTabeli_IDImieNazwisko, Integer> kolumnaId;
    @FXML
    private TableColumn<ModelTabeli_IDImieNazwisko, String> kolumnaImie;
    @FXML
    private TableColumn<ModelTabeli_IDImieNazwisko, String> kolumnaNazwisko;

    ObservableList<ModelTabeli_IDImieNazwisko> listaDoTabeliZaleglosci = FXCollections.observableArrayList();

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

            ResultSet rs = stmt.executeQuery("SELECT * FROM Student WHERE czyZaplacone = 0");

            while(rs.next()) {
                listaDoTabeliZaleglosci.add(new ModelTabeli_IDImieNazwisko(rs.getInt("id_studenta"), rs.getString("imie"), rs.getString("nazwisko")));
            }
        } catch (Exception e) {
            System.out.println("Blad: " + e.getMessage());
        }

        kolumnaId.setCellValueFactory(new PropertyValueFactory<>("id_studenta"));
        kolumnaImie.setCellValueFactory(new PropertyValueFactory<>("imie"));
        kolumnaNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        tabelaListyZaleglosci.setItems(listaDoTabeliZaleglosci);

    }

}