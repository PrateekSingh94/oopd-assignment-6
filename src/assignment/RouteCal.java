package assignment;

import java.util.*;

public class RouteCal{
	

	HashMap<String, ArrayList<ArrayList<String>>> JunctionRouteMap = new HashMap<String, ArrayList<ArrayList<String>>>();
	ArrayList<String> junctions = new ArrayList<String>();
	ArrayList<String> route1 = new ArrayList<String>();
	ArrayList<String> route2 = new ArrayList<String>();
	ArrayList<String> route3 = new ArrayList<String>();
	ArrayList<String> route4 = new ArrayList<String>();
	ArrayList<ArrayList<String>> routeIndex = new ArrayList<ArrayList<String>>();
	/*public void junctionCalculator()
	{
		getData();
		ArrayList<ArrayList<String>> routeData = new ArrayList<ArrayList<String>>();
		routeIndex.add(route1);
		routeIndex.add(route2);
		routeIndex.add(route3);
		routeIndex.add(route4);
		for(String data: junctions)
		{
			routeData.clear();
			//System.out.println(data);
			for(ArrayList<String> Iterator: routeIndex)
			{
				
				//System.out.println(Iterator);
				if(Iterator.contains(data)==true)
				{
					routeData.add(Iterator);	
				}
				JunctionRouteMap.put(data, routeData);
				
			}
				//System.out.println(Iterator);
			}	
		
		System.out.println(JunctionRouteMap);
	}*/
	public void getData()
	{
		routeIndex.clear();
		ReadRoute rr = new ReadRoute();
		rr.readList();
		junctions = rr.getJunctions();
		route1 = rr.getRoute1();
		route2 = rr.getRoute2();
		route3 = rr.getRoute3();
		route4 = rr.getRoute4();
		routeIndex.add(route1);
		routeIndex.add(route2);
		routeIndex.add(route3);
		routeIndex.add(route4);
	}
	public ArrayList<ArrayList<String>> getRouteforStation(String s)
	{
		getData();
		ArrayList<ArrayList<String>> temp = new ArrayList();
		temp.clear();
		if(route1.contains(s)==true) temp.add(route1);
		if(route2.contains(s)==true) temp.add(route2);
		if(route3.contains(s)==true) temp.add(route3);
		if(route4.contains(s)==true) temp.add(route4);
		return temp;
	}
	public String getJunction(ArrayList<String> source, ArrayList<String> destination)
	{
		String junction = null;
		for(String i: source)
		{
			for(String j: destination)
			{
				if(i.equals(j)) {junction = i;break;}
			}
		}
		
		
		return junction;
	}
	public ArrayList<String> getCommon(String source ,String destination)
	{
		ArrayList<ArrayList<String>> SourceRouteArray = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> DestinationRouteArray = new ArrayList<ArrayList<String>>();
		ArrayList<String> Common = new ArrayList<String>();
		SourceRouteArray = getRouteforStation(source);
		DestinationRouteArray = getRouteforStation(destination);
		//Common = SourceRouteArray;
		//System.out.println(Common);
		Common.retainAll(DestinationRouteArray);
		System.out.println(Common);
		return Common;
	}
	public int ReturnPositive(int a, int b)
	{
		if(a<0 || b<0) return -1;
		System.out.println(b-a);
		if(a>b) return a-b;
		else return b-a;
	}
	public int GetSingleTrackDistance(String source, String destination, ArrayList<String> route)
	{
		int start = route.indexOf(source);
		int end = route.indexOf(destination);
		int diff = start - end;
		if(diff>0) return diff;
		else return -1*diff;
	}
	public int CalculateIntermediateStations(String source, String destination)
	{
		ArrayList<ArrayList<String>> SourceRouteArray = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> DestinationRouteArray = new ArrayList<ArrayList<String>>();
		ArrayList<String> Common = new ArrayList();
		ArrayList<String> Common1 = new ArrayList();
		ArrayList<String> Tempor = new ArrayList();
		ArrayList<String> TempStore = new ArrayList();
		ArrayList<String> TempStore2 = new ArrayList();
		Common.clear();
		SourceRouteArray = getRouteforStation(source);
		DestinationRouteArray = getRouteforStation(destination);
		for(ArrayList<String> data: SourceRouteArray)
		{
			for(ArrayList<String> data2: DestinationRouteArray)
			{
				TempStore = data;
				TempStore2 = data2;
				//System.out.println("Point 1"+data);
				//System.out.println(data2);
				//System.out.println("Point 2"+data);
				if(TempStore.equals(TempStore2))
				{
					return GetSingleTrackDistance(source,destination,data2);
				}
				
				else
				{
					//System.out.println("Point 3"+data);
					//TempStore = data;
					//data = TempStore;
					//data2 = TempStore2;
					String junction;
					//ArrayList<String> common = new ArrayList<>();
					//common = data;
					//common.retainAll(data2);
					junction = getJunction(data, data2);
					//System.out.println("Point 3"+data);
					if(junction==null)
					{
						//System.out.println(junction);
						return GetWayWithoutJunction(data,data2,source,destination);
					}
					else
					{
						//System.out.println("Point 4"+data);
						System.out.println("Junction "+junction);
						//System.out.println(data);
						//System.out.println(data2);
						return GetWayWithJunction(data,data2,junction,source,destination);
					}
				}
			}
		}
		return -1;
	}
	public int GetWayWithJunction(ArrayList<String> data, ArrayList<String> data2, String junction, String source,
			String destination) {
		// TODO Auto-generated method stub
		//System.out.println(source+junction+GetSingleTrackDistance(source, junction, data));
		//System.out.println(junction+destination+GetSingleTrackDistance(junction, destination, data2));
		return GetSingleTrackDistance(source, junction, data)+GetSingleTrackDistance(junction, destination, data2);
	}
	public int GetWayWithoutJunction(ArrayList<String> data, ArrayList<String> data2, String source,
			String destination) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<String>> remove = new ArrayList<>();
		remove.add(data);
		remove.add(data2);
		ArrayList<ArrayList<String>> remove1 = new ArrayList<>();
		remove1 = routeIndex;
		remove1.removeAll(remove);
		ArrayList<Integer> Calc = new ArrayList<>();
		//Case1: source->route1->destination
		System.out.println("Case 1");
		String junction = getJunction(data, remove1.get(0));
		String junction2 = getJunction(remove1.get(0),data2);
		System.out.println("Junction 1 " +junction);
		System.out.println("Junction 2 " +junction2);
		if(junction!=null && junction2!=null)
		{
			//System.out.println(GetWayWithJunction(data, remove1.get(0), junction, source, junction2)+GetWayWithJunction(remove1.get(0), data2, junction2, junction2, destination));
			Calc.add(GetWayWithJunction(data, remove1.get(0), junction, source, junction2)+GetWayWithJunction(remove1.get(0), data2, junction2, junction2, destination));
		}
		//case2: source->route2->destination
		System.out.println("Case 2");
		junction = getJunction(data, remove1.get(1));
		junction2 = getJunction(remove1.get(1),data2);
		System.out.println("Junction 1 " +junction);
		System.out.println("Junction 2 " +junction2);
		if(junction!=null && junction2!=null)
		{
			Calc.add(GetWayWithJunction(data, remove1.get(1), junction, source, junction2)+GetWayWithJunction(remove1.get(1), data2, junction2, junction2, destination));
		}
		//case3: source->route1->route2->destination
		System.out.println("Case 3");
		junction = getJunction(data, remove1.get(0));
		junction2 = getJunction(remove1.get(0), remove1.get(1));
		String junction3 = getJunction(remove1.get(1), data2);
		System.out.println("Junction 1 " +junction);
		System.out.println("Junction 2 " +junction2);
		System.out.println("Junction 3 " +junction3);
		if(junction!=null && junction2!=null && junction3!=null)
		{
			Calc.add(GetWayWithJunction(data, remove1.get(0), junction, source, junction2)+GetWayWithJunction(remove1.get(0), remove1.get(1), junction2, junction2, junction3)+GetWayWithJunction(remove1.get(1), data2, junction3, junction3, destination));
		}
		//case4: source->route2->route1->destination
		System.out.println("Case 4");
		junction = getJunction(data, remove1.get(1));
		junction2 = getJunction(remove1.get(1), remove1.get(0));
		junction3 = getJunction(remove1.get(0), data2);
		System.out.println("Junction 1 " +junction);
		System.out.println("Junction 2 " +junction2);
		System.out.println("Junction 3 " +junction3);
		if(junction!=null && junction2!=null && junction3!=null)
		{
			Calc.add(GetWayWithJunction(data, remove1.get(1), junction, source, junction2)+GetWayWithJunction(remove1.get(1), remove1.get(0), junction2, junction2, junction3)+GetWayWithJunction(remove1.get(0), data2, junction3, junction3, destination));
		}
		return Collections.min(Calc);
	}
}

