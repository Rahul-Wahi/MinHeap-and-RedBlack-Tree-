package ads.datastructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
	BufferedWriter writer ;
	String command  ;
	boolean hasMoreCommands ;
	Integer nextCommandTime ;
	
	
	
	public risingCity( int capacity , BufferedReader br , BufferedWriter writer) {
		buildingRecord = new MinHeap<Building>(capacity , new Comparatorexecutedtime() ) ;
		buildingRecordByBuilding = new RedBlackTree<Building>(new ComparatorbuildingNum() ) ;
		this.br = br ;
		this.writer = writer ;
		this.nextCommandTime = -1 ;
		readLine() ;
		
	}
	
	
	/***********************
	Function Name: readLine
	Argument: None
	Description: This function will the read the next line from the input file
	             and if no more commands available set the hasMoreCommands to false 
	Return: None
	 ***********************/
	public void readLine()
	{
		try {
			String command = "" ;
			if( ( command = br.readLine() ) != null)
			{
				this.command = command  ;
				String[] commands = this.command.split(":");
				int time = Integer.parseInt( commands[0].trim() ) ; 
				this.nextCommandTime = time ;
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
	
	/***********************
	Function Name: incrementCounter
	Argument: None
	Description: This function will Increment global counter and call executeCommand function if hasMoreCommands = true
	Return: None
	 ***********************/
	public void incrementCounter()
	{
		risingCity.globalTime++ ;
		if( !hasMoreCommands)
			return ;
		executeCommand() ;
		
		
	}
	
	/***********************
	Function Name: executeCommand
	Argument: None
	Description: This function will parse the command and execute commands(insert/print) if command time matches global time
	Return: None
	 ***********************/
	public void executeCommand()
	{
		
		//int time = Integer.parseInt( commands[0].trim() ) ; 
		
		if( this.nextCommandTime == risingCity.globalTime)
		{
			String[] commands = this.command.split(":");
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


	/***********************
	Function Name: startWork
	Argument: None
	Description: This function is responsible for the work flow. It will continue to work until there are
				 no more commands and no buildings available for construction 
				 (will pick buildings from heap 1 by 1) (  will call incrementCounter function )
	Return: None
	 ***********************/
	public void startWork(  )
	{
		//incrementCounter() ;
		executeCommand() ;
		while( work )
		{
			
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
				
				//Lets now print,  as at this point global counter reaches the completed time
				if( minTime == remainingTime)
				{
					int completedTime = risingCity.globalTime + minTime - 1 ;
					//System.out.println( "(" + buildingNum +  ","  + risingCity.globalTime + ")"  ) ;
					this.write("(" + buildingNum +  ","  + risingCity.globalTime + ")\n") ;
					
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
	
	/***********************
	Function Name: write
	Argument: String str
	Description: This function will write str to opened output_file.txt
	Return: None
	 ***********************/
	public void  write(String str)
	{
		try {
			writer.write( str );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/***********************
	Function Name: insert
	Argument: int buildingNum, int total_time
	Description: This function insert building with building no as buildingNum
				 and total time as total_time into heap and Red black tree
	Return: None
	 ***********************/
	public void insert(int buildingNum, int total_time)
	{

		Building building = new Building( buildingNum, total_time ) ;
		buildingRecord.insert( building );
		buildingRecordByBuilding.insert(buildingNum,building);
	}
	
	/***********************
	Function Name: print
	Argument: int buildingNum
	Description: This function print the triplet building no, exdcuted_time , total_time of the building
	 			 whose building no = buildingNum, if there is no building available then it will print (0,0,0)
	Return: None
	 ***********************/
	public void print( int buildingNum)
	{
		List<Building> result = buildingRecordByBuilding.findElement(buildingNum);
		
		if( result.size() == 0)
		{
			//System.out.println("(0,0,0)");
			this.write("(0,0,0)\n") ; 
		}
		for(int i = 0 ; i < result.size() ; i++)
		{
			//System.out.print("(" +  result.get(i).buildingNum + "," + result.get(i).executed_time + "," + result.get(i).total_time + ")");
			this.write("(" +  result.get(i).buildingNum + "," + result.get(i).executed_time + "," + result.get(i).total_time + ")")  ;
			if( i != result.size() - 1)
			{
				//System.out.print(",") ;
				//this.write(",") ;
			}
			else
			{
				//System.out.println() ;
				this.write("\n") ;
			}
		}
	}
	
	/***********************
	Function Name: print
	Argument: int buildingNum1, int buildingNum2
	Description: This function print the triplet building no, exdcuted_time , total_time of all the building
	 			 whose building no is in between buildingNum1, buildingNum2 if there is no building available then it will print (0,0,0)
	Return: None
	 ***********************/
	public void print( int buildingNum1, int buildingNum2)
	{
		List<Building> result = buildingRecordByBuilding.findElement(buildingNum1, buildingNum2);
		
		if( result.size() == 0)
		{
			//System.out.println("(0,0,0)");
			this.write( "(0,0,0)\n" ) ;
		}
		for(int i = 0 ; i < result.size() ; i++)
		{
			//System.out.print("(" +  result.get(i).buildingNum + "," + result.get(i).executed_time + "," + result.get(i).total_time + ")");
			this.write( "(" +  result.get(i).buildingNum + "," + result.get(i).executed_time + "," + result.get(i).total_time + ")" ) ;
			if( i != result.size() - 1)
			{
				//System.out.print(",");
				this.write(",") ;
			}
			else
			{
				//System.out.println() ;
				this.write("\n") ;
			}
		}
	}
	
	/***********************
	Function Name: delete
	Argument: int buildingNum
	Description: This function will delete the building record
				 whose building no. = buildingNum
	Return: None
	 ***********************/
	public void delete( int buildingNum )
	{
	
		buildingRecord.extractMin();
		buildingRecordByBuilding.delete(buildingNum);
	}
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		final long startTime = System.currentTimeMillis();
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
		  
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("C:\\ADS\\RisingCity\\src\\ads\\datastructure\\output_file.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ;
		}
	   
            
		risingCity rc = new risingCity(2000 , br , writer) ;
		
		//start work
		rc.startWork();
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		final long endTime = System.currentTimeMillis();

		System.out.println("Total execution time: " + (endTime - startTime));
	}

}
