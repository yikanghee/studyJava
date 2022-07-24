package No_1;

public class No_3 {
    public static void main(String[] args) {

        int[] a = {1, 2, 3, 4, 5};

        int max = a[0];

        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) {
                System.out.println("max = " + max);
                max = a[i];
            }
        }
    }
}
