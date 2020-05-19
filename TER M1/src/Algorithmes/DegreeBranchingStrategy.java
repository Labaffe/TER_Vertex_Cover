package Algorithmes;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections15.Factory;

import Batterie.Graphe;
import agape.algos.MVC;
import agape.tools.Operations;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class DegreeBranchingStrategy extends AlgoExacte {
	
	protected Factory<Graph<Integer,String>> factory = new Factory<Graph<Integer, String>>() {
		@Override
		public Graph<Integer, String> create() {
			Graph<Integer, String> g = new UndirectedSparseGraph<Integer, String>();
			return g;
		}
	};

	@Override
	public boolean algo(int n, Graphe g) {

		int nbEdge = g.getGraphe().getEdgeCount();
		int nbVertices = g.getGraphe().getVertexCount();

		//si le nombre d'aretes vaut 0
		if (nbEdge == 0) {
			return true;
		}

		//si le nombre d'aretes >= k*nombre de sommets
		if (nbEdge >= n*nbVertices)
			return false;
		

		//si il existe un sommet de degree 1
		Integer verticeUn = Operations.getDegVertex(g.getGraphe(), 1);
		if (verticeUn != null) {
			//on récupère son voisin et on rappelle l'algo avec le graphe privé de ce voisin et pour k-1
			Set<Integer> voisinsUn = new HashSet<Integer>(g.getGraphe().getNeighbors(verticeUn));
			Graphe gtemp = new Graphe();
			gtemp.setGraphe(Operations.copyGraph((Graph<Integer,String>)g.getGraphe(), this.factory));
			Operations.removeAllVertices(gtemp.getGraphe(), voisinsUn);
			return algo(n-1,gtemp);
		}

		//si il existe un sommet de degree 2		
		Integer verticeDeux = Operations.getDegVertex(g.getGraphe(), 2);
		if (verticeDeux != null) {
			//on récupère ses voisins et on rappelle l'algo avec le graphe privé de ces voisins et pour k-2
			Set<Integer> voisins2 = new HashSet<Integer>(g.getGraphe().getNeighbors(verticeDeux));
			Graphe gtemp = new Graphe();
			gtemp.setGraphe(Operations.copyGraph((Graph<Integer,String>)g.getGraphe(), this.factory));
			Operations.removeAllVertices(gtemp.getGraphe(), voisins2);
			boolean appel1 = algo(n-2, gtemp);
			
			//on récupère les voisins des voisins et on les compte
			//on rappelle l'algo avec le graphe privé des voisins des voisins et pour k-(nombre de voisins de voisins)
			Graphe gtemp2 = new Graphe();
			gtemp2.setGraphe(Operations.copyGraph((Graph<Integer,String>)g.getGraphe(), this.factory));
			Set<Integer> voisinsDesVoisins = Operations.getNeighbors(g.getGraphe(), verticeDeux, 2);
			Operations.removeAllVertices(gtemp2.getGraphe(), voisinsDesVoisins);
			boolean appel2 = algo(n-(voisinsDesVoisins.size()), gtemp2);
			
			return appel1||appel2;
		}

		//si il existe un sommet de degree 3 ou plus
		Integer verticeMax = Operations.getMaxDegVertex(g.getGraphe());
		if(verticeMax!=null && g.getGraphe().degree(verticeMax)>=3) {
			//on récupère ses voisins et on rappelle l'algo avec le graphe privé de ces voisins et pour k-3
			Set<Integer> voisinsMax = new HashSet<Integer>(g.getGraphe().getNeighbors(verticeMax));
			Graphe gtemp = new Graphe();
			gtemp.setGraphe(Operations.copyGraph((Graph<Integer,String>)g.getGraphe(), this.factory));
			Operations.removeAllVertices(gtemp.getGraphe(), voisinsMax);
			boolean appel1 = algo(n-voisinsMax.size(), gtemp);
			
			//on récupère le sommet et on rappelle l'algo avec le graphe privé de ce sommet et pour k-1
			Graphe gtemp2 = new Graphe();
			gtemp2.setGraphe(Operations.copyGraph((Graph<Integer,String>)g.getGraphe(), this.factory));
			Set<Integer> temp  =new HashSet<Integer>();
			temp.add(verticeMax);
			Operations.removeAllVertices(gtemp2.getGraphe(), temp);
			boolean appel2 = algo(n-1, gtemp2);
			
			return appel1||appel2;
		}
		return false;
	}
}

