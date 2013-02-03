package simple.spring.batch.job2;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class Job2Writer implements ItemWriter<Object> {

  public void write(List<? extends Object> items) throws Exception {

    System.out.println(">>> " + items);
    
  }

}
