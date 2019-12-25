import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Sort1 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("sort.in"));
		int n = Integer.parseInt(reader.readLine());
		int[] nums = new int[n];
		HashMap<Integer,Integer> PInd = new HashMap<Integer,Integer>(n);
		for (int i=0;i<n;i++){
			int num = Integer.parseInt(reader.readLine());
			nums[i] = num;
			PInd.put(num, i);
		}
		reader.close();
		Arrays.sort(nums);
		HashMap<Integer,Integer> AInd = new HashMap<Integer,Integer>();
		for (int i=0;i<n;i++){
			AInd.put(nums[i],i);
		}
		int max = 0;
		for (Integer key: AInd.keySet()){
			int diff = PInd.get(key)-AInd.get(key);
			max = Math.max(max, diff);
		}
		PrintWriter writer = new PrintWriter(new FileWriter("sort.out"));
		writer.write(Integer.toString(max+1));
		writer.close();
	}
}