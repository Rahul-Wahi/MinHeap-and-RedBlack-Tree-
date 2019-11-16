package ads.datastructure;

import java.util.Comparator;

public class RedBlackTree<T> {

	RedBlackNode root;
	Comparator<T> comp;

	public RedBlackTree(Comparator<T> comp) {
		this.comp = comp;
	}

	public void insert( T data)
	{
		if( root == null)
		{
			//root is black node
			root = new RedBlackNode(data , 0) ;
			return ;
		}
		
		//insert()
	}

	public RedBlackNode insert(RedBlackNode root, T data) {
		if (root == null) {
			// root is black node
			RedBlackNode newNode =  new RedBlackNode(data, 0);
			
			return newNode ;
			
		}

		if (comp.compare((T) root.data, data) > 0) {
			root.left = insert(root.left, data);
		} else {
			root.right = insert(root.right, data);
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
			x.parent.parent.color = 1 ;
			
			RedBlackNode parent = x.parent ;
			RedBlackNode grandParent = x.parent.parent ;
			
			// parent color will be chnaged to black color in all cases
			parent.color = 0 ;
			
			if( x == parent.left && parent == grandParent.left)
			{
				//check for LLb case
				if( !( grandParent.right != null && grandParent.right.color == 0 ) )
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
				if( !( grandParent.right != null && grandParent.right.color == 0 ) )
				{
					//change grandparent color to red
					grandParent.color = 1 ;
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
				if( !( grandParent.left != null && grandParent.left.color == 0 ) )
				{
					//change grandparent color to red
					grandParent.color = 1 ;
					rrRotation(x) ;
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
			else
			{
				//check for RLb case
				if( !( grandParent.left != null && grandParent.left.color == 0 ) )
				{
					//change grandparent color to red
					grandParent.color = 1 ;
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

		// changing the parent of x
		x.parent = parent.parent;

		// Change the root pointer , if parent is the root
		if( x.parent == this.root)
		{
			this.root = x.parent ;
		}
		if (x.right != null) {
			// shifting the right child to right side
			x.right.parent = parent;

			// changing the old parent left child pointer
			parent.left = x.right;
		}

		// changing the right child of x
		x.right = parent;

		// Changing the parent pointer of old parent
		parent.parent = x;

		// Changing the color of node x from red to black
		x.color = 0;

		// Changing the color of old parent from black to red
		parent.color = 1;
	}

	// Will do the RR rotation from node x
	public void rrRotation(RedBlackNode x) {
		// save x parents
		RedBlackNode parent = x.parent;

		// Change the root pointer , if parent is the root
		if( x.parent == this.root)
		{
			this.root = x.parent ;
		}
		// changing the parent of x
		x.parent = parent.parent;

		if (x.left != null) {
			// shifting the right child to right side
			x.left.parent = parent;

			// changing the old parent left child pointer
			parent.right = x.right;
		}

		// changing the right child of x
		x.left = parent;

		// Changing the parent pointer of old parent
		parent.parent = x;

		
	}

	// rotate lr rotions with respect to node x
	public void lrRotation(RedBlackNode x) {

		// first do rr rotation with respect to x
		rrRotation(x);

		// then do ll rotation with respect to x
		llRotation(x);

	}

	// rotate lr rotions with respect to node x
	public void rlRotation(RedBlackNode x) {

		// first do ll rotation with respect to x
		llRotation(x);

		// then do rr rotation with respect to x
		rrRotation(x);

	}

	class RedBlackNode<T> {
		int color;// 0 for black , 1 for red
		RedBlackNode left, right, parent;
		T data;

		public RedBlackNode(T data, int color) {
			this.color = color;
			this.data = data;
		}

	}

}
