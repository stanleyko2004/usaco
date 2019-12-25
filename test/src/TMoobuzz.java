import java.io.*;
import java.util.*;
import java.util.Arrays;

public class TMoobuzz {
    final static int[] key = new int[]{1,2,4,7,8,11,13,14};
    public static void main (String [] args) throws IOException {
    	PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("diff.out")));
    	for (int i=1;i<10000000;i++){
    		int N = i;
    	    
    	    int m = bsearch(0, 1000000001, N);
    	    int c = m;
    	    while (m % 5 == 0 || m % 3 == 0) m--;
    	    int s = mine(i);
    	    if (m!=s){
    	    	writer.println(i+" "+m+" "+s);
    	    }
    	}
    	writer.close();
    }
    public static int mine (int n){
		int offset = key[(n-1)%8];
		int add = (n-1)/8;
		return add*15+offset;
    }
    public static int bsearch(int L, int R, int target)
    {
        while (L <= R)
        {
            int m = (L+R) / 2;
            if (val(m) < target) L = m + 1;
            if (val(m) > target) R = m - 1;
            if (val(m) == target) return m;
        }
        return 0;
    }
    public static int val(int n) {return (n - (n / 3) - (n/5) + (n/15));}
}