// A simple Edge class defined for undirected graph

public class EdgeInG {
	
	public int weight;
	public Vertex left;
	public Vertex right;
	
	public EdgeInG (Vertex v1, Vertex v2, int w){
		this.left = v1;
		this.right = v2;
		this.weight = w;
	}

}
