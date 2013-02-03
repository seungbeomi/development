package simple.spring.batch.job2;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class Job2Reader implements ItemReader<String> {

  private String[] input = { "Hello world!", null };

  private int index = 0;

  public String read() throws Exception, UnexpectedInputException,
      ParseException, NonTransientResourceException {

    System.out.println("<<< " + getClass().getSimpleName());
    
    if (index < input.length) {
      return input[index++];
    }
    else {
      return null;
    }
  }

}
