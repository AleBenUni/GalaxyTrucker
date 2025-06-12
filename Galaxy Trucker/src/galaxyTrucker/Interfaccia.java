package galaxyTrucker;

import carte.Livello;
import carte.Mazzo;
import componenti.Componente;
import componenti.Mucchio;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Interfaccia extends Application {
    // Dimensioni Finestra e UI
    private int lFinestra = 1300;
    private int aFinestra = 750;
    private int gCelleNave = 70;
    private int gComponentiDisponibili = 70;

    // Dimensioni e Stile Plancia
    private double altezzaPreferitaPlanciaPane = 700;
    private double rCerchioGiorno = 12;
    private double laMazzoLogica = 75; // Larghezza logica (se verticale)
    private double aMazzoLogica = 105; // Altezza logica (se verticale)
    private double paddingPlanciaInterno = 30;


    // Riferimenti UI
    private Pane panePlanciaGrafica;
    
    private Gioco gioco;
    private Nave nave;
    private Plancia plancia;
    private int giocatoreCorrente=0;
    private boolean[] naveTerminata;
    
    private HBox areaComponentiHBox;
    private GridPane grigliaNave;
    
    private Componente componenteLogico=null;
    private Mucchio mano;
    private Integer indiceCorrente=null;
    private int startPos;
    
    private static Gioco giocoDaUsare;
    private static CountDownLatch latchFineCostruzione;

    public static void setParametriDiAvvio(Gioco gioco, CountDownLatch latch) {
        giocoDaUsare=gioco;
        latchFineCostruzione=latch;
    }

    @Override
    public void start(Stage primaryStage) {
    	int nGiocatori;
		
        this.gioco = giocoDaUsare;
        nGiocatori=gioco.getNGiocatori();
        this.plancia = gioco.getPlancia();
        this.nave = gioco.getNave(giocatoreCorrente);
        this.naveTerminata=new boolean[nGiocatori];
        startPos=0;
        for(int i=0;i<nGiocatori;i++)
        	naveTerminata[i]=false;

        if (this.nave == null || this.plancia == null) {
            mostraErroreEChiudi("Errore Inizializzazione", "Impossibile caricare i dati di gioco.");
            return;
        }

        primaryStage.setTitle("Galaxy Trucker");

        BorderPane finestraLayoutPrincipale = new BorderPane();
        finestraLayoutPrincipale.setStyle("-fx-background-color: #2A2A2A;");

        // --- ZONA SUPERIORE: PLANCIA ---
        panePlanciaGrafica=new Pane();
        creaPanePlanciaGrafica((lFinestra/5)*2 - 30, altezzaPreferitaPlanciaPane);
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
        Button termina=new Button("Termina");
        
        final int giocatori= nGiocatori;
        
        passaTurno.setOnAction(event -> {
        	
        	nextPlayer(giocatori);
        	
        });
        
        termina.setOnAction(event -> {
        	naveTerminata[giocatoreCorrente]=true;
        	nave.setGiorniVolo(plancia.getStartPos(startPos));
        	creaPanePlanciaGrafica((lFinestra/5)*2 - 30, altezzaPreferitaPlanciaPane);
        	startPos++;
        	nextPlayer(giocatori);
        });
        contenitorePulsanti.getChildren().addAll(passaTurno,termina);
        
        contenitoreCentraleVBox.setAlignment(Pos.TOP_CENTER);
        contenitoreCentraleVBox.getChildren().addAll(areaComponentiHBox, grigliaNave, contenitorePulsanti);
        finestraLayoutPrincipale.setCenter(contenitoreCentraleVBox);
        BorderPane.setAlignment(contenitoreCentraleVBox, Pos.CENTER);


        // --- SCENA E STAGE ---
        Scene scena = new Scene(finestraLayoutPrincipale, lFinestra, aFinestra, false, SceneAntialiasing.BALANCED);
        primaryStage.setScene(scena);
        primaryStage.setMinWidth(lFinestra * 0.85);
        primaryStage.setMinHeight(aFinestra * 0.85);
        primaryStage.setOnCloseRequest(event -> { // Se l'utente chiude la finestra con la X
            terminaFaseCostruzioneUI(); // Termina la fase e sblocca il thread console
       });
        primaryStage.show();
    }
    
    private void terminaFaseCostruzioneUI() {
        
        //System.out.println("INTERFACCIA: Fase costruzione terminata. Sblocco la logica console.");
        if (latchFineCostruzione != null && latchFineCostruzione.getCount() > 0) {
            latchFineCostruzione.countDown();
        }
        
        // Chiudi la finestra grafica
        Platform.runLater(() -> {
            Stage stage = (Stage) grigliaNave.getScene().getWindow();
            if (stage != null) {
                stage.close();
            }
        });
    }
    
    
	private void nextPlayer(int nGiocatori) {
		while(!mano.isEmpty())
    		gioco.getMucchio().add(mano.pesca());
    	
    	int i=0;
    	while(i<nGiocatori) {
    		if(giocatoreCorrente+1==nGiocatori)
        		giocatoreCorrente=0;
        	else
        		giocatoreCorrente+=1;
    		if(!naveTerminata[giocatoreCorrente])
    			break;
    		i++;
    	}
    	
    	if(i==nGiocatori) {
    		//System.out.println("Costruzione finita");
    		terminaFaseCostruzioneUI();
    	}
    	else {
    		nave=gioco.getNave(giocatoreCorrente);
        	mano=generaMano();
        	popolaAreaComponentiHBox(this.areaComponentiHBox, gComponentiDisponibili, gioco);
        	popolaGrigliaNave(this.grigliaNave, nave, gCelleNave);
    	}
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
        //Rectangle[] arrayScarti = new Rectangle[2];
        
        
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
            
            try {
                String imagePath=mano.getComponenteAt(i).getImagePath();
                Image img = new Image(getClass().getResourceAsStream(imagePath));
                if(img.isError())
                	throw new IOException("Risorsa non trovata: " + imagePath);
                arrayComponentiPlaceholder[i].setFill(new ImagePattern(img));
                arrayComponentiPlaceholder[i].setRotate(mano.getComponenteAt(i).getRotations());

            } catch (Exception e) {
                System.err.println("Errore caricamento immagine: " + e.getMessage());
                arrayComponentiPlaceholder[i].setFill(Color.DEEPPINK);
            }
            
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

                if(cellaLogica!=null) {
                	if(cellaLogica.isUtilizzabile()) {
                        if(cellaLogica.getComponente()==null) {
                            cellaGrafica.setFill(Color.LIGHTCYAN.deriveColor(0, 1.0, 0.9, 0.6));
                            cellaGrafica.setStroke(Color.CADETBLUE.deriveColor(0, 1.0, 0.8, 0.7));
                            cellaGrafica.setStrokeWidth(1.0);
                        } else {
                            if(cellaLogica.isConnesso()) {
                            	try {
	                                String imagePath=cellaLogica.getComponente().getImagePath();
	                                Image img = new Image(getClass().getResourceAsStream(imagePath));
	                                if(img.isError())
	                                	throw new IOException("Risorsa non trovata: " + imagePath);
	                                cellaGrafica.setFill(new ImagePattern(img));
	                            } catch (Exception e) {
	                                System.err.println("Errore caricamento immagine: " + e.getMessage());
	                                cellaGrafica.setFill(Color.GREEN);
	                            }
	                            cellaGrafica.setStroke(Color.GREEN);
	                            cellaGrafica.setStrokeWidth(1.5);
                            }else {
                            	try {
                                    String imagePath=cellaLogica.getComponente().getImagePath();
                                    Image img = new Image(getClass().getResourceAsStream(imagePath));
                                    if(img.isError())
                                    	throw new IOException("Risorsa non trovata: " + imagePath);
                                    cellaGrafica.setFill(new ImagePattern(img));
                                } catch (Exception e) {
                                    System.err.println("Errore caricamento immagine: " + e.getMessage());
                                    cellaGrafica.setFill(Color.RED);
                                }
                            	
                                cellaGrafica.setStroke(Color.RED);
                                cellaGrafica.setStrokeWidth(1.5);
                            }
                            cellaGrafica.setRotate(cellaLogica.getComponente().getRotations());
                        	
                        }
                    } else {	//Cella non utilizzabile
                       
                            cellaGrafica.setFill(Color.valueOf("#2E2E2E"));
                            cellaGrafica.setStroke(Color.valueOf("#202020"));
                            cellaGrafica.setStrokeWidth(0.5);
                    }
                }/*
                
                Cella cellaLogica = nave.getCella(new Posizione(i, j));
                if(cellaLogica!=null) {
                    Componente compInCella=cellaLogica.getComponente();
                    // --- CASO 1: LA CELLA CONTIENE UN COMPONENTE ---
                    if(compInCella!=null) {
                        try {
                            String imagePath = compInCella.getImagePath();
                            if(imagePath==null || imagePath.isBlank()) {
                                throw new Exception("Percorso immagine nullo per " + compInCella.getClass().getSimpleName());
                            }
                            Image img=new Image(getClass().getResourceAsStream(imagePath));
                            if(img.isError()) {
                                throw new IOException("Risorsa immagine non trovata o corrotta: " + imagePath);
                            }
                            cellaGrafica.setFill(new ImagePattern(img));

                        }catch (Exception e) {
                            System.err.println("Errore caricamento immagine per cella ("+i+","+j+"): " + e.getMessage());
                            // Se l'immagine non carica, usa un colore di errore come riempimento
                            cellaGrafica.setFill(Color.DEEPPINK);
                        }
                        
                        // Ora decidiamo il colore del BORDO in base allo stato
                        if(cellaLogica.isUtilizzabile() && cellaLogica.isConnesso()) {
                            // BORDO VERDE: Componente funzionante e ben connesso
                            cellaGrafica.setStroke(Color.LIMEGREEN);
                            cellaGrafica.setStrokeWidth(2.5);
                        }else {
                            // BORDO ROSSO: Componente piazzato ma con problemi (non utilizzabile o non connesso)
                            cellaGrafica.setStroke(Color.RED);
                            cellaGrafica.setStrokeWidth(2.5);
                        }
                        
                        cellaGrafica.setRotate(cellaLogica.getComponente().getRotations());

                    }else {
                        // --- CASO 2: LA CELLA È VUOTA ---
                        if(cellaLogica.isUtilizzabile()) {
                            // Spazio disponibile per il piazzamento
                            cellaGrafica.setFill(Color.LIGHTCYAN.deriveColor(0, 1.0, 0.9, 0.20));
                            cellaGrafica.setStroke(Color.CADETBLUE.deriveColor(0, 1.0, 0.8, 0.7));
                            cellaGrafica.setStrokeWidth(1.0);
                        }else {
                            // Spazio non utilizzabile (fuori dalla nave)
                            cellaGrafica.setFill(Color.valueOf("#2E2E2E"));
                            cellaGrafica.setStroke(Color.valueOf("#202020"));
                            cellaGrafica.setStrokeWidth(0.5);
                        }
                    }
                } else {
                    // Caso di sicurezza se getCella potesse mai restituire null
                    cellaGrafica.setFill(Color.BLACK);
                }*/
                	
                final int r = i;
                final int c = j;
                cellaGrafica.setOnMouseClicked(event -> {
                	if(componenteLogico!=null) {
                		if(nave.setCella(new Posizione(r,c), componenteLogico)) {
                		//nave.setCella(new Posizione(r,c), componenteLogico);
                			mano.removeComponenteAt(indiceCorrente);
                			popolaAreaComponentiHBox(this.areaComponentiHBox, gComponentiDisponibili, gioco);
                			popolaGrigliaNave(grigliaNave, nave, gCelleNave);
                			//nave.visualizzaNave();
                			//nave.visualizzaUtilizzabileNave();
                			componenteLogico=null;
                			indiceCorrente=null;
                		}
                		
                		
                	}
                		
                    //System.out.println("Click su cella nave (" + r + "," + c + ")");
                });
                grigliaNave.add(cellaGrafica, j, i);
            }
        }
    }
    
    
    
    
    
    private void creaPanePlanciaGrafica(double larghezzaTotalePane, double altezzaTotalePane) {
    	
    	panePlanciaGrafica.getChildren().clear();
    	
        panePlanciaGrafica.setPrefSize(larghezzaTotalePane, altezzaTotalePane);
        panePlanciaGrafica.setStyle(
            "-fx-background-color: #383838; " +
            "-fx-border-color: #585858; " +
            "-fx-border-width: 2; " +
            "-fx-background-radius: 12; " +
            "-fx-border-radius: 12;"
        );

        double W_contenuto = larghezzaTotalePane - (2 * paddingPlanciaInterno);
        double H_contenuto = altezzaTotalePane - (2 * paddingPlanciaInterno);

        int numGiorni=plancia.getGiorni();
        Nave[] tutteLeNavi=new Nave[gioco.getNGiocatori()];
        for(int i=0;i<gioco.getNGiocatori();i++) {
            tutteLeNavi[i]=gioco.getNave(i);
        }

        if (W_contenuto <= rCerchioGiorno * 6 || H_contenuto <= rCerchioGiorno * 6 || numGiorni <= 0) {
            Label infoLabel = new Label("Plancia non visualizzabile (spazio/giorni insuff.)");
            infoLabel.setTextFill(Color.WHITE);
            infoLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            StackPane placeholder = new StackPane(infoLabel);
            placeholder.prefWidthProperty().bind(panePlanciaGrafica.widthProperty());
            placeholder.prefHeightProperty().bind(panePlanciaGrafica.heightProperty());
            panePlanciaGrafica.getChildren().add(placeholder);
            return;
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
            for(int k=0;k<gioco.getNGiocatori();k++) {
            	if(tutteLeNavi[k].getGiorniVolo()==i)
            		cerchioGiornoShape.setFill(Color.valueOf(tutteLeNavi[k].getColor()));
            		
            }
            
            cerchioGiornoShape.setStroke(Color.DARKRED.darker());
            cerchioGiornoShape.setStrokeWidth(1.5);

            Text numeroGiornoText = new Text(String.valueOf(i+1));
            numeroGiornoText.setFont(Font.font("Arial", FontWeight.BOLD, rCerchioGiorno * 0.80));
            numeroGiornoText.setFill(Color.WHITE);

            StackPane cerchioConTesto = new StackPane(cerchioGiornoShape, numeroGiornoText);
            cerchioConTesto.setLayoutX(p.getX() - rCerchioGiorno);
            cerchioConTesto.setLayoutY(p.getY() - rCerchioGiorno);
            panePlanciaGrafica.getChildren().add(cerchioConTesto);
        }
        
        Color coloreMazzoFill = Color.SADDLEBROWN.deriveColor(0, 0.7, 0.6, 0.9);
        Color coloreMazzoStroke = Color.BLACK.deriveColor(0, 1, 1, 0.5);

        // Posizionamento dei Mazzi Orizzontali
        // Mazzo Superiore
        creaMazzoGraficoOrizzontale(panePlanciaGrafica, "Mazzo N", centroX_contenuto - mazzoVisualWidth / 2, paddingPlanciaInterno/2, coloreMazzoFill, coloreMazzoStroke);
        // Mazzo Inferiore
        creaMazzoGraficoOrizzontale(panePlanciaGrafica, "Mazzo S", centroX_contenuto - mazzoVisualWidth / 2, paddingPlanciaInterno+paddingPlanciaInterno/2 + H_contenuto - mazzoVisualHeight, coloreMazzoFill, coloreMazzoStroke);
        // Mazzo Sinistro
        creaMazzoGraficoOrizzontale(panePlanciaGrafica, "Mazzo O", paddingPlanciaInterno/2, centroY_contenuto - mazzoVisualHeight / 2, coloreMazzoFill, coloreMazzoStroke);
        // Mazzo Destro
        creaMazzoGraficoOrizzontale(panePlanciaGrafica, "Mazzo E", paddingPlanciaInterno+paddingPlanciaInterno/2 + W_contenuto - mazzoVisualWidth, centroY_contenuto - mazzoVisualHeight / 2, coloreMazzoFill, coloreMazzoStroke);
        

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

    /*public static void main(String[] args) {
        launch(args);
    }*/
    
    public static void main(String[] args) {
    	
    	Scanner scanner=new Scanner(System.in);
    	int nGiocatori;
    	Livello livello;
    	
    	System.out.println("--- BENVENUTO IN GALAXY TRUCKER (SETUP CONSOLE) ---\n");
		do {
			//scanner.nextLine();
			System.out.println("Inserire numero giocatori (2-4)");
			nGiocatori = scanner.nextInt();
		}while(nGiocatori<=1||nGiocatori>4);
		
		do {
			scanner.nextLine();
			System.out.println("Inserire Livello (1, 2 o 3)");
			livello=Livello.toLivello(scanner.nextLine());
		}while(livello==null);
        
        // 2. CREA L'OGGETTO GIOCO
        Gioco gioco = new Gioco(nGiocatori, livello);

        // 3. PREPARA LA SINCRONIZZAZIONE
        CountDownLatch latch = new CountDownLatch(1);
        
        // Passa l'istanza di gioco e il latch all'Interfaccia prima di avviarla
        Interfaccia.setParametriDiAvvio(gioco, latch);
        
        // 4. AVVIA IL THREAD PER LA LOGICA DI GIOCO POST-UI
        Thread giocoPostUIThread = new Thread(() -> {
            try {
                System.out.println("THREAD GIOCO: In attesa che la fase di costruzione grafica termini...");
                latch.await(); // Questo thread si blocca qui finché l'UI non ha finito
                
                System.out.println("\nTHREAD GIOCO: Ricevuto segnale dall'UI. Avvio FASE DI VOLO su console.");
                
                // Chiama il metodo di Gioco per la fase di volo (che userà lo stesso scanner)
                //TODO eseguiFaseVoloConsole;
                Mazzo mazzo = new Mazzo(false);
                
                for (int i=0; i<10; i++)
    	        mazzo.pescaAttivaEffetto(gioco);
                
                gioco.getNave(1).visualizzaNave();

            } catch (InterruptedException e) {
                System.err.println("Thread di gioco interrotto.");
            } finally {
                System.out.println("THREAD GIOCO: Partita terminata. Chiusura applicazione.");
                scanner.close();
                Platform.exit(); // Chiude l'applicazione JavaFX se è ancora attiva
                System.exit(0); // Uscita di sicurezza
            }
        });
        giocoPostUIThread.setDaemon(false); // Non daemon, così tiene viva l'app
        giocoPostUIThread.start();
        
        // 5. AVVIA L'INTERFACCIA GRAFICA
        // Questa chiamata bloccherà il thread 'main' finché la finestra non si chiude
        System.out.println("LAUNCHER: Avvio interfaccia grafica...");
        Application.launch(Interfaccia.class, args);
    }
    
}
