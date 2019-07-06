package interfaces;

public class InterfaceRunner {

    public static void main(String[] args) {
        Pizza pizza = new Pizza("Margarita", 1, 20, Size.XL);
        CellPhone phone = new CellPhone("Motorola", "XT1575", 1, 250);
        Fridge fridge = new Fridge("LG", "E9090", 1, 399);

        printDeliveryPrice(pizza);
        printDeliveryPrice(phone);
        printDeliveryPrice(fridge);

    }

    private static void printDeliveryPrice(Pricable del) {
        System.out.println("Delivery price is " + del.calcDeliveryPrice());
        System.out.println("Order price is " + del.calcOrderPrice());
    }
}
