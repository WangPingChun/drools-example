package wang.crispin.example.drools.chapter8;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import wang.crispin.example.drools.BaseTest;
import wang.crispin.example.drools.model.Car;
import wang.crispin.example.drools.model.Person;

/**
 * @author WangPingChun
 * @since 2018-12-18
 */
@Slf4j
public class ConditionTest extends BaseTest {

    @Test
    public void test() {
        final KieSession kieSession = getKieSessionByName("condition-rules");

        Person person = new Person();
        person.setAge(19);

        Car car = new Car();
        car.setDiscount(80);

        kieSession.insert(person);
        kieSession.insert(car);

        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
