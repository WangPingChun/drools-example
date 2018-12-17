package wang.crispin.example.drools.chapter6;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.kie.api.runtime.Calendars;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.time.Calendar;
import org.quartz.impl.calendar.WeeklyCalendar;
import wang.crispin.example.drools.BaseTest;
import wang.crispin.example.drools.model.Person;
import wang.crispin.example.drools.model.Server;

/**
 * @author WangPingChun
 * @since 2018-12-14
 */
@Slf4j
public class RulesAttrTest extends BaseTest {

    @Test
    public void testNoLoop() {
        final KieSession kieSession = super.getKieSessionByName("no-loop-rules");

        Person person = new Person();
        person.setAge(8);

        kieSession.insert(person);
        kieSession.fireAllRules();
        kieSession.dispose();
    }

    @Test
    public void testRowFlowGroup() {
        final KieSession kieSession = super.getKieSessionByName("ruleFlowGroup-rules");
        kieSession.getAgenda().getAgendaGroup("rule-flow-group-1").setFocus();
        kieSession.fireAllRules();
        // 一个session建立之后一个 rule-group 只能执行一次
        kieSession.fireAllRules();
        kieSession.dispose();
    }

    @Test
    public void lockOnActive() {
        final KieSession kieSession = super.getKieSessionByName("lock-on-active-rules");

        Person person = new Person();
        person.setAge(18);

        kieSession.insert(person);
        final int count = kieSession.fireAllRules();
        System.out.println(String.format("规则被触发了%s次", count));
        kieSession.dispose();
    }

    @Test
    public void testSalience() {
        final KieSession kieSession = super.getKieSessionByName("salience-rules");

        Person person = new Person();
        person.setAge(10);

        kieSession.insert(person);

        kieSession.fireAllRules();
        kieSession.dispose();
    }

    @Test
    public void testAgendaGroup() {
        final KieSession kieSession = super.getKieSessionByName("agendaGroup-rules");
        kieSession.getAgenda().getAgendaGroup("agenda-group-test").setFocus();

        kieSession.fireAllRules();
        kieSession.dispose();
    }

    @Test
    public void testAutoFocus() {
        final KieSession kieSession = super.getKieSessionByName("autoFocus-rules");

        kieSession.fireAllRules();
        kieSession.dispose();
    }

    @Test
    public void testActivationGroup() {
        final KieSession kieSession = super.getKieSessionByName("activationGroup-rules");

        kieSession.fireAllRules();
        kieSession.dispose();
    }

    @Test
    public void testDateEffective() {
        // 设置drools的日期格式
        System.setProperty("drools.dateformat", "yyyy-MM-dd");
        final KieSession kieSession = super.getKieSessionByName("dateEffective-rules");

        kieSession.fireAllRules();
        kieSession.dispose();
    }

    @Test
    public void testTimer() throws InterruptedException {
        final KieSession kieSession = super.getKieSessionByName("timer-rules");

        Server server = new Server();
        server.setTimes(0);

        new Thread(() -> kieSession.fireUntilHalt()).start();

        final FactHandle factHandle = kieSession.insert(server);
        for (int i = 1; i < 10; i++) {
            Thread.sleep(1000);
            server.setTimes(i);
            kieSession.update(factHandle, server);
        }

        Thread.sleep(3000);

        kieSession.halt();

        System.out.println(server.getResult());

        kieSession.dispose();
    }

    @Test
    public void testCalender() {
        final KieSession kieSession = super.getKieSessionByName("calenderTest-rules");

        final Calendars calendars = kieSession.getCalendars();
        calendars.set("weekday", WEEKDAY);

        kieSession.fireAllRules();
        kieSession.dispose();
    }

    private static final Calendar WEEKDAY = timestamp -> {
        WeeklyCalendar weeklyCalendar = new WeeklyCalendar();
        weeklyCalendar.setDaysExcluded(new boolean[]{false, false, false, false, false, false, false});
        weeklyCalendar.setDayExcluded(2, true);
        return weeklyCalendar.isTimeIncluded(timestamp);
    };
}
