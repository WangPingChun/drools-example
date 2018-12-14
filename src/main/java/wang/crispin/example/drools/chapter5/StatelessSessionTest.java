package wang.crispin.example.drools.chapter5;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.kie.api.runtime.StatelessKieSession;
import wang.crispin.example.drools.BaseTest;
import wang.crispin.example.drools.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WangPingChun
 * @since 2018-12-14
 */
@Slf4j
public class StatelessSessionTest extends BaseTest {
    @Test
    public void testStatelessSession() {
        final StatelessKieSession kieSession = super.getStatelessKieSession();

        List<Person> list = new ArrayList<>();

        Person person = new Person();
        person.setAge(90);

        Person person1 = new Person();
        person1.setAge(35);

        list.add(person);
        list.add(person1);

        kieSession.execute(list);
    }
}
