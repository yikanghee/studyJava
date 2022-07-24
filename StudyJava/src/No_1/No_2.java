package No_1;

import java.util.Scanner;

public class No_2 {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = a.clone();

        b[3] = 0;
        for (int i = 0; i < a.length; i++) {
            System.out.println("a 배열입니다 : " + a[i]);
            System.out.println("b 배열입니다 : " + b[i]);
        }
    }
}
