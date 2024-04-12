package ristinolla;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.KeyCombination;
import java.awt.Toolkit;
import java.awt.Dimension;

/**
Pelin pääluokka. Pelin pyöriminen toimii tämän luokan kautta.
*/

public class main extends Application{
	
	/**
	Aloittaa javafx applikaation.
	*/
	@Override
	public void start(Stage primaryStage){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenWidth = screenSize.getWidth();
		double screenHeight = screenSize.getHeight();
		//
		Menu menu = new Menu(screenWidth, screenHeight);		

		Scene kehys = new Scene(menu);
		kehys.setOnMouseClicked(menu::clickHandler);
        primaryStage.setScene(kehys);
        primaryStage.setTitle("Ristinolla");
		primaryStage.setFullScreen(true);
		primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.show();
	}
	/**
	Aloittaa pelin.
	*/
	public static void main(String[] args){
		launch(args);
	}
}
