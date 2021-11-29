package api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {
    GeoLocation g=new Glocation(1.5,4.1,2.3);
    GeoLocation g2=new Glocation(2.0,4.5,7.1);
    NodeData node=new Node(2,g);
    @Test
    void getKey() {
        assertEquals(node.getKey(),2);
        assertNotEquals(node.getKey(),5);
    }

    @Test
    void getLocation() {
       
    }

    @Test
    void setLocation() {

    }

    @Test
    void getWeight() {
    }

    @Test
    void setWeight() {
    }

    @Test
    void getInfo() {
    }

    @Test
    void setInfo() {
    }

    @Test
    void getTag() {
    }

    @Test
    void setTag() {
    }
}