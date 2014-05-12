package book.hibernate3.mapping01.component;

import java.io.Serializable;

public class Name implements Serializable {

  private Employment employment; // 컴포넌트에서 부모 객체 참조하기

  private String surname;
  private String name;
  private FamilyInfo familyInfo; // 컴포넌트 중첩

  public Name() {
  }

  public Name(String surname, String name) {
    this.surname = surname;
    this.name = name;
  }

  public Employment getEmployment() {
    return employment;
  }

  public void setEmployment(Employment employment) {
    this.employment = employment;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public FamilyInfo getFamilyInfo() {
    return familyInfo;
  }

  public void setFamilyInfo(FamilyInfo familyInfo) {
    this.familyInfo = familyInfo;
  }

}
