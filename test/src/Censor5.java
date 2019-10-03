import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Censor5 {
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("censor.in"));
		String toBe = reader.readLine();
		String censor = reader.readLine();
		BufferedWriter writer = new BufferedWriter(new FileWriter("censor.out"));
		char start = censor.charAt(0);
		int cLen = censor.length();
		Stack<Character> res = new Stack<Character>();
		ArrayList<Pair> prev = new ArrayList<Pair>();
		for (int i=0;i<toBe.length();i++){
			boolean occured = false;
			char curChar = toBe.charAt(i);
			res.push(curChar);
			if (curChar == start){
				prev.add(0,new Pair(i,true,-1));
			}
			if (!prev.isEmpty()){
				for (int j=0;j<prev.size();j++){
					Pair cur = prev.get(j);
					if (cur.pending){
						if (curChar == censor.charAt(cur.len+1)){
							occured = true;
							cur.len++;
						} else {
							cur.pending = false;
						}
					} else {
						break;
					}
				}
			}
			if (!occured){
				prev.clear();
			}
			if (!prev.isEmpty()){
				for (int j=0;j<prev.size();j++){
					if (prev.get(j).len==cLen-1){
						int s = prev.get(j).pos;
						for (int k=0;k<=j;k++){
							prev.remove(0);
						}
						for (Pair k:prev){
							if (s <= k.len+k.pos+1){
								k.pending = true;
								k.len = s-k.pos-1;
								k.pos = s+cLen-k.len;
							}
						}
						for (int k=0;k<cLen;k++){
							res.pop();
						}
						break;
					}
				}
			}
			
		}
		StringBuilder ans = new StringBuilder();
		for (char i:res){
			ans.append(i);
		}
		System.out.println(ans);
		writer.write(ans.toString());
		writer.close();
		reader.close();
	}
	public static class Pair {
		public int pos,len;
		public boolean pending;
		public Pair(int po,boolean pend,int l){
			pos = po;
			pending = pend;
			len = l;
		}
	}
}
