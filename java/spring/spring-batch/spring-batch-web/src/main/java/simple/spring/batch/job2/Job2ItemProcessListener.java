package simple.spring.batch.job2;

import org.springframework.batch.core.ItemProcessListener;

public class Job2ItemProcessListener implements ItemProcessListener<String, String> {

  public void beforeProcess(String item) {
    System.out.println("::: " + getClass().getSimpleName() + ".beforeProcess");
  }

  public void afterProcess(String item, String result) {
    System.out.println("::: " + getClass().getSimpleName() + ".afterProcess");
  }

  public void onProcessError(String item, Exception e) {
    System.out.println("::: " + getClass().getSimpleName() + ".onProcessError");
  }

}
