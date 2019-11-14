package ads.datastructure;

public class risingCity {

	public static int globalTime = 0 ;
	public boolean work = true ; 
	MinHeap<Building> buildingRecord;
	public risingCity( int capacity) {
		buildingRecord = new MinHeap<Building>(capacity , new Comparatorexecutedtime() ) ;
		
	}
	
	public void startWork()
	{
		risingCity.globalTime++ ;
		
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
		
		
	}

}