package No_1.baekjoon;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class gugudan {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for(int i=0; i<n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            System.out.printf("Case #%d: %d\n", (i+1), (a+b));
        }

//        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st;
//        int a,b;
//
//        int T = Integer.parseInt(br.readLine());
//
//        for(int i=1; i<=T; i++){
//            st = new StringTokenizer(br.readLine());
//            a = Integer.parseInt(st.nextToken());
//            b = Integer.parseInt(st.nextToken());
//        }
//
//        bw.flush();
//        br.close();
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//
//        StringTokenizer st;
//
//        int a, b;
//
//        int T = Integer.parseInt(br.readLine());
//
//        for (int i = 1; i <= T; i++) {
//            st = new StringTokenizer(br.readLine());
//            a = Integer.parseInt(st.nextToken());
//            b = Integer.parseInt(st.nextToken());
//            bw.write("Case #"+i+": "+(a+b)+"\n");
//        }
//
//        bw.flush();
//        br.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        int a, b;

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            bw.write("Case #" +i + ": " + (a+b) + "\n");
        }
        
    }
}
