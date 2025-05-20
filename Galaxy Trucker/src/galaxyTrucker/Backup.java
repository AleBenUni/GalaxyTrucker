package galaxyTrucker;

import carte.Livello;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Interfaccia extends Application{
	
	//a=Altezza, l=Larghezza, g=Grandezza
	
	private int lFinestra=1000;
	private int aFinestra=1000;
	private int gCelleNave=80;
	private int gComponentiDisponibili=80;
	private int nComponentiDisponibili=5;
	private int aPulsanteRuota=30;
	private int nRighe;
	private int nColonne;
	private Nave nave;
	private Plancia plancia;
	private HBox controlloOrientamento;
	private HBox componentiDisponibili;
	private HBox areaComponenti;
	
	private double rCerchioGiorno=12; // Aumentato leggermente
    private double laMazzoPlancia=70;
    private double aMazzoPlancia=100;
    private double paddingPlanciaInterno=25; // Padding dentro il pane della plancia
    private Pane panePlanciaGrafica;
	
	
	@Override
    public void start(Stage primaryStage) {
		Gioco gioco=new Gioco(1,Livello.III);
		//plancia=gioco.getPlancia();
		
		primaryStage.setTitle("Galaxy Trucker");
		
		BorderPane finestra=new BorderPane();
		areaComponenti=new HBox(15);
		areaComponenti.setPadding(new Insets(10));
		areaComponenti.setAlignment(Pos.CENTER);
		
		VBox[] areaSingoloComponente=new VBox[nComponentiDisponibili];
		Button[] pulsantiRuota=new Button[nComponentiDisponibili];
		Rectangle[] componenti=new Rectangle[nComponentiDisponibili];
		for(int i=0;i<nComponentiDisponibili;i++) {
			areaSingoloComponente[i]=new VBox(5);
			areaSingoloComponente[i].setAlignment(Pos.CENTER);
			
			pulsantiRuota[i]=new Button("Ruota");		
			areaSingoloComponente[i].getChildren().add(pulsantiRuota[i]);
			
			componenti[i]=new Rectangle(gComponentiDisponibili,gComponentiDisponibili);
			componenti[i].setSmooth(true);
			componenti[i].setFill(Color.LIGHTCYAN);
			componenti[i].setStroke(Color.DIMGRAY);
			componenti[i].setArcWidth(10); 
			componenti[i].setArcHeight(10);
			areaSingoloComponente[i].getChildren().add(componenti[i]);
			areaComponenti.getChildren().add(areaSingoloComponente[i]);
		}
		
		finestra.setTop(areaComponenti);	
		
		GridPane grigliaNave=new GridPane();
		grigliaNave.setPadding(new Insets(10));
		grigliaNave.setAlignment(Pos.CENTER);
		grigliaNave.setStyle("-fx-background-color: black;");
		nave=gioco.getNave(0);
		nRighe=nave.getNRighe();
		nColonne=nave.getNColonne();
		for(int i=0;i<nRighe;i++)
			for(int j=0;j<nColonne;j++) {
				Rectangle cellaGrafica=new Rectangle(gCelleNave,gCelleNave);
				cellaGrafica.setSmooth(true);
				if(nave.getCella(new Posizione(i, j)).isUtilizzabile()) {
					cellaGrafica.setFill(Color.LIGHTCYAN);
					cellaGrafica.setStroke(Color.DIMGRAY);
					cellaGrafica.setArcWidth(10); 
					cellaGrafica.setArcHeight(10);
					
				}
				else {
					cellaGrafica.setFill(Color.BLACK);
					cellaGrafica.setStroke(Color.BLACK);
				}
				
				grigliaNave.add(cellaGrafica, j, i);
			}
		
		finestra.setCenter(grigliaNave);
        BorderPane.setAlignment(grigliaNave, Pos.CENTER);
				
		//Scene scena=new Scene(finestra,lFinestra,aFinestra,false,SceneAntialiasing.BALANCED);
		Scene scena=new Scene(finestra);
		primaryStage.setScene(scena);
		primaryStage.show();
					
				
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
