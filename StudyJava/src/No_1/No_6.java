package No_1;

import java.util.Scanner;

public class No_6 {

    static void swap(int[] a, int idx1, int idx2) {
        System.out.println("a[" + idx1 +"] 와" + "a[" + idx2 +"]를 교환합니다");
        int t = a[idx1]; a[idx1] = a[idx2]; a[idx2]=t;
    }

    static void reverse(int[] a) {
        for (int i = 0; i < a.length / 2; i++) {
            swap(a, i, a.length - i - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();
        int[] height = new int[num];

        for (int i = 0; i < height.length; i++) {
            System.out.println("x[" + i + "]" + height[i]);
            height[i] = sc.nextInt();
        }

        reverse(height);

        for (int i = 0; i < height.length; i++) {
            System.out.println("[REVERSE] x[i" + "]" + height[i]);
        }

    }
}
