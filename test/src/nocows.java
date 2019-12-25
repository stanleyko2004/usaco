/*
ID: stanley26
LANG: JAVA
PROG: nocows
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class nocows {
	public static long[][] results;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("nocows.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
        StringTokenizer line = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(line.nextToken());
        int K = Integer.parseInt(line.nextToken());
        results = new long[N + 1][K + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(results[i], -1);
        }
        out.println(solve(N, K));
        br.close();
        out.close();
    }

    public static long solve(int n, int k) {
        if (results[n][k] != -1) {
            return results[n][k];
        }
        if (n < 1 || k < 1 || 2*k-1>n || n%2==0) {
            return results[n][k]=0;
        }
        if (n == 1) {
            if (k == 1) {
                return results[n][k]=1;
            }
            return results[n][k]=0;
        }
        results[n][k] = 0;
        for (int i = 1; i < n - 1; i+=2) {
            int temp = n - i - 1;
            for (int j = 0; j < k - 1; j++) {
                results[n][k] += solve(i, j) * solve(temp, k - 1);
                results[n][k] += solve(i, k - 1) * solve(temp, j);
            }
            results[n][k] += solve(i, k - 1) * solve(temp, k - 1);
        }
        results[n][k] %= 9901;
        return results[n][k];
    }
}