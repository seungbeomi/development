package book.hibernate3.mapping03.onetoone.foreignkey;


public class Member {

  private int id;
  private String username;
  private MemberDetail memberDetail;

  public Member() {
  }

  public Member(String username) {
    this.username = username;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public MemberDetail getMemberDetail() {
    return memberDetail;
  }

  public void setMemberDetail(MemberDetail memberDetail) {
    this.memberDetail = memberDetail;
  }

}
