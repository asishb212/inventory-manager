package frontend;

public class Item {
    private static long itemId;
    private static String itemName;
    private static String itemDescription;
    private static double itemUnitPrice;
    private static double itemDiscountPercent;

    // Getters
    public static long getItemId() {
        return itemId;
    }

    public static String getItemName() {
        return itemName;
    }

    public static String getItemDescription() {
        return itemDescription;
    }

    public static double getItemUnitPrice() {
        return itemUnitPrice;
    }

    public static double getItemDiscountPercent() {
        return itemDiscountPercent;
    }

    // Setters
    public static void setItemId(long itemId) {
        Item.itemId = itemId;
    }

    public static void setItemName(String itemName) {
        Item.itemName = itemName;
    }

    public static void setItemDescription(String itemDescription) {
        Item.itemDescription = itemDescription;
    }

    public static void setItemUnitPrice(double itemUnitPrice) {
        Item.itemUnitPrice = itemUnitPrice;
    }

    public static void setItemDiscountPercent(double itemDiscountPercent) {
        Item.itemDiscountPercent = itemDiscountPercent;
    }
}

