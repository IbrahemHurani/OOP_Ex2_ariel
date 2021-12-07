package core;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.Graph;

import java.io.*;
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
        Graph NewCopy=new Graph();
        Iterator<NodeData> node= graph.nodeIter();
        Iterator<EdgeData> edge= getGraph().edgeIter();
        while(node.hasNext()){
            NewCopy.addNode(node.next());
        }
        while(edge.hasNext()){
            NewCopy.connect(edge.next().getSrc(),edge.next().getDest(),edge.next().getDest());
        }
        NewCopy.mc=this.graph.getMC();
        return NewCopy;
    }


    @Override
    public boolean isConnected() {
        if(this.graph.nodeSize()==0) {
            return false;
        }
        boolean []Marked=new boolean[graph.edgeSize()];
        Iterator<NodeData> node=graph.nodeIter();
        while(node.hasNext()){
            int key=node.next().getKey();
            DFS(key,Marked);
            if(!visit(Marked)){
                return false;
            }
        }
    return true;
    }

    private boolean visit(boolean[] marked) {
        for(boolean i:marked){
            if(!i) {
                return false;
            }
        }
        return true;
    }

    private void DFS(int key, boolean[] marked)
    {
        Stack<Integer> KeyNode = new Stack<>();
        marked[key] = true;
        KeyNode.add(key);
        while (!KeyNode.isEmpty()) {
            int id_node = KeyNode.pop();
            Iterator<EdgeData> edgeForNode = graph.edgeIter(id_node);
            while (edgeForNode.hasNext()) {
                EdgeData e = edgeForNode.next();
                if (!marked[e.getDest()]) {
                    marked[e.getDest()] = true;
                    KeyNode.add(e.getDest());
                }
            }
        }

    }

    @Override
    public double shortestPathDist(int src, int dest) {
        Collection<NodeData> vertex = this.graph.getV();
        if (!vertex.contains(graph.getNode(src)) || (!vertex.contains(graph.getNode(dest)))) {
            return -1;
        }
        for (NodeData ver : vertex) {
            ver.setTag(0);
            ver.setInfo("");
            ver.setWeight(INFINITE);
        }
        this.graph.getNode(src).setWeight(0);
        for (int i = 1; i <= this.graph.nodeSize();) {
            int min = findMinNode();
            Collection<EdgeData> edge = this.graph.getE(this.graph.getNode(min).getKey());
            if (edge != null) {
                for (EdgeData e : edge) {
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
    public int findMinNode() {
        Collection<NodeData> vertex = this.graph.getV();
        double minWeight = INFINITE;
        int id = 1;
        for (NodeData ver : vertex) {
            if (ver.getWeight() <= minWeight && (ver.getTag() == 0)) {
                minWeight = ver.getWeight();
                id = ver.getKey();
            }
        }
        return id;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        List<NodeData> shortPath = new ArrayList<NodeData>();
        double path = shortestPathDist(src, dest);
        if (path == 0) {
            return shortPath;
        } else if (path == INFINITE) {
            throw new RuntimeException("No path");
        }
        String ans = this.graph.getNode(dest).getInfo();
        ans += this.graph.getNode(dest).getKey();
        String[] node = ans.split(",");
        for (int i = 0; i < node.length; i++) {
            int key = Integer.parseInt(node[i]);
            shortPath.add(this.graph.getNode(key));
        }
        return shortPath;
    }
    @Override
    public NodeData center() {
        if(!isConnected())
        return null;
        Iterator<NodeData> nodes=this.graph.nodeIter();
        HashMap<Integer,Double> pn=new HashMap<Integer,Double>();
        int key=0;
        double check;
        while(nodes.hasNext()){
            double max=0;
            Iterator<EdgeData> edge=this.graph.edgeIter(nodes.next().getKey());
            while(edge.hasNext()){
                check=shortestPathDist(nodes.next().getKey(),edge.next().getDest());
                if(check>max){
                    max=check;
                    key=nodes.next().getKey();
                }
            }
            pn.put(key,max);
        }
        double max2=0;
        for(Map.Entry<Integer,Double> entry:pn.entrySet()){
            double dist=entry.getValue();
            if(max2<dist){
                max2=dist;
                key= entry.getKey();
            }

        }
        return this.graph.getNode(key);
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        if (cities.size() == 0) return null;

        LinkedList<NodeData> nodesPath = new LinkedList<NodeData>();
        int i = 0;
        NodeData srcNode = cities.get(i++);

        if (cities.size() == 1) {
            nodesPath.add(this.graph.getNode(srcNode.getKey()));
            return nodesPath;
        }
        while (i < cities.size()) {
            NodeData destNode = cities.get(i++);
            if (shortestPath(srcNode.getKey(), destNode.getKey()) == null) return null;
            LinkedList<NodeData> newPath = (LinkedList<NodeData>)shortestPath(srcNode.getKey(), destNode.getKey());
            if (i != 2)
                newPath.remove(newPath.get(0));
            nodesPath.addAll(newPath);
            srcNode = destNode;
        }

        return nodesPath;
    }


    @Override
    public boolean save(String file) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(graph);
        try {
            PrintWriter pw = new PrintWriter(new File(file));
            pw.write(json);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public boolean load(String file) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Graph.class, new GraphJsonDeserializer());
        Gson gson = builder.create();
        try {
            FileReader fr = new FileReader(file);
            DirectedWeightedGraph loadedGraph = gson.fromJson(fr,Graph.class);
            this.init(loadedGraph);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    }

