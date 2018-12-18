package wang.crispin.example.drools.chapter9;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import wang.crispin.example.drools.BaseTest;
import wang.crispin.example.drools.model.Person;

/**
 * @author WangPingChun
 * @since 2018-12-18
 */
@Slf4j
public class QueryTest extends BaseTest {
    @Test
    public void testQuery() {
        final KieSession kieSession = getKieSessionByName("query-rules");

        Person person1 = new Person();
        person1.setAge(20);
        person1.setName("Tom");
        Person person2 = new Person();
        person2.setAge(23);
        person2.setName("Jack");

        kieSession.insert(person1);
        kieSession.insert(person2);

        kieSession.fireAllRules();

        //final QueryResults queryResults = kieSession.getQueryResults("query-by-age");
        //for (QueryResultsRow queryResult : queryResults) {
        //    final Person person = (Person) queryResult.get("$person");
        //    log.info(person.toString());
        //}
        final QueryResults queryResults = kieSession.getQueryResults("query-by-param", 21);
        for (QueryResultsRow queryResult : queryResults) {
            final Person person = (Person) queryResult.get("$person");
            log.info(person.toString());
        }
        kieSession.dispose();
    }
}
