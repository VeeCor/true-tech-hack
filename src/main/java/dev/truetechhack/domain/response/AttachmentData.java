package dev.truetechhack.domain.response;

import lombok.Data;

//Entity класс для парсинга поля data с вложениями из ответов от true tabs (может не понадобиться, сделала на всякий случай)
@Data
public class AttachmentData {
    private String id;
    private String name;
    private long size;
    private String mimeType;
    private String token;
    private int width;
    private int height;
    private String url;
}
