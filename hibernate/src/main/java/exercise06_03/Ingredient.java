package exercise06_03;

import javax.persistence.Embeddable;

@Embeddable
public class Ingredient {

    private int quantity;
    private String name;

    public Ingredient() {
    }

    public Ingredient(int quantity, String name) {
        this.quantity = quantity;
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "quantity=" + quantity +
                ", name='" + name + '\'' +
                '}';
    }
}
