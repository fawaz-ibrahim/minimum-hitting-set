import java.util.ArrayList;

public class Utilities {

    /**
     * Search for a vertex in a list of vertices according to it's id
     * @param id The id of wanted vertex
     * @param vertices List of vertices to search in
     * @return The vertex
     */
    public static Vertex getVertexFromVerticesListById(int id , ArrayList<Vertex> vertices){
        for (Vertex vertex: vertices) {
            if(vertex.id == id){
                return vertex;
            }
        }
        return null;
    }

    /**
     * List of sub sets that contain this vertex
     *
     * @param vertex The searched-for vertex
     * @param subSets List of subsets to search in
     * @return List of subsets
     */
    public static ArrayList<SubSet> getSubsetsThatContainAVertexFromSubsetsList(Vertex vertex , ArrayList<SubSet> subSets){
        ArrayList<SubSet> foundSubsets = new ArrayList<SubSet>();
        for (SubSet subSet:subSets) {
            if(subSet.containsVertex(vertex)){
                foundSubsets.add(subSet);
            }
        }
        return foundSubsets;
    }

    //Utility function
    public static void printer(ArrayList arrayList){
        for (Object object:arrayList) {
            System.out.println(object);
        }
    }

    //Utility function
    public static void printer(ArrayList<Vertex> solution , HittingSetAlgorithm algorithm){
        int numOfVertices = solution.size();
        double totalWeight = 0;
        for (Vertex vertex:solution) {
            totalWeight += vertex.weight;
        }
        if(algorithm.isWeighted){
            System.out.println("Solution : {" + numOfVertices + " vertices} with total weight of {" + totalWeight + "}");
        }else{
            System.out.println("Solution : {" + numOfVertices + " vertices}");
        }
        printer(solution);
    }

}
