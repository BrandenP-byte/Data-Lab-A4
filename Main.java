import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("CarDataset.csv"));
        List<Car> carList = new ArrayList<>();

        // skip header
        String line = br.readLine();
        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(",");
            if (parts.length < 11) continue; // skip malformed lines

            String company = parts[0].trim();
            String model = parts[1].trim();
            String engine = parts[2].trim();
            String cc = parts[3].trim();
            int horsePower = parseIntSafe(parts[4]);
            int maxSpeed = parseIntSafe(parts[5]);
            int performance = parseIntSafe(parts[6]);
            int price = parseIntSafe(parts[7]);
            String fuelType = parts[8].trim();
            int seats = parseIntSafe(parts[9]);
            String torque = parts[10].trim();

            Car car = new Car(company, model, engine, cc, horsePower, maxSpeed, performance, price, fuelType, seats, torque);
            carList.add(car);
        }
        br.close();

        // print all cars
        for (Car c : carList) {
            System.out.println(c);
        }
    }

    private static int parseIntSafe(String s) {
        double v = extractFirstNumber(s);
        return (int) Math.round(v);
    }

    private static double extractFirstNumber(String s) {
        if (s == null) return 0;
        String str = s.trim();
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("[-+]?[0-9]*\\.?[0-9]+");
        java.util.regex.Matcher m = p.matcher(str);
        if (m.find()) {
            try {
                return Double.parseDouble(m.group());
            } catch (Exception e) {
                return 0;
            }
        }
        return 0;
    }
}
