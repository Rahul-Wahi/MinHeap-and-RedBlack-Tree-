package ads.datastructure;

public class risingCity {

	public static int globalTime = 0 ;
	public boolean work = true ; 
	MinHeap<Building> buildingRecord;
	public risingCity( int capacity) {
		buildingRecord = new MinHeap<Building>(capacity , new Comparatorexecutedtime() ) ;
		
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
	}
	
	public void print( int buildingNum)
	{
		
	}
	
	public void print( int buildingNum1, int buildingNum2)
	{
		
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		risingCity rc = new risingCity(2000) ;
		rc.insert(1, 12);
		rc.insert(2, 12);
		rc.insert(3, 12);
		rc.insert(4, 12);
		System.out.println(rc.getMin());
		
		
		
	}

}
