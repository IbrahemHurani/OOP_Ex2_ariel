package core;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Graph_Algorithms implements api.DirectedWeightedGraphAlgorithms{
    private DirectedWeightedGraph graph;
    final int INFINITE = Integer.MAX_VALUE;

    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph=g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        if(graph==null)
            return null;
        DirectedWeightedGraph NewCopy=new Graph();
        Iterator<NodeData> node=this.graph.nodeIter();
        while(node.hasNext()){
            NodeData n=node.next();
            NewCopy.addNode(n);
        }
        while(node.hasNext()){
            Iterator<EdgeData> edge=this.graph.edgeIter(node.next().getKey());
            if(edge!=null){
                while(edge.hasNext()){
                    NewCopy.connect(edge.next().getSrc(),edge.next().getDest(),edge.next().getWeight());
                }
            }
        }


        return NewCopy;
    }


    @Override
    public boolean isConnected() {
        if(this.graph.nodeSize()==0) {
            return false;
        }
         boolean[] marked=new boolean[this.graph.nodeSize()];
        for(int j=0;j<marked.length;j++){
            marked[j]=false;
        }
         Iterator<NodeData> node=this.graph.nodeIter();
         while(node.hasNext()) {
             int key = node.next().getKey();
             Iterator<EdgeData> edgeForNode = this.graph.edgeIter(key);
             if (edgeForNode != null) {
                 marked[key]=true;
                 while (edgeForNode.hasNext()) {
                     int dest = edgeForNode.next().getDest();
                     marked[dest]=true;
                 }
             }
         }
         for(boolean i:marked){
             if(!i){
                 return false;
             }
         }
        return true;

    }

    @Override
    public double shortestPathDist(int src, int dest) {
        Iterator<NodeData> vertex = this.graph.nodeIter();
        if (this.graph.getNode(src)==null||this.graph.getNode(dest)==null) {
            return -1;
        }
        while(vertex.hasNext()) {
            NodeData v=vertex.next();
            v.setTag(0);
            v.setInfo("");
            v.setWeight(INFINITE);
        }
        this.graph.getNode(src).setWeight(0);
        for (int i = 1; i <= this.graph.nodeSize();) {
            int min = findMinNode();
            Iterator<EdgeData> edge = this.graph.edgeIter(this.graph.getNode(min).getKey());
            if (edge != null) {
                while(edge.hasNext()) {
                    EdgeData e=edge.next();
                    double currentWeight = this.graph.getNode(e.getDest()).getWeight();
                    double srcPlusEdge = this.graph.getNode(min).getWeight() + e.getWeight();
                    if (srcPlusEdge < currentWeight) {
                        this.graph.getNode(e.getDest()).setWeight(srcPlusEdge);
                        this.graph.getNode(e.getDest()).setInfo(this.graph.getNode(min).getInfo() + min + ",");
                    }
                }
            }
            this.graph.getNode(min).setTag(1);
            i++;
        }
        return this.graph.getNode(dest).getWeight();

    }
    private  int findMinNode() {
        Iterator<NodeData> vertex = this.graph.nodeIter();
        double minWeight = INFINITE;
        int id = 1;
        while(vertex.hasNext()) {
            NodeData v=vertex.next();
            if (v.getWeight() <= minWeight && (v.getTag() == 0)) {
                minWeight = v.getWeight();
                id = v.getKey();
            }
        }
        return id;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        List<NodeData> sPath = new ArrayList<NodeData>();
        double path = shortestPathDist(src, dest);
        if(path==-1){
            return null;
        }
        String ans = this.graph.getNode(dest).getInfo();
        ans += this.graph.getNode(dest).getKey();
        String[] node = ans.split(",");
        for (int i = 0; i < node.length; i++) {
            int key = Integer.parseInt(node[i]);
            sPath.add(this.graph.getNode(key));
        }
        return sPath;
    }
    @Override
    public NodeData center() {
        if(!isConnected())
          return null;
        double min=Double.MAX_VALUE;
        double sum=0;
        NodeData centerNode=null;
        int size=this.graph.nodeSize();
        Iterator<NodeData> n1=this.graph.nodeIter();
        while(n1.hasNext()){
            NodeData firstNode=n1.next();
            Iterator<NodeData> n2=this.graph.nodeIter();
            while(n2.hasNext()){
                NodeData secondNode=n2.next();
                sum+=shortestPathDist(firstNode.getKey(),secondNode.getKey()/size);
            }
            if(sum<min){
                min=sum;
                centerNode=firstNode;
            }
        }
        return centerNode;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
       return null;
    }


    @Override
    public boolean save(String file) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(graph);
        try {
            PrintWriter w = new PrintWriter(new File(file));
            w.write(json);
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public boolean load(String file) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Graph.class, new GraphJson());
        Gson gson = builder.create();
        try {
            FileReader f = new FileReader(file);
            DirectedWeightedGraph loadedGraph = gson.fromJson(f,Graph.class);
            this.init(loadedGraph);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    }


