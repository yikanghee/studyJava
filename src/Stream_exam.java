import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Stream_exam {
    public static void main(String[] args) {

        // 예제 1
        Stream<String> stream1 = Stream.of("code", "chacha", "exam");
        System.out.println("예제 1");
        stream1.forEach(s -> System.out.println(s));

        /*
        Stream.of()로 생성하려는 객체를 입력하면, 스트림 객체로 생성이된다
        출력은 forEach로 내부 순환하여 객체들을 출력한다
        arguments로 출력에 사용될 함수를 넘겨준다
        -------------------------------------------
                         출력 결과
        -------------------------------------------
        code
        chacha
        exam
         */

        // 예제 2
        Stream<String> stream2 = Stream.empty();
        System.out.println("예제 2");
        stream2.forEach(s -> System.out.println(s));

        /*
        Stream.empty()는 비어있는 스트림을 생성한다

        -------------------------------------------
                         출력 결과
        -------------------------------------------
        결과 없음
         */

        Stream<String> stream3 = Stream.generate(() -> "Echo").limit(5);
        System.out.println("예제 3");
        stream3.forEach(System.out::println);

        /*
        Stream.generate()를 사용하면 Argument로 함수를 받는다
        함수에서 리턴되는 객체가 스트림으로 생성된다
        함수는 무한히 호출되기 때문에 무한한 길이의 스트림이 생성된다
        그렇기 떄문에 limit(5)로 5개의 개체만 생성하도록함
        System.out::println을 함수로 넣어주면 객체들을 출력해준다

        -------------------------------------------
                         출력 결과
        -------------------------------------------
        Echo
        Echo
        Echo
        Echo
        Echo
         */

        Stream<Double> stream4 = Stream.generate(Math::random).limit(5);
        System.out.println("예제 4");
        stream4.forEach(System.out::println);

        /*
        Stream.Generate()에 Math::random을 Args로 넘겨주면
        랜던 숫자로 이어진 스트림이 생성됨

        -------------------------------------------
                         출력 결과
        -------------------------------------------
        0.9843243270075234
        0.6037447042790027
        0.9375820345272727
        0.8864894191532746
        0.44219174822200713

         */


        Stream<Integer> stream5 = Stream.iterate(0, n -> n + 2).limit(5);
        System.out.println("예제 5");
        stream5.forEach(System.out::println);

        /*
        Stream.iterate도 Stream.generate와 유사하다
        첫번쨰는 초기값, 두번쨰는 함수이다
        함수에 대입하면 무한대로 들어나기 때문에 limit 해준다

        -------------------------------------------
                         출력 결과
        -------------------------------------------
        0
        2
        4
        6
        8
         */

        List<String> list = Arrays.asList("a1", "a2", "b1", "b2", "c2", "c1");
        System.out.println("list exam" + list);
        Stream<String> stream6 = list.stream();
        System.out.println("예제 6");
        stream6.forEach(System.out::println);

        /*
        List로 생성된 객체도 스트림으로 생성이 가능하다
        list에 stream() 메소드를 적용하여 스트림으로 생성해준다

        -------------------------------------------
                         출력 결과
        -------------------------------------------
        a1
        a2
        b1
        b2
        c2
        c1
         */

        String[] array = new String[]{"a1", "a2", "b1", "b2", "c2", "c1"};
        System.out.println("array exam" + array);
        Stream<String> stream7 = Arrays.stream(array);
        System.out.println("예제 7");
        stream7.forEach(System.out::println);

    }
}
