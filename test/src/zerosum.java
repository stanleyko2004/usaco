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
	static PrintWriter writer;
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("zerosum.in"));
        writer = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
        n = Integer.parseInt(reader.readLine());
        genExp();
        eval();
		writer.close();
	}
    public static void eval(){
    	for (String i: exps[n-1]){
    		int total = 0;
    		int cur = 0;
    		String copy = i;
    		copy.replaceAll(" ","");
    		for (int j=0; j<copy.length(); j++){
    			if (copy.charAt(j)=='+'){
    				total += cur;
    				cur = 0;
    			} else if (copy.charAt(j)=='-'){
    				total -= cur;
    				cur = 0;
    			} else {
    				cur *= 10;
    				cur += Integer.parseInt(String.valueOf(copy.charAt(j)));
    			}
    		}
    		if (total == 0){
    			writer.println(i);
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