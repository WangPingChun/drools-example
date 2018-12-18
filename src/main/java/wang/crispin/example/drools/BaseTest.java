package wang.crispin.example.drools;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

/**
 * @author : WangPingChun
 * 2018-12-13
 */
public class BaseTest {
    protected KieSession getKieSession() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer container = kieServices.getKieClasspathContainer();

        return container.newKieSession("all-rules");
    }

    protected KieSession getKieSessionByName(String sessionName) {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer container = kieServices.getKieClasspathContainer();

        return container.newKieSession(sessionName);
    }

    protected KieSession getKieSession(String agendaGroupName) {
        KieSession kieSession = this.getKieSession();
        kieSession.getAgenda().getAgendaGroup(agendaGroupName).setFocus();
        return kieSession;
    }

    protected StatelessKieSession getStatelessKieSession() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer container = kieServices.getKieClasspathContainer();

        return container.newStatelessKieSession("stateless-rules");
    }

    protected KieContainer getKieContainer() {
        final KieServices kieServices = KieServices.Factory.get();
        return kieServices.getKieClasspathContainer();
    }
}
