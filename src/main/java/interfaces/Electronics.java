package interfaces;

public abstract class Electronics implements Pricable {

    private String brand;
    private String model;
    private int quantity;
    private int price;

    public Electronics(String brand, String model, int quantity, int price) {
        this.brand = brand;
        this.model = model;
        this.quantity = quantity;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public int calcOrderPrice() {
        return getQuantity() * getPrice();
    }
}
