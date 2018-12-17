package wang.crispin.example.drools.chapter7;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import wang.crispin.example.drools.BaseTest;

/**
 * @author WangPingChun
 * @since 2018-12-17
 */
@Slf4j
public class LHSTest extends BaseTest {


    @Test
    public void testLhs() {
        final KieSession kieSession = super.getKieSessionByName("lhs-rules");
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
