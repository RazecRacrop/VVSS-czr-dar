package drinkshop.export;

import drinkshop.domain.Order;
import drinkshop.domain.OrderItem;
import drinkshop.domain.Product;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class CsvExporter {
    public static void exportOrders(List<Product> products, List<Order> orders, String path) {
        try (FileWriter w = new FileWriter(path)) {
            w.write("OrderId,Product,Quantity,Price,ItemTotal,OrderTotal,Date\n");

            String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            for (Order o : orders){
                for (OrderItem i : o.getItems()) {
                    String numeProdus = i.getProduct().getNume();
                    w.write(o.getId() + "," + numeProdus + "," + i.getQuantity() + "," + i.getTotal()
                            +  o.getTotal() + "," + date + "\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}