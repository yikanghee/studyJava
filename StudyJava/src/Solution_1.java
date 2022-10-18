import java.util.Arrays;

public class Solution_1 {

    public static void main(String[] args) {

        String s = "(())()";
        boolean result = false;
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            }
            if (s.charAt(i) == ')') {
                count--;
            }
            if (count < 0) {
                break;
            }
        }

        if (count == 0) {
            result = true;
        }

        System.out.println("result = " + result);
    }
}
