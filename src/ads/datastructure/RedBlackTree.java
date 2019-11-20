package ads.datastructure;

import java.util.Comparator;

public class RedBlackTree<T> {

	RedBlackNode root;
	Comparator<T> comp;
	RedBlackNode newNode ;
	

	public RedBlackTree(Comparator<T> comp) {
		this.comp = comp;
	}

	public void insert( int key , T data)
	{
		if( this.root == null)
		{
			//root is black node
			this.root = new RedBlackNode(key , null ,  data , 0) ;
			this.newNode = this.root ;
			return ;
		}
		
		RedBlackNode<T> insertedNode = insert( root , key ,  data ) ;
		doBalancing( this.newNode ) ;
		
		//insert()
	}

	public RedBlackNode<T> insert(RedBlackNode root, int key , T data) {
		if (root.isLeafNode == true) {
			// root is black node
			RedBlackNode newNode =  new RedBlackNode(key , root.parent ,  data, 1);
			this.newNode =  newNode;
			return newNode ;
			
		}

		if (comp.compare((T) root.data, data) > 0) {
			root.left = insert(root.left, key,  data);
		} else {
			root.right = insert(root.right, key, data);
		}
		
		//if XY b case
		{
			// Changing the color of node x from red to black
			//x.color = 0;

			// Changing the color of old parent from black to red
			//parent.color = 1;
		}
		return root;
	}

	public void doBalancing(RedBlackNode x)
	{
		if( x ==  this.root)
			return;
		
		//do balancing if parent color is also red
		if(x.parent.color == 1)
		{
			
			
		   // change grandparent color to red
			//x.parent.parent.color = 1 ;
			
			RedBlackNode parent = x.parent ;
			RedBlackNode grandParent = x.parent.parent ;
			
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
			else
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
	// Will do the LL rotation from node x
	public void llRotation(RedBlackNode x) {
		// save x parents
		RedBlackNode parent = x.parent;

		

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

	// Will do the RR rotation from node x
	public void rrRotation(RedBlackNode x) {
		// save x parents
		RedBlackNode parent = x.parent;

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

	// rotate lr rotions with respect to node x
	public void lrRotation(RedBlackNode x) {

		// first do rr rotation with respect to x
		//rrRotation(x);
		RedBlackNode parent = x.parent ;
		RedBlackNode grandparent = x.parent.parent ;
		
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
		
		// then do ll rotation with respect to x
		//llRotation(x);

	}

	// rotate lr rotions with respect to node x
	public void rlRotation(RedBlackNode x) {

		// first do ll rotation with respect to x
		//llRotation(x);
		
		RedBlackNode parent = x.parent ;
		RedBlackNode grandparent = x.parent.parent ;
		
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
        
		// then do rr rotation with respect to x
		//rrRotation(x);

	}

	public void printRange( int buildingNum1 , int buildingNum2 ) {
		
		
		RedBlackNode node = findNodeBetweenRange( this.root , buildingNum1, buildingNum2 ) ;
		//if(  !( root != null && buildingNum1 <= root.key  &&  buildingNum2 >= root.key ) )
		if(node == null)
		{
			System.out.println("(0,0,0)");
		}
		else
		{
			printRangeHelper( buildingNum1 , buildingNum2 , node ) ;
		}
		
	}
	
	public RedBlackNode findNodeBetweenRange(RedBlackNode node, int buildingNum1 , int buildingNum2 )
	{
		if(node == null || node.isLeafNode)
			return null ;
		
		RedBlackNode result = null ;
		if( node.key <  buildingNum1)
		{
			result =  findNodeBetweenRange(node.right , buildingNum1 , buildingNum2) ;
		}
		if(node.key > buildingNum2)
		{
			result =  findNodeBetweenRange(node.left, buildingNum1 , buildingNum2 ) ;
		}
		
		if( buildingNum1 <= node.key && node.key <= buildingNum2 )
		{
			return node ;
		}
		
		return result ;
		
	}
	
	public void printRangeHelper( int buildingNum1 , int buildingNum2 , RedBlackNode root )
	{
		if( root == null || root.isLeafNode == true )
			return ;
		
		if( buildingNum1 <= root.key  &&  buildingNum2 >= root.key )
		{
			printRangeHelper(buildingNum1 , buildingNum2 , root.left ) ;
			//((T) root.data).printValues() ;
			((Building)root.data).printValues() ;
			printRangeHelper(buildingNum1 , buildingNum2 , root.right ) ;
		}
		
		
		
	}
	
	public void delete( T data)
	{
		
	}
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
