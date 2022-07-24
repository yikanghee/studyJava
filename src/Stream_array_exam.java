import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Stream_array_exam {

    public static void main(String[] args) {

        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");

        String[] result = list1.stream()
                .map(String::toUpperCase)
                .toArray(String[]::new);

        for (String s : result) {
            System.out.println(s);
        }

        /*

        Stream을 String array로 변환한다
        toUpperCase를 적용하여 대문자로 변환한다

        -------------------------------------------
                         출력 결과
        -------------------------------------------
        A
        B
        C
         */

        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);

        Integer[] result2 = list2.stream()
                .map(x -> x * x)
                .toArray(Integer[]::new);

        for (Integer n : result2) {
            System.out.println(n);
        }

        /*

        Stream을 Integer array 로 변환한다

        -------------------------------------------
                         출력 결과
        -------------------------------------------
        1
        4
        9
        */

        int[] num = {1, 2, 3};

        IntStream intStream = Arrays.stream(num);

        Integer[] result3 = intStream
                .map(x -> x * x)
                .boxed()
                .toArray(Integer[]::new);

        for (Integer n : result3) {
            System.out.println(n);
        }

        /*

        Stream을 Integer[] 로 변환하는 경우
        boxed() 로 int를 Integer로 변환한 뒤에 Array로 변환한다

        -------------------------------------------
                         출력 결과
        -------------------------------------------
        1
        4
        9

         */

        int[] num1 = {1, 2, 3,};

        IntStream intStream2 = Arrays.stream(num1);

        int[] result4 = intStream2
                .map(x -> x * x)
                .toArray();

        for (int n : result4) {
            System.out.println(n);
        }

        /*
        Stream을 int[]으로 변환하는 경우
        toArray()로 변환시킬 수 있다
         */
    }
}
