package api;

import java.util.ArrayList;
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
        if(!this.edge.get(src).containsKey(dest)&&this.node.containsKey(src)&&this.node.containsKey(dest)){
            EdgeData newEdage=new Edge(src,dest,w);
            this.edge.get(src).put(dest,newEdage);
            this.mc++;
        }
    }

    @Override
    //check this function
    public Iterator<NodeData> nodeIter() {
        return this.node.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
       ArrayList<EdgeData> newList=new ArrayList<EdgeData>();
        for(int i:this.edge.keySet()){
            for(int j:this.edge.get(i).keySet())
                newList.add(this.edge.get(i).get(j));
        }
        return newList.iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return this.edge.get(node_id).values().iterator();
    }

    @Override
    public NodeData removeNode(int key) {
        if(this.node.containsKey(key)){
            this.edge.get(key).remove(key);
            for(int i:this.edge.keySet()){
                this.removeEdge(i,key);
            }
            this.mc--;
            return this.node.remove(key);
        }
        return null;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        if(!this.edge.get(src).containsKey(dest)) {
            return null;
        }
        this.mc--;
        return this.edge.get(src).remove(dest);
    }

    @Override
    public int nodeSize() {
        return this.node.size();
    }

    @Override
    public int edgeSize() {
        int count=0;
        for(int i:this.edge.keySet()) {
            count += this.edge.get(i).size();
        }
        return count;
    }

    @Override
    public int getMC() {
        return this.mc;
    }
}
