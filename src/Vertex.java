public class Vertex {

    //ID of the vertex (it's number)
    public int id;
    //Weight of the vertex
    public double weight;

    //Constructor
    public Vertex(int id, double weight) {
        this.id = id;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "id=" + id +
                ", weight=" + weight +
                '}';
    }
}
