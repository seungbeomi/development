package book.hibernate3.mapping03.onetoone.foreignkey;


public class MemberDetail {

  private int id;
  private String zipcode;
  private String address;
  private Member member;

  public MemberDetail() {
  }

  public MemberDetail(String zipcode, String address) {
    this.zipcode = zipcode;
    this.address = address;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getZipcode() {
    return zipcode;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }

}
