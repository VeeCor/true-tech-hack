package dev.truetechhack.app.api.order;

import dev.truetechhack.domain.order.Order;

import java.util.List;

public interface GetAllOrdersInbound {
    List<Order> execute();
}
