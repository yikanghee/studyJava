package No_1;

import java.util.Random;
import java.util.Scanner;

public class No_5 {

    static int maxOf(int[] a) {

        int max = a[0];

        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();
        int[] height = new int[num];

        for (int i = 0; i < num; i++) {
            height[i] = 100 + random.nextInt(90);
            System.out.println("height = [" + i + "]" + height[i]);
        }

        System.out.println("최대값은 : " + maxOf(height) + "입니다");
    }
}
