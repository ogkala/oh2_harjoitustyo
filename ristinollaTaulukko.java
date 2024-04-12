package ristinolla;

import java.io.Serializable;

/**
Perus olio, jossa pelin data säilötään.

@since       1.0
*/
public class ristinollaTaulukko implements Serializable{
	private ristinollaTaulukko[][] alaTaulu;
	private Boolean[][] taulu = new Boolean[3][3];
	private boolean taysi;
	private int kerros;
	private ristinollaTaulukko isa;
	private int isa_x;
	private int isa_y;
	private int alkioita;
	//Only for serialization purposes
	private int[] playableX;
	private int[] playableY;
	private boolean vuoro;
	
	/**
	Tekee 0 syvän taulukon.
	*/

	public ristinollaTaulukko(){
		this.kerros = 0;
	}
	
	/**
	Tekee k syvän taulukon.
	@param k		Taulunkon syvyys.
	*/
	
	public ristinollaTaulukko(int k){
		this.alaTaulu = new ristinollaTaulukko[3][3];
		this.kerros = k-1;
		if (k > 0){
			for (int i = 0; i < 3; i++){
				for (int j = 0; j < 3; j++){
					this.alaTaulu[i][j] = new ristinollaTaulukko(kerros, this, i, j);
				}
			}
		}
	}

	private ristinollaTaulukko(int k,ristinollaTaulukko isaSolmu, int xt, int yt){
		this.alaTaulu = new ristinollaTaulukko[3][3];
		this.kerros = k-1;
		this.isa = isaSolmu;
		this.isa_x = xt;
		this.isa_y = yt;
		if (k > 0){
			for (int i = 0; i < 3; i++){
				for (int j = 0; j < 3; j++){
					this.alaTaulu[i][j] = new ristinollaTaulukko(kerros,this,i,j);
				}
			}
		}
	}
	
	/**
	Palauttaa taulukon nykyisen kerroksen taulun.
	*/
	public Boolean[][] getTaulu(){
		return taulu;
	}

	/**
	Palauttaa taulukon nykyisen kerroksen alatauluista.
	*/
	public ristinollaTaulukko[][] getAlaTaulu(){
		return alaTaulu;
	}

	/**
	Palauttaa onko taulu täynä vai ei.
	*/	
	public boolean getTaysi(){
		return taysi;
	}
	
	/**
	Palauttaa nykyisen taulukon kerroksen. (0 on alin)
	*/
	public int getKerros(){
		return kerros;
	}

	/**
	Palauttaa pelattavan alueen x-koordinaatin. Vain tallennusta varten.
	*/	
	protected int[] getPX(){
		return playableX;
	} 

	/**
	Palauttaa pelattavan alueen y-koordinaatin. Vain tallennusta varten.
	*/	
	protected int[] getPY(){
		return playableY;
	} 

	/**
	Palauttaa pelin nykyisen vuoron. Vain tallennusta varten.
	*/	
	protected boolean getVuoro(){
		return vuoro;
	} 

	/**
	Asettaa onko nykyisen kerroksen taulu täynä vai ei.
	@param taysi	Onko nykyinen taulukko täysi vai ei.
	*/	

	public void setTaysi(boolean taysi){
		this.taysi = taysi;
	}

	/**
	Asettaa tallennusta varten tarvittavat arvot. Vain tallennusta varten.
	@param pX		Pelattavan alueen x-koordinaatti. Vain tallennusta varten.
	@param pY		Pelattavan alueen y-koordinaatti. Vain tallennusta varten.
	@param v		Pelin nykyinen vuoro. Vain tallennusta varten.
	*/

	protected void setGameVars(int[] pX, int[] pY, boolean v){
		this.playableX = pX;
		this.playableY = pY;
		this.vuoro = v;
	}
	
	/**
	Asettaa taulun alkion pelaajan merkiksi. Palauttaa kuinka korkeimman kerroksen mihin teki muutoksen.
	@param pelaaja		Pelaaja joka tekee siirron.
	@param x			Taulun x-koordinaatti.
	@param y			Taulun y-koordinaatti.
	@return 			Korkeimman kerroksen mihin teki muutoksen.
	*/
	public int setTauluAlkio(boolean pelaaja, int x, int y){
		this.alkioita += 1;
		if (alkioita == 9){
			setTaysi(true);
		}
		this.taulu[x][y] = pelaaja;
		if (checkVoitto(pelaaja, x, y)){
			setTaysi(true);
			if (isa != null){
				return isa.setTauluAlkio(pelaaja, isa_x ,isa_y);
			} else{
				return 10;
			}
		}
		return kerros;
	}
	
	/**
	Resetoi taulun.
	*/
	public void resetTaulu(){
		this.taysi = false;
		this.taulu = new Boolean[3][3];
		this.alkioita = 0;
		if (kerros != 0) {
			for (int i = 0; i < 3; i++){
				for (int j = 0; j < 3; j++){
					this.alaTaulu[i][j].resetTaulu();
				}
			}
		}
	}
	/**
	Tarkistaa voittiko pelaaja taulun.
	@param pelaaja		Pelaaja joka teki siirron.
	@param x			Pelaajan tekemän siirron x-koordinaatti.
	@param y			Pelaajan tekemän siirron y-koordinaatti.
	@return				true, jos pelaaja voitti taulun.
	*/
	//lazy brute approach
	public boolean checkVoitto(Boolean pelaaja, int x, int y){
		if (getTaulu()[x][0] == pelaaja && getTaulu()[x][1] == pelaaja && getTaulu()[x][2] == pelaaja){
			return true;
		}
		if (getTaulu()[0][y] == pelaaja && getTaulu()[1][y] == pelaaja && getTaulu()[2][y] == pelaaja){
			return true;
		}
		if (getTaulu()[0][0] == pelaaja && getTaulu()[1][1] == pelaaja && getTaulu()[2][2] == pelaaja){
			return true;
		}
		if (getTaulu()[0][2] == pelaaja && getTaulu()[1][1] == pelaaja && getTaulu()[2][0] == pelaaja){
			return true;
		}
		return false;
	}
}





