import java.util.*;

public class UndirectedGraph {
	Vertex[] vertices;
	EdgeInG[] edges;
	
	public UndirectedGraph(Vertex[] v, EdgeInG[] e){
		this.vertices = v;
		this.edges = e;
	}
	
	//return the vertex index in the array, -1 if there is no such vertex
	public int vertexIndex(Vertex u){
		int ans = -1;
		for(int i=0;i<vertices.length;i++){
			if(vertices[i].id == u.id){
				return i;
			}
		}
		return ans;
	}
	
	//return a list of all the neighbors
	public List adjV(Vertex v){
		List<Vertex> neighbors = new ArrayList<Vertex>();
		for(int i=0;i<edges.length;i++){
			if(edges[i].left == v){
				neighbors.add(edges[i].right);
			}
			else if(edges[i].right == v){
				neighbors.add(edges[i].left);
			}
		}
		return neighbors;
	}
	
	//return the edge for given two vertices
	public EdgeInG findEdge(Vertex v, Vertex u){
		EdgeInG ans = null;
		for(int i=0;i<edges.length;i++){
			if((edges[i].left==v && edges[i].right==u)||(edges[i].left==u &&edges[i].right==v)){
				ans = edges[i];
			}
		}
		return ans;
	}
	
	//return the weight of one edge, infinite if there is no edge
	public int findWeight(Vertex v1, Vertex v2){
		int w = Integer.MAX_VALUE;
		for(int i=0;i<edges.length;i++){
			if((edges[i].left==v1 && edges[i].right==v2)||(edges[i].right==v1&&edges[i].left==v2)){
				return edges[i].weight;
			}
		}
		return w;
	}

}
