package book.hibernate3.mapping01.component;

import java.io.Serializable;

public class Employment implements Serializable {

  private int id;
  private Name korName;
  private Name engName;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Name getKorName() {
    return korName;
  }

  public void setKorName(Name korName) {
    this.korName = korName;
  }

  public Name getEngName() {
    return engName;
  }

  public void setEngName(Name engName) {
    this.engName = engName;
  }

}
