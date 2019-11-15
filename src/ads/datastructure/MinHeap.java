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
	
	//Insert and Bottom up Heapify
	public void insert(T key)
	{
		hArray[size++] = key ; 
		bottomupHeapify( size-1 ) ;
	}
	
	//Return the minimum(root) element
	public T getMin()
	{
		if(size == 0)
			return null ;
		return hArray[0] ;
	}
	
	//return the minimum and also remove the min and
	//heapify
	public T extractMin()
	{
		if(size == 0)
			return null ;
		T min =  hArray[0] ;
		hArray[0] = hArray[size--] ;
		if( size > 0)
		{
			topdownHeapify(0) ;
		}
		return min ;
	}
	
	//will store the updated value of key
	// caller will get the data stored and update the value and call this function
	// 
	public void updateKey( T key , int index )
	{
		if( index > 0 && index < size)
		{
			hArray[index] = key ;
			bottomupHeapify(index) ;
			topdownHeapify(index) ;
		}
	}
	
	public void increaseKey( T key , int index )
	{
		if( index > 0 &&  index < size)
		{
			hArray[index] = key ;
			topdownHeapify(index) ;
		}
	}
	//Bottomup heapify
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
	
	//Top down heapify
	public void topdownHeapify( int index )
	{
		int smallest = index ;
		int left = leftChild(index) ;
		int right = rightChild(index) ;
		
		if( left < size && comp.compare( hArray[left] , hArray[index] ) < 0 )
		{
			smallest = left ;
		}
		if( right < size && comp.compare( hArray[right] , hArray[index] ) < 0 )
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
	
	public int getSize()
	{
		return this.size ;
	}
	public int parent(int index)
	{
		return (index-1)/2;
	}
	
	public int leftChild(int index)
	{
		return 2*index + 1 ;
	}
	
	public int rightChild(int index)
	{
		return 2*index + 2 ;
	}
	

}
