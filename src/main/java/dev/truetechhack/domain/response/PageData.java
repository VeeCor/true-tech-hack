package dev.truetechhack.domain.response;

import lombok.Data;

import java.util.List;

//Entity класс для парсинга поля data из ответов от true tabs (может не понадобиться, сделала на всякий случай)
@Data
public class PageData<T> {
    private int total;
    private int pageNum;
    private int pageSize;
    private List<T> records;
}
