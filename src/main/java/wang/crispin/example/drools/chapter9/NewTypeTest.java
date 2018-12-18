package wang.crispin.example.drools.chapter9;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.definition.type.FactType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import wang.crispin.example.drools.BaseTest;

/**
 * @author WangPingChun
 * @since 2018-12-18
 */
@Slf4j
public class NewTypeTest extends BaseTest {
    @Test
    public void testNewType() throws IllegalAccessException, InstantiationException {
        final KieContainer kieContainer = getKieContainer();
        final KieBase kieBase = kieContainer.getKieBase("new-type-kbase");
        final FactType factType = kieBase.getFactType("com.newType", "Country");
        final Object country = factType.newInstance();
        factType.set(country, "name", "美国");


        final KieSession kieSession = kieContainer.newKieSession("new-type-rules");

        kieSession.insert(country);

        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
