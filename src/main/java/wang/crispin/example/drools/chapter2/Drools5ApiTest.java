package wang.crispin.example.drools.chapter2;

import org.junit.Test;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import wang.crispin.example.drools.model.Goods;

import java.util.Collection;

/**
 * @author WangPingChun
 * @since 2018-12-13
 */
public class Drools5ApiTest {

    @Test
    public void drools5ApiTest() {
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        builder.add(ResourceFactory.newClassPathResource("com/rules/goods.drl", this.getClass()), ResourceType.DRL);

        if (builder.hasErrors()) {
            System.out.println(builder.getErrors());
        }

        KnowledgeBase knowledgeBase = builder.newKnowledgeBase();
        Collection<KnowledgePackage> packages = builder.getKnowledgePackages();
        knowledgeBase.addKnowledgePackages(packages);

        StatefulKnowledgeSession statefulKnowledgeSession = knowledgeBase.newStatefulKnowledgeSession();

        Goods goods = new Goods();

        statefulKnowledgeSession.insert(goods);

        int count = statefulKnowledgeSession.fireAllRules();
        System.out.println("Fire " + count + " rules!");
        statefulKnowledgeSession.dispose();

        System.out.println("The new discount is " + goods.getDiscount());


    }
}
