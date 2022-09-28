package test;

public class Shef {

    String getShef(String food, String tools) {

        String shef = getString(food, tools);

        return shef;
    }

    private String getString(String food, String tools) {
        Menu orderDto = new Menu();

        String shef = "";

        if (food.equals("마파두부")) {
            orderDto.setShef("중국");
        } else if (food.equals("갈비찜")) {
            orderDto.setShef("한식");
        } else if (food.equals("파스타")) {
            orderDto.setShef("양식");
        } else {
            orderDto.setShef("없음");
        }

        orderDto.setTool(tools);

        shef = orderDto.getShef();

        boolean cooking = startOrder(shef, food);

        boolean finishCooking = false;

        if (cooking) {
            finishCooking = finishOrder(shef, food);
        }

        if (finishCooking) {
            Dish dish = new Dish(shef, food, tools);
            System.out.println("고객은 주문한 " + food + "을 받았으며 " + tools + "을 먹었습니다");
        }

        return shef;
    }

    private boolean startOrder(String shef, String food) {
        System.out.println(shef + "요리사는 " + food + " 주문을 받았습니다");

        if (shef==null && food==null) {
            return false;
        }

        return true;
    }

    private boolean finishOrder(String shef, String food) {
        System.out.println(shef + "요리사는 " + food + "을 만들었습니다");

        if (shef==null && food==null) {
            return false;
        }

        return true;
    }


}
