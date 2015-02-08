import java.util.*;

public class DijkstraShortestPath {
	//The distance from source to any vertex
	List<Integer> dist = new ArrayList<Integer>();
	
	//The previous vertex in the shortest path
	List<Vertex> pred = new ArrayList<Vertex>();
	
	//The unvisited vertices with their distance
	List<Integer> unvisited = new ArrayList<Integer>();
	
	Set<Vertex> explored = new HashSet<Vertex>();
	UndirectedGraph G;
	Vertex source;
	
	public DijkstraShortestPath(UndirectedGraph G,Vertex s){
		//Initialization
		for(int i=0;i<G.vertices.length;i++){
			//Initial distance is infinite for all the vertices except source
			dist.add(Integer.MAX_VALUE);
			//Initial previous vertex is null
			pred.add(null);
			unvisited.add(Integer.MAX_VALUE);
		}
		this.G = G;
		this.source = s;
		dist.set(0,0);
		unvisited.set(0,0);
	}
	
	public List<Integer> findShortest(){
		for(int i=0;i<G.vertices.length;i++){
			System.out.println(explored.size()+" vertices are explored");
			//determine the next visiting vertex based on distance
			int nextId = dist.indexOf(Collections.min(unvisited));
			Vertex next = G.vertices[nextId];
			System.out.println("will visit "+next.id);
			visit(next);
		}
		return dist;
	}
	
	public void visit(Vertex v){
		List<Vertex> adjV = new ArrayList<Vertex>();
		//provided method in Graph class, returns a list of the neighbors of given vertex
		adjV = G.adjV(v);
		int vIndex = G.vertexIndex(v);
		Iterator<Vertex> adjItr = adjV.iterator();
		while(adjItr.hasNext()){
			Vertex u = adjItr.next();
			if(!explored.contains(u)){
				int vu = G.findWeight(v, u);
				int uIndex = G.vertexIndex(u);
				if(dist.get(uIndex)>(dist.get(vIndex)+ vu)){
					dist.set(uIndex,(dist.get(vIndex)+ vu));
					//System.out.println("set "+u.id+" distance to be "+dist.get(uIndex));
					pred.set(uIndex, v);
					//System.out.println("set "+u.id+" previous to be "+v.id);
				}
				unvisited.set(uIndex, dist.get(uIndex));
			}
		}
		unvisited.set(vIndex, Integer.MAX_VALUE);
		explored.add(v);
	}
	
	public static void main(String[] args){
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
		Vertex[] v = {v1,v2,v3,v4,v5};
		EdgeInG[] e = {e1,e2,e3,e4,e5,e6};
		UndirectedGraph g = new UndirectedGraph(v,e);
		DijkstraShortestPath Testcase = new DijkstraShortestPath(g,v1);
		List<Integer> result = Testcase.findShortest();
		for(int i=0;i<result.size();i++){
			System.out.println("Vertex "+(i+1)+" has distance "+result.get(i));
		}
	}
}

