package com.wikse.app;

public enum SpecialWord {
	THIS("this", "this"),
	IS("is", "is"),
	ANALISIS("analysis", "analysis"),
	FISH("fish","fishes"),
	BY("by", "by");
	String singularWord;
	String pluralWord;
	
	SpecialWord(String singularWord, String pluralWord) {
		this.singularWord = singularWord;
		this.pluralWord = pluralWord;
	}

	public static SpecialWord get(String word) {
		for (SpecialWord speWord : SpecialWord.values()) {
			if (speWord.singularWord.equals(word) ||  speWord.pluralWord.equals(word)) {
				return speWord;
			}
		}
		return null;
	}
	
	
}
