import java.util.*;
import java.io.*;

public class Censor6 {

	public static void main(String[] args) throws Exception {

		BufferedReader stdin = new BufferedReader(new FileReader("censor.in"));
		FileWriter fout = new FileWriter(new File("censor.out"));

		String line = stdin.readLine();
		String pattern = stdin.readLine();

		// Get the Z values.
		int[] zArr = getZarr(pattern);

		// Make our previous array (longest previous match)
		int[] prev = new int[pattern.length()];
		int idx = 0, maxPrev = 0;

		// Sweep through the zArr. Idea is if z[3] = 7, then line[3] -> line[0], line[4] -> line[1], etc.
		while (idx < zArr.length) {

			// This is a count adjusted value for the longest previous prefix running.
			maxPrev--;

			// Only if the z value here is greater than 0 do we care to update.
			if (zArr[idx] > 0 && idx > 0) {

				// This z value only makes an update if it supercedes the most important previous z value.
				if (zArr[idx] > maxPrev) {

					// For a speed up, the only values that matter are ones not affected by an old z value.
					// Hence the weird starting spot for the loop.
					for (int i=Math.max(0,maxPrev); i<zArr[idx]; i++) prev[idx+i] = Math.max(prev[idx+i],i+1);
				}

				// Update this.
				maxPrev = Math.max(maxPrev, zArr[idx]);
			}

			// Go to the next index.
			idx++;
		}

		// Cur is index into the pattern we are currently matching, sI is index into our stack.
		int cur = 0;
		int sI = 0;

		// Stores integer values of indexes into each pattern that are in our stack.
		int[] iStack = new int[line.length()];

		// Stores our current output.
		char[] buffer = new char[line.length()];

		// Now, just simulate what they ask, with a stack, for speed.
		for (int i=0; i<line.length(); i++) {

			// Start us off.
			if (cur == 0) {
				cur = line.charAt(i) == pattern.charAt(0) ? 1 : 0;
			}

			// We have some stuff matching.
			else {

				// We can't do worse than 0.
				while (cur >= 0) {

					// This is a match - get out.
					if (line.charAt(i) == pattern.charAt(cur)) {
						cur++;
						break;
					}

					if (cur == 0) break;

					// The next place to try.
					cur = prev[cur-1];
				}
			}

			// We got a match, pop off these characters.
			if (cur == pattern.length()) {
				sI -= (pattern.length()-1);
				cur = sI > 0 ? iStack[sI-1] : 0;
			}

			// Just push.
			else {
				buffer[sI] = line.charAt(i);
				iStack[sI] = cur;
				sI++;
			}

		}

		// Copy over result.
		char[] res = new char[sI];
		for (int i=0; i<sI; i++)
			res[i] = buffer[i];

		// Ta da!
		fout.write((new String(res))+"\n");
		fout.close();
		stdin.close();
	}

    // Efficiently makes the string by adding each char from s.
	public static StringBuilder makeStr(String text, Stack<Integer> s) {
		StringBuilder ans = new StringBuilder();
		while (s.size() > 0)
			ans.insert(0, text.charAt(s.pop()-1));
		return ans;
	}

	//  Fills Z array for given string str[]
	public static int[] getZarr(String str) {

    	int n = str.length();
    	int[] Z = new int[n];
    	int L, R, k;
    	Z[0] = n; // Necessary for my purposes...

    	// [L,R] make a window which matches with prefix of s
    	L = R = 0;
    	for (int i = 1; i < n; ++i) {

        	// if i>R nothing matches so we will calculate.
        	// Z[i] using naive way.
        	if (i > R) {
	            L = R = i;

    	        // R-L = 0 in starting, so it will start
        	    // checking from 0'th index. For example,
            	// for "ababab" and i = 1, the value of R
            	// remains 0 and Z[i] becomes 0. For string
            	// "aaaaaa" and i = 1, Z[i] and R become 5
            	while (R<n && str.charAt(R-L) == str.charAt(R))
                	R++;
            	Z[i] = R-L;
            	R--;
        	}
        	else {

            	// k = i-L so k corresponds to number which
            	// matches in [L,R] interval.
            	k = i-L;

            	// if Z[k] is less than remaining interval
            	// then Z[i] will be equal to Z[k].
            	// For example, str = "ababab", i = 3, R = 5
            	// and L = 2
            	if (Z[k] < R-i+1)
                	 Z[i] = Z[k];

            	// For example str = "aaaaaa" and i = 2, R is 5,
            	// L is 0
            	else {

                	//  else start from R  and check manually
                	L = i;
                	while (R<n && str.charAt(R-L) == str.charAt(R))
                    	R++;
                	Z[i] = R-L;
                	R--;
            	}
        	}
    	}

    	return Z;
	}
}
