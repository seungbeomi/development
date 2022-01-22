package kr.co.bnksys.datatable;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.drools.decisiontable.ExternalSpreadsheetCompiler;
import org.junit.jupiter.api.Test;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.core.io.ClassPathResource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataTableTest {

    @Test
    void test() throws IOException {

        InputStream templateStream = new ClassPathResource("kr/co/bnksys/datatable/template-sample.drt").getInputStream();
        InputStream xlsStream = new ClassPathResource("kr/co/bnksys/datatable/template-sample.xlsx").getInputStream();

        ExternalSpreadsheetCompiler compiler = new ExternalSpreadsheetCompiler();
        String drl = compiler.compile(xlsStream, templateStream, 3, 2);

        KieSession kSession = this.createKieSessionFromDRL(drl);
    }

    private KieSession createKieSessionFromDRL(String drl) {
        KieHelper kHelper = new KieHelper();
        kHelper.addContent(drl, ResourceType.DRL);

        Results results = kHelper.verify();
        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)) {
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                log.error("ERROR: {}", message.getText());
            }
            throw new IllegalStateException("Compilation errors were found. Check the logs");
        }
        return kHelper.build().newKieSession();
    }

}
