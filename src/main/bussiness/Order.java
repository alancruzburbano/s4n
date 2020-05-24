package main.bussiness;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private Path orderFile;
    private String description;

    public Order(Path orderPath) {
        this.description = orderPath.getFileName().toString();
        this.orderFile = orderPath;
    }

    public List<String> getOrderLines() {
        List<String> linesInOrder = new ArrayList<>();
        try {
            linesInOrder = Files.readAllLines(orderFile);
        } catch (IOException e) {
            System.out.println("File not found: ");
            e.printStackTrace();
        } catch (Exception i) {
            System.out.println("There was an error unknown: ");
            i.printStackTrace();
        }
        return linesInOrder;
    }

    public Path getOrderFile() {
        return orderFile;
    }

    public void setOrderFile(Path orderFile) {
        this.orderFile = this.orderFile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
