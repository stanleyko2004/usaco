import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Helpcross {
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("helpcross.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("helpcross.out")));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		int c = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		ArrayList<Integer> chickens = new ArrayList<Integer>(c);
		for (int i=0;i<c;i++){
			chickens.add(Integer.parseInt(reader.readLine()));
		}
		ArrayList<Cow> cows = new ArrayList<Cow>(n);
		for (int i=0;i<n;i++){
			st = new StringTokenizer(reader.readLine());
			cows.add(new Cow(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		Collections.sort(chickens);
		Collections.sort(cows);
		int ans = 0;
        for(int i = 0; i < cows.size(); i++){
            Cow cow = cows.get(i);
            int index = Collections.binarySearch(chickens, cow.s);
            if (index < 0){
                index = index*-1 - 1;
                if (index >= chickens.size())
                    continue;
            }
            if(chickens.get(index) > cow.e){
            	continue;
            }
            chickens.remove(index);
            ans++;
            
        }
		writer.println(ans);
		writer.close();
		reader.close();
	}	
	public static class Cow implements Comparable<Cow>{
		public int s,e;
		public Cow(int start,int end){
			s = start;
			e = end;
		}
		public int compareTo(Cow c){
    		if (e < c.e){
                return -1;
            }
            else if (e > c.e){
                return 1;
            }
            if (s < c.s) {
                return 1;
            } else if (s > c.s) {
                return -1;
            }
            return 0;
    	}
	}
}
