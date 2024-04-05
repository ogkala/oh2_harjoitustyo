import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.geometry.Insets;


public class Menu extends BorderPane{
	private static final Color pohjavari = Color.GRAY;
	private double leveys;
	private double korkeus;
	private double sivuKoko;
	private Pane vuoroPane;
	
	public Menu(double leveys, double korkeus){
		this.leveys = leveys;
		this.korkeus = korkeus;
		this.sivuKoko = (leveys - korkeus) / 2;
		piirraMenu();
	}
	
	public void piirraMenu(){
		StackPane vasen = new StackPane();
		StackPane oikea = new StackPane();
		//pohjaväri
		Rectangle vasenPohja = new Rectangle(0, 0, sivuKoko,korkeus);
		Rectangle oikeaPohja = new Rectangle(0, 0, sivuKoko,korkeus);
		vasenPohja.setFill(pohjavari);
		oikeaPohja.setFill(pohjavari);
		//vasen
		VBox gameNapit = new VBox(korkeus / 5);
		Button uusi = new Button("New Game");
		Button lataa = new Button("Load Game");
		Button tallenna = new Button("Save Game");
		gameNapit.setAlignment(Pos.CENTER);
		gameNapit.getChildren().addAll(uusi,lataa,tallenna);
		//oikea
		VBox vuoroBox = new VBox();
		Label vuoroLabel = new Label("Pelaaja:");
		this.vuoroPane = new Pane();
		vuoroPane.setMaxWidth(korkeus / 4);
		Pane realVuoro = rnTaulukkoFX.getRisti(korkeus / 4);
		vuoroPane.getChildren().add(realVuoro);
		vuoroBox.setAlignment(Pos.CENTER);
		vuoroBox.getChildren().addAll(vuoroLabel,vuoroPane);
		//lisäys
		vasen.getChildren().addAll(vasenPohja,gameNapit);
		oikea.getChildren().addAll(oikeaPohja,vuoroBox);
		setLeft(vasen);
		setRight(oikea);
	}
	
	public void paivitaVuoro(boolean vuoro){
		if (vuoro){
			vuoroPane.getChildren().clear();
			Pane nolla = rnTaulukkoFX.getNolla(korkeus / 4);
			((Circle)nolla.getChildren().get(0)).setFill(pohjavari);
 			vuoroPane.getChildren().add(nolla);
			
		} else {
			vuoroPane.getChildren().clear();
			vuoroPane.getChildren().add(rnTaulukkoFX.getRisti(korkeus / 4));
		}
	}
}




