package ads.datastructure;

import java.util.Comparator;

public class MinHeap<T> {
	
	private int size;
	private int capacity;
	private T[] hArray ;
	Comparator<T> comp ;
	
	@SuppressWarnings("unchecked")
	public MinHeap(int capacity , Comparator<T> comp)
	{
		this.size = 0 ;
		this.capacity = capacity;
		hArray = (T[]) new Object[ capacity ] ;
		this.comp = comp ;
	}
	
	/***********************
	Function Name: insert
	Argument: T key
	Description: This function will insert the given key ( type can be anything, as this class supports generic type<T>)
				  and heapify the heap
	Return: None
	 ***********************/
	//Insert and Bottom up Heapify
	public void insert(T key)
	{
		hArray[size++] = key ; 
		bottomupHeapify( size-1 ) ;
	}
	
	/***********************
	Function Name: getMin
	Argument: None
	Description: This function will return the minimum value, the root of heap
	Return: type T
	 ***********************/
	public T getMin()
	{
		if(size == 0)
			return null ;
		return hArray[0] ;
	}
	
	/***********************
	Function Name: extractMin
	Argument: None
	Description: This function will remove the minimum(root element) value and return it and heapify for correcting the heap
	Return: type T
	 ***********************/
	public T extractMin()
	{
		if(size == 0)
			return null ;
		T min =  hArray[0] ;
		hArray[0] = hArray[--size] ;
		if( size > 0)
		{
			topdownHeapify(0) ;
		}
		return min ;
	}
	
	/***********************
	Function Name: updateKey
	Argument: T key, int index
	Description: This function will update the key at given index with provided key and perform heapification
	Return: none
	 ***********************/
	public void updateKey( T key , int index )
	{
		if( index >= 0 && index < size)
		{
			hArray[index] = key ;
			bottomupHeapify(index) ;
			topdownHeapify(index) ;
		}
	}
	
	/***********************
	Function Name: increaseKey
	Argument: T key, int index
	Description: This function will update the key at given index with provided key and perform heapification
	Return: none
	 ***********************/
	public void increaseKey( T key , int index )
	{
		if( index > 0 &&  index < size)
		{
			hArray[index] = key ;
			topdownHeapify(index) ;
		}
	}



	/***********************
	Function Name: bottomupHeapify
	Argument: int index
	Description: This function will start the heapification from the given index and will continue up till the root 
	Return: none
	 ***********************/
	public void bottomupHeapify( int index)
	{
		
		while( parent(index) != index && comp.compare( hArray[parent(index)] , hArray[index] ) > 0)
		{
			T tmp = hArray[parent(index)] ;
			hArray[parent(index)] = hArray[index] ;
			hArray[index] = tmp ;
			index = parent(index) ;
		}
		//if( hArray[index]. )
	}
	
	/***********************
	Function Name: topdownHeapify
	Argument: int index
	Description: This function will start the heapification from the given index and will continue down till the leaf elements 
	Return: none
	 ***********************/
	public void topdownHeapify( int index )
	{
		int smallest = index ;
		int left = leftChild(index) ;
		int right = rightChild(index) ;
		
		if( left < size && comp.compare( hArray[left] , hArray[index] ) < 0 )
		{
			smallest = left ;
		}
		if( right < size && comp.compare( hArray[right] , hArray[smallest] ) < 0 )
		{
			smallest = right ;
		}
		
		
		if(smallest != index)
		{
			T tmp = hArray[smallest] ;
			hArray[smallest] = hArray[index] ;
			hArray[index] = tmp ;
			topdownHeapify(smallest) ;
		}
		
	}
	
	/***********************
	Function Name: getSize
	Argument: None
	Description: This function will return the size of Heap 
	Return: int
	 ***********************/
	public int getSize()
	{
		return this.size ;
	}
	
	/***********************
	Function Name: parent
	Argument: int index
	Description: This function will return parent's index of the given index
	Return: int
	 ***********************/
	public int parent(int index)
	{
		return (index-1)/2;
	}
	
	/***********************
	Function Name: leftChild
	Argument: int index
	Description: This function will return left child's index of the given index
	Return: int
	 ***********************/
	public int leftChild(int index)
	{
		return 2*index + 1 ;
	}
	
	/***********************
	Function Name: rightChild
	Argument: int index
	Description: This function will return right child's index of the given index
	Return: int
	 ***********************/
	public int rightChild(int index)
	{
		return 2*index + 2 ;
	}
	

}
