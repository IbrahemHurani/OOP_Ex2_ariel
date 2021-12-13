##  Directed Weighted Graph
![images](https://user-images.githubusercontent.com/86603326/145286722-dcdbf181-97f5-4f8c-9db1-57e1cc49047b.jpg)

an introduction-->[Graph](https://en.wikipedia.org/wiki/Directed_graph)

## My Algorithm:
In this algorithm we make an implementation of the graph with

packge api:
----
In this packge we have all the interfaces for Graph.
1)interface NodeData

2)interface EdageData

3)interface GeoLocation

4)interface DirectedWeightedGraph

5)interface DirectedWeightedGraphAlgorithms

package Core:
---
1)Class Node:this class implements NodeData interface.
                                                                    
2)Class Edge:this class implements EdgeData Interface.

3)Class Graph:this class implements DirectedWeightedGraph interface.

4)Class Graph_Algorithms:this class implements DirectedWeightedGraphAlgorithms interface.

5)Class Glocation:this class implements GeoLocation interface.

6)Class Ex2:this class is the main, read form File json and input all the information in the Variables and run the GUI for the graph.


# Time analysis for function in class Graph_algorithms:

|Name Function|Node numbers with averge edge for node in/out| Time for this function|
|-------------|---------------------------------------------|-----------------------|
|shortestpath |  100 Nodes and averges 20 for one node      |    125 ms             |   
|center       |  100 Nodes and averges 20 for one node      |    47 ms              |
|Tsp          |  100 Nodes and averges 20 for one node      |    31 ms              |
|isConnected  |  100 Nodes and averges 20 for one node      |    47 ms              |
|shortestpathD|  100 Nodes and averges 20 for one node      |    10 ms              |


|Name Function|Node numbers with averge edge for node in/out| Time for this function|
|-------------|---------------------------------------------|-----------------------|
|shortestpath |  1000 Nodes and averges 20 for one node     |    256 ms             |   
|center       |  1000 Nodes and averges 20 for one node     |    890 ms             |
|Tsp          |  1000 Nodes and averges 20 for one node     |    250 ms             |
|isConnected  |  1000 Nodes and averges 20 for one node     |    749 ms             |
|shortestpathD|  1000 Nodes and averges 20 for one node     |    32 ms              |

|Name Function|Node numbers with averge edge for node in/out| Time for this function|
|-------------|---------------------------------------------|-----------------------|
|shortestpath |  10000 Nodes and averges 20 for one node    |    3 sec 343ms        |   
|center       |  10000 Nodes and averges 20 for one node    |    47 sec 731ms       |
|Tsp          |  10000 Nodes and averges 20 for one node    |    14 sec 420 ms      |
|isConnected  |  10000 Nodes and averges 20 for one node    |    47 sec             |
|shortestpathD|  10000 Nodes and averges 20 for one node    |    2 sec 400 ms       |

with 10.000 Nodes with 20 edges for one node the timeout and the same thing with 1.000.000 nodes.











