import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Lemonade {
	
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("lemonade.in"));
		int n = Integer.parseInt(reader.readLine());
		int[] desire = new int[n];
		StringTokenizer st = new StringTokenizer(reader.readLine());
		reader.close();
		for (int i=0;i<n;i++){
			desire[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(desire);
		int left = 0;
		int ans = 0;
		for (int i=0;i<n;i++){
			if (desire[n-i-1]<i-left){
				left++;
			} else {
				ans++;
			}
		}
		PrintWriter writer = new PrintWriter(new FileWriter("lemonade.out"));
		writer.write(Integer.toString(ans));
		writer.close();
	}
}