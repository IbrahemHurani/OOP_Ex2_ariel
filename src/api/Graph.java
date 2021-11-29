package api;

import java.util.Iterator;
import java.util.HashMap;
public class Graph implements DirectedWeightedGraph{
     private   HashMap<Integer,NodeData> node;
     private HashMap<Integer,HashMap<Integer,EdgeData>> edge;
     private int mc;
    public Graph(){
        this.node=new HashMap<>();
        this.edge=new HashMap<>();
        this.mc=0;
    }
    @Override
    public NodeData getNode(int key) {
        return this.node.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        return this.edge.get(src).get(dest);
    }

    @Override
    public void addNode(NodeData n) {
        if(!this.node.containsKey(n.getKey())){
            this.node.put(n.getKey(),n);
            this.edge.put(n.getKey(),new HashMap<>());
            this.mc++;
        }
    }

    @Override
    public void connect(int src, int dest, double w) {

    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return this.edge.get(node_id).values().iterator();
    }

    @Override
    public NodeData removeNode(int key) {
        return null;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        return null;
    }

    @Override
    public int nodeSize() {
        return this.node.size();
    }

    @Override
    public int edgeSize() {
        return 0;
    }

    @Override
    public int getMC() {
        return this.mc;
    }
}
