package pl.dziekanat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class MainDziekanat extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/OknoGlowne.fxml"));
        primaryStage.setTitle("Program do obslugi dziekanatu");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        primaryStage.setHeight(500.0);   //poczatkowa wysokosc/szerokosc okna
        primaryStage.setWidth(800.0);
        primaryStage.setMinHeight(400);   //minimalna wysokosc/szerokosc okna
        primaryStage.setMinWidth(400);
        primaryStage.setOnCloseRequest(event -> {Controller.zamknijWszystko();});
    }



    public static void main(String[] args) throws SQLException {


        SQLiteSterowanie baza = new SQLiteSterowanie();
       // baza.listaStudentow();      //id imie nazwisko

        baza.polaczZBaza();
        // baza.StudentDodaj();   //dodwanie bez GUI
        // baza.StudentUsun();  //usuwanie bez GUI
        // baza.StudenBezOplaty();    //lista studentow, ktorzy nie zaplacili za studia

        //baza.pelnaInformacjaStudentow();        //id imie nazwisko nr_grupy rok_studiow czyZaplacone czyLegitymacjaPodbita
        //baza.NauczycielOdMatematyki();      //przydzial nauczycieli do przedmiotu matematyka

        launch(args);

       // baza.zakonczPolaczenieZBaza();
    }
}
