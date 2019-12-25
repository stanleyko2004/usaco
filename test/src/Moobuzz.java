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

public class Moobuzz {
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("moobuzz.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("moobuzz.out")));
		int n = Integer.parseInt(reader.readLine());
		int[] key = new int[]{1,2,4,7,8,11,13,14};
		int offset = key[(n-1)%8];
		int add = (n-1)/8;
		writer.print(add*15+offset);
		writer.close();
		reader.close();
	}
}
