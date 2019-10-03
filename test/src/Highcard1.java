import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Highcard1 {
	public static void main(String[] args) throws IOException{
		Scanner reader = new Scanner(new FileReader("highcard.in"));
		int n = reader.nextInt();
		int numC = 2*n;
		boolean[] cards = new boolean[numC];
		for (int i=0; i<n; i++){
			cards[reader.nextInt()-1] = true;
		}
		int ans = n;
		int toBeat = 0;
		for (int i=0;i<numC;i++){
			if (cards[i]){
				toBeat++;
			} else {
				if (toBeat>0){
					toBeat--;
				} else {
					ans--;
				}
			}
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("highcard.out"));
		writer.write(Integer.toString(ans));
		writer.close();
	}
}
