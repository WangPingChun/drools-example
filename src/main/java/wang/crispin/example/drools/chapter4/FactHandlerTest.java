package wang.crispin.example.drools.chapter4;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import wang.crispin.example.drools.BaseTest;
import wang.crispin.example.drools.model.Person;

/**
 * @author WangPingChun
 * @since 2018-12-14
 */
@Slf4j
public class FactHandlerTest extends BaseTest {

    @Test
    public void factHandlerTest() {
        final KieSession kieSession = super.getKieSession("fact-handler-group");

        Person person = new Person();
        person.setAge(81);

        final FactHandle factHandle = kieSession.insert(person);
        log.info("toExternalForm = {}", factHandle.toExternalForm());

        final int count = kieSession.fireAllRules();
        log.info("count = {}", count);

        person.setAge(90);
        kieSession.getAgenda().getAgendaGroup("fact-handler-group").setFocus();
        kieSession.update(factHandle, person);
        kieSession.fireAllRules();

        kieSession.dispose();
    }
}
