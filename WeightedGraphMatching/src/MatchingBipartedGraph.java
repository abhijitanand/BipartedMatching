import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MatchingBipartedGraph {
	Map<Integer, Integer> MapVLabel = new HashMap<Integer, Integer>();
	Map<Integer, Map<Integer, Integer>> MapUVValue = new HashMap<Integer, Map<Integer, Integer>>();
	Map<Integer, Integer> VUMatching = new HashMap<Integer, Integer>();
	int max = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filename = ".\\data\\bipartedFile.csv";
		MatchingBipartedGraph match = new MatchingBipartedGraph();
		// match.findMaxWeight(filename);
		match.matchBipartedWeightedGraph(filename);
	}

	void findMaxWeight(String filename,
			Map<Integer, Map<Integer, Integer>> MapUVValue) {
		String line = "";
		BufferedReader br = null;
		int  min = 0;
		int localMax = 0, localMin = 0;
		try {
			br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
				String[] data = line.split(";");
				int u = Integer.parseInt(data[0].split(",")[0]);
				int v = Integer.parseInt(data[0].split(",")[1]);
				int value = Integer.parseInt(data[1]);

				MapVLabel.put(v, 0);
				if (MapUVValue.containsKey(u)) {
					Map<Integer, Integer> temp = MapUVValue.get(u);
					temp.put(v, value);
				} else {
					max = max + localMax;
					min = min + localMin;
					localMin = 10000;
					localMax = 0;
					Map<Integer, Integer> temp = new HashMap<Integer, Integer>();
					temp.put(v, value);
					MapUVValue.put(u, temp);
				}
				if (value > localMax) {
					localMax = value;
				}
				if (value < localMin) {
					localMin = value;
				}
			}
			System.out.println("Max:" + max + "Min:" + min);
		} catch (FileNotFoundException ex) {
			Logger.getLogger(MatchingBipartedGraph.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(MatchingBipartedGraph.class.getName()).log(
					Level.SEVERE, null, ex);
		}
	}

	void getMatchForNode(int u) {
		Map.Entry<Integer, Integer> minEntry = null;
		int oldKey = 0;
		int oldU = 0;
		boolean bFlag = false;
		if(MapUVValue.containsKey(u))
		{
			System.out.println(u);
			for (Map.Entry<Integer, Integer> entry : MapUVValue.get(u).entrySet()) {
				if (MapVLabel.get(entry.getKey()) > 0) {
					oldKey = entry.getKey();
					entry.setValue(MapVLabel.get(oldKey) + entry.getValue());
				}
				if ((minEntry == null || entry.getValue() < minEntry.getValue())&& entry.getValue()< max) {
					minEntry = entry;
				}
			}
			if(minEntry !=null)
			{
				int v = minEntry.getKey();
				int minValue = minEntry.getValue();
				MapVLabel.put(v, minValue);	
					if (VUMatching.containsKey(v)) {
						bFlag = true;
						oldU = VUMatching.get(v);
					}
					VUMatching.remove(v);
					VUMatching.put(v, u);		
				while (bFlag) {
					getMatchForNode(oldU);
				}
			}
			

		}
		else
		{
			System.out.println(u);
		}
		
			}

	void matchBipartedWeightedGraph(String filename) {

		findMaxWeight(filename, MapUVValue);

		for (Map.Entry<Integer, Map<Integer, Integer>> pairs : MapUVValue
				.entrySet()) {
			int u = pairs.getKey();
			getMatchForNode(u);
		}
		for (Map.Entry<Integer, Integer> pairs : VUMatching
				.entrySet()) {
			System.out.println(pairs.getKey()+":"+pairs.getValue());
		}
		int minValue = 0;
		for (Map.Entry<Integer, Integer> pairs : MapVLabel
				.entrySet()) {
			minValue = minValue + pairs.getValue();
		}
		System.out.println("Min Value:"+minValue);
	}
}
