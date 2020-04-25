//objet g�n�r� lors de l'appel de la m�thode run() d'un VertexCover
public class Resultat {
	
	private long tempsExec;
	private boolean reponse;
	
	public Resultat() {
	}
	
	public Resultat(long l, boolean r) {
		tempsExec=l;
		reponse=r;
	}
	
	public long getTemps() {
		return tempsExec;
	}
	
	public boolean getRep() {
		return reponse;
	}
	
	public void setTemps(long l) {
		tempsExec=l;
	}
	
	public void setRep(boolean r) {
		reponse=r;
	}
	
	public String toString() {
		String res = "";
		res += "R�ponse :"+reponse+"\n";
		res += "Temps d'�x�cution : "+tempsExec+"\n";
		return res;
	}
}
