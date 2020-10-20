### The system comprises of the following classes:

1.	risingCity: This is the main class which contains the main method. This class contains the application logic. All the functionalities is explained in main method section.
2.	MinHeap: This class implements the Min Heap data structure that accepts data of any type and is used to store the building records (executed_time is used for comparisons)
3.	Building: This class defines the building structure with attributes buildingNum, executed_time, total_time
4.	RedBlackTree: This class is for implementation of the red black tree data structure and accepts data of any type and is used to store building records 
5.	RedBlackNode: This class is nested within RedBlackTree class, and defines node structure and constructor for initializing the node attributes for RedBlackTree structure (buildingNum is used for comparisons)
6.	ComparatorbuildingNum: This class implements the comparator class for custom compare function on the basis of building no. and this function will be passed RedBlackTree data structure
7.	Comparatorexecutedtime: This class creates defines the attributes and methods for the PrintRange task.
___

### Project Structure:

#### 1.	Class risingCity(risingCity.java)

This is the main class which contains the main method. This class contains the application logic. All  the functionalities is explained in main method section.

This class has 9 member variables:
  ..1.   public static int globalTime = 0 (global timer)
2.	public static boolean work = true (used to check if we have more work/commands or not)
3.	MinHeap<Building> buildingRecord: a heap to store the building records (initialized in constructor)
4.	RedBlackTree<Building> buildingRecordByBuilding: red black tree for storing the building record on the basis of building number ((initialized in constructor)
5.	BufferedReader br: handle for opened input file, used to read lines (initialized in constructor)
6.	BufferedWriter writer: handle for opened output file, used to write output (initialized in constructor)
7.	String command: set to the read line
8.	boolean hasMoreCommands: set to false when there are no more lines to be read from input file 
9.	Integer nextCommandTime: set to the time for the command that need to be executed (initialized in constructor)

##### Constructor:  

•	public class risingCity
Function Name: risingCity
Argument: int capacity, BufferedReader br, BufferedWriter writer
Description: Parametrized Constructor for risingCity class and will initialize thebuildingRecord(heap) with passed capacity and Comparatorexecitedtime, buildingRecordByBuiling(red black tree) with comparator ComparatorbuildingNum

##### Methods:

•	public void readLine ()
Function Name: readLine
	Argument: None
Description: This function will the read the next line from the input file and if no more     commands available set the hasMoreCommands to false
	Return: None

•	public void incrementCounter ()
	Function Name: incrementCounter
	Argument: None
	Description: This function will Increment global counter and call executeCommand
function if hasMoreCommands = true
	Return: None

	
•	public void executeCommand ()
	Function Name: executeCommand
	Argument: None
Description: This function will parse the command and execute commands(insert/print) if   command time matches global time
	Return: None


•	public void startWork ()
	Function Name: startWork
	Argument: None
Description: This function is responsible for the workflow. It will continue to work until there are no more commands and no buildings available for construction (will pick buildings from heap 1 by 1) (will call incrementCounter function)
	Return: None
	 
•	public void write (String str)
	Function Name: write
	Argument: String str
	Description: This function will write str to opened output_file.txt
	Return: None
	 
•	public void insert (int buildingNum, int total_time)
	Function Name: insert
	Argument: int buildingNum, int total_time
Description: This function insert building with building no as buildingNum and total time as total_time into heap and Red black tree
	Return: None
	 
•	public void print (int buildingNum)
	Function Name: print
	Argument: int buildingNum
Description: This function prints the triplet building no, executed_time, total_time of the building whose building no = buildingNum, if there is no building available then it will print (0,0,0)
	Return: None
	 
•	public void print (int buildingNum1, int buildingNum2)
	Function Name: print
	Argument: int buildingNum1, int buildingNum2
Description: This function print the triplet building no, executed_time , total_time of all the building whose building no is in between buildingNum1, buildingNum2 if there is no building available then it will print (0,0,0)
	Return: None


##### public static void main (String args[]) 
Description:

This is the driver function, which receives command line arguments, open the passed input file for reading and open the output_file.txt for writing the output. Then it creates the object for risingCity and call its startWork function which will do all the work. 

#### Brief Working:

1.	A work flag defined. Application will keep on working while this flag is true. This flag is set to false when there are no more commands to read and there are building to work upon in the heap. 

2.	First command is read and executed when its time matches the global timer.

3.	Then code enters into the while loop and this loop will keep on running until work flag is false.

4.	In each iteration, it will check heap size is greater than 0. If yes, then it gets minimum value from heap to work on building.

5.	It than increment executed_time by min (5, remaining time of building). Then call the updateKey or extractMi (If work is completed) function for the heap. This update is done in advance because if there are insert commands while work is being done on the building than the index might change of the current building.

6.	Then reset the execute_time of the building to old value (but won’t call the updateKey function and increment sequentially with each day. So, when print commands comes, it will print the correct value building records triplet.)  Also, we call the incrementCounter function on each day building construction.

7.	incrementCounter function will increment the global counter and if hasMoreCoomands flag is true then call the executeCommand function.

8.	Execute command function will compare the current command time of current with global time If it is equal to the global time, then it executes the command also call readLine function.

9.	ReadLine function will read the next line from input file and fetch time and set the current command time and currentCommand member variables. If this is the last line, then it also set the hasMoreCommands = false.

10.	Then it will return to work loop, checks if current building work is completed then it writes the buildingNum and global time to output file and delete the building record from red black tree structure by calling the red black tree’s delete function.

11.	If heap size is 0 then it goes to else if black and checks if hasMoreCommand = true, then it calls the incrementCounter function.

#### 2 Class Building (Building.java)

Contains the attributes and constructors to create a node of building. Each building node comprises of the following three attributes: buildingNum, executed_time, and total_time.

This class has 3 instance variables:
1.	int buildingNum: set the building number (initialized in constructor)
2.	int executed_time: set to the executed time (initialized in constructor)
3.	int total_time: set to the total time (initialized in constructor)

##### Constructor:

•	public Building (int buildingNum, int total_time)
Function Name: Building
Argument: int buildingNum, int total_time
Description: Initialize the member variables buildingNum, total_time to passed value and executed_time to 0 


#### 3.	Class ComparatorbuildingNum

This class is defined in Building.java. This class implement the interface Comparator, and override the compare function, so that custom compare function can be used in Red Black tree for comparisons as I added the supports for generic data type.

Methods:

•	public int compare (Building a, Building b)
Function Name: compare 
Argument: None 
Description: Comparator function for comparing the Building Class Object on the basis of building number
Return: int (-1,0,1)  


#### 4.	Class Comparatorexecutedtime

This class is defined in Building.java. This class implement the interface Comparator, and override the compare function, so that custom compare function can be used in Min Heap from comparisons as I added the supports for generic data type.

Methods:

•	public int compare (Building a, Building b)
Function Name: compare 
Argument: None 
Description: Comparator function for comparing the Building Class Object on the basis of executed time and if same executed time than on the basis of building number
Return: int (-1,0,1)  


#### 5. Class MinHeap<T> (MinHeap.java)

This is the class that implements Min Heap data structure and all the heap functions and accept data of generic class type <T>. In order to use this data structure, we have to pass comparator functions for comparing the data. In application it used to store the building data and executed_time is used for comparing the data and if executed_time is same for two data than data with small building number is considered as small.

This class has 4 instance variables:
1.	private int size: Current size of heap (initialized in constructor)
2.	private int capacity: total capacity of heap (initialized in constructor)
3.	 private T[] hArray : array of T objects and objects stored according to the heap property (initialized in constructor)
4.	 Comparator<T> comp: Comparator function for comparing the objects of T type (initialized in constructor)

Constructor:

•	public MinHeap (int capacity, Comparator<T> comp) 
Argument: int capacity, Comparator<T> comp, comparator will be used for comparing the inserted data. 
Description: Used to initialize the min heap

Methods:

•	public void insert (T key)
	Function Name: insert
	Argument: T key
Description: This function will insert the given key (type can be anything, as this class supports generic type<T>) and bottom up heapify the heap
	Return: None
	
•	public T getMin ()
	Function Name: getMin
	Argument: None
	Description: This function will return the minimum value, the root of heap
	Return: type T
	
•	public T extractMin ()
	Function Name: extractMin
	Argument: None
Description: This function will remove the minimum (root element) value and return it and heapify for correcting the heap
	Return: type T
	
•	public void updateKey (T key, int index)
	Function Name: updateKey
	Argument: T key, int index
Description: This function will update the key at given index with provided key and perform heapification
	Return: none
		
•	private void bottomupHeapify (int index)
	Function Name: bottomupHeapify
	Argument: int index
Description: This function will start the heapification from the given index and will continue up till the root 
	Return: none
	
•	private void topdownHeapify (int index)
	Function Name: topdownHeapify
	Argument: int index
Description: This function will start the heapification from the given index and will continue down till the leaf elements 
	Return: none
	 
•	private int getSize ()
	Function Name: getSize
	Argument: None
	Description: This function will return the size of Heap 
	Return: int


#### 6.	Class RedBlackTree<T> (RedBlackTreeStructure.java)

This class contains the implementation of red black tree data structure and it accepts the data of any class type as I added the support for generic types<T>.

This class has 3 instance variables:
1.	RedBlackNode<T> root:  it is set to the root node of red black tree and null if tree is empty
2.	Comparator<T> comp:  comparator function used to compare the data stored in node.
3.	RedBlackNode<T> newNode:  this is value set to the newly inserted node. This is value is needed when balancing is done.




Methods:

•	public void insert int key, T data)
	Function Name: insert
	Argument: int key, T data
