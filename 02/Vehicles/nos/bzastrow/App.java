package nos.bzastrow;

public class App {
    public static void main(String[] args) {
        Car c = new Car("green", 240, 2000, 180, "Benzin", 80.0, 200, 4, "Peter Pan", "18-01-1345", "Nimmerlandstraße 8, Nimmerland ABC");
        VehiclePrinter.print(c);
        
        c.startEngine();
        c.drive(200);
        VehiclePrinter.print(c);

        CarDealership cd = new CarDealership();
        cd.buyBackVehicle(c);
        VehiclePrinter.print(c);
        
        VehicleOwner own = new VehicleOwner("Herbert Grönemeyer", "19-02-1776", "Herbert von G Straße 4, 1234 Herbertstadt, Herbertprovinz, Herbertland");
        Vehicle newCar = cd.sellVehicle(0, own);
        VehiclePrinter.print(newCar);
    }
    
}
