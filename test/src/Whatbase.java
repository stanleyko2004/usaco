import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Whatbase {
	public static void main(String[] args) throws IOException{
		BufferedReader reader;
		reader = new BufferedReader(new FileReader("whatbase.in"));
		String K = reader.readLine();
		int k = Integer.parseInt(K);
		BufferedWriter writer = new BufferedWriter(new FileWriter("whatbase.out"));
		for (int i=0;i<k;i++){
			String nums = reader.readLine();
			String[] sepNum = nums.split(" ");
			int num1 = Integer.parseInt(sepNum[0]);int num2 = Integer.parseInt(sepNum[1]);
			int[] res = findRes(num1,num2);
			writer.write(Integer.toString(res[0])+" "+Integer.toString(res[1])+"\n");
		}
		writer.close();
	}
	public static int[] findRes(int num1,int num2){
		int counter1 = 10;
		int counter2 = 10;
		while (counter1<=15000 && counter2<=15000){
			int final1 = 0; int final2 = 0;
			int num1C = num1; int num2C = num2;
			for (int i=0;i<3;i++){
				final1+=(num1C%10)*(Math.pow(counter1, i));final2+=(num2C%10)*(Math.pow(counter2, i));
				num1C /= 10;num2C /= 10;
			}
			if (final1==final2){
				int[] ans = new int[2];
				ans[0] = counter1;ans[1] = counter2;
				return ans;
			} else if (final1>final2){
				counter2++;
			} else {
				counter1++;
			}
		}
		int[] ans = new int[2];
		ans[0] = counter1;ans[1] = counter2;
		return ans;
	}
}
