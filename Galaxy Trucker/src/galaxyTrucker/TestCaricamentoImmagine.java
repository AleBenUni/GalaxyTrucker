package galaxyTrucker;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.InputStream;

public class TestCaricamentoImmagine extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Test Caricamento Risorsa");

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        
        // MODIFICA QUESTA RIGA CON IL PERCORSO DI UN'IMMAGINE CHE SEI SICURO ESISTA
        String percorsoRisorsaTest = "/images/Batteria/97.jpg";

        Label titoloLabel = new Label("Test di Caricamento per:\n" + percorsoRisorsaTest);
        titoloLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        
        try {
            InputStream stream = getClass().getResourceAsStream(percorsoRisorsaTest);
            
            if (stream == null) {
                // FALLIMENTO: La risorsa non è stata trovata nel classpath
                root.setStyle("-fx-background-color: #A03030;"); // Sfondo rosso
                titoloLabel.setTextFill(Color.WHITE);
                Label esitoLabel = new Label("FALLIMENTO");
                esitoLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
                esitoLabel.setTextFill(Color.WHITE);
                Label erroreLabel = new Label("getResourceAsStream() ha restituito null.\nLa risorsa non è stata trovata nel classpath.\n\nControlla che:\n1. La cartella 'resources' sia un 'Source Folder' nel Build Path.\n2. Il percorso '" + percorsoRisorsaTest + "' sia scritto correttamente.\n3. Il file esista esattamente in 'resources" + percorsoRisorsaTest + "'.");
                erroreLabel.setTextFill(Color.WHITE);
                root.getChildren().addAll(titoloLabel, esitoLabel, erroreLabel);

            } else {
                // SUCCESSO: La risorsa è stata trovata
                root.setStyle("-fx-background-color: #30A030;"); // Sfondo verde
                titoloLabel.setTextFill(Color.WHITE);
                Label esitoLabel = new Label("SUCCESSO!");
                esitoLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
                esitoLabel.setTextFill(Color.WHITE);
                
                Image img = new Image(stream);
                ImageView imgView = new ImageView(img);
                imgView.setFitHeight(100);
                imgView.setPreserveRatio(true);
                
                root.getChildren().addAll(titoloLabel, esitoLabel, imgView);
                stream.close();
            }

        } catch (Exception e) {
            // Eccezione generica
            root.setStyle("-fx-background-color: #A03030;");
            titoloLabel.setTextFill(Color.WHITE);
            Label esitoLabel = new Label("ERRORE DURANTE IL TEST");
            esitoLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
            esitoLabel.setTextFill(Color.WHITE);
            Label erroreLabel = new Label("È stata lanciata un'eccezione:\n" + e.getMessage());
            erroreLabel.setTextFill(Color.WHITE);
            root.getChildren().addAll(titoloLabel, esitoLabel, erroreLabel);
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}