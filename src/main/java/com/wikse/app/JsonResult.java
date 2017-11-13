package com.wikse.app;

import java.util.List;

public class JsonResult {
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
