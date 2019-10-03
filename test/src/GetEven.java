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
import java.util.StringTokenizer;

public class GetEven {
	
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("geteven.in"));
		int n = Integer.parseInt(reader.readLine());
		Map<String,int[]> letters = new HashMap<String,int[]>();
		for (String i:new String[]{"B","E","S","I","G","O","M"}){
			letters.put(i, new int[]{0,0});
		}
		for (int i=0;i<n;i++){
			String st = reader.readLine();
			String[] splitted = st.split(" ");
			if (isEven(Integer.parseInt(splitted[1]))){
				letters.get(splitted[0])[0]++;
			} else {
				letters.get(splitted[0])[1]++;
			}
		}
		int ans = 0;
		for (int b=0;b<2;b++){
			for (int e=0;e<2;e++){
				for (int s=0;s<2;s++){
					for (int i=0;i<2;i++){
						for (int g=0;g<2;g++){
							for (int o=0;o<2;o++){
								for (int m=0;m<2;m++){
									if (isEven((b+e+s+s+i+e)*(g+o+e+s)*(m+o+o))){
										ans += letters.get("B")[b]*letters.get("E")[e]*letters.get("S")[s]*letters.get("I")[i]*letters.get("G")[g]*letters.get("O")[o]*letters.get("M")[m];
									}
								}
							}
						}
					}
				}
			}
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("geteven.out"));
		writer.write(Integer.toString(ans));
		writer.close();
	}
	public static boolean isEven(int num){
		return num%2==0;
	}
}
