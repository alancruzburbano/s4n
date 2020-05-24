package main.operations;

import main.bussiness.Deliverable;

public class OrderManager implements Runnable {

    Deliverable deliver;

    public OrderManager(Deliverable deliver) {
        this.deliver = deliver;
    }

    @Override
    public void run() {
        deliver.executeDelivery();
    }
}
