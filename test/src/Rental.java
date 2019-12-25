import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Rental {
	public static void main(String[] args) throws IOException{
		Scanner reader = new Scanner(new FileReader("rental.in"));
		int n = reader.nextInt();
		int m = reader.nextInt();
		int r = reader.nextInt();
		Integer[] output = new Integer[n];
		for (int i=0;i<n;i++){
			output[i] = reader.nextInt();
		}
		milk[] milking = new milk[m];
		for (int i=0;i<m;i++){
			milking[i] = new milk(reader.nextInt(),reader.nextInt());
		}
		int[] renting = new int[r];
		for (int i=0;i<r;i++){
			renting[i] = reader.nextInt();
		}
		reader.close();
		Arrays.sort(output,Collections.reverseOrder());
		Arrays.sort(milking);
		Arrays.sort(renting);
		long earnings = 0L;
		int mCount = 0;
		int stop = r;
		for (int i=0;i<n;i++){
			int utility = 0;
			while (output[i]>0){
				if (output[i]>milking[mCount].quant){
					utility += milking[mCount].total;
					output[i] -= milking[mCount].quant;
					mCount++;
					continue;
				}
				utility += output[i]*milking[mCount].qual;
				milking[mCount].quant -= output[i];
				milking[mCount].recal();
				output[i] = 0;
				break;
			}
			if (n-i>r){
				earnings+=utility;
				continue;
			} else {
				if (utility>renting[i]){
					earnings += utility;
				} else {
					stop = i-1;
					break;
				}
			}
		}
		for (int i=stop;i<r;i++){
			earnings+=renting[i];
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("rental.out"));
		writer.write(Long.toString(earnings)+'\n');
		writer.close();
	}
	public static class milk implements Comparable<milk>{
		int quant,qual,total;
		public milk(int quantity,int quality){
			quant = quantity;
			qual = quality;
			total = quantity*quality;
		}
		public void recal(){
			this.total = this.quant*this.qual;
		}
		public int compareTo(milk other){
			return other.total-this.total;
		}
	}
}
