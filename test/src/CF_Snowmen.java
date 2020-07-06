import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CF_Snowmen {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		HashMap<Integer, Integer> balls = new HashMap<Integer, Integer>();
		for (int i=0; i<n; i++){
			int s = Integer.parseInt(st.nextToken());
			if (balls.containsKey(s)){
				balls.put(s, balls.get(s)+1);
			} else {
				balls.put(s, 0);
			}
		}
		Iterator it = balls.entrySet().iterator();
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		while (it.hasNext()){
			Map.Entry mapElement = (Map.Entry)it.next();
			int k = (int) mapElement.getKey();
			int v = (int) mapElement.getValue();
			pq.add(new Pair(k, v));
		}
		ArrayList<int[]> snowmen = new ArrayList<int[]>();
		while (pq.size() > 2){
			Pair first = pq.poll();
			Pair second = pq.poll();
			Pair third = pq.poll();
			int[] temp = new int[]{first.x, second.x, third.x};
			Arrays.sort(temp);
			int placeholder = temp[2];
			temp[2] = temp[0];
			temp[0] = placeholder;
			snowmen.add(temp);
			first.y -= 1; second.y -= 1; third.y -= 1;
			if (first.y>0){
				pq.offer(first);
			}
			if (second.y>0){
				pq.offer(first);
			}
			if (third.y>0){
				pq.offer(first);
			}
		}
		System.out.println(snowmen.size());
		for (int[] i : snowmen){
			System.out.println(i[0]+" "+i[1]+" "+i[2]);
		}
	}
	public static class Pair implements Comparable<Pair>{
		public int x,y;
		public Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
		public int compareTo(Pair other){
			return other.y-this.y;
		}
	}
}
