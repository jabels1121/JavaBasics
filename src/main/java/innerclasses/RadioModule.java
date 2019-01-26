package innerclasses;

public class RadioModule {

    public RadioModule() {
        System.out.println("Radio module initialized");
    }

    public void call(String phoneNumber) {

        int length = 10;

        class GSMModule {

            private String phoneNumber;
            private int validNumber;

            public GSMModule(String phoneNumber) {
                this.phoneNumber = phoneNumber;
            }

            public boolean isValid() {
                if (phoneNumber.length() != length) {
                    return false;
                } else {
                    try {
                        validNumber = Integer.parseInt(phoneNumber);
                        return true;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }
            }

            public void dialIn() {
                if (isValid()) {
                    System.out.println("Calling " + validNumber);
                } else {
                    System.out.println("Phone number is invalid. Please write correct phone number.");
                }
            }
        }

        GSMModule module = new GSMModule(phoneNumber);
        module.dialIn();

    }

}
