package vol;

public class Vol {
	private String numeroVol;
	private String villeDepart;
	private String villeArrive;

	public Vol() {};
	public Vol(String numeroVol, String villeDepart, String villeArrive) {
		this.numeroVol = numeroVol;
		this.villeDepart = villeDepart;
		this.villeArrive = villeArrive;
	}
	
	public String getNumeroVol() {
		return numeroVol;
	}
	public void setNumeroVol(String numeroVol) {
		this.numeroVol = numeroVol;
	}
	public String getVilleDepart() {
		return villeDepart;
	}
	public void setVilleDepart(String villeDepart) {
		this.villeDepart = villeDepart;
	}
	public String getVilleArrive() {
		return villeArrive;
	}
	public void setVilleArrive(String villeArrive) {
		this.villeArrive = villeArrive;
	}
	
	@Override
	public String toString() {
		return "Vol [numeroVol=" + numeroVol + ", villeDepart=" + villeDepart + ", villeArrive=" + villeArrive + "]";
	}
}
