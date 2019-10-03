import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Meeting {
	static int[][] bessie;
	static int[][] elsie;
	
	static boolean[] bessiePos;
	static boolean[] elsiePos;
	
	static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader reader;
		reader = new BufferedReader(new FileReader("meeting.in"));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		bessie = new int[n][n];
		elsie = new int[n][n];
		for (int i=0;i<m;i++){
			st = new StringTokenizer(reader.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			bessie[x][y] = Integer.parseInt(st.nextToken());
			elsie[x][y] = Integer.parseInt(st.nextToken());
		}
		bessiePos = new boolean[20000];
		elsiePos  = new boolean[20000];
		find(bessie,bessiePos,0,0);
		find(elsie,elsiePos,0,0);
		int ans = Integer.MAX_VALUE;
		for (int i=0;i<bessiePos.length;i++){
			if (bessiePos[i] && elsiePos[i]){
				ans = i;
				break;
			}
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("meeting.out"));
		if (ans==Integer.MAX_VALUE){
			writer.write("IMPOSSIBLE");
		} else {
			writer.write(Integer.toString(ans));
		}
		writer.close();
	}
	public static void find (int[][] cowGrid, boolean[] pos,int curF, int curD){
		if (curF==n-1){
			pos[curD]=true;
			return;
		}
		for (int i=curF+1;i<n;i++){
			if (cowGrid[curF][i]>0){
				find(cowGrid,pos,i,curD+cowGrid[curF][i]);
			}
		}
	}
}
