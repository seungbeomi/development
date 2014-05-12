package book.hibernate.chapter03.entity;


public class Message {

  private String message;

  public Message() {
  }

  public Message(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
