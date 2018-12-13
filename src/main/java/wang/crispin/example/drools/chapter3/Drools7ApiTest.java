package wang.crispin.example.drools.chapter3;

import org.junit.Test;
import org.kie.api.runtime.KieSession;
import wang.crispin.example.drools.BaseTest;
import wang.crispin.example.drools.model.Car;
import wang.crispin.example.drools.model.Person;

/**
 * @author : wpc
 * 2018-12-13
 */
public class Drools7ApiTest extends BaseTest {

    @Test
    public void drools7ApiTest() {
        KieSession kieSession = super.getKieSession("test-drools7");

        Person p1 = new Person();
        p1.setAge(30);
        Car c1 = new Car();
        c1.setPerson(p1);

        Person p2 = new Person();
        p2.setAge(70);
        Car c2 = new Car();
        c2.setPerson(p2);

        kieSession.insert(c1);
        kieSession.insert(c2);

        int count = kieSession.fireAllRules();

        kieSession.dispose();
        System.out.println("Fire " + count + " rules!");

        System.out.println("The discount of c1 is " + c1.getDiscount());
        System.out.println("The discount of c2 is " + c2.getDiscount());
    }
}
