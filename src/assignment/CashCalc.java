package assignment;
import java.util.*;

public class CashCalc {
	public int CalculateCost(String source, String destination)
	{
		int Cost;
		RouteCal rc = new RouteCal();
		int StationCount = rc.CalculateIntermediateStations(source, destination);
		if(StationCount<2)return 8;
		else return 8+(StationCount-2);
	}
}
