package frontend;

import java.util.ArrayList;

public class cart_items {
    public static ArrayList<ArrayList<Long>> items = new ArrayList<>();

    public static void addItem(ArrayList<Long> item_deets){
        //System.out.println(item_deets.get(0));
        //System.out.println(item_deets.get(1));

        cart_items.items.add(item_deets);
    }

    public static ArrayList<ArrayList<Long>> get_cart_items(){
        return cart_items.items;
    }

    public static void remove_cart_item(ArrayList<Long> item_deets) {
        items.remove(item_deets);
    }
}
