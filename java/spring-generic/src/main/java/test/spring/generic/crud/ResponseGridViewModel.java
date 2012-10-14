package test.spring.generic.crud;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ResponseGridViewModel<T> implements Serializable {

	private List<T> gridData;
	private int currPage;
	private int totalPages;
	private int totalRecords;
	
	public ResponseGridViewModel() {
	}

	public ResponseGridViewModel(List<T> gridData, int currPage, int totalPages, int totalRecords) {
		this.gridData = gridData;
		this.currPage = currPage;
		this.totalPages = totalPages;
		this.totalRecords = totalRecords;
	}

}
