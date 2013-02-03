package simple.spring.batch.job2;

import org.springframework.batch.item.ItemProcessor;

public class Job2Processor implements ItemProcessor<String, String> {

  public String process(String item) throws Exception {
    
    System.out.println("::: " + getClass().getSimpleName() + " :::");
    
    System.out.println("<<< " + item);
    item = "seungbeomi";
    System.out.println(">>> " + item);
    
    return item;
  }


}
 