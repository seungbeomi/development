package simple.spring.batch.job2;

import org.springframework.batch.core.ItemReadListener;

public class Job2ItemReadListener implements ItemReadListener<String> {

  public void beforeRead() {
    System.out.println("::: " + getClass().getSimpleName() + ".beforeRead");
  }

  public void afterRead(String item) {
    System.out.println("::: " + getClass().getSimpleName() + ".afterRead");
  }

  public void onReadError(Exception ex) {
    System.out.println("::: " + getClass().getSimpleName() + ".onReadError");
  }

}
