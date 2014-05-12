package book.hibernate.chapter03.entity;


public class Advert {

  private long id;
  private String title;
  private String message;
  private User user;

  public Advert() {
  }

  public Advert(String title, String message, User user) {
    this.title = title;
    this.message = message;
    this.user = user;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

}
