package main;

import main.bussiness.Deliver;
import main.bussiness.Deliverable;
import main.bussiness.Order;
import main.operations.Drone;
import main.operations.OrderManager;
import main.utils.ReadProperties;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ApplicationRunner {
    public static void main(String[] args) {
        ReadProperties propertiesReader = new ReadProperties();
        propertiesReader.initProperties();
        Integer dronesAvailable = Integer.valueOf(propertiesReader
                .readPropertieByKey("drones-available"));
        Integer droneCapacity = Integer.valueOf(propertiesReader
                .readPropertieByKey("drone-capacity"));
        ExecutorService deliveryExecutorService = Executors.newFixedThreadPool(dronesAvailable);
        try {
            List<Path> orders = Files.list(Paths.get("input/"))
                    .filter(f -> f.toString().toLowerCase().endsWith(".txt"))
                    .collect(Collectors.toList());

            for (int i = 0; i < orders.size(); i++) {
                if (i <= 10) {
                    Order order = new Order(orders.get(i));
                    Drone drone = new Drone(droneCapacity);
                    Deliverable deliver = new Deliver(order, drone);
                    Runnable ordersDelivery = new OrderManager(deliver);
                    deliveryExecutorService.execute(ordersDelivery);
                } else {
                    System.out.println("there is not enough drones available");
                }
            }
            deliveryExecutorService.shutdown();
            System.out.println("Orders processed");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
