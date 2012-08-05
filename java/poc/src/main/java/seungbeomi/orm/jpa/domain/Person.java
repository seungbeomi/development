package seungbeomi.orm.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

//@Entity
//@Table(name="Person")
public class Person {

//	@Id
	private int id;
	private String name;
//	@OneToOne
//	@JoinColumn(name="DETAIL_ID")
	private PersonDetail detail;

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

	public PersonDetail getDetail() {
		return detail;
	}

	public void setDetail(PersonDetail detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
