package pl.dziekanat;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    Stage stage;

    public Stage getStage(){
        return stage;
    }

    void setStage(Stage stage){
        this.stage = stage;
    }

    Connection c = null;
    Statement stmt = null;
    private Connection conn;


    @FXML
    Button okno1;

    @FXML
    Button okno2;

    @FXML
    private TextField usunID;

    @FXML
    private ComboBox<String> podbijBOX;

    @FXML
    private ComboBox<String> oplacBOX;

    ObservableList<String> listaNiePodbitychLegitymacji = FXCollections.observableArrayList();

    ObservableList<String> listaNieOplaconych = FXCollections.observableArrayList();



    @Override
    public void initialize(URL location, ResourceBundle resources){

    }


    //wypelnianie lista ID studentow, ktorzy nie podbili legitymacji
    public void wypelnijComboBoXLegitymacji() {
        listaNiePodbitychLegitymacji.clear();   // czysci liste przed ponownym kliknieciem

        try
        {
            conn = SQLiteSterowanie.polaczZBaza();
            String sql = "SELECT id_studenta FROM Student WHERE czyLegitymacjaPodbita = 0";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                String id_stud = rs.getString("id_studenta");
                listaNiePodbitychLegitymacji.add(id_stud);
            }
            podbijBOX.setItems(listaNiePodbitychLegitymacji);
        } catch (SQLException e){
            System.out.println("Blad: " + e.getMessage());
        }
    }

    //wypelnia liste id studentow, ktorzy nie oplacili
    public void wypelnijComboBoXPlatnosci() {
        listaNieOplaconych.clear();             //powinno wyczyscic liste przed ponownym kliknieciem

        try{
            conn = SQLiteSterowanie.polaczZBaza();
            String sql = "SELECT id_studenta FROM Student WHERE czyZaplacone = 0";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                String id_stud = rs.getString("id_studenta");
                listaNieOplaconych.add(id_stud);
            }
            oplacBOX.setItems(listaNieOplaconych);
        } catch (SQLException e){
            System.out.println("Blad: " + e.getMessage());
        }
    }


    //po kliknieciu podbij legitymacje, pobiera zaznaczona wartosc z comboboxa i odznacza podbicie
    public void podbijLegitymacje () {
        conn = SQLiteSterowanie.polaczZBaza();
        String id_studenta = podbijBOX.getValue();
        SQLiteSterowanie.podbijLegitymacjeSQL(conn, id_studenta);
    }

    //po klikniciu oplacenia, zmienia status platnosic
    public void zglosOplacenie () {
        conn = SQLiteSterowanie.polaczZBaza();
        String id_studenta = oplacBOX.getValue();
        SQLiteSterowanie.zglosOplacenieSQL(conn, id_studenta);
    }

    public void usunStudenta() {
        conn = SQLiteSterowanie.polaczZBaza();
        String id_studenta = usunID.getText();
        SQLiteSterowanie.usunStudentaSQL(conn, id_studenta);
    }


    public static void zamknijWszystko() {    //zamykanie okna glownego i potomnych
        Platform.exit();
        System.exit( 0);
    }


    public void otworzOknoStudenci(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Studenci.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage = new Stage();
        stage.setTitle("Okno studenci");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    //okno do wyswietlania tabeli

    public void otworzOknoWyswietlania() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/OknoWyswietlania.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage = new Stage();
        stage.setTitle("Okno wyswietlania tebeli");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    //wyswietla informacje zaleznie od potrzeby

    public void otworzOknoUniwestalnegoWyswietlania() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/UniwersalneOknoWyswietlania.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage = new Stage();
        stage.setTitle("Okno do wyswietlania informacji");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    public void otworzOknoZaleglosci() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/OknoZaleglosci.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage = new Stage();
        stage.setTitle("Okno wyswietlania tebeli");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    public void otworzOknoLegitymacji() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/OknoLegitymacji.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage = new Stage();
        stage.setTitle("Okno wyswietlania tebeli");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}