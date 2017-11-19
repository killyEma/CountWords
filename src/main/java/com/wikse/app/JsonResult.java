package com.wikse.app;

import java.io.Serializable;
import java.util.List;

public class JsonResult implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<WordCount> results;
	
	public JsonResult(List<WordCount> results){
		this.setWordCounts(results);
	}

	public List<WordCount> getWordCounts() {
		return results;
	}

	public void setWordCounts(List<WordCount> results) {
		this.results = results;
	}
	
}
