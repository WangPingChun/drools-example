package wang.crispin.example.drools.model;

import lombok.Data;

/**
 * @author : WangPingChun
 * 2018-12-13
 */
@Data
public class Car {
    private int discount = 100;
    private Person person;
}
