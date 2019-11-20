package ads.datastructure;

import ads.datastructure.RedBlackTree.RedBlackNode;

public class risingCity {

	public static int globalTime = 0 ;
	public boolean work = true ; 
	MinHeap<Building> buildingRecord;
	RedBlackTree<Building> buildingRecordByBuilding ;
	public risingCity( int capacity) {
		buildingRecord = new MinHeap<Building>(capacity , new Comparatorexecutedtime() ) ;
		buildingRecordByBuilding = new RedBlackTree<Building>(new ComparatorbuildingNum() ) ;
		
	}
	
	//Increment counter and call executeCommand function
	public void incrementCounter()
	{
		risingCity.globalTime++ ;
		executeCommand() ;
		
	}
	
	//will exexute commands based on global counter value
	public void executeCommand()
	{
		
	}
	
	//will exexute commands based on global counter value
	public int getMin()
	{
		return 	buildingRecord.getMin().buildingNum ;
	}
	
	// will pick buildings from heap 1 by 1
	// will call incrementCounter function
	public void startWork()
	{
		incrementCounter() ;
		
		while( work )
		{
			if( buildingRecord.getSize() > 0 )
			{
				Building building = buildingRecord.getMin() ;
				int remainingTime = building.total_time - building.total_time ;
				int minTime =  Math.min(remainingTime, 5) ;
				
				if( minTime == remainingTime)
				{
					buildingRecord.extractMin() ;
				}
				
				while( minTime > 0 )
				{
					incrementCounter() ;
					minTime-- ;
				}
			}
		}
		
	}
	public void insert(int buildingNum, int total_time)
	{
		Building building = new Building( buildingNum, total_time ) ;
		buildingRecord.insert( building );
		buildingRecordByBuilding.insert(buildingNum,building);
	}
	
	public void print( int buildingNum)
	{
		
	}
	
	public void print( int buildingNum1, int buildingNum2)
	{
		buildingRecordByBuilding.printRange(buildingNum1, buildingNum2);
	}
	
	public void printRangeHelper( int buildingNum1 , int buildingNum2 , RedBlackNode root )
	{
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		risingCity rc = new risingCity(2000) ;
		rc.insert(1, 12);
		rc.insert(10, 10);
		rc.insert(2, 11);
		rc.insert(9, 9);
		rc.insert(3, 9);
		rc.insert(8, 9);
		rc.insert(4, 9);
		rc.insert(7, 9);
		System.out.println(rc.getMin());
		rc.print(11,10) ;
		
		
		
	}

}
