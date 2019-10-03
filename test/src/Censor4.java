import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Censor4 {
	public static void main(String[] args) throws IOException{
		BufferedReader reader;
		reader = new BufferedReader(new FileReader("censor.in"));
		String toBe = reader.readLine();
		String censor = reader.readLine();
		BufferedWriter writer = new BufferedWriter(new FileWriter("censor.out"));
		while (toBe.contains(censor)){
			toBe = toBe.replaceAll(censor, "");
		}
		writer.write(toBe);
		writer.close();
	}
}
