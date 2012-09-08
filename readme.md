Trains
====================

Tests
------
The solution has been prepared using TDD methodology with a top-down approach. For all significant (non Value Object or main class - TrainApp) classes,
 there is a test checking the basic functionality as well as edge cases.

Design
---------

### Domain model
The domain model consists of `Station`, `Route` and `Graph` classes.

### Graph data structure
Instead of using adjacency matrix, the graph has been implemented by including references to all edges (routes) in each vertex (station).
The `Graph` object serves as an index, providing a lookup method.

### Object decomposition
Object decomposition stems from TDD approach. A design by contract (thru interfaces) has been chosen, to decouple modules and allow easy
 extension.

### Input
The input file path is passed as an argument. If not, then the default location is used.

#### Validation
Graph string definition is validated against a pattern. The correct format of input data is comma delimited XY$, where X and Y are station symbols
 and $ is the distance between them.

#### Assumptions and limitations

1. It's assumed that the station symbols are one-character long. 
2. The distance can be multi-digit, with 2^31-1 being the maximum value, however Dijkstra algorithm sums the distances up, so it is
 assumed that the distances and number of station is small enough not to cause overflow. For bigger distances a **`long`** should be used.

### Algorithm

#### Finding route combinations

For both - by distance and by stops count - a recursive approach has been chosen. For two 'by stops' methods, the same recursive 
routine has been used, with an additional parameter determining whether it's the exact
amount of stops or less than. The additional parameter is a trade off for avoiding copy-paste code and using separate methods.

#### Finding shortest route

For its simplicity, Dijkstra algorithm has been chosen. A modification has been made to the classic version of the algorithm, to allow finding
a round trip. During relaxation stage, if one of the neighbors is the desired (source) station, add it back to the unvisited queue.  
For the unvisited queue, the `PriorityQueue` has been used, to facilitate the ordering. 

### Exception handling

Application throws a custom `MissingRouteException` in case a station or a route does not exist. The `GraphController` handles returning a
null response and continuing execution. A `RuntimeException` will be thrown for both - missing graph file and incorrect format. Those exceptions
are not recoverable and cause program termination.  

### Possible improvements

1. Defining queries through a file or other input.
2. Multi-character station labels
3. Reading graph from a database (i.e. neo4j)
4. Adding logging with log4j or slf4j

Running
---------------------
JDK version: **1.7.0\_04**	 _(I used NIO.2, which requires Java 7)_

### Maven
mvn exec:java -Dexec.mainClass="com.danielsiwiec.trains.TrainApp" -Dexec.args= _graphFileLocation_

e.g.

mvn exec:java -Dexec.mainClass="com.danielsiwiec.trains.TrainApp" -Dexec.args="src/main/resources/graph.txt"

### Directly wih Java
java com.danielsiwiec.trains.TrainApp _graphFileLocation_

e.g.

java com.danielsiwiec.trains.TrainApp /home/daniel/workspaces/eclipse/thoughtworks/trains/src/main/resources/graph.txt


Results
--------

### Output for sample input
	Output #1: 9
	Output #2: 5
	Output #3: 13
	Output #4: 22
	Output #5: NO SUCH ROUTE
	Output #6: 2
	Output #7: 3
	Output #8: 9
	Output #9: 9
	Output #10: 7

### Test results
	-------------------------------------------------------
	 T E S T S
	-------------------------------------------------------
	Running TestSuite
	Tests run: 34, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.587 sec
	
	Results :
	
	Tests run: 34, Failures: 0, Errors: 0, Skipped: 0