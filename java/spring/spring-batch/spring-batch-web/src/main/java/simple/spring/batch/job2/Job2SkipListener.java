package simple.spring.batch.job2;

import org.springframework.batch.core.SkipListener;

public class Job2SkipListener implements SkipListener<String, String> {

  public void onSkipInRead(Throwable t) {
    System.out.println("::: " + getClass().getSimpleName() + ".onSkipInRead");
  }

  public void onSkipInWrite(String item, Throwable t) {
    System.out.println("::: " + getClass().getSimpleName() + ".onSkipInWrite");
  }

  public void onSkipInProcess(String item, Throwable t) {
    System.out.println("::: " + getClass().getSimpleName() + ".onSkipInProcess");
  }

}
