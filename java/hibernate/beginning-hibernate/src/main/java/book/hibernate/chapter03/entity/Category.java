package book.hibernate.chapter03.entity;

import java.util.HashSet;
import java.util.Set;

public class Category {

  private long id;
  private String title;
  private Set<Advert> adverts = new HashSet<Advert>();

  public Category() {
  }

  public Category(String title) {
    this.title = title;
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

  public Set<Advert> getAdverts() {
    return adverts;
  }

  public void setAdverts(Set<Advert> adverts) {
    this.adverts = adverts;
  }

}
