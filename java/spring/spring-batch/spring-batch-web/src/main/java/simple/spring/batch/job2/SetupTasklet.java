package simple.spring.batch.job2;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.Resource;

public class SetupTasklet implements Tasklet {
  
  private Resource[] resources;

  public void setResources(Resource[] resources) {
    this.resources = resources;
  }

  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
    
    System.out.println("<<< " + getClass().getSimpleName());
    for (Resource r : resources) {
      System.out.println("<<< " + r.getFilename());
    }
    
    return RepeatStatus.FINISHED;
  }

}
