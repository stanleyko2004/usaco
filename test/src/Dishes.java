import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Dishes {
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("dishes.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("dishes.out")));
		int n = Integer.parseInt(reader.readLine());
		Stack<Integer>[] dc = new Stack[n];// dish configuration
		for (int i=0;i<n;i++){
			dc[i] = new Stack<Integer>();
		}
		int[] bottom = new int[n];//bottom of dish stacks
		int ct = 0;//clean dish top
		int ans = n;
		for (int i=0;i<n;i++){
			int dish = Integer.parseInt(reader.readLine())-1;
			//can't add new dish
			if (dish<ct){
				ans = i;
				break;
			}
			for (int j=dish;j>0 && bottom[j]==0;j--){
				bottom[j] = dish;
			}
			//erase all dishes larger
			while (!dc[bottom[dish]].isEmpty() && dc[bottom[dish]].peek()<dish){
				ct = dc[bottom[dish]].pop();
			}
			//put dish on top
			dc[bottom[dish]].push(dish);
		}
		writer.print(ans);
		writer.close();
		reader.close();
	}
}
