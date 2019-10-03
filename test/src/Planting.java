import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class Planting {
	public static void main(String[] args) throws IOException{
		Scanner reader = new Scanner(new FileReader("planting.in"));
		int n = reader.nextInt();
		int[] fields = new int[n];
		for (int i=0;i<n-1;i++){
			fields[reader.nextInt()-1]++;
			fields[reader.nextInt()-1]++;
		}
		int max = -1;
		for (int i:fields){
			if (i>max){
				max = i;
			}
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("planting.out"));
		writer.write(Integer.toString(max+1));
		writer.close();
	}
}
