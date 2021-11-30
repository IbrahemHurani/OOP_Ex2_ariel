package api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
public class Graph implements DirectedWeightedGraph{
        HashMap<Integer,NodeData> node;
        ArrayList<EdgeData> edge;
        HashMap<Integer,HashMap<Integer,EdgeData>> CompleteGraph;
         int mc;
    public Graph(){
        this.node=new HashMap<>();
        this.edge=new ArrayList<>();
        this.CompleteGraph=new HashMap<>();
        this.mc=0;
    }
    @Override
    public NodeData getNode(int key) {
        if(this.node.containsKey(key)) {
            return this.node.get(key);
        }
        return null;
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
      if(this.CompleteGraph.containsKey(src)&&this.CompleteGraph.get(src).containsKey(dest)){
          return this.CompleteGraph.get(src).get(dest);
      }
      return null;
    }

    @Override
    public void addNode(NodeData n) {
        if(!this.node.containsKey(n.getKey())){
            this.node.put(n.getKey(),n);
            this.CompleteGraph.put(n.getKey(),new HashMap<>());
            this.mc++;
        }
        System.out.println("this Node already exists");
    }

    @Override
    public void connect(int src, int dest, double w) {
        if(this.CompleteGraph.containsKey(src)&&!this.CompleteGraph.get(src).containsKey(dest)){
            EdgeData NewEdage= new Edge(src,dest,w);
            this.edge.add(NewEdage);
            this.CompleteGraph.get(src).put(dest,NewEdage);
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
        return this.edge.iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        if(this.CompleteGraph.containsKey(node_id)){
            return this.CompleteGraph.get(node_id).values().iterator();
        }
        return null;
    }

    @Override
    public NodeData removeNode(int key) {
        if(this.CompleteGraph.containsKey(key)){
            Iterator<EdgeData> RemoveAllEdges=edgeIter(key);
            while(RemoveAllEdges.hasNext()){
                EdgeData RemoveOneEdge=RemoveAllEdges.next();
                removeEdge(RemoveOneEdge.getSrc(),RemoveOneEdge.getDest());
            }
            this.CompleteGraph.remove(key);
            NodeData NodeWantRemove=this.node.get(key);
            this.node.remove(key);
            return NodeWantRemove;
        }
        return null;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
            if(this.CompleteGraph.containsKey(src)){
                EdgeData RemoveEdage=this.CompleteGraph.get(src).get(dest);
                this.edge.remove(RemoveEdage);
                this.CompleteGraph.get(src).remove(dest);
                return RemoveEdage;
            }
            return null;
    }

    @Override
    public int nodeSize() {
        return this.node.size();
    }

    @Override
    public int edgeSize() {
        return this.edge.size();
    }

    @Override
    public int getMC() {
        return this.mc;
    }
}
