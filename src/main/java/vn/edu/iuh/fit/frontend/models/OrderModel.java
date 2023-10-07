package vn.edu.iuh.fit.frontend.models;

import vn.edu.iuh.fit.backend.models.Order;
import vn.edu.iuh.fit.backend.services.OrderServices;

public class OrderModel {
    private final OrderServices orderServices;

    public OrderModel() {
        orderServices = new OrderServices();
    }

    public boolean add(Order order) {
        return orderServices.add(order);
    }
}
