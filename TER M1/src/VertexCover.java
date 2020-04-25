
public abstract class VertexCover {

	//� impl�menter par les g�nies
	public abstract boolean algo(int n, Graphe g);
	
	//test de l'algo pour un graphe et un n donn�
	//retourne un objet Resultat qui contient le temps d'exec et la r�ponse oui ou non.
	public Resultat run(int n, Graphe g) {
		Resultat r = new Resultat();
		long startTime = System.nanoTime();
		boolean reponse = algo(n, g);
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		r.setRep(reponse);
		r.setTemps(duration);
		System.out.println(r);
		return r;
	}
	
}
