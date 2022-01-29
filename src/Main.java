import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

	static Scanner sc = new Scanner(System.in);
	static int n,m,p,q;
	static List<Path> paths = new ArrayList<>();
	static int[] setsOfCities;
	static int[] setsOfPlanets;
	static int noOfCitySets;
	static int noOfPlanetSets;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int maxCost = 0;
		
		n=sc.nextInt();
		m=sc.nextInt();
		p=sc.nextInt();
		q=sc.nextInt();
		
		setsOfCities = new int[m];
		noOfCitySets = m;
		setsOfPlanets = new int[n];
		noOfPlanetSets = n;
		
		
		
		for (int i=0;i<setsOfCities.length;i++) {
			setsOfCities[i]=i;
		}
		
		for (int i=0;i<setsOfPlanets.length;i++) {
			setsOfPlanets[i]=i;
		}
		
		int tmpCost = 0;
		for (int i=0;i<p;i++) {
			Path path = new Path(PathType.flight, sc.nextInt(),sc.nextInt(),sc.nextInt());
			paths.add(path);
			tmpCost+=path.cost;
		}
		maxCost+=tmpCost*n;
		
		tmpCost = 0;
		for (int i=0;i<q;i++) {
			Path path = new Path(PathType.portal, sc.nextInt(),sc.nextInt(),sc.nextInt());
			paths.add(path);
			tmpCost+=path.cost;
		}
		maxCost+=tmpCost*m;
		
		Collections.sort(paths, (x,y)->x.cost-y.cost);
		
		int minCost = getMinCost();
		System.out.println(maxCost-minCost);
		
	}

	private static int getMinCost() {
		// TODO Auto-generated method stub
		int minCost = 0;
		int idx = 0;
		while (!(noOfCitySets==1 && noOfPlanetSets==1)) {
			Path path = paths.get(idx);
			
			if (path.type == PathType.flight) {
				int set1 = getSet(path.node1);
				int set2 = getSet(path.node2);
				if (set1!=set2) {
					mergeSet(setsOfCities,set1,set2);
					minCost += path.cost * noOfPlanetSets;
					noOfCitySets -= 1;
				} 
			}
			else {
				
			}
		}
		
		return minCost;
	}


}


enum PathType {
	flight,
	portal
}

class Path {
	PathType type;
	int node1;
	int node2;
	int cost;
	
	public Path(PathType type, int node1,int node2, int cost) {
		this.type = type;
		this.node1=node1;
		this.node2 = node2;
		this.cost = cost;
	}
}