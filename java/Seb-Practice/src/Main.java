public class Main {
    public static void main(String args[]) {
        Car rodeo = new Car(3,35);
        rodeo.printInfo();
        System.out.println("*********");
        rodeo.setSpeed(5);
        rodeo.printInfo();
    }
}
