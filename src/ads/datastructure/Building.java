package ads.datastructure;

import java.util.Comparator;

public class Building {
	int buildingNum ;
	int executed_time ;
	int total_time ;
	
	public Building(int buildingNum , int total_time)
	{
		this.buildingNum = buildingNum ;
		this.total_time = total_time ;
		this.executed_time = 0 ;
	}
}
	class ComparatorbuildingNum implements Comparator<Building> 
	{ 
	    // Used for sorting in ascending order of 
	    // roll number 
	    public int compare(Building a, Building b) 
	    { 
	        return a.buildingNum - b.buildingNum; 
	    } 
	} 
	  
	class Comparatorexecutedtime implements Comparator<Building> 
	{ 
	    // Used for sorting in ascending order of 
	    // roll name 
	    public int compare(Building a, Building b) 
	    { 
	       int diff = a.executed_time - b.executed_time ;
	       
	       if( diff == 0)
	       {
	    	   diff = a.buildingNum - b.buildingNum ;
	       }
	       
	       return diff ;
	    } 
	} 


