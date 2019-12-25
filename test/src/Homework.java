import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Homework {
	public static void main(String[] args) throws IOException{
		Scanner reader = new Scanner(new FileReader("homework.in"));
		int n = reader.nextInt();
		int[] scores = new int[n];
		for (int i=0;i<n;i++){
			scores[i] = reader.nextInt();
		}
		reader.close();
		double max = -1.0;
		ArrayList<Integer> maxInd = new ArrayList<Integer>();
		int min = scores[n-1];
		int sum = scores[n-1];
		int cur = 0;
		for (int i=n-2;i>0;i--){
			cur++;
			if (scores[i]<min){
				min = scores[i];
			}
			sum += scores[i];
			double temp = ((double)(sum-min))/(cur);
			if (temp>max){
				max = temp;
				maxInd.clear();
				maxInd.add(i);
			} else if (temp==max){
				maxInd.add(0,i);
			}
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("homework.out"));
		for (Integer i:maxInd){
			writer.write(Integer.toString(i)+'\n');
		}
		writer.close();
	}
}
