package test.spring.generic.crud;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class RequestGridViewModel implements Serializable {
	
	private Map<String, Object> criteria;
	private Boolean search = Boolean.FALSE;
	private Integer page = 0;
	private Integer rows = 0;
	private String order;
	private String sort;

	public RequestGridViewModel() {
		criteria = new HashMap<String, Object>();
	}

}
