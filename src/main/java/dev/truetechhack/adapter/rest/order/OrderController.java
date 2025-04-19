package dev.truetechhack.adapter.rest.order;

import dev.truetechhack.app.api.order.GetAllOrdersInbound;
import dev.truetechhack.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest-api/order")
@RequiredArgsConstructor
public class OrderController {
    private final GetAllOrdersInbound getAllOrdersInbound;

    @GetMapping("/getAll")
    public List<Order> getAllOrders() {
        return getAllOrdersInbound.execute();
    }
}
