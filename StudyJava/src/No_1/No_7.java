package No_1;

import java.util.Scanner;

public class No_7 {

    static void swap(int[] a, int idx1, int idx2) {
        System.out.println("a[" + idx1 + "]와" + "a[" + idx2 + "] 를 교환합니다.");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
        int t = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = t;

    }

    static void reverse(int[] a) {
        for (int i = 0; i < a.length / 2; i++) {
            swap(a, i, a.length - i - 1);
        }
    }

    static int sumOf(int[] a) {
        int num = 0;
        for (int i = 0; i < a.length; i++) {
            num += a[i];
        }
        return num;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int[] a = new int[6];

        for (int i = 0; i < a.length; i++) {
            a[i] = sc.nextInt();
            System.out.print(a[i] + " ");
        }
        System.out.println();

        reverse(a);

        System.out.println(sumOf(a));
    }
}
