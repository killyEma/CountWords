package com.wikse.app;

import java.io.Serializable;
import java.util.List;

public class WordCount implements Serializable{

	private static final long serialVersionUID = 1L;
	private String word;
	private int count;
	private List<String> sentenceIndexes; 
	
	public WordCount(String word, int count, List<String> sentenceIndexes) {
		this.word = word;
		this.setCount(count);
		setSentenceIndexes(sentenceIndexes);
	}
	
	@Override
	public boolean equals(Object obj) {
		return word.equals(((WordCount)obj).getWord());
	}
	
	public String getWord() {
		return word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}

	public List<String> getSentenceIndexes() {
		return sentenceIndexes;
	}

	public void setSentenceIndexes(List<String> sentenceIndexes) {
		this.sentenceIndexes = sentenceIndexes;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
