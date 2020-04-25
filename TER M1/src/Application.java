import java.util.ArrayList;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class Application {
	public static void main(String[] args) {
		
		//je cr�e une liste de deux graphes random
		ArrayList<Graphe> mesGraphes = new ArrayList<Graphe>();
		Graphe random100 = new Graphe(100);
		Graphe random10 = new Graphe(10);
		mesGraphes.add(random10);
		mesGraphes.add(random100);
		
		//je cr�e une liste d'algo a partir de deux implementations 
		ArrayList<VertexCover> mesAlgos = new ArrayList<VertexCover>();
		VertexCover v1 = new VertexCover1();
		VertexCover v2 = new VertexCover2();
		mesAlgos.add(v1);
		mesAlgos.add(v2);
		
		//je g�n�re les r�sultats pour n=10
		BatterieTest b = new BatterieTest(mesGraphes, mesAlgos);
		b.runBatterie(10);
		System.out.println("Temps moyen d'exec : "+b.moyenne());
	}
}
