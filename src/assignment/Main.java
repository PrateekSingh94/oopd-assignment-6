package assignment;

public class Main {
	public static void main(String args[])
	{
		//ReadRoute rr = new ReadRoute();
		RouteCal rc = new RouteCal();
		CashCalc cc = new CashCalc();
		//System.out.println(rc.existsInRoute("Akshardhama"));
		System.out.println(cc.CalculateCost("Dwarka","IGI Airport"));
		//System.out.println(rc.CalculateIntermediateStations("Dwarka Sec-10","Delhi Aero City"));
		//rc.junctionCalculator();
		//System.out.println(rc.getRouteforStation("New Delhi"));
		
	}
}
