/*
ID: stanley26
LANG: JAVA
PROG: zerosum
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class zerosum {
	public static int n;
	public static final String[] operations = new String[]{"+","-"," "};
	public static ArrayList<String>[] exps;
	public static ArrayList<String> ans = new ArrayList<String>();
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("zerosum.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
        n = Integer.parseInt(reader.readLine());
        genExp();
        eval();
        Collections.sort(ans);
        for (String i : ans){
        	writer.println(i);
        }
		writer.close();
	}
    public static void eval(){
    	for (String i: exps[n-1]){
    		int total = 0;
    		String copy = String.valueOf(i);
    		copy = copy.replaceAll(" ", "");
    		//find first
    		int prev = 0;
    		int cnt = 0;
    		while (cnt < copy.length() && copy.charAt(cnt) != '+' && copy.charAt(cnt) != '-'){
    			cnt++;
    		}
    		int cur = Integer.parseInt(String.valueOf(copy.substring(0,cnt)));
    		total += cur;
    		prev = cnt + 1;
    		while (cnt < copy.length()){
    			if (copy.charAt(cnt) == '+'){
    				cnt++;
    				while (cnt < copy.length() && copy.charAt(cnt) != '+' && copy.charAt(cnt) != '-'){
    					cnt++;
    				}
    	    		cur = Integer.parseInt(String.valueOf(copy.substring(prev,cnt)));
    	    		total += cur;
    	    		prev = cnt + 1;
    			} else {
    				cnt++;
    				while (cnt < copy.length() && copy.charAt(cnt) != '+' && copy.charAt(cnt) != '-'){
    					cnt++;
    				}
    	    		cur = Integer.parseInt(String.valueOf(copy.substring(prev,cnt)));
    	    		total -= cur;
    	    		prev = cnt + 1;
    			}
    		}
    		if (total == 0){
    			ans.add(i);
    		}
    	}
    }
    public static void genExp(){
    	exps = new ArrayList[n];
    	for (int i=0; i<n; i++){
    		exps[i] = new ArrayList<String>();
    	}
    	exps[0].add("1");
    	for (int i=2; i<=n; i++){
    		for (int j=0; j<exps[i-2].size(); j++){
    			for (String k: operations){
    				String temp = exps[i-2].get(j);
    				temp += k;
    				temp += Integer.toString(i);
    				exps[i-1].add(temp);
    			}
    		}
    	}
    }
}