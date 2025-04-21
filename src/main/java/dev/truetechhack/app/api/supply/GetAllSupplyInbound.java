package dev.truetechhack.app.api.supply;

import dev.truetechhack.domain.supply.Supply;

import java.util.List;

public interface GetAllSupplyInbound {
    List<Supply> execute();
}
