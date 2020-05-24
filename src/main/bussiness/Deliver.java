package main.bussiness;

import main.operations.Drone;

public class Deliver implements Deliverable {

    private Order order;
    private Drone drone;

    public Deliver(Order order, Drone drone) {
        this.order = order;
        this.drone = drone;
    }

    public void executeDelivery() {
        drone.dispatchOrder(order);
    }
}