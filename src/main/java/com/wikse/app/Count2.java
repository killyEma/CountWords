package com.wikse.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jibx.schema.codegen.extend.NameConverter;

import com.google.gson.Gson;

public class Count2 {
	private static final String DOT_REGEX = "\\.";
	private static final String DIVIDER_REGEX = "\\s+";

	private static final String SPACE_DOT_CHARACTER = " .";
	private static final String DOT_CHARACTER = ".";
	private static final String SPACE_CHARACTER = " ";
	private static final String DISABLED_CHARATER = "x";
	
	private NameConverter nameTools;
	private Gson gson;

	public Count2(String input, NameConverter nameTools, Gson gson) {
		this.nameTools = nameTools;
		this.gson = gson;
	}


	public String retrieveJsonStructureWord(String input) {
		input = sanitazeInput(input);
		List<WordCount> wordCounts = new ArrayList<>();
		List<String> items = retrieveItemsInput(input);
		List<Integer> indexDots = findIndexDots(items);
		
		for(int index = 0 ; index < items.size() ; index++) {
			String word = items.get(index);
			if (isAnInValidInputWord(word)) {
				continue;
			}
			
			wordCounts.add(isSpecialWord(word) ? 
					buildSpecialWord(index, word, items, indexDots) :
					buildWord(word, index, items, indexDots));
			
			items = invalidateWordFound(items, word);
		}

		wordCounts.sort((p1, p2) -> p1.getWord().compareTo(p2.getWord()));

		return gson.toJson(wordCounts);
	}

	private WordCount buildWord(String word, int index, List<String> items, List<Integer> indexDots) {
		int wordCountRepeted = 0;
		List<String> sentenceIndexes = new ArrayList<>();
		word  = nameTools.pluralize(word);
		
		for(int indexWord = index ; indexWord < items.size() ; indexWord ++) {
			String pluralWord = nameTools.pluralize(items.get(indexWord));
			
			if (word.equals(pluralWord)) {
				wordCountRepeted++;
				sentenceIndexes.add(getParagraphNumber(indexWord, indexDots));
			}
		}
		word = nameTools.depluralize(word);

		return new WordCount(word, wordCountRepeted, sentenceIndexes);
	}

	private WordCount buildSpecialWord(int index, String word, List<String> items, List<Integer> indexDots) {
		int wordCountRepeted = 0;
		List<String> sentenceIndexes = new ArrayList<>();
		SpecialWord specialWord = SpecialWord.get(word);

		for(int indexWord = index ; indexWord < items.size() ; indexWord ++) {
			String nextWord = items.get(indexWord);
			
			if (areTheSameWords(specialWord, nextWord)) {
				wordCountRepeted++;
				sentenceIndexes.add(getParagraphNumber(indexWord, indexDots));
			}
		}
		return new WordCount(word, wordCountRepeted, sentenceIndexes);
	}
	
	private boolean areTheSameWords(SpecialWord specialWord, String nextWord) {
		return specialWord.singularWord.equalsIgnoreCase(nextWord) || specialWord.pluralWord.equalsIgnoreCase(nextWord);
	}
	
	private boolean isAnInValidInputWord(String word) {
		return word.equals(DOT_CHARACTER) || word.equals(DISABLED_CHARATER);
	}

	private List<String> invalidateWordFound(List<String> items, String word) {
		
		if(isSpecialWord(word)) {
			
			SpecialWord spWord = SpecialWord.get(word);
			items.replaceAll( 
					t -> spWord.singularWord.equalsIgnoreCase(t) || 
						spWord.pluralWord.equalsIgnoreCase(t) ? DISABLED_CHARATER : t);

		} else {
			items.replaceAll( t -> t.equals(word) ? DISABLED_CHARATER : t);
			items.replaceAll( t -> nameTools.depluralize(t).equals(nameTools.depluralize(word)) ? DISABLED_CHARATER : t);
		}
		
		return items;
	}

	private String sanitazeInput(String input) {
		input = input.replaceAll(DOT_REGEX, SPACE_DOT_CHARACTER);
		for (InvalidInput invalidInput : InvalidInput.list()) {
			input = input.replaceAll(invalidInput.first, SPACE_CHARACTER);
			input = input.replaceAll(invalidInput.second, SPACE_CHARACTER);
		}
		
		return input;
	}

	private List<String> retrieveItemsInput(String input) {
		return Arrays.asList(input.toLowerCase().split(DIVIDER_REGEX));
	}

	private String getParagraphNumber(int indexWord, List<Integer> sentenceIndexes) {
		for (Integer dotIndex : sentenceIndexes) {
			if(indexWord < dotIndex) {
				return String.valueOf(sentenceIndexes.indexOf(dotIndex));
			}
		}
		return null;
	}

	private List<Integer> findIndexDots(List<String> inputs) {
		List<Integer> indexDots = new ArrayList<Integer>();
		for (int index = 0 ; index < inputs.size() ; index++ ) {
			if (inputs.get(index).equals(DOT_CHARACTER)) {
				indexDots.add(index);
			}
		}
		return indexDots;
	}

	private boolean isSpecialWord(String word) {
		return SpecialWord.get(word) != null;
	}
}
