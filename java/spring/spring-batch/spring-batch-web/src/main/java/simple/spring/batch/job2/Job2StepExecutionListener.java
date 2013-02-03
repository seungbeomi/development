package simple.spring.batch.job2;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class Job2StepExecutionListener implements StepExecutionListener {

  public void beforeStep(StepExecution stepExecution) {
    System.out.println("::: " + getClass().getSimpleName() + ".beforeStep");
  }

  public ExitStatus afterStep(StepExecution stepExecution) {
    System.out.println("::: " + getClass().getSimpleName() + ".afterStep");
    return ExitStatus.COMPLETED;
  }

}
