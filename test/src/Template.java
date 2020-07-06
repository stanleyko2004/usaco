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
		for (float i=0; i<=1; i+=0.01){
			System.out.println(Math.round(i*100.0)/100.0);
		}
	}
}
