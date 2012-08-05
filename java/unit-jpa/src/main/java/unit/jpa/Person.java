package unit.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class Person {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Length(min=4,max=8,message="パスワードは4～8文字で入力してください。")
	@NotEmpty
	private String name;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Person[");
		sb.append("id=" + id);
		sb.append(", name=" + name);
		sb.append("]");
		return sb.toString();
	}

}
