public class ristinollaTaulukko{
	private ristinollaTaulukko[][] alaTaulu;
	private Boolean[][] taulu = new Boolean[3][3];
	private Boolean voittaja;
	private boolean taysi; //optional, in case I want to implement where you can place in already won tables
	private byte kerros;
	private ristinollaTaulukko isa;
	
	public ristinollaTaulukko(){
		this.kerros = 0;
	}
	
	public ristinollaTaulukko(byte k){
		this.alaTaulu = new ristinollaTaulukko[3][3];
		this.kerros = (byte)(k-1);
		if (k > 0){
			for (int i = 0; i < 3; i++){
				for (int j = 0; j < 3; j++){
					this.alaTaulu[i][j] = new ristinollaTaulukko(kerros,this);
				}
			}
		}		
	}

	public ristinollaTaulukko(byte k,ristinollaTaulukko isaSolmu){
		this.alaTaulu = new ristinollaTaulukko[3][3];
		this.kerros = (byte)(k-1);
		this.isa = isaSolmu;
		if (k > 0){
			for (int i = 0; i < 3; i++){
				for (int j = 0; j < 3; j++){
					this.alaTaulu[i][j] = new ristinollaTaulukko(kerros,this);
				}
			}
		}
	}
	
	public Boolean[][] getTaulu(){
		return taulu;
	}

	public ristinollaTaulukko[][] getAlaTaulu(){
		return alaTaulu;
	}
	
	public Boolean getVoittaja(){
		return voittaja;
	}
	
	public boolean getTaysi(){
		return taysi;
	}
	
	public byte getKerros(){
		return kerros;
	}

	public void setVoittaja(Boolean v){
		this.voittaja = v;
	}
	
	public void setTaysi(boolean t){
		this.taysi = t;
	}
	
	public void setTauluAlkio(boolean pelaaja, int x, int y){
		this.taulu[x][y] = pelaaja;
	}
	
	public void resetTaulu(){
		this.voittaja = null;
		this.taysi = false;
		this.taulu = new Boolean[3][3];
		if (kerros != 0) {
			for (int i = 0; i < 3; i++){
				for (int j = 0; j < 3; j++){
					this.alaTaulu[i][j].resetTaulu();
				}
			}
		}
	}
	
	public boolean checkVoitto(){
		//logiikka voiton katsomiseen, teen tämän myöhemmin, kun keksin järkevän (ja nopean) tavan siihen
	}
	

	public static void main(String[] args){
		byte ok = 100;
		ristinollaTaulukko wack = new ristinollaTaulukko(ok);
		System.out.println(wack.getTaulu());
		System.out.println(wack.getVoittaja());
		System.out.println(wack.getTaysi());
		System.out.println(wack.getKerros());
	}
}





