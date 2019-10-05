import java.util.ArrayList;

public class SubSet {
    //ID of the subset (it's number)
    public int id;
    //Vertices in this subset
    public ArrayList<Vertex> vertices;

    //Constructor
    public SubSet(int id) {
        this.id = id;
        vertices = new ArrayList<Vertex>();
    }

    //Constructor
    public SubSet(int id, ArrayList<Vertex> vertices) {
        this.id = id;
        this.vertices = vertices;
    }

    /**
     * Add new vertex to this subset vertices
     *
     * @param vertex the vertex to add
     */
    public void addVertex(Vertex vertex){
        vertices.add(vertex);
    }

    /**
     *
     * @param vertex The vertex to search for
     * @return whether the subset contains this vertex
     */
    public boolean containsVertex(Vertex vertex){
        return vertices.contains(vertex);
    }

    @Override
    public String toString() {
        String res = "";
        res = "SubSet{" +
                "id=" + id +
                ", vertices={";
        for (Vertex vertex: vertices) {
            res += vertex.id + ",";
        }
        res = res.substring(0 , res.length()-1);
        res +=  "} }";
        return  res;
    }
}