Description: This function will insert the provided key, data of any type (Generic T) in to tree and do the appropriate balancing. This function is just a wrapper for the actual insert function
	Return: None
	 
•	private void doBalancing (RedBlackNode<T> x)
	Function Name: doBalancing
	Argument: RedBlackNode<T> x
Description: This function will do the balancing of tree after inserting and starting from     the given node x and continue the balancing up the tree until it reaches root or tree is balanced   
	Return: RedBlackNode<T>
	 
•	private void llRotation (RedBlackNode<T> x)
	Function Name: llRotation
	Argument: RedBlackNode<T> x
	Description: This function will do the LL Rotation from the given node x 
	Return: None
	 
•	private void rrRotation (RedBlackNode<T> x)
	Function Name: rrRotation
	Argument: RedBlackNode<T> x
	Description: This function will do the RR Rotation from the given node x 
	Return: None
	 
•	private void lrRotation (RedBlackNode<T> x)
	Function Name: lrRotation
	Argument: RedBlackNode<T> x
	Description: This function will do the LR Rotation from the given node x 
	Return: None
	 
•	private void rlRotation (RedBlackNode<T> x)
	Function Name: rlRotation
	Argument: RedBlackNode<T> x
	Description: This function will do the RL Rotation from the given node x 
	Return: None
	 
