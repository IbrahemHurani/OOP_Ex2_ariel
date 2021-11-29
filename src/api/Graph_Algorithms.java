package api;

import java.util.Iterator;
import java.util.List;

public class Graph_Algorithms implements DirectedWeightedGraphAlgorithms{
     DirectedWeightedGraph graph;

     public Graph_Algorithms(){
         this.graph=new Graph();
     }
    @Override
    public void init(DirectedWeightedGraph g) {
        Iterator<NodeData> firstClean=this.graph.nodeIter();
        while(firstClean.hasNext()){
            this.graph.removeNode(firstClean.next().getKey());
        }
        Iterator<NodeData> inputNode=g.nodeIter();
        while(inputNode.hasNext()){
            this.graph.addNode(inputNode.next());
        }
        Iterator<EdgeData> inputEdage=g.edgeIter();
        while(inputEdage.hasNext()){
            this.graph.connect(inputEdage.next().getSrc(),inputEdage.next().getDest(),inputEdage.next().getSrc());
        }
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return null;
    }

    @Override
    public DirectedWeightedGraph copy() {
        return null;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
    }

    @Override
    public NodeData center() {
        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {
        return false;
    }
}
