public class Car {
    private int gas;
    private int speed;
    private static String street;

    private final int TOP_SPEED = 120;

    public Car(int initGas, int initSpeed, String initStreet) {
        gas = initGas;
        speed = initSpeed;
        street = initStreet;
    }

    public void printInfo() {
        System.out.println("Gas: "+gas);
        System.out.println("Speed: "+speed);
        System.out.println("Road: "+street);
    }

    public void setSpeed(int newSpeed) {
        if (newSpeed <= TOP_SPEED) {
            speed = newSpeed;
        }
    }
}
