package kr.co.bnksys.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.junit.jupiter.api.Test;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class CalculatorTest {

    @Test
    void testCalculator() {

        String str = "";
        str += "import kr.co.bnksys.calculator.Input; \n";
        str += "rule plus \n";
        str += "  when \n";
        str += "    i : Input(\"+\".equals(op)); \n";
        str += "  then \n";
        str += "    i.setResult( i.getNum1() + i.getNum2() ); \n";
        str += "end \n";

        KnowledgeBuilder kbuilBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilBuilder.add(ResourceFactory.newByteArrayResource(str.getBytes()), ResourceType.DRL);

        if (kbuilBuilder.hasErrors()) {
            log.warn(kbuilBuilder.getErrors().toString());
        }
        assertFalse(kbuilBuilder.hasErrors());

        InternalKnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addPackages(kbuilBuilder.getKnowledgePackages());

        KieSession ksession = kbase.newKieSession();

        Input input = Input.builder()
                .op("+")
                .num1(1)
                .num2(2)
                .build();

        ksession.insert(input);
        ksession.fireAllRules();

        assertEquals(3, input.getResult());
    }

}
