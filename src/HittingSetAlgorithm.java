import java.util.ArrayList;

public class HittingSetAlgorithm {

    public int numberOfVertices;
    public int numberOfSubsets;
    public ArrayList<Vertex> vertices;
    public ArrayList<SubSet> subSets;
    public boolean isWeighted;

    public HittingSetAlgorithm(int numberOfVertices, int numberOfSubsets, ArrayList<Vertex> vertices, ArrayList<SubSet> subSets) {
        this.numberOfVertices = numberOfVertices;
        this.numberOfSubsets = numberOfSubsets;
        this.vertices = vertices;
        this.subSets = subSets;
        this.isWeighted = false;
    }

    /**
     * Set this algorithm to be weighted, so it will chose the next vertex depending
     * on the relation ship between its cost and effectiveness
     */
    public void setAlgorithmTOBeWeighted(){
        this.isWeighted = true;
    }

    /**
     * The core of the algorithm
     * @return ArrayList of vertices (solution)
     */
    public ArrayList<Vertex> run(){

        //Empty array list for vertices that will form the solution
        ArrayList<Vertex> solution = new ArrayList<Vertex>();

        //Array list that will hold vertices that we didn't chose yet to enhance performance
        ArrayList<Vertex> verticesNotChosenYet = new ArrayList<Vertex>(this.vertices);

        //Array list that will hold subsets we haven't managed to hit yet
        ArrayList<SubSet> subSetsNotHitYet = new ArrayList<SubSet>(this.subSets);

        //While there are some sub sets haven't been hit yet
        while (subSetsNotHitYet.size() != 0){

            //Initiate a list to hold the sub sets that will be hit in this iteration
            ArrayList<SubSet> hitSubSetsByChoosingThisVertex = new ArrayList<SubSet>();

            //Get the vertex to choose, and also fill the previous list with the subsets
            Vertex chosenVertex = chooseBestVertexToCoverRemainingSubSets(verticesNotChosenYet , subSetsNotHitYet , hitSubSetsByChoosingThisVertex);

            //Add the vertex to solution list, and remove it from not chosen yet list
            solution.add(chosenVertex);
            verticesNotChosenYet.remove(chosenVertex);

            //Remove subsets hit by this vertex from the list of subsets that weren't hit yet
            subSetsNotHitYet.removeAll(hitSubSetsByChoosingThisVertex);
        }

        //Return found solution (list of vertices that hit all subsets)
        return solution;
    }

    /**
     * Chooses the best vertex among those that haven't been chosen yet, and that
     * provides the best hitting (according to it's weight or number of hit subsets
     * or both factors), also provides a list of subsets that will be hit by it.
     *
     * @param verticesNotChosenYet List of vertices that haven't been chosen yet
     * @param subSetsNotHitYet list of subsets that haven't been hit yet
     * @param hitSubSetsByChoosingThisVertex list of subsets that will be hit by choosing this vertex (reference)
     * @return ArrayList of vertices (solution)
     */
    public Vertex chooseBestVertexToCoverRemainingSubSets(ArrayList<Vertex> verticesNotChosenYet , ArrayList<SubSet> subSetsNotHitYet, ArrayList<SubSet> hitSubSetsByChoosingThisVertex){
        //Call vertex choosing function depending on whether the vertices are weighted or not
        if(isWeighted){
            return chooseBestVertexToCoverRemainingSubSets_ForWeightedProblem(verticesNotChosenYet , subSetsNotHitYet , hitSubSetsByChoosingThisVertex);
        }else{
            return chooseBestVertexToCoverRemainingSubSets_ForNotWeightedProblem(verticesNotChosenYet , subSetsNotHitYet , hitSubSetsByChoosingThisVertex);
        }
    }

