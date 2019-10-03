import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cow {
	public static void main(String[] args) throws IOException{
		BufferedReader reader;
		reader = new BufferedReader(new FileReader("cow.in"));
		String len = reader.readLine();
		int length = Integer.parseInt(len);
		String cow = reader.readLine();
		BufferedWriter writer = new BufferedWriter(new FileWriter("cow.out"));
		long C = 0;
		long CO = 0;
		long COW = 0;
		for (int i=0; i<length; i++){
			if (cow.charAt(i)=='C'){
				C++;
			} else if (cow.charAt(i)=='O'){
				CO+=C;
			} else if (cow.charAt(i)=='W'){
				COW+=CO;
			}
		}
		writer.write(Long.toString(COW));
		writer.close();
	}
}
