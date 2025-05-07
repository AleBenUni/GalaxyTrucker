package galaxyTrucker;

import carte.Livello;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Interfaccia extends Application{
	
	private int grandezzaCelle=40;
	private int nRighe;
	private int nColonne;
	private Nave nave;
	
	@Override
    public void start(Stage primaryStage) {
		Gioco gioco=new Gioco(1,Livello.I);
		nave=gioco.getNave(0);
		nRighe=nave.getNRighe();
		nColonne=nave.getNColonne();
		primaryStage.setTitle("Galaxy Trucker");
		GridPane grigliaNave=new GridPane();
		for(int i=0;i<nRighe;i++)
			for(int j=0;j<nColonne;j++) {
				Rectangle cellaGrafica=new Rectangle(grandezzaCelle,grandezzaCelle);
				if(nave.getCella(new Posizione(i, j)).isUtilizzabile())
					cellaGrafica.setFill(Color.LIGHTCYAN);
				else
					cellaGrafica.setFill(Color.BLACK);
				grigliaNave.add(cellaGrafica, j, i);
			}
				
		Scene scena=new Scene(grigliaNave);
		primaryStage.setScene(scena);
		primaryStage.show();
					
				
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
