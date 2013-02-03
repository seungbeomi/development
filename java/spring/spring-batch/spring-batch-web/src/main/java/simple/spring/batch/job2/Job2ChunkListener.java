package simple.spring.batch.job2;

import org.springframework.batch.core.ChunkListener;

public class Job2ChunkListener implements ChunkListener {

  public void beforeChunk() {
    System.out.println("::: " + getClass().getSimpleName() + ".beforeChunk");
  }

  public void afterChunk() {
    System.out.println("::: " + getClass().getSimpleName() + ".afterChunk");
  }

}
