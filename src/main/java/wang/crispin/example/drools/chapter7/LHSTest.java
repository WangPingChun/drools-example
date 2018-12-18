package wang.crispin.example.drools.chapter7;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import wang.crispin.example.drools.BaseTest;
import wang.crispin.example.drools.model.Car;
import wang.crispin.example.drools.model.Person;
import wang.crispin.example.drools.model.SubPerson;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WangPingChun
 * @since 2018-12-17
 */
@Slf4j
public class LHSTest extends BaseTest {


    @Test
    public void testLhs() {
        final KieSession kieSession = super.getKieSessionByName("lhs-rules");

        Person person = new Person();
        person.setAge(15);

        Person person1 = new Person();
        person1.setAge(10);

        kieSession.insert(person1);
        kieSession.insert(person);

        kieSession.fireAllRules();
        kieSession.dispose();
    }

    @Test
    public void testPattern() {
        System.setProperty("drools.dateformat", "yyyy-MM-dd");
        final KieSession kieSession = super.getKieSessionByName("pattern-rules");
        //
        //Person person = new Person();
        //person.setAge(28);
        //person.setName("Tom");
        //kieSession.insert(person);

        //Car car = new Car();
        //
        //SubPerson subPerson = new SubPerson();
        //subPerson.setAge(10);
        //
        //car.setSubPerson(subPerson);
        //
        //kieSession.insert(car);

        Person person = new Person();
        person.setBirthday(new Date());

        kieSession.insert(person);

        kieSession.fireAllRules();
        kieSession.dispose();
    }

    @Test
    public void testMapAndList() {
        final KieSession kieSession = super.getKieSessionByName("mapAndList-rules");

        //Map<String, Integer> map = new HashMap<>(16);
        //map.put("a", 1);
        //map.put("b", 2);
        //
        //kieSession.insert(map);


        List<Person> list = new ArrayList<>();
        Person person = new Person();
        person.setAge(18);
        list.add(person);

        kieSession.insert(list);
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
