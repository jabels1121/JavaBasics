package innerclasses;

public class CellPhone {

    private String brand;
    private String model;
    private Display display;
    private RadioModule gsm;
    private AbstractPhoneButton button;

    public interface AbstractPhoneButton {
        void click();
    }

    public CellPhone(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public void turnOn() {
        initDisplay();
        gsm = new RadioModule();
        initButton();
    }

    public void initButton() {
        button = new AbstractPhoneButton() {
            @Override
            public void click() {
                System.out.println("Button clicked");
            }
        };
    }

    public void call(String phoneNumber) {
        button.click();
        gsm.call(phoneNumber);
    }

    private void initDisplay() {
        this.display = new Display();
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Display getDisplay() {
        return display;
    }
}
