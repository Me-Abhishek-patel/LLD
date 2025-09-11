package design_patterns.Composite;

import java.util.ArrayList;
import java.util.List;

public class CompositePattern {
    public interface Item {
        public int getPrice();

    }

    public static class Product implements Item {

        @Override
        public int getPrice() {
            return 5;
        }

    }

    public static class Box implements Item {

        List<Item> items;

        public Box() {
            items = new ArrayList<>();
        }

        @Override
        public int getPrice() {
            int price = 0;
            for (Item item : items) {
                price += item.getPrice();
            }
            return price;
        }

        public void addItem(Item item){
            items.add(item);
        }

        public void removeItem(Item item){
            items.remove(item);
        }

    }

    public static void main(String[] args) {
        Item product = new Product();
        Box box = new Box();
        box.addItem(new Product());
        box.addItem(new Product());
        Box box2 = new Box();
        box2.addItem(box);
        box2.addItem(product);

        System.out.println("Prodcut price: "+ product.getPrice());
        System.out.println("Box  price: "+ box.getPrice());
        System.out.println("Box 2 price: "+ box2.getPrice());
    }
}
