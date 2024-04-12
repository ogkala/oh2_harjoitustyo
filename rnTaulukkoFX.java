package ristinolla;

import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;

/**
Pelin luokka, joka hoitaa taulukon graafiset komponentit.
*/


public class rnTaulukkoFX extends Pane{
	private double koko;
	private ristinollaTaulukko taulu;
	
	/**
	Konstruktoi rnTaulukkoFX-olion.
	@param koko		Taulukon haluttu koko
	@param rn		ristinollaTaulukko, josta taulukko rakennetaan.
	*/
	public rnTaulukkoFX(double koko, ristinollaTaulukko rn){
		this.koko = koko;
		this.taulu = rn;
		if (taulu.getKerros() < 3){ //Maksimi näytettävät kerrokset 
			getChildren().add(piirraTaulukko(taulu, koko, taulu.getKerros()));
		} else {
			getChildren().add(piirraTaulukko(taulu, koko, 2));
		}
	}
	
	
	private Pane piirraTaulukko(ristinollaTaulukko taulu, double koko, int kerros){
		Pane tauluPohja = getTaulukko(koko);
		GridPane ruutuPohja = new GridPane();
		
		for (int k = 0; k < 3; k++){
			ruutuPohja.getColumnConstraints().add(new ColumnConstraints(koko / 3));
			ruutuPohja.getRowConstraints().add(new RowConstraints(koko / 3));
		}
		//
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				Boolean alkio = taulu.getTaulu()[i][j];
				if (alkio == null){
					if (kerros > 0){
						ruutuPohja.add(piirraTaulukko(taulu.getAlaTaulu()[i][j], koko / 3, kerros - 1),i,j);
					}
				} else if(alkio == true){
					ruutuPohja.add(getNolla(koko / 3),i,j);
				} else if(alkio == false){
					ruutuPohja.add(getRisti(koko / 3),i,j);
				}
			}
		}
		tauluPohja.getChildren().add(ruutuPohja);
		return tauluPohja;
	}
	
	/**
	Metodi joka palauttaa halutun kokoisen tyhjän taulukon.
	@param koko			Halutun taulukon koko.
	@return 			Pane:n jossa sisällä halutun kokoinen taulukko.	
	*/
	//Grafiiset komponentit
	public static Pane getTaulukko(double koko){ //taulun koko
		double siivu = koko / 3;
		double leveys = (Math.log(koko) / Math.log(3)) - 1;
		double reuna = Math.pow(1.05,5.5 - leveys) - 1; 
		//System.out.println(reuna);
		//System.out.println(leveys);
		//
		Pane p1 = new Pane();
		Line lp1 = new Line(  siivu, reuna*koko,   siivu, (1-reuna)*koko);
		Line lp2 = new Line(2*siivu, reuna*koko, 2*siivu, (1-reuna)*koko);
		Line lv1 = new Line(reuna*koko,   siivu, (1-reuna)*koko,   siivu);
		Line lv2 = new Line(reuna*koko, 2*siivu, (1-reuna)*koko, 2*siivu);
		lp1.setStrokeWidth(leveys);
		lp2.setStrokeWidth(leveys);
		lv1.setStrokeWidth(leveys);
		lv2.setStrokeWidth(leveys);
		p1.getChildren().addAll(lp1,lp2,lv1,lv2);
		return p1;
	}
	
	/**
	Metodi joka palauttaa halutun kokoisen ristin.
	@param koko			Halutn ristin koko.
	@return				Pane:n jossa sisällä halutun kokoinen risti.
	*/
	public static Pane getRisti(double koko){ //Ruudun, ei koko taulun koko
		Pane p1 = new Pane();
		double leveys = (Math.log(koko) / Math.log(3)) - 1;
		Line l1 = new Line(0.1*koko, 0.1*koko, 0.9*koko, 0.9*koko);
		Line l2 = new Line(0.1*koko, 0.9*koko, 0.9*koko, 0.1*koko);
		l1.setStrokeWidth(leveys);
		l2.setStrokeWidth(leveys);
		p1.getChildren().addAll(l1,l2);
		return p1;
	}

	/**
	Metodi joka palauttaa halutun kokoisen nollan.
	@param koko			Halutn nollan koko.
	@return				Pane:n jossa sisällä halutun kokoinen nolla.
	*/
	public static Pane getNolla(double koko){ //Ruudun, ei koko taulun koko
		Pane p1 = new Pane();
		double leveys = (Math.log(koko) / Math.log(3)) - 1;
		Circle c1 = new Circle(koko/2, koko/2, 0.4*koko);
		c1.setStrokeWidth(leveys);
		c1.setFill(Color.WHITE);
		c1.setStroke(Color.BLACK);
		p1.getChildren().add(c1);
		return p1;
	}

}
