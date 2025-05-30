package galaxyTrucker;

import carte.Livello;
import componenti.Componente;
import componenti.Mucchio;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Point2D; // Per gestire coordinate
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
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Interfaccia extends Application {

    // Dimensioni Finestra e UI
    private int lFinestra = 1300;
    private int aFinestra = 750;
    private int gCelleNave = 70;
    private int gComponentiDisponibili = 70;

    // Dimensioni e Stile Plancia
    private double altezzaPreferitaPlanciaPane = 700;
    private double rCerchioGiorno = 12;
    // Queste ora rappresentano le dimensioni LOGICHE di un mazzo "in piedi"
    // Per la visualizzazione orizzontale, le useremo scambiate.
    private double laMazzoLogica = 75; // Larghezza logica (se verticale)
    private double aMazzoLogica = 105; // Altezza logica (se verticale)
    private double paddingPlanciaInterno = 30;


    // Riferimenti UI
    private Pane panePlanciaGrafica;
    
    private Gioco gioco;
    private Nave nave;
    private Plancia plancia;
    private int giocatoreCorrente=0;
    
    private HBox areaComponentiHBox;
    private GridPane grigliaNave;
    
    private Componente componenteLogico=null;
    private Mucchio mano;
    private Integer indiceCorrente=null;


    @Override
    public void start(Stage primaryStage) {
    	int nGiocatori=1;
    	Livello livello=Livello.I;
    	/*Scanner scanner=new Scanner(System.in);
		do {
			//scanner.nextLine();
			System.out.println("Inserire numero giocatori");
			nGiocatori = scanner.nextInt();
		}while(nGiocatori<=1||nGiocatori>4);
		
		do {
			scanner.nextLine();
			System.out.println("Inserire Livello");
			livello=Livello.toLivello(scanner.nextLine());
		}while(livello==null);*/
		
        this.gioco = new Gioco(nGiocatori, livello);
        this.plancia = gioco.getPlancia();
        this.nave = gioco.getNave(giocatoreCorrente);

        if (this.nave == null || this.plancia == null) {
            mostraErroreEChiudi("Errore Inizializzazione", "Impossibile caricare i dati di gioco.");
            return;
        }

        primaryStage.setTitle("Galaxy Trucker");

        BorderPane finestraLayoutPrincipale = new BorderPane();
        finestraLayoutPrincipale.setStyle("-fx-background-color: #2A2A2A;");

        // --- ZONA SUPERIORE: PLANCIA ---
        panePlanciaGrafica = creaPanePlanciaGrafica(this.plancia, (lFinestra/5)*2 - 30, altezzaPreferitaPlanciaPane);
        BorderPane.setMargin(panePlanciaGrafica, new Insets(15, 15, 15, 15));
        BorderPane.setAlignment(panePlanciaGrafica, Pos.CENTER);
        finestraLayoutPrincipale.setLeft(panePlanciaGrafica);

        // --- ZONA INTERMEDIA: COMPONENTI DISPONIBILI ---
        areaComponentiHBox = new HBox(15);
        areaComponentiHBox.setPadding(new Insets(10));
        areaComponentiHBox.setAlignment(Pos.CENTER);
        mano=generaMano();
        popolaAreaComponentiHBox(this.areaComponentiHBox, gComponentiDisponibili, this.gioco);

        // --- ZONA INFERIORE: GRIGLIA NAVE ---
        grigliaNave=new GridPane();
        grigliaNave.setPadding(new Insets(10));
        grigliaNave.setAlignment(Pos.CENTER);
        grigliaNave.setStyle("-fx-background-color: #1C1C1C; -fx-border-color: #404040; -fx-border-width: 1; -fx-background-radius: 8; -fx-border-radius: 8;");
        grigliaNave.setHgap(3);
        grigliaNave.setVgap(3);
        popolaGrigliaNave(grigliaNave, nave, gCelleNave);
        
        
        
        // --- ASSEMBLAGGIO CENTRALE ---
        VBox contenitoreCentraleVBox = new VBox(20);
        HBox contenitorePulsanti=new HBox(15);
        contenitorePulsanti.setPadding(new Insets(10));
        contenitorePulsanti.setAlignment(Pos.CENTER);
        Button passaTurno=new Button("PassaTurno");
        
        passaTurno.setOnAction(event -> {
        	if(giocatoreCorrente+1==gioco.getNGiocatori())
        		giocatoreCorrente=0;
        	else
        		giocatoreCorrente+=1;
        	
        	while(!mano.isEmpty())
        		gioco.getMucchio().add(mano.pesca());
        	
        	nave=gioco.getNave(giocatoreCorrente);
        	mano=generaMano();
        	popolaAreaComponentiHBox(this.areaComponentiHBox, gComponentiDisponibili, gioco);
        	popolaGrigliaNave(this.grigliaNave, nave, gCelleNave);
        });
        contenitorePulsanti.getChildren().add(passaTurno);
        
        contenitoreCentraleVBox.setAlignment(Pos.TOP_CENTER);
        contenitoreCentraleVBox.getChildren().addAll(areaComponentiHBox, grigliaNave, contenitorePulsanti);
        finestraLayoutPrincipale.setCenter(contenitoreCentraleVBox);
        BorderPane.setAlignment(contenitoreCentraleVBox, Pos.CENTER);


        // --- SCENA E STAGE ---
        Scene scena = new Scene(finestraLayoutPrincipale, lFinestra, aFinestra, false, SceneAntialiasing.BALANCED);
        primaryStage.setScene(scena);
        primaryStage.setMinWidth(lFinestra * 0.85);
        primaryStage.setMinHeight(aFinestra * 0.85);
        primaryStage.show();
    }
    
    
    
    
    private Mucchio generaMano() {
    	Mucchio mano=new Mucchio();
    	Mucchio mucchio=gioco.getMucchio();
    	Random random = new Random();
        int nComponentiDisponibili=random.nextInt(4)+2;
        if(!mucchio.isEmpty()) {
        	if(mucchio.dimensione()-nComponentiDisponibili<0)
        		nComponentiDisponibili=mucchio.dimensione();
        }else
        	return null;
        for(int i=0;i<nComponentiDisponibili;i++)
        	mano.add(mucchio.pesca());
        return mano;
    }
    
    // --- COMPONENTI PESCATI ---
    private void popolaAreaComponentiHBox(HBox areaComponentiHBox, int gCoponentiDisponibili, Gioco gioco) {
    	areaComponentiHBox.getChildren().clear();
    	
    	int nComponentiDisponibili=mano.dimensione();
        
        HBox areaScarti=new HBox(15);
        areaScarti.setPadding(new Insets(10));
        areaScarti.setAlignment(Pos.CENTER);
        Rectangle[] arrayScarti = new Rectangle[2];
        
        
        Button[] arrayPulsantiRuota = new Button[nComponentiDisponibili];
        Rectangle[] arrayComponentiPlaceholder = new Rectangle[nComponentiDisponibili];

        for (int i=0;i<nComponentiDisponibili;i++) {
            VBox areaSingoloComponenteVBox = new VBox(5);
            areaSingoloComponenteVBox.setAlignment(Pos.CENTER);

            arrayPulsantiRuota[i] = new Button("Ruota");
            arrayPulsantiRuota[i].setMinWidth(gComponentiDisponibili);
            
            
            final int indice = i;
            arrayPulsantiRuota[i].setOnAction(event -> {
                if (arrayComponentiPlaceholder[indice] != null) {
                    arrayComponentiPlaceholder[indice].setRotate((arrayComponentiPlaceholder[indice].getRotate() + 90) % 360);
                    if(mano.getComponenteAt(indice)!=null)
                    	mano.getComponenteAt(indice).ruotaComponenteOrario(90);
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
                componenteLogico=mano.getComponenteAt(indice);
                indiceCorrente=indice;
            });
            areaSingoloComponenteVBox.getChildren().add(arrayComponentiPlaceholder[i]);
            areaComponentiHBox.getChildren().add(areaSingoloComponenteVBox);
            
        }
        
    }
    
    
    

    
    private void popolaGrigliaNave(GridPane grigliaNave, Nave nave, int gCelleNave) {
    	
    	grigliaNave.getChildren().clear();
    	
        int nRighe = nave.getNRighe();
        int nColonne = nave.getNColonne();

        for (int i = 0; i < nRighe; i++) {
            for (int j = 0; j < nColonne; j++) {
                Rectangle cellaGrafica = new Rectangle(gCelleNave, gCelleNave);
                cellaGrafica.setSmooth(true);
                cellaGrafica.setArcWidth(12);
                cellaGrafica.setArcHeight(12);
                Cella cellaLogica = nave.getCella(new Posizione(i, j));

                if(cellaLogica != null)
	                /*if(cellaLogica.isUtilizzabile() && cellaLogica.getComponente()==null) {
	                    cellaGrafica.setFill(Color.LIGHTCYAN.deriveColor(0, 1, 1, 0.75));
	                    cellaGrafica.setStroke(Color.CADETBLUE.deriveColor(0, 1, 1, 0.5));
	                    cellaGrafica.setStrokeWidth(1);
	                } else if(cellaLogica.isUtilizzabile() && cellaLogica.getComponente()!=null){
	                	cellaGrafica.setFill(Color.LIGHTCYAN.deriveColor(0, 1, 1, 0.75));
	                    cellaGrafica.setStroke(Color.CADETBLUE.deriveColor(0, 1, 1, 0.5));
	                }else if(!cellaLogica.isUtilizzabile() && cellaLogica.getComponente()!=null) {
	                	cellaGrafica.setFill(Color.RED);
	                    cellaGrafica.setStroke(Color.valueOf("#202020"));
	                }else {
		                	cellaGrafica.setFill(Color.valueOf("#2E2E2E"));
		                    cellaGrafica.setStroke(Color.valueOf("#202020"));
	                }*/
                	if (cellaLogica.isUtilizzabile()) {
                        if (cellaLogica.getComponente() == null) {
                            // 4. SPAZIO DISPONIBILE: Utilizzabile e senza componente
                            cellaGrafica.setFill(Color.LIGHTCYAN.deriveColor(0, 1.0, 0.9, 0.6)); // Un po' più trasparente/chiaro
                            cellaGrafica.setStroke(Color.CADETBLUE.deriveColor(0, 1.0, 0.8, 0.7));
                            cellaGrafica.setStrokeWidth(1.0);
                        } else {
                            // 3. UTILIZZABILE CON COMPONENTE: Lo vuoi VERDE
                            cellaGrafica.setFill(Color.LIMEGREEN.deriveColor(0, 1.0, 0.85, 0.75)); // Un verde brillante ma non troppo acceso
                            cellaGrafica.setStroke(Color.DARKGREEN.deriveColor(0, 1.0, 0.6, 0.8));
                            cellaGrafica.setStrokeWidth(1.5); // Magari un bordo più evidente per i componenti piazzati
                            // Qui potresti anche voler disegnare qualcosa che rappresenti il componente (testo, icona)
                            // sopra la cellaGrafica, magari usando uno StackPane per cellaPane.
                        }
                    } else { // Cella NON è utilizzabile
                        if (cellaLogica.getComponente() != null) {
                            // 2. NON UTILIZZABILE MA CON COMPONENTE: Lo vuoi ROSSO (indica un problema?)
                            cellaGrafica.setFill(Color.INDIANRED.deriveColor(0, 1.0, 0.9, 0.75)); // Un rosso non troppo aggressivo
                            cellaGrafica.setStroke(Color.FIREBRICK.deriveColor(0, 1.0, 0.7, 0.8));
                            cellaGrafica.setStrokeWidth(1.5);
                            // Anche qui, potresti voler visualizzare il componente problematico.
                        } else {
                            // 1. FUORI DALLA NAVE (NON UTILIZZABILE e SENZA COMPONENTE)
                            cellaGrafica.setFill(Color.valueOf("#2E2E2E")); // Grigio scuro, come avevi
                            cellaGrafica.setStroke(Color.valueOf("#202020")); // Bordo ancora più scuro
                            cellaGrafica.setStrokeWidth(0.5);
                        }
                    }
                final int r = i;
                final int c = j;
                cellaGrafica.setOnMouseClicked(event -> {
                	if(componenteLogico!=null) {
                		//if(nave.setCella(new Posizione(r,c), componenteLogico)) {
                		nave.setCella(new Posizione(r,c), componenteLogico);
                			mano.removeComponenteAt(indiceCorrente);
                			popolaAreaComponentiHBox(this.areaComponentiHBox, gComponentiDisponibili, gioco);
                			popolaGrigliaNave(grigliaNave, nave, gCelleNave);
                			nave.visualizzaNave();
                			nave.visualizzaUtilizzabileNave();
                			componenteLogico=null;
                			indiceCorrente=null;
                		//}
                		
                		
                	}
                		
                    System.out.println("Click su cella nave (" + r + "," + c + ")");
                });
                grigliaNave.add(cellaGrafica, j, i);
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private Pane creaPanePlanciaGrafica(Plancia planciaLogica, double larghezzaTotalePane, double altezzaTotalePane) {
        Pane layoutPlancia = new Pane();
        layoutPlancia.setPrefSize(larghezzaTotalePane, altezzaTotalePane);
        layoutPlancia.setStyle(
            "-fx-background-color: #383838; " +
            "-fx-border-color: #585858; " +
            "-fx-border-width: 2; " +
            "-fx-background-radius: 12; " +
            "-fx-border-radius: 12;"
        );

        double W_contenuto = larghezzaTotalePane - (2 * paddingPlanciaInterno);
        double H_contenuto = altezzaTotalePane - (2 * paddingPlanciaInterno);

        int numGiorni = planciaLogica.getGiorni();

        if (W_contenuto <= rCerchioGiorno * 6 || H_contenuto <= rCerchioGiorno * 6 || numGiorni <= 0) {
            Label infoLabel = new Label("Plancia non visualizzabile (spazio/giorni insuff.)");
            infoLabel.setTextFill(Color.WHITE);
            infoLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            StackPane placeholder = new StackPane(infoLabel);
            placeholder.prefWidthProperty().bind(layoutPlancia.widthProperty());
            placeholder.prefHeightProperty().bind(layoutPlancia.heightProperty());
            layoutPlancia.getChildren().add(placeholder);
            return layoutPlancia;
        }

        double centroX_contenuto = paddingPlanciaInterno + W_contenuto / 2;
        double centroY_contenuto = paddingPlanciaInterno + H_contenuto / 2;

        // Dimensioni VISUALI dei mazzi (ora orizzontali)
        double mazzoVisualWidth = this.aMazzoLogica; // Altezza logica diventa larghezza visuale
        double mazzoVisualHeight = this.laMazzoLogica; // Larghezza logica diventa altezza visuale

        // Spazio disponibile per l'ellisse DEI CENTRI dei cerchi, tra i mazzi
        // I mazzi laterali (Ovest, Est) ora hanno larghezza 'mazzoVisualWidth'
        // I mazzi superiore/inferiore (Nord, Sud) ora hanno altezza 'mazzoVisualHeight'
        double spazioPerEllisseOrizz = W_contenuto - (2 * mazzoVisualWidth);
        double spazioPerEllisseVert = H_contenuto - (2 * mazzoVisualHeight);

        double targetSemiA = (spazioPerEllisseOrizz / 2) - rCerchioGiorno;
        double targetSemiB = (spazioPerEllisseVert / 2) - rCerchioGiorno;

        if (targetSemiA < rCerchioGiorno) targetSemiA = rCerchioGiorno;
        if (targetSemiB < rCerchioGiorno) targetSemiB = rCerchioGiorno;
        
        double semiasseA = targetSemiA;
        double semiasseB = targetSemiB;
        
        double absoluteMinSemiAxis = rCerchioGiorno * 1.5; 
        if (numGiorni > 20) absoluteMinSemiAxis = rCerchioGiorno * 1.8;
        if (numGiorni > 30) absoluteMinSemiAxis = rCerchioGiorno * 2.2;

        if (semiasseA < absoluteMinSemiAxis) semiasseA = absoluteMinSemiAxis;
        if (semiasseB < absoluteMinSemiAxis) semiasseB = absoluteMinSemiAxis;

        if ((semiasseA + rCerchioGiorno) * 2 > spazioPerEllisseOrizz) {
            semiasseA = (spazioPerEllisseOrizz / 2) - rCerchioGiorno;
            if (semiasseA < rCerchioGiorno * 0.5) semiasseA = rCerchioGiorno * 0.5; 
        }
        if ((semiasseB + rCerchioGiorno) * 2 > spazioPerEllisseVert) {
            semiasseB = (spazioPerEllisseVert / 2) - rCerchioGiorno;
            if (semiasseB < rCerchioGiorno * 0.5) semiasseB = rCerchioGiorno * 0.5; 
        }
        
        double h = Math.pow(semiasseA - semiasseB, 2) / Math.pow(semiasseA + semiasseB, 2);
        double perimetroApprossimato = Math.PI * (semiasseA + semiasseB) * (1 + (3 * h) / (10 + Math.sqrt(4 - 3 * h)));
        
        if (Double.isNaN(perimetroApprossimato) || perimetroApprossimato <=0 || numGiorni == 0) {
            perimetroApprossimato = 2 * Math.PI * Math.sqrt((semiasseA*semiasseA + semiasseB*semiasseB)/2);
            if (numGiorni == 0 && perimetroApprossimato <=0) perimetroApprossimato = 1;
        }

        double distanzaTargetTraGiorni = (numGiorni > 0) ? (perimetroApprossimato / numGiorni) : 0;
        List<Point2D> puntiGiorni = new ArrayList<>();

        if (numGiorni > 0) {
            double angoloCorrente = -Math.PI / 2; 
            Point2D puntoPrecedente = new Point2D(
                    centroX_contenuto + semiasseA * Math.cos(angoloCorrente),
                    centroY_contenuto + semiasseB * Math.sin(angoloCorrente)
            );
            puntiGiorni.add(puntoPrecedente);
            double deltaAngoloMoltoPiccolo = 0.001; 

            for (int i = 1; i < numGiorni; i++) {
                double lunghezzaArcoAccumulata = 0;
                Point2D prossimoPuntoCalcolato = puntoPrecedente; 
                Point2D ultimoPuntoStep = puntoPrecedente;

                while(lunghezzaArcoAccumulata < distanzaTargetTraGiorni) {
                    angoloCorrente += deltaAngoloMoltoPiccolo;
                    Point2D puntoTestCorrente = new Point2D(
                        centroX_contenuto + semiasseA * Math.cos(angoloCorrente),
                        centroY_contenuto + semiasseB * Math.sin(angoloCorrente)
                    );
                    lunghezzaArcoAccumulata += ultimoPuntoStep.distance(puntoTestCorrente);
                    ultimoPuntoStep = puntoTestCorrente;

                    if (lunghezzaArcoAccumulata >= distanzaTargetTraGiorni || angoloCorrente > 3 * Math.PI) { 
                        prossimoPuntoCalcolato = ultimoPuntoStep;
                        break;
                    }
                }
                puntiGiorni.add(prossimoPuntoCalcolato);
                puntoPrecedente = prossimoPuntoCalcolato;
            }
        }

        for (int i = 0; i < puntiGiorni.size(); i++) {
            Point2D p = puntiGiorni.get(i);
            Circle cerchioGiornoShape = new Circle(rCerchioGiorno);
            cerchioGiornoShape.setSmooth(true);
            cerchioGiornoShape.setFill(Color.LIGHTGRAY.deriveColor(0, 1.1, 0.9, 0.95));
            cerchioGiornoShape.setStroke(Color.DARKRED.darker());
            cerchioGiornoShape.setStrokeWidth(1.5);

            Text numeroGiornoText = new Text(String.valueOf(i + 1));
            numeroGiornoText.setFont(Font.font("Arial", FontWeight.BOLD, rCerchioGiorno * 0.80));
            numeroGiornoText.setFill(Color.WHITE);

            StackPane cerchioConTesto = new StackPane(cerchioGiornoShape, numeroGiornoText);
            cerchioConTesto.setLayoutX(p.getX() - rCerchioGiorno);
            cerchioConTesto.setLayoutY(p.getY() - rCerchioGiorno);
            layoutPlancia.getChildren().add(cerchioConTesto);
        }
        
        Color coloreMazzoFill = Color.SADDLEBROWN.deriveColor(0, 0.7, 0.6, 0.9);
        Color coloreMazzoStroke = Color.BLACK.deriveColor(0, 1, 1, 0.5);

        // Posizionamento dei Mazzi Orizzontali
        // Mazzo Superiore
        creaMazzoGraficoOrizzontale(layoutPlancia, "Mazzo N", centroX_contenuto - mazzoVisualWidth / 2, paddingPlanciaInterno/2, coloreMazzoFill, coloreMazzoStroke);
        // Mazzo Inferiore
        creaMazzoGraficoOrizzontale(layoutPlancia, "Mazzo S", centroX_contenuto - mazzoVisualWidth / 2, paddingPlanciaInterno+paddingPlanciaInterno/2 + H_contenuto - mazzoVisualHeight, coloreMazzoFill, coloreMazzoStroke);
        // Mazzo Sinistro
        creaMazzoGraficoOrizzontale(layoutPlancia, "Mazzo O", paddingPlanciaInterno/2, centroY_contenuto - mazzoVisualHeight / 2, coloreMazzoFill, coloreMazzoStroke);
        // Mazzo Destro
        creaMazzoGraficoOrizzontale(layoutPlancia, "Mazzo E", paddingPlanciaInterno+paddingPlanciaInterno/2 + W_contenuto - mazzoVisualWidth, centroY_contenuto - mazzoVisualHeight / 2, coloreMazzoFill, coloreMazzoStroke);
        
        return layoutPlancia;
    }

    // Modificato per creare mazzi orizzontali
    private void creaMazzoGraficoOrizzontale(Pane contenitore, String etichetta, double x, double y, Color fill, Color stroke) {
        // Per i mazzi orizzontali, la larghezza visuale è l'altezza logica, e viceversa
        double mazzoVisualWidth = this.aMazzoLogica; 
        double mazzoVisualHeight = this.laMazzoLogica;

        Rectangle mazzoRect = new Rectangle(mazzoVisualWidth, mazzoVisualHeight);
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
        // Alert alert = new Alert(Alert.AlertType.ERROR);
        // alert.setTitle(titolo); alert.setHeaderText(null); alert.setContentText(messaggio);
        // alert.showAndWait();
        // javafx.application.Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
