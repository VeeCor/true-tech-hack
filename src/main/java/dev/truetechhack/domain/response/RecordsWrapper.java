package dev.truetechhack.domain.response;

import lombok.Data;

@Data
public class RecordsWrapper<T> {
    private String recordId;
    private T fields;
}
