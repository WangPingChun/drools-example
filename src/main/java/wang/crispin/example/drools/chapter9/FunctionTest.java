package wang.crispin.example.drools.chapter9;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import wang.crispin.example.drools.BaseTest;

/**
 * @author WangPingChun
 * @since 2018-12-18
 */
@Slf4j
public class FunctionTest extends BaseTest {
    @Test
    public void testFunction() {
        final KieSession kieSession = getKieSessionByName("function-rules");

        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
