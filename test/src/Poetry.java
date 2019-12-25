import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class Poetry {
	public final static long mod = 1000000007L;
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("poetry.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("poetry.out")));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		HashMap<Integer,ArrayList<Integer>> s2r = new HashMap<Integer,ArrayList<Integer>>();//syllables to rhyme class
		int SS = 5001;//smallest number of syllables
		for (int i=0;i<n;i++){
			st = new StringTokenizer(reader.readLine());
			int syllables = Integer.parseInt(st.nextToken());
			int rhyme = Integer.parseInt(st.nextToken());
			if (!s2r.containsKey(syllables)){
				s2r.put(syllables, new ArrayList<Integer>());
			}
			s2r.get(syllables).add(rhyme);
			if (syllables<SS){
				SS = syllables;
			}
		}
		Set<Integer> sylPos = s2r.keySet();
		long[] dp = new long[k];
		dp[0] = 1L;
		HashMap<Integer,Long> r2n = new HashMap<Integer,Long>();//rhyme class to number of ways it could end in that class
		for (int i=0;i<k+1-SS;i++){
			if (dp[i]==0L){
				continue;
			}
			for (int j : sylPos){
				if (i+j<k){
					dp[i+j]+=(dp[i]*s2r.get(j).size())%mod;
				} else if (i+j==k){
					for (int rc:s2r.get(j)){//loop through rhyme class
						if (!r2n.containsKey(rc)){
							r2n.put(rc, 0L);
						}
						r2n.put(rc, (r2n.get(rc)+dp[i])%mod);
					}
				}
			}
		}
		HashMap<String,Integer> rs = new HashMap<String,Integer>();//rhyme scheme
		for (int i=0;i<m;i++){
			String rhyme = reader.readLine();
			if (!rs.containsKey(rhyme)){
				rs.put(rhyme, 0);
			}
			rs.put(rhyme,rs.get(rhyme)+1);
		}
		Collection<Integer> struct = rs.values();
		Collection<Long> pos = r2n.values();
		long ans = 1L;
		for (int i:struct){
			long temp = 0L;
			for (Long j:pos){
				temp = (temp+exp(j%mod,i)+mod)%mod;
			}
			ans = (ans*temp+mod)%mod;
		}
		writer.print(ans);
		writer.close();
		reader.close();
	}
	public static long exp(long base, int power){
	      if(power == 0){
	    	  return 1;
	      }
	      if(power == 1){
	    	  return (base + mod) % mod;
	      }
	      long ans = exp(base,power/2);
	      ans = (ans*ans + mod) % mod;
	      if(power%2 == 1){
	    	  ans = (ans*base + mod) % mod;
	      }
	      return (ans + mod) % mod;
	   }
}
