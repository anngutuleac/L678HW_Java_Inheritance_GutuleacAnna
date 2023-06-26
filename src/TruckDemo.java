
class Vehicle {
    private int passengers; // количество пассажиров
    private int fuelcap; // объем топливного бака в галлонах
    private int mpg; // потребление топлива в милях на галлон

    // Конструктор класса Vehicle
    Vehicle(int p, int f, int m) {
        passengers = p;
        fuelcap = f;
        mpg = m;
    }

    // Возвратить дальность поездки транспортного средства
    int range() {
        return mpg * fuelcap;
    }

    // Вычислить объем топлива, требующегося
// для прохождения заданного пути
    double fuelneeded(int miles) {
        return (double) miles / mpg;
    }

    // Методы доступа к переменным экземпляра
    int getPassengers() {
        return passengers;
    }

    void setPassengers(int p) {
        passengers = p;
    }

    int getFuelcap() {
        return fuelcap;
    }

    void setFuelcap(int f) {
        fuelcap = f;
    }

    int getMpg() {
        return mpg;
    }

    void setMpg(int m) {
        mpg = m;
    }
}
// Расширение класса Vehicle для грузовиков
class Truck extends Vehicle {
    private int cargocap; // грузоподъемность в фунтах

    // Конструктор класса Truck
    Truck(int p, int f, int m, int c) {
/* Инициализация членов класса Vehicle
с использованием конструктора этого класса. */
        super(p, f, m);
        cargocap = c;
    }

    // Методы доступа к переменной cargocap
    int getCargo() {
        return cargocap;
    }

    void putCargo(int c) {
        cargocap = c;
    }
}
class TruckDemo {
    public static void main(String args[]) {
// Создать ряд новых объектов типа Truck
        Truck semi = new Truck(2, 200, 7, 44000);
        Truck pickup = new Truck(3, 28, 15, 2000);
        double gallons;
        int dist = 252;
        gallons = semi.fuelneeded(dist);
        System.out.println("Гpyзoвик может перевезти " + semi.getCargo() + " фунтов.");
        System.out.println("Для преодоления " + dist + " миль грузовику требуется " + gallons + " галлонов топлива.\n");
        gallons = pickup.fuelneeded(dist);

        System.out.println("Пикaп может перевезти " + pickup.getCargo() + " фунтов.");
        System.out.println("Для преодоления " + dist + " миль пикапу требуется "  + gallons + " галлонов топлива.");
    }
}
