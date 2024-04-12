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

import javafx.scene.input.MouseEvent;

import java.awt.Toolkit;
import java.awt.Dimension;

public class main extends Application{

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
		System.out.println(screenHeight);
		System.out.println(screenWidth);
	}
	
	public static void main(String[] args){
		launch(args);
	}
}

//.setStyle("-fx-border-color: green;\n-fx-border-width: 3;\n-fx-border-style: dashed;\n");