•	public List<T> findElement (int key1, int key2)
	Function Name: findElement
	Argument: int key1, int key2
Description: This function will find and return list of red black nodes whose key lies between key1 and key2 (both including) and will return the list of nodes
	Return: List<T>
	 
•	public List<T> findElement (int key1)
	Function Name: findElement
	Argument: int key1
Description: This function will find and return list of red black node key equal to the given key1 
	Return: List<T>
	 
•	public void delete (int key)
	Function Name: delete
	Argument: int key
	Description: This function will delete the node with given key
	Return: Node
	 
•	private void deleteFixup(RedBlackNode<T> node)
	Function Name: deleteFixup
	Argument: RedBlackNode<T> node
Description: This function will start fixing the tree after deletion from given node and continue up the tree until deficiency is removed or it reaches the root node 
	Return: None
	 
•	private RedBlackNode<T> inorderSuccessor(RedBlackNode<T> node)
	Function Name: inorderSuccessor
	Argument: RedBlackNode<T> node
Description: This function will find the in order successor (minimum element in right subtree of node) of given node  
	Return: RedBlackNode<T>
	 

#### 	7.  Class RedBlackNode

It is nested class in RedBlackTree. It defines the red black tree node structure and constructors to create the node with input arguments. This class accepts argument of generic type <T>. So, any type of data can be stored in the node. 

Constructors:

•	public RedBlackNode (int key , RedBlackNode<T> parent ,  T data, int color)
Function Name: RedBlackNode
Argument: int key, RedBlackNode<T> node
Description: Constructor for initialization of Red Black Tree
		
•	public RedBlackNode ()
Function Name: RedBlackNode
Argument: None
Description: Constructor for creating the Nil Nodes (External Nnodes) of Red Black Tree



Following is the snapshot from CISE server, make is used to compiles the java files. Then execute it using java risingCiy filename, code generates the output_file.txt, I have shown the sample input and output
