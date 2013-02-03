package simple.spring.batch.job2;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class Job2Listener implements JobExecutionListener {

  public void beforeJob(JobExecution jobExecution) {
    System.out.println("::: " + getClass().getSimpleName() + ".beforeJob");
  }

  public void afterJob(JobExecution jobExecution) {
    System.out.println("::: " + getClass().getSimpleName() + ".afterJob");
    
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      System.out.println("Called when job ends successfully.");
    } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
      System.out.println("Called when job ends in failure.");
    }
  }

}
