import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Censor2 {

	public static void main(String[] args) throws Exception {

		BufferedReader reader = new BufferedReader(new FileReader("censor.in"));
		FileWriter writer = new FileWriter(new File("censor.out"));

		String line = reader.readLine();
		String censor = reader.readLine();
		int[][] f = new int[censor.length()][26];
		f[0][censor.charAt(0)-'a'] = 1;
		for (int i=1; i<f.length; i++) {
			for (int j=0; j<26; j++) {
				String next = censor.substring(0, i) + (char)('a'+j);
				f[i][j] = prefix(next, censor);
			}
		}
		int cur = 0;
		Stack<Integer> s = new Stack<Integer>();
		StringBuilder ans = new StringBuilder();
		for (int i=0; i<line.length(); i++) {
			cur = f[cur][line.charAt(i)-'a'];
			if (cur == 0) {
				ans.append(makeStr(censor, s));
				ans.append(line.charAt(i));
				s = new Stack<Integer>();
			}
			else if (cur == censor.length()) {
				for (int j=0; j<censor.length()-1; j++)
					s.pop();
				cur = s.empty() ? 0 : s.peek();
			}
			else
				s.push(cur);
		}
		if (s.size() > 0) ans.append(makeStr(censor, s));
		System.out.println(ans);
		writer.write(ans+"\n");
		writer.close();
		reader.close();
	}
	public static StringBuilder makeStr(String text, Stack<Integer> s) {
		StringBuilder ans = new StringBuilder();
		while (s.size() > 0)
			ans.insert(0, text.charAt(s.pop()-1));
		return ans;
	}
	public static int prefix(String sub, String total) {
		for (int i=sub.length(); i>0; i--)
			if (sub.substring(sub.length()-i).equals(total.substring(0, i)))
				return i;
		return 0;
	}
}
class pair {
	public int x;
	public int y;
	public pair(int inx, int iny) {
		x = inx;
		y = iny;
	}
}
