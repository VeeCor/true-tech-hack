package dev.truetechhack.app.api.supply;

import dev.truetechhack.domain.supply.Supply;
import reactor.core.publisher.Flux;

import java.util.List;

public interface GetAllSupplyInbound {
    Flux<Supply> execute();
}
