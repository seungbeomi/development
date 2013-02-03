package simple.spring.batch.job2;

import java.util.List;

import org.springframework.batch.core.ItemWriteListener;

public class Job2ItemWriteListener implements ItemWriteListener<String> {

  public void beforeWrite(List<? extends String> items) {
    System.out.println("::: " + getClass().getSimpleName() + ".beforeWrite");
  }

  public void afterWrite(List<? extends String> items) {
    System.out.println("::: " + getClass().getSimpleName() + ".afterWrite");
  }

  public void onWriteError(Exception exception, List<? extends String> items) {
    System.out.println("::: " + getClass().getSimpleName() + ".onWriteError");
  }

}
