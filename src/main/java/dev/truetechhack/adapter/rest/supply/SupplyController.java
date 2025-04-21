package dev.truetechhack.adapter.rest.supply;

import dev.truetechhack.app.api.supply.GetAllSupplyInbound;
import dev.truetechhack.domain.supply.Supply;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/rest-api/supply")
@RequiredArgsConstructor
public class SupplyController {
    private final GetAllSupplyInbound getAllSupplyInbound;

    @GetMapping("/getAll")
    public Flux<Supply> getAllSupply() {
        return getAllSupplyInbound.execute();
    }
}
