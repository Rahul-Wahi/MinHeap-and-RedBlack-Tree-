package ads.datastructure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ads.datastructure.RedBlackTree.RedBlackNode;

public class risingCity {

	public static int globalTime = 0 ;
	public boolean work = true ; 
	MinHeap<Building> buildingRecord;
	RedBlackTree<Building> buildingRecordByBuilding ;
	BufferedReader br ;
	String command  ;
	boolean hasMoreCommands ;
	Integer currentBuildingNumber ;
	List<String> insertCommands ; // will need this to defer inserts in heap
	
	
	
	public risingCity( int capacity , BufferedReader br) {
		buildingRecord = new MinHeap<Building>(capacity , new Comparatorexecutedtime() ) ;
		buildingRecordByBuilding = new RedBlackTree<Building>(new ComparatorbuildingNum() ) ;
		//command = new ArrayList<String>() ;
		currentBuildingNumber = null ;
		this.br = br ;
		
		readLine() ;
		
	}
	
	public void readLine()
	{
		try {
			String command = "" ;
			if( ( command = br.readLine() ) != null)
			{
				this.command = command  ;
				hasMoreCommands = true;
			}
			else
			{
				hasMoreCommands = false ;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Increment counter and call executeCommand function
	public void incrementCounter()
	{
		risingCity.globalTime++ ;
		if( !hasMoreCommands)
			return ;
		executeCommand() ;
		
		
	}
	
	//will exexute commands based on global counter value
	public void executeCommand()
	{
		String[] commands = this.command.split(":");
		int time = Integer.parseInt( commands[0].trim() ) ; 
		
		if( time == risingCity.globalTime)
		{
			if( commands[1].toLowerCase().contains("insert"))
			{
				Pattern p = Pattern.compile("-?\\d+");
				Matcher m = p.matcher(commands[1]);
				int buildingNum = 0, total_time = 0 ;
				
				if (m.find()) {
					buildingNum= Integer.parseInt( m.group() ) ;
			        
				 // System.out.println(m.group());
				}
				if (m.find()) {
					total_time = Integer.parseInt( m.group() ) ;
			        
				 // System.out.println(m.group());
				}
				
				this.insert(buildingNum, total_time);
				
			}
			else
			{
				Pattern p = Pattern.compile("-?\\d+");
				Matcher m = p.matcher(commands[1]);
				int buildingNum1 = 0, buildingNum2 ;
				if(m.find()) {
					buildingNum1= Integer.parseInt( m.group() ) ;
			        
				 // System.out.println(m.group());
				}
				if (m.find()) {
					
					
					buildingNum2 = Integer.parseInt( m.group() ) ;
					//Second building is also found so call the print( buildingNum1, buildingNum2 )
					this.print(buildingNum1, buildingNum2);
			        
				 // System.out.println(m.group());
				}
				else
				{
					//Second building not found, so call the sing argument function
					this.print(buildingNum1);
				}
				
			}
			
			readLine() ;
		}
	}

	
	//will exexute commands based on global counter value
	public int getMin()
	{
		return 	buildingRecord.getMin().buildingNum ;
	}
	
	// will pick buildings from heap 1 by 1
	// will call incrementCounter function
	public void startWork(  )
	{
		//incrementCounter() ;
		executeCommand() ;
		while( work )
		{
			
			this.currentBuildingNumber = null ;
			
			if( !hasMoreCommands &&  buildingRecord.getSize() == 0)
			{
				break ;
			}
			if( buildingRecord.getSize() > 0 )
			{
				Building building = buildingRecord.getMin() ;
				int buildingNum = building.buildingNum ;
				int remainingTime = building.total_time - building.executed_time ;
				int minTime =  Math.min(remainingTime, 5) ;
				int tmp = minTime ;
				this.currentBuildingNumber = buildingNum ;
				int old_executed_time = building.executed_time ;
				building.executed_time += minTime  ; 
				
				
				
			    //We need to update/delete from heap early on, otherwise index will be updated by insert commands for this building 
				if( minTime == remainingTime)
				{ 
					//remove from minHeap
					buildingRecord.extractMin() ;
					//delete(building.buildingNum) ;
				}
				else
				{
					//Update MinHeap
					buildingRecord.updateKey(building, 0);
				}
				
				
				while( tmp > 0 )
				{
					old_executed_time += 1;
					building.executed_time = old_executed_time ;
					//incrementing pointer upto construction time and executing ongoing commands
					incrementCounter() ;
					tmp-- ;
				}
				
				//Lets now print when global counter reaches the completed time
				if( minTime == remainingTime)
				{
					int completedTime = risingCity.globalTime + minTime - 1 ;
					System.out.println( "(" + buildingNum +  ","  + risingCity.globalTime + ")"  ) ; 
					//Now delete from RBT, when global counter reaches the construction time
					buildingRecordByBuilding.delete(buildingNum);
				}
				
				
				
				
				
				
				
			}
			else if(hasMoreCommands)
			{
				incrementCounter();
			}
		}
		
	}
	public void insert(int buildingNum, int total_time)
	{
//		if( buildingNum == this.currentBuildingNumber )
//		{
//			System.out.println("Building Number " + buildingNum + " already exists, stopping the program");
//			System.exit(0); 
//		}
		
		Building building = new Building( buildingNum, total_time ) ;
		buildingRecord.insert( building );
		buildingRecordByBuilding.insert(buildingNum,building);
	}
	
	public void print( int buildingNum)
	{
		List<Building> result = buildingRecordByBuilding.findElement(buildingNum);
		
		if( result.size() == 0)
		{
			System.out.println("(0,0,0)");
		}
		for(int i = 0 ; i < result.size() ; i++)
		{
			System.out.print("(" +  result.get(i).buildingNum + "," + result.get(i).executed_time + "," + result.get(i).total_time + ")");
			if( i != result.size() - 1)
			{
				System.out.print(",");
			}
			else
			{
				System.out.println() ;
			}
		}
	}
	
	public void print( int buildingNum1, int buildingNum2)
	{
		List<Building> result = buildingRecordByBuilding.findElement(buildingNum1, buildingNum2);
		
		if( result.size() == 0)
		{
			System.out.println("(0,0,0)");
		}
		for(int i = 0 ; i < result.size() ; i++)
		{
			System.out.print("(" +  result.get(i).buildingNum + "," + result.get(i).executed_time + "," + result.get(i).total_time + ")");
			if( i != result.size() - 1)
			{
				System.out.print(",");
			}
			else
			{
				System.out.println() ;
			}
		}
	}
	
	public void printRangeHelper( int buildingNum1 , int buildingNum2 , RedBlackNode root )
	{
		
	}
	
	public void delete( int buildingNum )
	{
	
		buildingRecord.extractMin();
		buildingRecordByBuilding.delete(buildingNum);
	}
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String fileName = "" ;
		if( args.length == 0)
		{
			System.out.println("Please provide the input file");
			return ;
		}
		else
		{
			fileName = args[0] ;
		}
		
		 File file = new File(fileName); 
		  
		 BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return ;
		} 
		  
	   
            //System.out.println("args[" + i + "]: " + args[i]);
		risingCity rc = new risingCity(2000 , br) ;
//		rc.insert(1, 12);
//		rc.insert(10, 10);
//		rc.insert(2, 11);
//		rc.insert(9, 9);
//		rc.insert(3, 9);
//		rc.insert(8, 9);
//		rc.insert(4, 9);
//		rc.insert(7, 9);
		//System.out.println(rc.getMin());
		//rc.print(1) ;
		rc.startWork();
		
		
		
	}

}
