package wang.crispin.example.drools.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : WangPingChun
 * 2018-12-13
 */
@Data
public class Person implements Serializable {
    private int age;
    private String name;
    private Date birthday;
}
