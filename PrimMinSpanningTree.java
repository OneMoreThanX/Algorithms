import java.util.*;

public class PrimMinSpanningTree {
	/*
	 * Given an undirected graph and pick any vertex as the start v
	 * */
	UndirectedGraph G;
	Vertex start;
	ArrayList<Integer> weight = new ArrayList<Integer>();
	ArrayList<Integer> temp = new ArrayList<Integer>();
	Set<Vertex> explored = new HashSet<Vertex>();
	Set<EdgeInG> edges = new HashSet<EdgeInG>();
	
	public PrimMinSpanningTree(UndirectedGraph G,Vertex s){
		//Initialization
		for(int i=0;i<G.edges.length;i++){
			weight.add(Integer.MAX_VALUE);
		}
		this.G = G;
		this.start = s;
	}
	
	public Set<EdgeInG> findMST(){
		int score = 0;
		explored.add(start);
		while(explored.size() < G.vertices.length){
			Iterator<Vertex> itr = explored.iterator();
			int light = Integer.MAX_VALUE;
			EdgeInG min = null;
			Vertex neighbor = null;
			while(itr.hasNext()){
				//for all the vertices inside the set
				Vertex next = itr.next();
				List<Vertex> adjV = G.adjV(next);
				/*
				 * for each vertex, go over all edges between it and unexplored vertices
				 * find the lightest edge as min, set its weight as light, and mark that
				 * neighbor as neighbor
				 */
				for(int i=0;i<adjV.size();i++){
					Vertex u = adjV.get(i);
					if(!explored.contains(u)){
						int w = G.findWeight(next, u);
						if(light > w){
							light = w;
							min = G.findEdge(u, next);
							neighbor = u;
						}
					}
				}
			}
			System.out.println("Lightest edge is "+light+" between "+min.left.id+" and "+min.right.id);
			edges.add(min);
			explored.add(neighbor);
			score = score + light;
		}
		System.out.println("Total weight of MST is "+score);
		return edges;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//a simple test case to see how it works
		Vertex v1 = new Vertex(1);
		Vertex v2 = new Vertex(2);
		Vertex v3 = new Vertex(3);
		Vertex v4 = new Vertex(4);
		Vertex v5 = new Vertex(5);
		EdgeInG e1 = new EdgeInG(v1,v2,1);
		EdgeInG e2 = new EdgeInG(v1,v4,8);
		EdgeInG e3 = new EdgeInG(v2,v3,7);
		EdgeInG e4 = new EdgeInG(v2,v4,2);
		EdgeInG e5 = new EdgeInG(v3,v4,3);
		EdgeInG e6 = new EdgeInG(v4,v5,2);
		EdgeInG e7 = new EdgeInG(v1,v5,3);
		Vertex[] v = {v1,v2,v3,v4,v5};
		EdgeInG[] e = {e1,e2,e3,e4,e5,e6,e7};
		UndirectedGraph g = new UndirectedGraph(v,e);
		PrimMinSpanningTree test = new PrimMinSpanningTree(g,v2);
		Set<EdgeInG> result = test.findMST();

	}

}
