import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Learning {
	public static void main(String[] args){// throws IOException{
		System.out.print("this is working");
/*
		BufferedReader reader;
		reader = new BufferedReader(new FileReader("learning.in"));
		String line = reader.readLine();
		String[] NAB = line.split(" ");
		int N = Integer.parseInt(NAB[0]);
		List<Integer> s = new ArrayList<Integer>();
		List<Integer> ns = new ArrayList<Integer>();
		for (int i=0;i<N;i++){
			line = reader.readLine();
			String[] sw = line.split(" ");
			if (sw[0].equals("S")){
				s.add(Integer.parseInt(sw[1]));
			} else if (sw[0].equals("NS")){
				ns.add(Integer.parseInt(sw[1]));
			}
		}
		reader.close();
		Collections.sort(s);
		Collections.sort(ns);
		int result = learning(s,ns);
		System.out.println(result);
		BufferedWriter writer = new BufferedWriter(new FileWriter("learning.out"));
		writer.write(result);
		writer.close();
/*
		String str = "Hello";
		BufferedWriter writer = new BufferedWriter(new FileWriter("learning.out"));
		writer.write(str);
		writer.close();
*
	}
	public static int learning(List<Integer> s, List<Integer> ns){
		int sCount = 0;
		int nsCount = 0;
		boolean cur;
		if (s.get(0)>ns.get(0)){
			cur = false;
			nsCount++;
		} else {
			cur = true;
			sCount++;
		}
		int ans = 0;
		s.add(1000000001);
		ns.add(1000000001);
		while (sCount<s.size()-1 || nsCount<ns.size()-1){
			if (cur){
				if (ns.get(nsCount)<=s.get(sCount)){
					ans += (ns.get(nsCount)-s.get(sCount-1))/2+1;
					cur = false;
					nsCount++;
				} else {
					ans += ns.get(nsCount)-s.get(sCount-1)+1;
					sCount++;
				}
			} else if (!cur){
				if (ns.get(nsCount)>=s.get(sCount)){
					ans += (s.get(sCount)-ns.get(nsCount-1))/2+1;
					cur = true;
					sCount++;
				} else {
					nsCount++;
				}
			}
		}
		return ans;
*/
	}
}