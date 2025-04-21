package dev.truetechhack.app.api.product;

import java.util.Map;

public interface UpdateProductInbound {
    void execute(Map<String, Object> field);
}
