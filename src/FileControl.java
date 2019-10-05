import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileControl {

    /*
    File format is as the following:
    Line1: Integer , number of nodes (N)
    Line2: Collection of Comma-separated Double numbers, weights of nodes (Number of weights = Number of nodes (N))
    Line3: Integer, Number of subsets
    Line4..X: Collection of Comma-separated Integer numbers, Ids of the vertices:
                -   Number of values <= Number of nodes (N)
                -   Each value must be between 1 and Number of nodes (included) : 1 <= v <= N
     */

    public static void readInputFile( String dataFile ,int [] params , ArrayList<Vertex> vertices , ArrayList<SubSet> subSets){
        int numberOfVertices = 0;
        int numberOfSubsets = 0;
        try {
            File file = new File("src/" + dataFile + ".txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            int lineNumber = 0;
            while ((line = bufferedReader.readLine()) != null) {
                lineNumber++;
                if(lineNumber == 1) {
                    //First Line:
                    //Reading the number of vertices
                    numberOfVertices = Integer.parseInt(line);
                }
                else if(lineNumber == 2){
                    //Second Line:
                    //Reading vertices weights and creating objects of vertex
                    String[] parsing = line.split(",");
                    for(int i=0 ; i < numberOfVertices ; i++){
                        int id = i+1;
                        double weight = Double.parseDouble(parsing[i]);
                        vertices.add(new Vertex( id , weight ));
                    }
                }else if(lineNumber == 3){
                    //Third Line:
                    //Reading number of subsets
                    numberOfSubsets = Integer.parseInt(line);
                }else{
                    // Line >= 4
                    //Reading each subset
                    String[] parsing = line.split(",");
                    int subsetId = lineNumber - 3;
                    SubSet subSet = new SubSet(subsetId);
                    for (String aParsing : parsing) {
                        int vertexId = Integer.parseInt(aParsing);
                        subSet.addVertex(Utilities.getVertexFromVerticesListById(vertexId, vertices));
                    }
                    subSets.add(subSet);
                }
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        params[0] = numberOfVertices;
        params[1] = numberOfSubsets;
    }
}
