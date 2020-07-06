import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Berries {
	public static int mod;
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(new File("berries.in")));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("berries.out")));
		StringTokenizer st = new StringTokenizer(reader.readLine());  
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(reader.readLine());
        Integer[] trees = new Integer[n];
        int mxB = 0;
        for (int i=0; i<n; i++){
        	trees[i] = Integer.parseInt(st.nextToken());
        	mxB = Math.max(mxB, trees[i]);
        }
        reader.close();
        int res = 0;
        for (int b=1; b<mxB; b++){
        	int full = 0;
    		for(int i=0;i<n;i++){
    			full += trees[i]/b;
    		}
    		if(full < k/2)
    			break;
    		if(full >= k)
    		{
    			res = Math.max(res, b*(k/2));
    			continue;
    		}
    		mod = b;
    		Arrays.sort(trees, new modComp());
    		int cur = b*(full - k/2);
    		for(int i=0;i<n && i+full<k;i++){
    			cur += trees[i]%b;
    		}
    		res = Math.max(res,cur);
        }
        writer.println((int)res);
        writer.close();
	}
	public static class modComp implements Comparator<Integer>{
		public int compare(Integer a, Integer b) {
			return (b%mod)-(a%mod);
		}
	}
}
