package wang.crispin.example.drools.chapter8;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import wang.crispin.example.drools.BaseTest;
import wang.crispin.example.drools.model.Person;

/**
 * @author WangPingChun
 * @since 2018-12-18
 */
@Slf4j
public class RHSTest extends BaseTest {

    @Test
    public void testInsert() {
        final KieSession kieSession = super.getKieSessionByName("insert-rules");

        kieSession.fireAllRules();
        kieSession.dispose();
    }

    @Test
    public void testUpdate() {
        final KieSession kieSession = super.getKieSessionByName("update-rules");
        kieSession.getAgenda().getAgendaGroup("update-demo").setFocus();

        Person person = new Person();
        person.setAge(24);
        final FactHandle factHandle = kieSession.insert(person);
        kieSession.fireAllRules();

        kieSession.getAgenda().getAgendaGroup("update-demo").setFocus();
        person.setAge(25);
        kieSession.update(factHandle, person);
        kieSession.fireAllRules();

        kieSession.dispose();
    }

    @Test
    public void testDelete() {
        final KieSession kieSession = super.getKieSessionByName("delete-rules");
        kieSession.getAgenda().getAgendaGroup("delete-demo").setFocus();

        Person person = new Person();
        person.setAge(21);

        final FactHandle factHandle = kieSession.insert(person);
        kieSession.delete(factHandle);

        kieSession.fireAllRules();
        kieSession.dispose();
    }

    @Test
    public void testModify() {
        final KieSession kieSession = super.getKieSessionByName("modify-rules");
        kieSession.getAgenda().getAgendaGroup("modify-demo").setFocus();

        Person person = new Person();
        person.setAge(21);

        kieSession.insert(person);
        kieSession.fireAllRules();
        kieSession.dispose();

        log.info("Person's name = {}", person.getName());
    }
}
