import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Marathon {
	public static void main(String[] args) throws IOException{
		BufferedReader reader;
		reader = new BufferedReader(new FileReader("marathon.in"));
		String SN = reader.readLine();
		int N = Integer.parseInt(SN);
		int[][] points = new int[N][2];
		String[] line;
		for (int i=0;i<N;i++){
			SN = reader.readLine();
			line = SN.split(" ");
			points[i][0] = Integer.parseInt(line[0]);
			points[i][1] = Integer.parseInt(line[1]);
		}
		reader.close();
		int result = marathon(points,N);
		BufferedWriter writer = new BufferedWriter(new FileWriter("marathon.out"));
		writer.write(Integer.toString(result));
		writer.close();
	}
	public static int marathon(int[][] p,int N){
		int maxS = -1;
		int totalDis = distance(p[0],p[1]);
		for (int i=1;i<N-1;i++){
			int savedDis = distance(p[i-1],p[i])+distance(p[i],p[i+1])-distance(p[i-1],p[i+1]);
			if (savedDis>maxS){
				maxS = savedDis;
			}
			totalDis+=distance(p[i],p[i+1]);
		}
		return totalDis-maxS;
	}
	public static int distance(int[] a, int[] b){
		return Math.abs(a[0]-b[0])+Math.abs(a[1]-b[1]);
	}
}
