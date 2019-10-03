import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Censor1 {
	public static void main(String[] args) throws IOException{
		BufferedReader reader;
		reader = new BufferedReader(new FileReader("censor.in"));
		String toBe = reader.readLine();
		String censor = reader.readLine();
		String res = "";
		BufferedWriter writer = new BufferedWriter(new FileWriter("censor.out"));
		int len = censor.length();
		for (int i=0; i<toBe.length();i++){
			res+=toBe.charAt(i);
			int resL = res.length();
			if (resL+1>len && res.substring(resL-len).equals(censor)){
				res=res.substring(0,res.length()-len);
			}
		}
		writer.write(res);
		writer.close();
	}
}
