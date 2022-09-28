package test;

import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        String result = getShef();

        Dish dish = new Dish();
        String tools = dish.getTool();
        String shef = dish.getShef();
        String food = dish.getFood();

//        System.out.println("고객은 " + food + "을 주문했습니다");
//        System.out.println(shef + "요리사는 " +  food + " 주문을 받았습니다");
//        System.out.println(shef + "요리사는" + food + "을 만들었습니다");
//        System.out.println("고객은 주문한 " +food + "을 받았으며" + tools + "으로 먹었습니다");
    }

    private static String getShef() {
        Scanner sc = new Scanner(System.in);

        String food = sc.next();
        String tools = sc.next();

        Shef shef = new Shef();

        System.out.println("고객은 " + food + "을 주문했습니다");

        String result = shef.getShef(food, tools);

        return result;
    }
}
