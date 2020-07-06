import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CF_Phoenix {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i=0; i<t; i++){
			int n = Integer.parseInt(br.readLine());
			int left = n-1;
			int adder = 1;
			ArrayList<Integer> seq = new ArrayList<Integer>();
			while (left > 0){
				if (left >= 4*adder){
					 seq.add(adder);
					 adder += adder;
				 } else if (left <= 2*adder){
					 seq.add(left-adder);
					 adder = left;
				 } else {
					 seq.add(left/2-adder);
					 adder = left/2;
				 }
				 left -= adder;
			}
			System.out.println(seq.size());
			for (int j=0; j<seq.size(); j++){
				System.out.print(seq.get(j));
				if (j==seq.size()-1){
					System.out.println();
				} else {
					System.out.print(" ");
				}
			}
		}
	}
}
