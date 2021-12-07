package Test;

import core.Glocation;
import core.Graph;
import core.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
        Graph G=new Graph();
        Node[] randomNode;

    @Test
    void getNode() {//can check the function addNode and function getNode
    MakeNode(15);
    int index1=0,index2=0;
    for(Node n:randomNode) {
        G.addNode(n);
        assertEquals(index1++,G.getNode(index2++).getKey());
    }
    }

    @Test
    void getEdge() {

    }

    @Test
    void connect() {
    }

    @Test
    void nodeIter() {
    }

    @Test
    void edgeIter() {
    }

    @Test
    void testEdgeIter() {
    }

    @Test
    void removeNode() {
    }

    @Test
    void removeEdge() {
    }

    @Test
    void nodeSize() {
    }

    @Test
    void edgeSize() {
    }

    @Test
    void getMC() {
    }
    private void MakeNode(int size){
         randomNode=new Node[size];
        for(int i=0;i<size;i++){
            randomNode[i]=new Node(i,new Glocation(i*2,i*1.5,i*5));
        }

    }

}