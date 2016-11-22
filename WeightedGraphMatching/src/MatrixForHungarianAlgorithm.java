import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MatrixForHungarianAlgorithm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filename = ".\\data\\bipartedFile.csv";
	}
	double[][] generateCostMatrix(String filename)
	{
		String line = "";
		BufferedReader br = null;
		double [][] cost = new double[100][100];
		try {
			br = new BufferedReader(new FileReader(filename));
			
				for(int i=0;i<100;i++)
				{
					for(int j=0;j<100;j++)
					{
						while ((line = br.readLine()) != null) {
							String []data = line.split(";");
							cost[i][j] = Double.parseDouble(data[1]);
							break;
						}
						
					}
					
				}
			
		} catch (FileNotFoundException ex) {
			Logger.getLogger(MatchingBipartedGraph.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(MatchingBipartedGraph.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		
		
		return cost;
	}
}
