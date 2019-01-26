package interfaces;

public class CellPhone extends Electronics {

    public CellPhone(String brand, String model, int quantity, int price) {
        super(brand, model, quantity, price);
    }

    @Override
    public int calcDeliveryPrice() {
        if (getPrice() >= 150) {
            return 0;
        } else {
            return 10;
        }
    }
}
