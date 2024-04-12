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
import javafx.scene.input.MouseEvent;

public class Menu extends BorderPane{
	private static final Color pohjavari = Color.GRAY;
	private double leveys;
	private double korkeus;
	private double sivuKoko;
	private Pane vuoroPane;
	private ristinollaTaulukko rn;
	private rnTaulukkoFX rnFX;
	private boolean vuoro = false;
	private int[] playableX = {10};
	private int[] playableY = {10};
	
	public Menu(double leveys, double korkeus){
		this.leveys = leveys;
		this.korkeus = korkeus;
		this.sivuKoko = (leveys - korkeus) / 2;
		this.rn = new ristinollaTaulukko(3);
		piirraMenu();
		makeTaulukkoFX();
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
	
	public void makeTaulukkoFX(){
		this.rnFX = new rnTaulukkoFX(korkeus, rn);
		setCenter(rnFX);
	}
	
	public void paivitaVuoro(boolean fvuoro){
		if (fvuoro){
			vuoroPane.getChildren().clear();
			Pane nolla = rnTaulukkoFX.getNolla(korkeus / 4);
			((Circle)nolla.getChildren().get(0)).setFill(pohjavari);
 			vuoroPane.getChildren().add(nolla);
			
		} else {
			vuoroPane.getChildren().clear();
			vuoroPane.getChildren().add(rnTaulukkoFX.getRisti(korkeus / 4));
		}
	}
	
	public void clickHandler(MouseEvent event){
		double x = event.getX();
		double y = event.getY();
		if ((x > sivuKoko) &&  (x < (leveys - sivuKoko))){
			double ruutuKoko = korkeus / 27;
			int xsq = (int)((x - sivuKoko) / ruutuKoko);
			int x1 = (int)(xsq / 9);
			int x2 = (int)((xsq % 9) / 3);
			int x3 = (int)(xsq % 3);
			int ysq = (int)(y / ruutuKoko);
			int y1 = (int)(ysq / 9);
			int y2 = (int)((ysq % 9) / 3);
			int y3 = (int)(ysq % 3);
			//
			if ((rn.getAlaTaulu()[x1][y1].getAlaTaulu()[x2][y2].getTaulu()[x3][y3] == null) &&
				(rn.getAlaTaulu()[x1][y1].getTaulu()[x2][y2] == null) && (rn.getTaulu()[x1][y1] == null)){
				
				if (playableX.length == 2){
					if ((playableX[0] != x1) || (playableX[1] != x2) || (playableY[0] != y1) || (playableY[1] != y2)){
						return;
						}
				} else if(((playableX[0] != x1) || (playableY[0] != y1)) && (playableX[0] != 10)){
					return;
				}
				int kerros = rn.getAlaTaulu()[x1][y1].getAlaTaulu()[x2][y2].setTauluAlkio(vuoro,x3,y3);
				
				if (kerros == 10){
					System.out.println("You won");
					return;
				}
				
				this.vuoro = !vuoro;
				paivitaVuoro(vuoro);
				makeTaulukkoFX();
				Rectangle alue = new Rectangle();
				double koko3 = korkeus/3;
				double koko9 = korkeus/9;
				if (kerros == 0){
					if ((rn.getAlaTaulu()[x1][y1].getTaulu()[x3][y3] != null) || (rn.getAlaTaulu()[x1][y1].getAlaTaulu()[x3][y3].getTaysi())){
						this.playableX = new int[] {x1};
						this.playableY = new int[] {y1};
						alue = new Rectangle(x1*koko3, y1*koko3, koko3, koko3);
						if (rn.getAlaTaulu()[x1][y1].getTaysi()){
							this.playableX = new int[] {10};
							this.playableY = new int[] {10};
							alue = new Rectangle(0, 0, korkeus, korkeus);							
						}
					} else {
						this.playableX = new int[] {x1,x3};
						this.playableY = new int[] {y1,y3};
						alue = new Rectangle(x1*koko3 + x3*koko9, y1*koko3 + y3*koko9, koko9, koko9);						
					}
				} else if(kerros == 1){
					if ((rn.getTaulu()[x1][y1] != null) || (rn.getAlaTaulu()[x1][y1].getTaysi())){
						this.playableX = new int[] {10};
						this.playableY = new int[] {10};
						alue = new Rectangle(0,0,korkeus,korkeus);
					} else {
						this.playableX = new int[] {x2};
						this.playableY = new int[] {y2};
						alue = new Rectangle(x2*koko3, y2*koko3, koko3, koko3);
					}
				} else if(kerros == 2){
					this.playableX = new int[] {10};
					this.playableY = new int[] {10};
					alue = new Rectangle(0,0,korkeus,korkeus);
				}
				alue.setFill(Color.rgb(255,0,0,0.2));
				rnFX.getChildren().add(alue);
			}
		}
	}
}




