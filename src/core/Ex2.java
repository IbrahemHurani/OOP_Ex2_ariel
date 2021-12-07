package core;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.GeoLocation;
import api.NodeData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class is the main class for core.Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) throws IOException, ParseException {
        DirectedWeightedGraph ans = new Graph();
        JSONParser parser=new JSONParser();
        JSONObject ob= (JSONObject) parser.parse(new FileReader(json_file));
        JSONArray edge= (JSONArray) ob.get("Edges");
        JSONArray node=(JSONArray) ob.get("Nodes");
        for(Object j:edge){
            JSONObject o=(JSONObject) j;
            int src=Integer.parseInt(o.get("src").toString());
            double weight=Integer.parseInt(o.get("w").toString());
            int dest=Integer.parseInt(o.get("dest").toString());
            ans.connect(src,dest,weight);
        }
        for(Object i:node){
            JSONObject o=(JSONObject) i;
            String str=o.get("pos").toString();
            String [] Xyz=str.split(",");
            GeoLocation geo=new Glocation(Integer.parseInt(Xyz[0]),Integer.parseInt(Xyz[1]),Integer.parseInt(Xyz[2]));
            NodeData n=new Node(Integer.parseInt(o.get("id").toString()),geo);
            ans.addNode(n);
        }
        
        return ans;
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) throws IOException, ParseException {
        DirectedWeightedGraph g=getGrapg(json_file);
        DirectedWeightedGraphAlgorithms ans = new Graph_Algorithms();
        ans.init(g);
        return ans;
    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) throws IOException, ParseException {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        // ****** Add your code here ******
        //
        // ********************************
    }



    }
