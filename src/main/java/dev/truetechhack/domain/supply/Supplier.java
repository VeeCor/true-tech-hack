package dev.truetechhack.domain.supply;

import lombok.Data;

import java.util.List;

//TODO доделать (написать поля)
@Data
public class Supplier {
    private String id;
    private String name;
    private String country;
    private String contactFace;
    private String email;
    private String phoneNumber;
}