package drinkshop.service.validator;

import drinkshop.domain.Order;
import drinkshop.domain.OrderItem;

public class OrderValidator implements Validator<Order> {

    private final OrderItemValidator itemValidator = new OrderItemValidator();

    @Override
    public void validate(Order order) {

        StringBuilder bld = new StringBuilder();

        if (order.getId() <= 0)
            bld.append( "ID comanda invalid!\n");

        if (order.getItems() == null || order.getItems().isEmpty())
            bld.append( "Comanda fara produse!\n");

        for (OrderItem item : order.getItems()) {
            try {
                itemValidator.validate(item);
            } catch (ValidationException e) {
                bld.append( e.getMessage());
            }
        }

        if (order.getTotalPrice() < 0)
            bld.append( "Total invalid!\n");

        String errors = bld.toString();
        if (!errors.isEmpty())
            throw new ValidationException(errors);
    }
}
