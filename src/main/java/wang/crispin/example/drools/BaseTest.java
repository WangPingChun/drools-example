package wang.crispin.example.drools;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @author : wpc
 * 2018-12-13
 */
public class BaseTest {
    protected KieSession getKieSession() {
        KieServices kieServices = KieServices.get();
        KieContainer container = kieServices.getKieClasspathContainer();

        return container.newKieSession("all-rules");
    }

    protected KieSession getKieSession(String agendaGroupName) {
        KieSession kieSession = this.getKieSession();
        kieSession.getAgenda().getAgendaGroup(agendaGroupName).setFocus();
        return kieSession;
    }
}
