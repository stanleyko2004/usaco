import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cowjog {
	public static void main(String[] args) throws IOException{
		BufferedReader reader;
		reader = new BufferedReader(new FileReader("cowjog.in"));
		String n = reader.readLine();
		int N = Integer.parseInt(n);
		List<Integer> speeds = new ArrayList<Integer>();
		for (int i=0;i<N;i++){
			n = reader.readLine();
			String[] ps = n.split(" ");
			speeds.add(Integer.parseInt(ps[1]));
		}
		reader.close();
		int result = cowjog(speeds);
		BufferedWriter writer = new BufferedWriter(new FileWriter("cowjog.out"));
		writer.write(Integer.toString(result));
		writer.close();
	}
	public static int cowjog(List<Integer> speeds){
		int min = 1000000001;
		int ans = 0;
		for (int i=speeds.size()-1;i>=0;i--){
			if (speeds.get(i)<=min){
				ans++;
				min=speeds.get(i);
			}
		}
		return ans;
	}
}
