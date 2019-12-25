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

public class Template {
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("poetry.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("poetry.out")));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		int n = Integer.parseInt(st.nextToken());
		
		writer.close();
		reader.close();
	}
}
