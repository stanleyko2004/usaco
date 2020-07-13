import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CF_Baseball {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i=0; i<t; i++){
			int n = Integer.parseInt(br.readLine());
			boolean wrong = false;
			boolean right = false;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=1; j<=n; j++){
				if (Integer.parseInt(st.nextToken()) == j){
					right = true;
				} else {
					wrong = true;
				}
			}
			if (right && wrong){
				System.out.println(2);
			} else if (right && !wrong){
				System.out.println(0);
			} else {
				System.out.println(1);
			}
		}
	}
}
