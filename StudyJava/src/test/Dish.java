package test;


public class Dish {

    private String shef;
    private String food;
    private String tool;

    public Dish(String shef, String food, String tool) {
        this.shef = shef;
        this.food = food;
        this.tool = tool;
    }

    public Dish() {

    }

    // Getter
    public String getShef() {
        return shef;
    }

    // Getter
    public String getFood() {
        return food;
    }

    // Getter
    public String getTool() {
        return tool;
    }
}
