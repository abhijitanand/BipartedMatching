import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MatchingBipartedGraph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filename = ".\\data\\bipartedFile.csv";
		MatchingBipartedGraph match = new MatchingBipartedGraph();
		match.findMaxWeight(filename);
	}

	void findMaxWeight(String filename) {
		String line = "";
		BufferedReader br = null;
		int max=0,min=0;
		int localMax=0, localMin=0;
		Map<Integer, Map<Integer,Integer>> MapUVValue = new HashMap();
		try {
			br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
				String[] data = line.split(";");
				int u = Integer.parseInt(data[0].split(",")[0]);
				int v = Integer.parseInt(data[0].split(",")[1]);
				int value = Integer.parseInt(data[1]);
			
				if(MapUVValue.containsKey(u))
				{
					Map<Integer,Integer> temp = MapUVValue.get(u);
					temp.put(v, value);
				}
				else
				{
					max = max + localMax;
					min = min + localMin;
					localMin=10000;
					localMax=0;
					Map<Integer,Integer> temp = new HashMap<Integer, Integer>();
					temp.put(v, value);
					MapUVValue.put(u, temp);
				}
				if(value>localMax)
				{
					localMax = value;
				}
				if(value<localMin)
				{
					localMin = value;
				}
			}
			System.out.println("Max:"+max+"Min:"+min);
		} catch (FileNotFoundException ex) {
			Logger.getLogger(MatchingBipartedGraph.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(MatchingBipartedGraph.class.getName()).log(
					Level.SEVERE, null, ex);
		}
	}

	void matchBipartedWeightedGraph(String filename) {

	}
}
