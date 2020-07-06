import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Cereal {
	public static int[] f,s,flavors;
	public static boolean[] onSecond;
	public static int cur;
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(new File("cereal.in")));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("cereal.out")));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		f = new int[n];
		s = new int[n];
		for (int i=0; i<n; i++){
			st = new StringTokenizer(reader.readLine());
			f[i] = Integer.parseInt(st.nextToken())-1;
			s[i] = Integer.parseInt(st.nextToken())-1;
		}
		reader.close();
		cur = 0;
		flavors = new int[m];
		Arrays.fill(flavors, -1);
		int[] ans = new int[n];
		for (int i=n-1; i>=0; i--){
			int cow = i;
			int flav = f[i];
			//kick off who ever is on the cereal
			while (true){
				if (flavors[flav] == -1){//unoccupied
					flavors[flav] = cow;
					cur++;
					break;
				} else if (flavors[flav] < cow){//someone more senior is there
					break;
				} else {//kick
					int temp = flavors[flav];//to be kicked
					flavors[flav] = cow;//put in cow
					if (flav == s[temp]){//kicked was already on second
						break;
					}
					cow = temp;//kicked was on first, set kicked to kick next cow
					flav = s[temp];//set flavor to kick next cow
				}
			}
			ans[i] = cur;
		}
		for (int i=0; i<n; i++){
			writer.println(ans[i]);
		}
		writer.close();
	}
}