    /**
     * Chooses the best vertex among those that haven't been chosen yet, and that
     * provides the best hitting (according to it's weight and number of hit subsets
     * using relation "vertex cost / number of hit sub sets"), also provides a list
     * of subsets that will be hit by it.
     *
     * @param verticesNotChosenYet List of vertices that haven't been chosen yet
     * @param subSetsNotHitYet list of subsets that haven't been hit yet
     * @param hitSubSetsByChoosingThisVertex list of subsets that will be hit by choosing this vertex (reference)
     * @return ArrayList of vertices (solution)
     */
    public Vertex chooseBestVertexToCoverRemainingSubSets_ForWeightedProblem(ArrayList<Vertex> verticesNotChosenYet , ArrayList<SubSet> subSetsNotHitYet, ArrayList<SubSet> hitSubSetsByChoosingThisVertex){
        //The to-be-returned vertex
        Vertex chosenVertex = null;

        //Indicator of worst weight of vertex yet
        double costOfBestHittingVertex = 10000000;

        //Looping through the vertices that haven't been chosen yet
        for (Vertex vertex:verticesNotChosenYet) {

            //Getting all subsets (among those not hit yet) that will be hit (contains) this vertex
            ArrayList<SubSet> subSetsHitByThisVertex = Utilities.getSubsetsThatContainAVertexFromSubsetsList(vertex , subSetsNotHitYet);

            //If the previous list size was (0), then there is no use of this vertex
            if(subSetsHitByThisVertex.size() == 0){
                continue;
            }

            //Calculate the cost of choosing this vertex according to the formula:
            //      Cost = Vertex weight / number of newly hit subsets by choosing this vertex
            //and we always want Cost -> MIN
            double cost = vertex.weight / (1.0 * subSetsHitByThisVertex.size());

            //If the current cost is less thant the least cost achieved yet, replace
            if(cost < costOfBestHittingVertex){

                //Set the least cost so far to be this vertex's
                costOfBestHittingVertex = cost;

                //Refill the list of sub sets that are hit
                hitSubSetsByChoosingThisVertex.clear();
                hitSubSetsByChoosingThisVertex.addAll(subSetsHitByThisVertex);

                //Set this vertex to be the chosen one
                chosenVertex = vertex;
            }
        }
        //Return chosen vertex
        return chosenVertex;
    }

    /**
     * Chooses the best vertex among those that haven't been chosen yet, and that
     * provides the best hitting (according to the number of hit subsets), also
     * provides a list of subsets that will be hit by it.
     *
     * @param verticesNotChosenYet List of vertices that haven't been chosen yet
     * @param subSetsNotHitYet list of subsets that haven't been hit yet
     * @param hitSubSetsByChoosingThisVertex list of subsets that will be hit by choosing this vertex (reference)
     * @return ArrayList of vertices (solution)
     */
    public Vertex chooseBestVertexToCoverRemainingSubSets_ForNotWeightedProblem(ArrayList<Vertex> verticesNotChosenYet , ArrayList<SubSet> subSetsNotHitYet, ArrayList<SubSet> hitSubSetsByChoosingThisVertex){

        //The to-be-returned vertex
        Vertex chosenVertex = null;

        //Indicator of the best vertex yet
        int goodnessOfBestHittingVertex = 0;

        //Looping through the vertices that haven't been chosen yet
        for (Vertex vertex:verticesNotChosenYet) {

            //Getting all subsets (among those not hit yet) that will be hit (contains) this vertex
            ArrayList<SubSet> subSetsHitByThisVertex = Utilities.getSubsetsThatContainAVertexFromSubsetsList(vertex , subSetsNotHitYet);

            //If the number of hit sub sets is better than the best achieved so far change it
            if(subSetsHitByThisVertex.size() > goodnessOfBestHittingVertex){

                //Set the best achieved hit so far to be this vertex's
                goodnessOfBestHittingVertex = subSetsHitByThisVertex.size();

                //Refill the list of sub sets that are hit
                hitSubSetsByChoosingThisVertex.clear();
                hitSubSetsByChoosingThisVertex.addAll(subSetsHitByThisVertex);

                //Set this vertex to be the chosen one
                chosenVertex = vertex;
            }
        }
        //Return chosen vertex
        return chosenVertex;
    }

}
