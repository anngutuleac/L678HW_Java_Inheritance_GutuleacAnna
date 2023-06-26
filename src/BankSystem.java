public class BankSystem {
    public static void main(String args[]) {
        int clientID = 100001;
        String clientName = "Анна Гуцуляк";
        int savingsCountID = 200002;
        double sum = 100;
        String currency = "USD";
        int transactionID = 300003;

        SavingsCount sc = createSavingsCount(savingsCountID, sum, currency);
        Client client = createClient(clientID, clientName, sc);
        Transaction transaction = createTransaction(transactionID, client);

        countReport(client);
        transaction.addMoney(200);
        countReport(client);
        transaction.extractMoney(150);
        countReport(client);
        transaction.extractMoney(350);
        countReport(client);
    }
    private static CurrentCount createCurrentCount(int id, double sum, String currency) {
        return new CurrentCount(id, sum, currency);
    }

    private static SavingsCount createSavingsCount(int id, double sum, String currency) {
        return new SavingsCount(id, sum, currency);
    }

    private static Client createClient(int id, String name, Count count) {
        return new Client(id, name, count);
    }

    private static Transaction createTransaction(int id, Client client) {
        return new Transaction(id, client);
    }

    private static void countReport(Client client) {
        System.out.println("Клиент: " + client.name);
        System.out.println("Тип счета: " + client.count.type);
        System.out.println("Состояние счета " + client.count.id + ": " + client.count.sum + " " + client.count.currency);
    }
}

class Client {
    int id;
    String name;
    Count count;

    public Client(int id, String name, Count count) throws IllegalArgumentException {
        if (id <= 0) { throw new IllegalArgumentException("ID клиента должен быть положительным!"); }
        if (name.length() == 0) { throw new IllegalArgumentException("Имя клиента не должно быть пустым!"); }
        this.id = id;
        this.name = name;
        this.count = count;
    }
}

class Count {
    int id;
    String type;
    double sum;
    String currency;

    public Count(int id, String type, double sum, String currency) throws IllegalArgumentException {
        if (id <= 0) { throw new IllegalArgumentException("ID счета должен быть положительным!"); }
        if (type.length() == 0) { throw new IllegalArgumentException("Тип счета должна иметь валидное значение!"); }
        if (sum < 0) { throw new IllegalArgumentException("Сумма на счету должна быть неотрицательной!"); }
        if (currency.length() == 0) { throw new IllegalArgumentException("Валюта должна иметь валидное значение!"); }
        this.id = id;
        this.type = type;
        this.sum = sum;
        this.currency = currency;
    }
}

class CurrentCount extends Count {
    public CurrentCount(int id, double sum, String currency) throws IllegalArgumentException {
        super(id, "Текущий", sum, currency);
    }
}

class SavingsCount extends Count {
    public SavingsCount(int id, double sum, String currency) throws IllegalArgumentException {
        super(id, "Накопительный", sum, currency);
    }
}

class Transaction {
    int id;
    Client client;

    public Transaction(int id, Client client) throws IllegalArgumentException {
        if (id <= 0) { throw new IllegalArgumentException("ID транзакции должен быть положительным!"); }
        this.id = id;
        this.client = client;
    }

    void addMoney(double sum) {
        if (sum < 0) {
            System.out.println("Сумма пополнения счета должна быть положительной!");
        } else {
            client.count.sum += sum;
            System.out.println("Было добавлено: " + sum);
        }
    }

    void extractMoney(double sum) {
        if (client.count.sum < sum) {
            System.out.println("У вас недостаточно средств на счету!");
        } else {
            client.count.sum -= sum;
            System.out.println("Было снято: " + sum);
        }
    }
}
