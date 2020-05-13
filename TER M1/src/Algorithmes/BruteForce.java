package Algorithmes;

import org.apache.commons.collections15.Factory;

import Batterie.Graphe;
import agape.algos.MVC;
import agape.tools.Operations;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedGraph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class BruteForce extends AlgoExacte {
	
	protected Factory<Graph<Integer,String>> factory = new Factory<Graph<Integer, String>>() {
		@Override
		public Graph<Integer, String> create() {
			Graph<Integer, String> g = new UndirectedSparseGraph<Integer, String>();
			return g;
		}
	};

	@Override
	public boolean algo(int k, Graphe g) {

		if (g.getGraphe().getEdgeCount()==0) {return true;}
			if (k==0) {return false;}
			for (Integer  value : g.getGraphe().getVertices()) {
				Graphe gtemp = new Graphe();
				gtemp.setGraphe(Operations.copyGraph((Graph<Integer,String>)g.getGraphe(), this.factory));
				gtemp.getGraphe().removeVertex(value);
				if (algo(k-1, gtemp)){
					return true;
				}
			}

			return false;
	}
}

