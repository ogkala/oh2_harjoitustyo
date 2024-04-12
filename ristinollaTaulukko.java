public class ristinollaTaulukko{
	private ristinollaTaulukko[][] alaTaulu;
	private Boolean[][] taulu = new Boolean[3][3];
	private boolean taysi;
	private int kerros;
	private ristinollaTaulukko isa;
	private int isa_x;
	private int isa_y;
	private int alkioita;

	public ristinollaTaulukko(){
		this.kerros = 0;
	}
	
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

	public ristinollaTaulukko(int k,ristinollaTaulukko isaSolmu, int xt, int yt){
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
	
	public Boolean[][] getTaulu(){
		return taulu;
	}

	public ristinollaTaulukko[][] getAlaTaulu(){
		return alaTaulu;
	}
	
	public boolean getTaysi(){
		return taysi;
	}
	
	public int getKerros(){
		return kerros;
	}

	public void setTaysi(boolean v){
		this.taysi = v;
	}
	
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
	
	public void resetTaulu(){
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
	
	//lazy brute approach
	public boolean checkVoitto(Boolean pelaaja, int x, int y){
		//System.out.println(getTaulu()[x][y].equals(pelaaja));
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





