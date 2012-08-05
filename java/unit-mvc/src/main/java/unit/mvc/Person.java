package unit.mvc;

import java.util.concurrent.atomic.AtomicLong;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Person {
	
	private Long id;
	
	@NotNull
	@Size(min=1, max=25)
	private String name;
	
	private static final AtomicLong idSequence = new AtomicLong();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	Long assignId() {
		this.id = idSequence.incrementAndGet();
		return id;
	}
}
