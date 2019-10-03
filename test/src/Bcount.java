import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Bcount {
	
	public static void main(String[] args) throws IOException{
		Scanner reader = new Scanner(new FileReader("bcount.in"));
		int n = reader.nextInt();
		int q = reader.nextInt();
		int[][] count = new int[n+1][3];
		for (int i=1;i<n+1;i++){
			for (int j=0;j<3;j++){
				count[i][j] = count[i-1][j];
			}
			count[i][reader.nextInt()-1]++; 
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("bcount.out"));
		for (int i=0;i<q;i++){
			int start = reader.nextInt();
			int end = reader.nextInt();
			writer.write(Integer.toString(count[end][0]-count[start-1][0])+" "+
						Integer.toString(count[end][1]-count[start-1][1])+" "+
						Integer.toString(count[end][2]-count[start-1][2])+"\n");
		}
		writer.close();
	}
}
