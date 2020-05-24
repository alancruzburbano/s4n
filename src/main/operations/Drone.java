package main.operations;

import main.bussiness.Order;
import main.enumerations.Cardinals;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Drone {

    private FileWriter reporter;
    private DroneEngine droneEngine;
    private int capacity;

    public Drone(int capacity) {
        this.capacity = capacity;
        droneEngine = new DroneEngine(0, 0, Cardinals.N);
    }


    public void dispatchOrder(Order order) {
        List<String> paths = order.getOrderLines();
        try {
            reporter = new FileWriter("reports/".concat("report_").concat(order.getDescription()));
            reporter.write("== Deliveries Report ==".concat("\n"));
            for (int i = 1; i <= paths.size(); i++) {
                if (i % capacity == 0) {
                    droneEngine.executePath(paths.get(i - 1), reporter);
                    droneEngine.initializeCoordinates();
                    reporter.write("drone empty, go for more lunches to restaurant\n");
                } else {
                    droneEngine.executePath(paths.get(i - 1), reporter);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reporter != null) {
                    reporter.flush();
                    reporter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}