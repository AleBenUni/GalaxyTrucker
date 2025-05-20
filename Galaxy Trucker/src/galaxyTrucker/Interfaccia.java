package galaxyTrucker;

import carte.Livello;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Interfaccia extends Application {

    // Dimensioni Finestra e UI
    private int lFinestra = 1050;
    private int aFinestra = 1250;
    private int gCelleNave = 70;
    private int gComponentiDisponibili = 70;
    private int nComponentiDisponibili = 5;

    // Dimensioni e Stile Plancia
    private double altezzaPreferitaPlanciaPane = 400; // Aumentata leggermente per più spazio
    private double rCerchioGiorno = 12; // Raggio dei cerchi che rappresentano i giorni
    private double laMazzoPlancia = 75; // Larghezza Mazzo
    private double aMazzoPlancia = 105; // Altezza Mazzo
    private double paddingPlanciaInterno = 30; // Aumentato per dare più respiro


    // Riferimenti Logica di Gioco
    private Nave nave;
    private Plancia plancia;

    // Riferimenti UI
    private Pane panePlanciaGrafica;
    // private Button[] arrayPulsantiRuota; // Se servono per interazioni complesse
    // private Rectangle[] arrayComponentiPlaceholder;


    @Override
    public void start(Stage primaryStage) {
        Gioco gioco = new Gioco(1, Livello.III);
        this.plancia = new Plancia(Livello.III);
        this.nave = gioco.getNave(0);

        if (this.nave == null || this.plancia == null) {
            mostraErroreEChiudi("Errore Inizializzazione", "Impossibile caricare i dati di gioco.");
            return;
        }

        primaryStage.setTitle("Galaxy Trucker");

        BorderPane finestraLayoutPrincipale = new BorderPane();
        finestraLayoutPrincipale.setStyle("-fx-background-color: #2A2A2A;");

        // --- ZONA SUPERIORE: PLANCIA ---
        panePlanciaGrafica = creaPanePlanciaGrafica(this.plancia, lFinestra - 30, altezzaPreferitaPlanciaPane); // Passa dimensioni disponibili
        BorderPane.setMargin(panePlanciaGrafica, new Insets(15, 15, 10, 15));
        finestraLayoutPrincipale.setTop(panePlanciaGrafica);

        // --- ZONA INTERMEDIA: COMPONENTI DISPONIBILI ---
        HBox areaComponentiHBox = new HBox(15);
        areaComponentiHBox.setPadding(new Insets(10));
        areaComponentiHBox.setAlignment(Pos.CENTER);

        Button[] arrayPulsantiRuota = new Button[nComponentiDisponibili];
        Rectangle[] arrayComponentiPlaceholder = new Rectangle[nComponentiDisponibili];

        for (int i = 0; i < nComponentiDisponibili; i++) {
            VBox areaSingoloComponenteVBox = new VBox(5);
            areaSingoloComponenteVBox.setAlignment(Pos.CENTER);

            arrayPulsantiRuota[i] = new Button("Ruota");
            arrayPulsantiRuota[i].setMinWidth(gComponentiDisponibili);
            final int indice = i;
            arrayPulsantiRuota[i].setOnAction(event -> {
                if (arrayComponentiPlaceholder[indice] != null) {
                    arrayComponentiPlaceholder[indice].setRotate((arrayComponentiPlaceholder[indice].getRotate() + 90) % 360);
                }
            });
            areaSingoloComponenteVBox.getChildren().add(arrayPulsantiRuota[i]);

            arrayComponentiPlaceholder[i] = new Rectangle(gComponentiDisponibili, gComponentiDisponibili);
            arrayComponentiPlaceholder[i].setSmooth(true);
            arrayComponentiPlaceholder[i].setFill(Color.LIGHTSKYBLUE.deriveColor(0, 1, 1, 0.85));
            arrayComponentiPlaceholder[i].setStroke(Color.STEELBLUE);
            arrayComponentiPlaceholder[i].setStrokeWidth(1.5);
            arrayComponentiPlaceholder[i].setArcWidth(15);
            arrayComponentiPlaceholder[i].setArcHeight(15);
            arrayComponentiPlaceholder[i].setOnMouseClicked(event -> {
                for (Rectangle rect : arrayComponentiPlaceholder) {
                    rect.setStroke(Color.STEELBLUE);
                    rect.setStrokeWidth(1.5);
                }
                arrayComponentiPlaceholder[indice].setStroke(Color.GOLD);
                arrayComponentiPlaceholder[indice].setStrokeWidth(2.5);
            });
            areaSingoloComponenteVBox.getChildren().add(arrayComponentiPlaceholder[i]);
            areaComponentiHBox.getChildren().add(areaSingoloComponenteVBox);
        }

        // --- ZONA INFERIORE: GRIGLIA NAVE ---
        GridPane grigliaNave = new GridPane();
        // ... (configurazione grigliaNave come prima) ...
        grigliaNave.setPadding(new Insets(10));
        grigliaNave.setAlignment(Pos.CENTER);
        grigliaNave.setStyle("-fx-background-color: #1C1C1C; -fx-border-color: #404040; -fx-border-width: 1; -fx-background-radius: 8; -fx-border-radius: 8;");
        grigliaNave.setHgap(3);
        grigliaNave.setVgap(3);

        int nRighe = nave.getNRighe();
        int nColonne = nave.getNColonne();

        for (int i = 0; i < nRighe; i++) {
            for (int j = 0; j < nColonne; j++) {
                Rectangle cellaGrafica = new Rectangle(gCelleNave, gCelleNave);
                cellaGrafica.setSmooth(true);
                cellaGrafica.setArcWidth(12);
                cellaGrafica.setArcHeight(12);
                Cella cellaLogica = nave.getCella(new Posizione(i, j));

                if (cellaLogica != null && cellaLogica.isUtilizzabile()) {
                    cellaGrafica.setFill(Color.LIGHTCYAN.deriveColor(0,1,1,0.75));
                    cellaGrafica.setStroke(Color.CADETBLUE.deriveColor(0,1,1,0.5));
                    cellaGrafica.setStrokeWidth(1);
                } else {
                    cellaGrafica.setFill(Color.valueOf("#2E2E2E"));
                    cellaGrafica.setStroke(Color.valueOf("#202020"));
                }
                final int r = i; final int c = j;
                cellaGrafica.setOnMouseClicked(event -> {
                    System.out.println("Click su cella nave ("+r+","+c+")");
                });
                grigliaNave.add(cellaGrafica, j, i);
            }
        }
        
        // --- ASSEMBLAGGIO CENTRALE ---
        VBox contenitoreCentraleVBox = new VBox(20);
        contenitoreCentraleVBox.setAlignment(Pos.TOP_CENTER);
        contenitoreCentraleVBox.getChildren().addAll(areaComponentiHBox, grigliaNave);
        finestraLayoutPrincipale.setCenter(contenitoreCentraleVBox);

        // --- SCENA E STAGE ---
        Scene scena = new Scene(finestraLayoutPrincipale, lFinestra, aFinestra, false, SceneAntialiasing.BALANCED);
        primaryStage.setScene(scena);
        primaryStage.setMinWidth(lFinestra * 0.85);
        primaryStage.setMinHeight(aFinestra * 0.85);
        primaryStage.show();
    }

    private Pane creaPanePlanciaGrafica(Plancia planciaLogica, double larghezzaDisponibilePerPane, double altezzaDisponibilePerPane) {
        Pane layoutPlancia = new Pane();
        layoutPlancia.setPrefSize(larghezzaDisponibilePerPane, altezzaDisponibilePerPane);
        layoutPlancia.setStyle(
            "-fx-background-color: #383838; " +
            "-fx-border-color: #585858; " +
            "-fx-border-width: 2; " +
            "-fx-background-radius: 12; " +
            "-fx-border-radius: 12;"
        );

        // Area di disegno effettiva all'interno del Pane, dopo aver applicato il padding interno
        double W_contenuto = larghezzaDisponibilePerPane - (2 * paddingPlanciaInterno);
        double H_contenuto = altezzaDisponibilePerPane - (2 * paddingPlanciaInterno);

        if (W_contenuto <= 0 || H_contenuto <= 0 || planciaLogica.getGiorni() <= 0) {
            Label infoLabel = new Label("Plancia non visualizzabile (spazio insuff. o 0 giorni)");
            infoLabel.setTextFill(Color.WHITE);
            infoLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            StackPane placeholder = new StackPane(infoLabel);
            placeholder.setPrefSize(larghezzaDisponibilePerPane, altezzaDisponibilePerPane); // Occupa tutto il pane
            layoutPlancia.getChildren().add(placeholder);
            return layoutPlancia;
        }

        // Centro dell'area di contenuto (dove verrà centrata l'ellisse)
        // Le coordinate sono relative all'angolo superiore sinistro del Pane layoutPlancia
        double centroX_contenuto = paddingPlanciaInterno + W_contenuto / 2;
        double centroY_contenuto = paddingPlanciaInterno + H_contenuto / 2;

        // Spazio disponibile per l'ellisse DEI CENTRI dei cerchi-giorno,
        // DOPO aver allocato spazio per i mazzi e per il raggio dei cerchi-giorno stessi.
        // W_spazio_ellisse è la larghezza tra i bordi interni dei mazzi verticali.
        // H_spazio_ellisse è l'altezza tra i bordi interni dei mazzi orizzontali.
        double W_spazio_ellisse = W_contenuto - (2 * laMazzoPlancia) - (2 * rCerchioGiorno); // Sottrae larghezza mazzi e diametro cerchio
        double H_spazio_ellisse = H_contenuto - (2 * aMazzoPlancia) - (2 * rCerchioGiorno); // Sottrae altezza mazzi e diametro cerchio
        
        // I semi-assi sono la metà di questo spazio disponibile
        double semiasseA = W_spazio_ellisse / 2;
        double semiasseB = H_spazio_ellisse / 2;

        // Controllo di sicurezza per evitare semi-assi negativi o troppo piccoli
        double minSemiAsse = rCerchioGiorno * 1.5; // Minimo per evitare sovrapposizioni estreme
        if (semiasseA < minSemiAsse) semiasseA = minSemiAsse;
        if (semiasseB < minSemiAsse) semiasseB = minSemiAsse;
        if (planciaLogica.getGiorni() > 15 && semiasseA < rCerchioGiorno * 2.5) semiasseA = rCerchioGiorno * 2.5; // Più spazio se molti giorni
        if (planciaLogica.getGiorni() > 15 && semiasseB < rCerchioGiorno * 2.5) semiasseB = rCerchioGiorno * 2.5;


        int numGiorni = planciaLogica.getGiorni();
        for (int i = 0; i < numGiorni; i++) {
            double angolo = (2 * Math.PI * i / numGiorni) - (Math.PI / 2); // Inizia dall'alto
            double x_centro_cerchio = centroX_contenuto + semiasseA * Math.cos(angolo);
            double y_centro_cerchio = centroY_contenuto + semiasseB * Math.sin(angolo);

            Circle cerchioGiornoShape = new Circle(rCerchioGiorno);
            cerchioGiornoShape.setSmooth(true);
            cerchioGiornoShape.setFill(Color.ORANGERED.deriveColor(0, 1.1, 0.9, 0.95));
            cerchioGiornoShape.setStroke(Color.DARKRED.darker());
            cerchioGiornoShape.setStrokeWidth(1.5);
            
            Text numeroGiornoText = new Text(String.valueOf(i + 1));
            numeroGiornoText.setFont(Font.font("Arial", FontWeight.BOLD, rCerchioGiorno * 0.85));
            numeroGiornoText.setFill(Color.WHITE);
            
            StackPane cerchioConTesto = new StackPane(cerchioGiornoShape, numeroGiornoText);
            cerchioConTesto.setLayoutX(x_centro_cerchio - rCerchioGiorno); 
            cerchioConTesto.setLayoutY(y_centro_cerchio - rCerchioGiorno);
            layoutPlancia.getChildren().add(cerchioConTesto);
        }

        // Posizionamento dei Mazzi
        Color coloreMazzoFill = Color.SADDLEBROWN.deriveColor(0, 0.7, 0.6, 0.9);
        Color coloreMazzoStroke = Color.BLACK.deriveColor(0,1,1,0.5);

        // Mazzo Superiore: al bordo superiore del contenuto, centrato orizzontalmente
        creaMazzoGrafico(layoutPlancia, "Mazzo N", 
                         centroX_contenuto - laMazzoPlancia / 2, paddingPlanciaInterno, 
                         coloreMazzoFill, coloreMazzoStroke);
        // Mazzo Inferiore: al bordo inferiore del contenuto, centrato orizzontalmente
        creaMazzoGrafico(layoutPlancia, "Mazzo S", 
                         centroX_contenuto - laMazzoPlancia / 2, paddingPlanciaInterno + H_contenuto - aMazzoPlancia, 
                         coloreMazzoFill, coloreMazzoStroke);
        // Mazzo Sinistro: al bordo sinistro del contenuto, centrato verticalmente
        creaMazzoGrafico(layoutPlancia, "Mazzo O", 
                         paddingPlanciaInterno, centroY_contenuto - aMazzoPlancia / 2, 
                         coloreMazzoFill, coloreMazzoStroke);
        // Mazzo Destro: al bordo destro del contenuto, centrato verticalmente
        creaMazzoGrafico(layoutPlancia, "Mazzo E", 
                         paddingPlanciaInterno + W_contenuto - laMazzoPlancia, centroY_contenuto - aMazzoPlancia / 2, 
                         coloreMazzoFill, coloreMazzoStroke);

        return layoutPlancia;
    }

    private void creaMazzoGrafico(Pane contenitore, String etichetta, double x, double y, Color fill, Color stroke) {
        Rectangle mazzoRect = new Rectangle(laMazzoPlancia, aMazzoPlancia);
        mazzoRect.setSmooth(true);
        mazzoRect.setFill(fill);
        mazzoRect.setStroke(stroke);
        mazzoRect.setStrokeWidth(1.5);
        mazzoRect.setArcWidth(12);
        mazzoRect.setArcHeight(12);

        Label labelMazzo = new Label(etichetta);
        labelMazzo.setTextFill(Color.WHITE);
        labelMazzo.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        StackPane mazzoPane = new StackPane(mazzoRect, labelMazzo);
        mazzoPane.setLayoutX(x);
        mazzoPane.setLayoutY(y);
        contenitore.getChildren().add(mazzoPane);
    }
    
    private void mostraErroreEChiudi(String titolo, String messaggio) {
        System.err.println(titolo + ": " + messaggio);
        // In una GUI reale, useresti javafx.scene.control.Alert e Platform.exit()
    }

    public static void main(String[] args) {
        launch(args);
    }
}