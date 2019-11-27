package ads.datastructure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RedBlackTree<T> {

	RedBlackNode<T> root;
	Comparator<T> comp;
	RedBlackNode<T> newNode ;
	

	public RedBlackTree(Comparator<T> comp) {
		this.comp = comp;
	}

	/***********************
	Function Name: insert
	Argument: int key , T data
	Description: This function will insert the provided key, data of any type(Generic T) in to tree
				 and do the appropriate balancing. This function is just a wrapper for the actual insert function
	Return: None
	 ***********************/
	public void insert( int key , T data)
	{
		if( this.root == null || this.root.isLeafNode)
		{
			//root is black node
			this.root = new RedBlackNode<T>(key , null ,  data , 0) ;
			this.newNode = this.root ;
			return ;
		}
		
		RedBlackNode<T> insertedNode = insert( root , key ,  data ) ;
		doBalancing( this.newNode ) ;
		
//		if( validateRedBlackTree(this.root) )
//		{
//			System.out.println("Valid Insert");
//		}
//		else
//		{
//			System.out.println("InValid Insert");
//		}
		//preorderTraversal(this.root);
		//System.out.println();
		
		//insert()
	}

	
	/***********************
	Function Name: insert
	Argument: RedBlackNode<T> root, int key , T data
	Description: This function will insert the provided key, data of any type(Generic T). This is a recursive function
				 , it recursively traverse the tree and finds the correct position for the data and then insert in the tree
	Return: RedBlackNode<T>
	 ***********************/
	public RedBlackNode<T> insert(RedBlackNode<T> root, int key , T data) {
		if (root.isLeafNode == true) {
			// all new inserted nodes are red node
			RedBlackNode<T> newNode =  new RedBlackNode<T>(key , root.parent ,  data, 1);
			this.newNode =  newNode;
			return newNode ;
			
		}

		if (comp.compare((T) root.data, data) > 0) {
			root.left = insert(root.left, key,  data);
		} else if( comp.compare((T) root.data, data) < 0) {
			root.right = insert(root.right, key, data);
		}
		else
		{
			System.out.println("Building Number " + key + " already exists, stopping the program");
			System.exit(0); 
		}
		
		
		return root;
	}

	
	/***********************
	Function Name: doBalancing
	Argument: RedBlackNode<T> x
	Description: This function will do the balancing of tree starting from the given node x and
				 continue the balancing up the tree until it reaches root or tree is balanced   
	Return: RedBlackNode<T>
	 ***********************/
	public void doBalancing(RedBlackNode<T> x)
	{
		if( x ==  this.root)
			return;
		
		//do balancing if parent color is also red
		if(x.parent.color == 1)
		{
			
			
		   // change grandparent color to red
			//x.parent.parent.color = 1 ;
			
			RedBlackNode<T> parent = x.parent ;
			RedBlackNode<T> grandParent = x.parent.parent ;
			
			// parent color will be chnaged to black color in all cases
			parent.color = 0 ;
			
			if( x == parent.left && parent == grandParent.left)
			{
				//check for LLb case
				if(  grandParent.right.color == 0 ) 
				{
					//change grandparent color to red
					grandParent.color = 1 ;
					llRotation(x.parent) ;
				}
				else
				{
					//LLr case
					
		
					//Color flip to black for x's grandparent right child
					grandParent.right.color = 0 ;
					
					if( grandParent != this.root )
					{
						// Chnage grandparent color to red if it is not root
						grandParent.color = 1 ;
						
						//Continue do balancing upwards
						doBalancing( grandParent ) ;
						
					}
				}
			}
			else if ( x == parent.right && parent == grandParent.left )
			{
				//check for LRb case
				if(  grandParent.right.color == 0  )
				{
					//change grandparent color to red
					grandParent.color = 1 ;
					parent.color = 1 ;
					x.color = 0 ;
					lrRotation(x) ;
				}
				else
				{
					//LRr case
					
					//Color flip to black for x's grandparent right child
					grandParent.right.color = 0 ;
					
					if( grandParent != this.root )
					{
						// Chnage grandparent color to red if it is not root
						grandParent.color = 1 ;
						
						//Continue do balancing upwards
						doBalancing( grandParent ) ;
						
					}
				}
			}
			else if ( x == parent.right && parent == grandParent.right )
			{
				//check for RRb case
				if(  grandParent.left.color == 0  )
				{
					//change grandparent color to red
					grandParent.color = 1 ;
					rrRotation(x.parent) ;
				}
				else
				{
					//RRr vase
					//Color flip to black for x's grandparent right child
					grandParent.left.color = 0 ;
					
					if( grandParent != this.root )
					{
						// Chnage grandparent color to red if it is not root
						grandParent.color = 1 ;
						
						//Continue do balancing upwards
						doBalancing( grandParent ) ;
						
					}
				}
			}
			else if ( x == parent.left && parent == grandParent.right )
			{
				//check for RLb case
				if(  grandParent.left.color == 0  )
				{
					//change grandparent color to red
					grandParent.color = 1 ;
					parent.color = 1 ;
					x.color = 0 ;
					rlRotation(x) ;
				}
				else
				{
					//Color flip to black for x's grandparent right child
					grandParent.left.color = 0 ;
					
					if( grandParent != this.root )
					{
						// Chnage grandparent color to red if it is not root
						grandParent.color = 1 ;
						
						//Continue do balancing upwards
						doBalancing( grandParent ) ;
						
					}
				}
			}
		}
	}
	
	/***********************
	Function Name: llRotation
	Argument: RedBlackNode<T> x
	Description: This function will do the LL Rotation from the given node x 
	Return: None
	 ***********************/
	public void llRotation(RedBlackNode<T> x) {
		// save x parents
		RedBlackNode<T> parent = x.parent;

		

		// Change the root pointer , if parent is the root
		if( x.parent == this.root)
		{
			this.root = x ;
		}
		else
		{
			//check if parent is left child
			if( parent.parent.left == parent)
			{
				parent.parent.left = x ;
			}
			else
			{
				// else right child
				parent.parent.right = x ;
			}
		}
		
		// changing the parent of x
		x.parent = parent.parent;
		
		
		
				
		//if (x.right != null) { because added the null leaf nodes 
			// shifting the right child to right side
			x.right.parent = parent;

			// changing the old parent left child pointer
			parent.left = x.right;
		//}

		// changing the right child of x
		x.right = parent;

		// Changing the parent pointer of old parent
		parent.parent = x;

		// Changing the color of node x from red to black
		//x.color = 0;

		// Changing the color of old parent from black to red
		//parent.color = 1;
	}

	/***********************
	Function Name: rrRotation
	Argument: RedBlackNode<T> x
	Description: This function will do the RR Rotation from the given node x 
	Return: None
	 ***********************/
	public void rrRotation(RedBlackNode<T> x) {
		// save x parents
		RedBlackNode<T> parent = x.parent;

		// Change the root pointer , if parent is the root
		if( x.parent == this.root)
		{
			this.root = x ;
		}
		else
		{
			//check if parent is left child
			if( parent.parent.left == parent)
			{
				//update grand parent left child
				parent.parent.left = x ;
			}
			else
			{
				// else right child
				//update grand parent right child
				parent.parent.right = x ;
			}
		}
		// changing the parent of x
		x.parent = parent.parent;

		
		//if (x.left != null) { //because of null leaves nodes
			// shifting the right child to right side
			x.left.parent = parent;

			// changing the old parent left child pointer
			parent.right = x.left;
		//}

		// changing the right child of x
		x.left = parent;

		// Changing the parent pointer of old parent
		parent.parent = x;

		
	}

	/***********************
	Function Name: lrRotation
	Argument: RedBlackNode<T> x
	Description: This function will do the LR Rotation from the given node x 
	Return: None
	 ***********************/
	public void lrRotation(RedBlackNode<T> x) {

		RedBlackNode<T> parent = x.parent ;
		RedBlackNode<T> grandparent = x.parent.parent ;
		
		if( grandparent == this.root)
		{
			this.root = x ;
		}
		else
		{
			//check if parent is left child
			if( grandparent.parent.left == grandparent)
			{
				grandparent.parent.left = x ;
			}
			else
			{
				// else right child
				grandparent.parent.right = x ;
			}
		}
		
		grandparent.left = x.right ;
		parent.right = x.left ;
		x.left.parent = parent;
		x.right.parent = grandparent ;
		x.left = parent ;
		x.right = grandparent ;
		x.parent = grandparent.parent ;
		grandparent.parent = x;
		parent.parent = x ;
		
	}

	
	/***********************
	Function Name: rlRotation
	Argument: RedBlackNode<T> x
	Description: This function will do the RL Rotation from the given node x 
	Return: None
	 ***********************/
	public void rlRotation(RedBlackNode<T> x) {

		
		RedBlackNode<T> parent = x.parent ;
		RedBlackNode<T> grandparent = x.parent.parent ;
		
		if( grandparent == this.root)
		{
			this.root = x ;
		}
		else
		{
			//check if parent is left child
			if( grandparent.parent.left == grandparent)
			{
				grandparent.parent.left = x ;
			}
			else
			{
				// else right child
				grandparent.parent.right = x ;
			}
		}
		
		grandparent.right = x.left ;
		parent.left = x.right ;
		x.left.parent = grandparent ;
		x.right.parent = parent ;
		x.left = grandparent ;
		x.right = parent ;
		x.parent = grandparent.parent ;
		grandparent.parent = x;
		parent.parent = x ;
        
		
	}

	/***********************
	Function Name: findElement
	Argument: int key1 , int key2
	Description: This function will find and return list of red black nodes whose key lies between key1 and key2 ( both including ) 
				 and will return the list of nodes
	Return: List<T>
	 ***********************/
	public List<T> findElement( int key1 , int key2 ) {
		
		
		RedBlackNode<T> node = findKeyNode( this.root , key1, key2 ) ;
		List<T> result = new ArrayList<T>() ;
		//if(  !( root != null && key1 <= root.key  &&  key2 >= root.key ) )
		if(node == null)
		{
			//System.out.println("(0,0,0)");
			return result ;
		}
		else
		{
			result = findElementsInRange( key1 , key2 , node ) ;
		}
		
		return result ;
		
	}
	
	/***********************
	Function Name: findElement
	Argument: int key1
	Description: This function will find and return list of red black node key equal to the given key1 
	Return: List<T>
	 ***********************/
	public List<T> findElement( int key1  ) {
		
		
		RedBlackNode<T> node = findKeyNode(this.root , key1 ) ;
		List<T> result = new ArrayList<T>() ;
		//if(  !( root != null && key1 <= root.key  &&  key2 >= root.key ) )
		
		
		if(node == null)
		{
			//System.out.println("(0,0,0)");
			return result ;
		}
		else
		{
			result.add(node.data) ;
		}
		
		return result ;
		
	}
	
	public void inorder(RedBlackNode<T> node)
	{
		if(node.isLeafNode)
			return ;
		
		String color = "BLACK" ;
		if( node.color == 1)
		{
			color = "RED" ;
		}
		inorder(node.left) ;
		System.out.println( node.key + ":" + color  + " ");
		inorder(node.right) ;
	}
	
	/***********************
	Function Name: findKeyNode
	Argument: int key , RedBlackNode<T> node
	Description: This function will find the node with given key and it will start the search from the given node  
	Return: RedBlackNode<T>
	 ***********************/
	public RedBlackNode<T> findKeyNode( RedBlackNode<T> node , int key )
	{
		//When node is null or reached the null leaf node return null
		//No key found
		if(node == null || node.isLeafNode )
			return null ;
		
		RedBlackNode<T> keyNode = null ;
		if( node.key > key )
		{
			//when current node key is greater than the key, than search in left subtree
			keyNode = findKeyNode( node.left , key ) ;
		}
		else if( node.key < key )
		{
			//when current node key is smaller than the key, than search in left subtree
			keyNode = findKeyNode( node.right , key ) ;
		}
		else
		{
			//when equal return the node itself
			return node ;
		}
		
		return keyNode ;
	}
	
	/***********************
	Function Name: findKeyNode
	Argument: RedBlackNode<T> node, int key1 , int key2
	Description: This function will find the first node that is between key1 and key2 (both including) 
				 and will start the search from given node
	Return: RedBlackNode<T>
	 ***********************/
	public RedBlackNode<T> findKeyNode(RedBlackNode<T> node, int key1 , int key2 )
	{
		if(node == null || node.isLeafNode)
			return null ;
		
		RedBlackNode<T> result = null ;
		if( node.key <  key1)
		{
			result =  findKeyNode(node.right , key1 , key2) ;
		}
		if(node.key > key2)
		{
			result =  findKeyNode(node.left, key1 , key2 ) ;
		}
		
		if( key1 <= node.key && node.key <= key2 )
		{
			return node ;
		}
		
		return result ;
		
	}
	
	
	/***********************
	Function Name: findElementsInRange
	Argument: int key1 , int key2 , RedBlackNode<T> root
	Description: This function will find and return list of all the elements whose keys lies between
				 key1 and key2 (both including) and will search element from provided root node
	Return: List<T>
	 ***********************/
	public List<T> findElementsInRange( int key1 , int key2 , RedBlackNode<T> root )
	{
		if( root == null || root.isLeafNode == true )
			return null;
		
		List<T> result = new ArrayList<T>() ;
		if( key1 <= root.key  &&  key2 >= root.key )
		{
			List<T> tmp = findElementsInRange(key1 , key2 , root.left ) ;
			if (tmp != null)
			{
				result.addAll(tmp) ;
			}
			result.add(root.data) ;
			//((T) root.data).printValues() ;
			//((T)root.data).printValues() ;
			tmp = findElementsInRange(key1 , key2 , root.right ) ;
			if (tmp != null)
			{
				result.addAll(tmp) ;
			}
			
		}
		
		return result ;
		
		
		
	}
	
	/***********************
	Function Name: delete
	Argument: int key
	Description: This function will delete the node with given key
	Return: Node
	 ***********************/
	//This function will find the key node and delete that node
	public void delete( int key)
	{
		RedBlackNode<T> keyNode = findKeyNode( this.root , key ) ;
		
		if( keyNode == null)
		{
			System.out.println("Key  " + key + " not found");
			return ;
		}
		if( keyNode.right.isLeafNode == false && keyNode.left.isLeafNode == false )
		{
			//2 Children case, replace with inorder successor
			RedBlackNode<T> inorderSuccessorNode =  inorderSuccessor(keyNode) ;
			keyNode.key = inorderSuccessorNode.key ;
			keyNode.data  = inorderSuccessorNode.data ;
			keyNode = inorderSuccessorNode ;
			//deleteNode(inorderSuccessorNode) ;
		}
		
		deleteNode( keyNode ) ;

		
	}
	
	/***********************
	Function Name: delete
	Argument: RedBlackNode<T> node
	Description: This function will delete given Red Black Node node and call the deleteFixup function
	Return: None
	 ***********************/
	//This function will delete the given node
	public void deleteNode( RedBlackNode<T> node )
	{

		node.left.parent = node.parent ;
		node.right.parent = node.parent ;
		RedBlackNode<T> deficientNode = null ;
		
		if( node.right.isLeafNode)
		{
			//right child is null node, left can/can not be
			// 1 or 0 child case
			
			deficientNode = node.left ;
			
			//deleteNode() ;                                          
		}
		else if ( node.left.isLeafNode )
		{
			//left child is null node, right can/can not be
			// 1 or 0 child case 
			deficientNode = node.right ;
		}
		
		
		if( node != this.root && isLeftChild(node)  )
		{
			node.parent.left = deficientNode ;
		}
		else if( node != this.root )
		{
			node.parent.right = deficientNode ;
		}
		else
		{
			//if root Node
			deficientNode.parent = node.parent ;
			this.root =  deficientNode ;
			
		}
		

		if( node.color == 0)
		{
			if(deficientNode.color == 1)
			{
				deficientNode.color = 0 ;
			}
			else
			{
				// if color is black, fix the tree
				deleteFixup( deficientNode ) ;
			}
			
		}
		else
		{
			
		}
		
	}
	
	/***********************
	Function Name: deleteFixup
	Argument: RedBlackNode<T> node
	Description: This function will start fixing the tree from given node and contnue up the tree
				 until deficiency is removed or it reaches the root node 
	Return: None
	 ***********************/
	public void deleteFixup(RedBlackNode<T> node)
	{
		RedBlackNode<T> deficientNode = node ;
		RedBlackNode<T> parent = node.parent ;
		
		//case 1
		if( node == this.root )
		{
			//nothing to do
			return ;
		}
		
		
		
		if( isLeftChild( node ) )  
		{
			RedBlackNode<T> S = parent.right ;
			
			//case 6, parent color does not matter, sibling black, sibling's left child color does not matter
			//sibling's right child color red
			if( S.color == 0 && !S.isLeafNode && S.right.color == 1)
			{
				S.color = parent.color;
				S.right.color = 0 ;
				parent.color = 0 ;
				//rr rotation around S
				rrRotation(S) ;
				return ;
			}
			//from ppt lb1, case 2
			//case 7, parent color does not matter
			else if (S.color == 0 && !S.isLeafNode && S.left.color == 1)
			{
				S.left.color = parent.color ;
				parent.color = 0 ;
				rlRotation(S.left) ;
			}
			
			else if( parent.color == 0 )
			{
				//parent is black
				
				//case 2, sibling red, with both black childeren
				if( S.color == 1 && S.left.color == 0 && S.right.color == 0 )
				{
					rrRotation(S) ;
					S.color = 0 ;
					parent.color = 1 ;
					deleteFixup( node ) ;
				}
				
				//case 3, sibling is black, with both childeren red
				// make sibling red and shift deficiency to parent
				else if( S.color == 0 && S.left.color == 0 && S.right.color == 0 )
				{
					
					S.color = 1 ;
					//parent is deficient
					deleteFixup( parent ) ;
				}
				
				//case 5 , sibling is black, sibling's left child red, sibling right child black
				else if( S.color == 0 && S.left.color == 1 && S.right.color == 0 )
				{
					S.left.color = 0;
					S.color = 1 ;
					//LL rotation around sibling's red child
					llRotation(S.left) ;
					
					//above fixing lead to case 6
					deleteFixup(node) ;
				}
				
				
			}
			else
			{
				//parent is red
				//System.out.println("debug " + S.key + S.isLeafNode + " " + parent.key + " " + node.key);
				//case 4, sibling black, with both children black
				if( S.color == 0 && S.left.color == 0 && S.right.color == 0 )
				{
					S.color = 1 ;
					parent.color = 0 ;
					//parent is deficient
					//deleteFixup( parent ) ;
				}
			}
		}
		else
		{
			//all the above cases will be the mirror images when node is in the right side
			
			RedBlackNode<T> S = parent.left ;
			
			//case 6, parent color does not matter, sibling black, sibling's left child color does not matter
			//sibling's right child color red // check this
			if( S.color == 0 &&  !S.isLeafNode && S.left.color == 1)
			{
				S.color = parent.color;
				S.left.color = 0 ;
				parent.color = 0 ;
				//rr rotation around S
				llRotation(S) ;
				return ;
			}
			//from ppt rb1, case 2
			//case 7, parent color does not matter
			else if (S.color == 0 && !S.isLeafNode && S.right.color == 1)
			{
				S.right.color = parent.color ;
				parent.color = 0 ;
				lrRotation(S.right) ;
				return ;
			}
			else if( parent.color == 0 )
			{
				//parent is black
				
				//case 2, sibling red, with both black childeren
				if( S.color == 1 && S.left.color == 0 && S.right.color == 0 )
				{
					//check this
					llRotation(S) ;
					S.color = 0 ;
					parent.color = 1 ;
					deleteFixup( node ) ;
				}
				
				//case 3, sibling is black, with both childeren red
				// make sibling red and shift deficiency to parent
				else if( S.color == 0 && S.left.color == 0 && S.right.color == 0 )
				{
					
					S.color = 1 ;
					//parent is deficient
					deleteFixup( parent ) ;
				}
				
				//case 5 , sibling is black, sibling's left child red, sibling right child black
				else if( S.color == 0 && S.right.color == 1 && S.left.color == 0 )
				{
					S.right.color = 0;
					S.color = 1 ;
					//rr rotation around sibling's red child
					rrRotation(S.right) ; // Check this
					
					//above fixing lead to case 6
					deleteFixup(node) ;
				}
				
				
			}
			else
			{
				//parent is red
				
				//case 4, sibling black, with both children black
				if( S.color == 0 && S.left.color == 0 && S.right.color == 0 )
				{
					
					S.color = 1 ;
					parent.color = 0 ;
					//parent is deficient
					//deleteFixup( parent ) ;
				}
			}
		}
		
		
	}
	
	/***********************
	Function Name: isLeftChild
	Argument: RedBlackNode<T> node
	Description: This function will check if the given node is left child of its parent or not
	Return: boolean
	 ***********************/
	public boolean isLeftChild(RedBlackNode<T> node)
	{
		if(  node.parent.left == node)
		{
			//yes given is left child of its parent
			return true ;
		}

		//No, given node is the right child of its parent
		return false ;
			
	}
	
	/***********************
	Function Name: inorderSuccessor
	Argument: RedBlackNode<T> node
	Description: This function will find the in order successor(minimum element in right subtree of node) of given node  
	Return: RedBlackNode<T>
	 ***********************/
	public RedBlackNode<T> inorderSuccessor(RedBlackNode<T> node)
	{
		//return leftmost not null node in right subtree of node
		RedBlackNode<T> right = node.right ;
		
		while( !right.left.isLeafNode )
		{
			right = right.left ;
		}
		return right ;
	}
	
	
	
	
	/***********************
	Function Name: RedBlackNode<T>
	Argument: int key , RedBlackNode<T> node
	Description: Constructor for initialization of Red Black Tree
	Return: RedBlackNode<T>
	 ***********************/
	class RedBlackNode<T> {
		int color;// 0 for black , 1 for red
		RedBlackNode<T> left, right, parent;
		int key ; // key for the data, for this case we will pass building no.
		T data;
		boolean isLeafNode ;

		public RedBlackNode(int key , RedBlackNode<T> parent ,  T data, int color) {
			this.color = color;
			this.data = data;
			this.key = key ; 
			this.left = new RedBlackNode<T>() ;
			this.right = new RedBlackNode<T>() ;
			this.left.parent = this;
			this.right.parent = this;
			this.isLeafNode = false ;
			this.parent = parent ;
		}
		
		public RedBlackNode() {
			this.color = 0; //NULL/Leaf nodes will be black in color
			this.isLeafNode =  true ;
			
			
		}

	}

}
