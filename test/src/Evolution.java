import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Evolution {
	public final static int mod = (int)(1e9+7);
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(new File("evolution.in")));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("evolution.out")));
		int n = Integer.parseInt(reader.readLine());
		HashSet<String> features = new HashSet<String>();
		ArrayList<String>[] cowFeatures = new ArrayList[n];
		for (int i=0; i<n; i++){
			cowFeatures[i] = new ArrayList<String>();
		}
		String feature;
		for (int i=0; i<n; i++){
			StringTokenizer st = new StringTokenizer(reader.readLine());
			int k = Integer.parseInt(st.nextToken());
			for (int j=0; j<k; j++){
				feature = st.nextToken();
				features.add(feature);
				cowFeatures[i].add(feature);
			}
		}
		java.util.Iterator<String> it = features.iterator();
		int ct = 0;
		String[] featuresArr = new String[features.size()];
		while(it.hasNext()){
			featuresArr[ct++] = it.next();
		}
		int numFeat = featuresArr.length;
		boolean possible = true;
		for (int i=0; i<numFeat; i++){
			for (int j=i+1; j<numFeat; j++){
				boolean isI = false, isJ = false, isIAndJ = false;
				for (ArrayList cow : cowFeatures){
					boolean tempI = false, tempJ = false;
					for (Object k : cow){
						if (k.equals(featuresArr[i])){
							tempI = true;
						}
						if (k.equals(featuresArr[j])){
							tempJ = true;
						}
					}
					if (tempI && tempJ){
						isIAndJ = true;
					} else if (tempI){
						isI = true;
					} else if (tempJ){
						isJ = true;
					}
				}
				if (isI && isJ && isIAndJ){
					possible = false;
				}
			}
			if (!possible){
				break;
			}
		}
		if (possible){
			writer.println("yes");
		} else {
			writer.println("no");
		}
		writer.close();
	}
}
