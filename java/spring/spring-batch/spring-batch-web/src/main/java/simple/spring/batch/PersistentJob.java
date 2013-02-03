package simple.spring.batch;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

public class PersistentJob implements StatefulJob {

  public void execute(JobExecutionContext context) throws JobExecutionException {
    System.out.println("*********************************");
  }

}
