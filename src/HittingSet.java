import javax.swing.*;
import java.util.ArrayList;

public class HittingSet {
    public static void main(String[] args){
        //Array that will hold:
        // params[0] number of vertices
        // params[1] number of subsets
        int [] params = new int[2];

        //Vertices that will be read from the file
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();

        //Subsets that will be read from the file
        ArrayList<SubSet> subSets = new ArrayList<SubSet>();

        //Reading the file:
        // data1
        //TODO: Uncomment if you want to use (remember to comment data2 instruction)
        //FileControl.readInputFile("data1" ,params , vertices , subSets);
        // data2
        FileControl.readInputFile("data2" ,params , vertices , subSets);

        //Run The Algorithm and print the result
        HittingSetAlgorithm hittingSetAlgorithm = new HittingSetAlgorithm(params[0] , params[1] , vertices , subSets);

        /*
         * set the algorithms to take the weights of the nodes in consideration, or not
         * 1 => for weighted nodes
         * 0 => otherwise
         */
        String weightedOrNot = JOptionPane.showInputDialog("Write 1 if you want the vertices to be weighted, 0 otherwise:");

        // Setting the algorithm to be "weighted" if that's what user wants
        if(weightedOrNot.trim().equalsIgnoreCase("1")){
            hittingSetAlgorithm.setAlgorithmTOBeWeighted();
        }

        //Running the algorithm and returning the Sol(x) set
        ArrayList<Vertex> solution = hittingSetAlgorithm.run();

        //Printing the solution
        Utilities.printer(solution, hittingSetAlgorithm);
    }
}
