package simple.spring.batch;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;


/**
 * Dummy {@link ItemWriter} which only logs data it receives.
 */
@Component("writer")
public class ExampleItemWriter implements ItemWriter<Object> {

	private static final Log log = LogFactory.getLog(ExampleItemWriter.class);
	
	/**
	 * @see ItemWriter#write(java.util.List)
	 */
	public void write(List<? extends Object> data) throws Exception {
	  System.out.println(">>> itemWriter");
		log.info(data);
		//throw new RuntimeException("에러에러에러");
	}

}
