import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Random;

//Generate complete weighted biparted graph
public class BipartedGraphGeneration {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String bipFile = ".\\data\\bipartedFile.csv";
		int numNodes =200;
		BipartedGraphGeneration bG = new BipartedGraphGeneration();
		bG.generateCompleteWeightedBipartedGraph(bipFile, numNodes);
	}

	void generateCompleteWeightedBipartedGraph(String bipFile, int numNodes) {
		int minimum = 1;
		int maximum = 100;
		try {
			File statText = new File(bipFile);
			FileOutputStream is = new FileOutputStream(statText);
			OutputStreamWriter osw = new OutputStreamWriter(is);
			Writer w = new BufferedWriter(osw);

			for(int u=1;u<=numNodes;u++)
			{
				for(int v=1;v<=numNodes;v++)
				{
					if(u!=v && u%2!=0 && v%2==0)
					{
						String uv = Integer.toString(u).concat(",").concat(Integer.toString(v));
						Random rn = new Random();
						int range = maximum - minimum + 1;
						int randomNum =  rn.nextInt(range) + minimum;
						w.write(uv+";"+Integer.toString(randomNum)+"\n");
					}
				}
			}
			w.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
