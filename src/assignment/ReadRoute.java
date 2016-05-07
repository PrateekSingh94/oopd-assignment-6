package assignment;

import java.io.*;
import java.util.*;

public class ReadRoute {

	ArrayList<String> junctions = new ArrayList<String>();
	ArrayList<String> route1 = new ArrayList<String>();
	ArrayList<String> route2 = new ArrayList<String>();
	ArrayList<String> route3 = new ArrayList<String>();
	ArrayList<String> route4 = new ArrayList<String>();
	//HashMap<Integer, String> map = new HashMap<Integer, String>();
	String fileName = "C:\\Users\\Prateek\\Documents\\routelist.txt";

	public void readList()
	{
		String line = null;
		int index = 0;
		try{
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			/*int StationNumber1 = 101;
			int StationNumber2 = 201;
			int StationNumber3 = 301;
			int StationNumber4 = 401;*/
			while((line = br.readLine())!=null)
			{
				if(line.equals("")) {line = br.readLine(); index = index+1;}
				if(index==0)
				{
					junctions.add(line);
				}
				if(index==1)
				{ 
					//map.put(StationNumber1, line);
					route1.add(line);
					//StationNumber1 = + 1;
				}
				if(index==2)
				{
					//map.put(StationNumber2, line);
					route2.add(line);
					//StationNumber2 = + 1;
				}
				if(index==3)
				{
					//map.put(StationNumber3, line);
					route3.add(line);
					//StationNumber3 = + 1;
				}
				if(index==4)
				{
					//map.put(StationNumber3, line);
					route4.add(line);
					//StationNumber4 = + 1;
				}
			}
			/*System.out.println(junctions);
			System.out.println(route1);
			System.out.println(route2);
			System.out.println(route3);
			System.out.println(route4);*/
			//System.out.println(map);
			br.close();
		}
		catch(FileNotFoundException ex)
		{
			System.out.println("Unable to open file - "+fileName+" - ");
		}
		catch(IOException ex){
			System.out.println("Error reading file - "+fileName+" - ");
		}
		
	}
	public ArrayList<String> getJunctions()
	{
		return junctions;
	}
	public ArrayList<String> getRoute1()
	{
		return route1;
	}
	public ArrayList<String> getRoute2()
	{
		return route2;
	}
	public ArrayList<String> getRoute3()
	{
		return route3;
	}
	public ArrayList<String> getRoute4()
	{
		return route4;
	}
	
}
