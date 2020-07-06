import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CF_Shoelaces {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		Student[] students = new Student[n];
		for (int i=0; i<n; i++){
			students[i] = new Student();
		}
		for (int i=0; i<m; i++){
			st = new StringTokenizer(br.readLine());
			int s1 = Integer.parseInt(st.nextToken())-1;
			int s2 = Integer.parseInt(st.nextToken())-1;
			students[s1].tied.add(s2);
			students[s2].tied.add(s1);
		}
		Queue<Integer> ones = new LinkedList<Integer>();
		for (int i=0; i<n; i++){
			if (students[i].tied.size()==1){
				ones.add(i);
				students[i].lvl = 1;
			}
		}
		int mxL = 0;
		while (!ones.isEmpty()){
			//bfs down each one
			int rem = ones.poll();
			if (students[rem].tied.size()==0){
				continue;
			}
			mxL = Math.max(mxL, students[rem].lvl);
			Student to = students[students[rem].tied.get(0)];//going to next student
			to.tied.removeIf(student -> student==rem);//remove from
			if (to.tied.size()==1){
				ones.offer(students[rem].tied.get(0));
				to.lvl = students[rem].lvl+1;
			}
		}
		System.out.println(mxL);
	}
	public static class Student{
		public ArrayList<Integer> tied;
		public int lvl;
		public Student(){
			tied = new ArrayList<Integer>();
		}
	}
}
