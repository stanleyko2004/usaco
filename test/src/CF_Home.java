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

public class CF_Home {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		String s = br.readLine();
		int[] pond = new int[n];
		Arrays.fill(pond, (int) 1e9);
		pond[0] = 0;
		for (int i=0; i<n; i++){
			if (s.charAt(i)=='1'){
				for (int j=1; j<=d && i+j<n; j++){
					pond[i+j] = Math.min(pond[i]+1, pond[i+j]);
				}
			}
		}
		System.out.println(pond[n-1]==(int) 1e9 ? -1 : pond[n-1]);
	}
}
