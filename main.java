import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.input.KeyCombination;

import java.awt.Toolkit;
import java.awt.Dimension;

public class main extends Application{

	@Override
	public void start(Stage primaryStage){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenWidth = screenSize.getWidth();
		double screenHeight = screenSize.getHeight();
		double sideSize = (screenWidth-screenHeight)/2;
		double taulukkoVali = screenHeight/3;
		//
		BorderPane pohja = new BorderPane();
		StackPane keskiPohja = new StackPane();
		GridPane alku = new GridPane();
		//Pysyva UI
		Color pohjavari = Color.GRAY;
		Rectangle vasenPohja = new Rectangle(0,0,sideSize,screenHeight);
		Rectangle oikeaPohja = new Rectangle(screenWidth-sideSize,0,sideSize,screenHeight);
		vasenPohja.setFill(pohjavari);
		oikeaPohja.setFill(pohjavari);
		//
		ristinollaTaulukko rn1 = new ristinollaTaulukko(3);
		rn1.setTauluAlkio(false,0,0);
		rn1.setTauluAlkio(true,1,2);
		rn1.getAlaTaulu()[1][1].setTauluAlkio(true,1,0);
		rn1.getAlaTaulu()[2][1].getAlaTaulu()[0][1].setTauluAlkio(false,0,1);
		rn1.getAlaTaulu()[2][1].getAlaTaulu()[0][1].setTauluAlkio(true,2,1);
		rnTaulukkoFX testi = new rnTaulukkoFX(screenHeight, rn1);
		keskiPohja.getChildren().add(testi);
		

		pohja.setLeft(vasenPohja);
		pohja.setRight(oikeaPohja);
		pohja.setCenter(keskiPohja);
		


		Scene kehys = new Scene(pohja);
        primaryStage.setScene(kehys);
        primaryStage.setTitle("Ristinolla");
		primaryStage.setFullScreen(true);
		primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.show();
		System.out.println(screenHeight);
		System.out.println(screenWidth);
	}
	
	//public Pane getRisti(double koko){
		
	//}
	
	public static void main(String[] args){
		launch(args);
	}	
}

