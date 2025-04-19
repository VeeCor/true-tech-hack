package dev.truetechhack.app.implementation.orders;

import dev.truetechhack.app.api.order.GetAllOrdersInbound;
import dev.truetechhack.domain.order.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetAllOrdersUseCase implements GetAllOrdersInbound {

    @Override
    public List<Order> execute() {
        return null;
    }
}
