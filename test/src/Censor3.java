import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Censor3 {
	public static void main(String[] args) throws IOException{
		BufferedReader reader;
		reader = new BufferedReader(new FileReader("censor.in"));
		String toBe = reader.readLine();
		String censor = reader.readLine();
		StringBuilder res = new StringBuilder(toBe);
		BufferedWriter writer = new BufferedWriter(new FileWriter("censor.out"));
		int len = censor.length();
		int index = -1;
		for (int i=0; i<toBe.length();i++){
			index++;
			res.setCharAt(index,toBe.charAt(i));
			if (index>=len-1 && res.substring(index-len+1,index+1).equals(censor)){
				index-=len;
			}
		}
		writer.write(res.substring(0,index+1));
		writer.close();
	}
}
