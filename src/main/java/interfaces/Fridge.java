package interfaces;

public class Fridge extends Electronics {

    public Fridge(String brand, String model, int quantity, int price) {
        super(brand, model, quantity, price);
    }

    @Override
    public int calcDeliveryPrice() {
        if (getPrice() >= 300) return 0;
        else return 25;
    }

}
