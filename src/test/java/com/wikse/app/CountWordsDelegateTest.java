package com.wikse.app;

import org.jibx.schema.codegen.extend.DefaultNameConverter;
import org.junit.Test;

import com.google.gson.Gson;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;

public class CountWordsDelegateTest {
	private static final String paragraph = "Take this paragraph of text and return an alphabetized list of ALL unique words." 
			+ "  A unique word is any form of a word often communicated with essentially the same meaning."
			+ " For example, fish and fishes could be defined as a unique word by using their stem fish."
			+ " For each unique word found in this entire paragraph, determine the how many times the word appears in total."
			+ " Also, provide an analysis of what sentence index position or positions the word is found. "
			+ "The following words should not be included in your analysis or result set: \"a\", \"the\", \"and\", \"of\", \"in\", \"be\", \"also\" and \"as\".  "
			+ "Your final result MUST be displayed in a readable console output in the same format as the JSON sample object shown below.";	

	private static final String WORD_VALUE = "word";
	
	private static final JsonResult FINAL_RESULT = new Gson().fromJson(getResult(), JsonResult.class);
	
	@Test
	public void shuldValidSpecificWordFromInput() {
		CountWordsDelegate delegate = new CountWordsDelegate(new DefaultNameConverter());
		
		JsonResult result = delegate.retrieveJsonStructureWord(paragraph);
		WordCount wCount = result.getWordCounts().stream()
				.filter(wordCount -> wordCount.getWord().equals(WORD_VALUE))
				.findFirst()
				.get();
		
		assertNotNull(wCount);
		assertThat(wCount.getWord(), is(WORD_VALUE));
		assertThat(wCount.getCount(), is(8));
		assertThat(wCount.getSentenceIndexes(), is(Arrays.asList("0",  "1", "2", "3", "4", "5")));
	}

	@Test
	public void shuldValidSpecificWordFromInpsdut() {
		List<WordCount> wordCountFinal = FINAL_RESULT.getWordCounts();
		CountWordsDelegate delegate = new CountWordsDelegate(new DefaultNameConverter());
		
		JsonResult result = delegate.retrieveJsonStructureWord(paragraph);
		
		
		List<WordCount> wordCount = result.getWordCounts();
		for (int index = 0; index < wordCount.size(); index++) {
			assertThat(wordCount.get(index).getWord(), is(wordCountFinal.get(index).getWord()));
			assertThat(wordCount.get(index).getCount(), is(wordCountFinal.get(index).getCount()));
			assertThat(wordCount.get(index).getSentenceIndexes(), is(wordCountFinal.get(index).getSentenceIndexes()));
		}
	}
	
	private static String getResult() {
		return "{\"results\":"
				+ "[{\"word\":\"all\",\"count\":1,\"sentenceIndexes\":[\"0\"]},"
				+ "{\"word\":\"alphabetized\",\"count\":1,\"sentenceIndexes\":[\"0\"]},"
				+ "{\"word\":\"analysis\",\"count\":2,\"sentenceIndexes\":[\"4\",\"5\"]},"
				+ "{\"word\":\"any\",\"count\":1,\"sentenceIndexes\":[\"1\"]},"
				+ "{\"word\":\"appear\",\"count\":1,\"sentenceIndexes\":[\"3\"]},"
				+ "{\"word\":\"below\",\"count\":1,\"sentenceIndexes\":[\"6\"]},"
				+ "{\"word\":\"by\",\"count\":1,\"sentenceIndexes\":[\"2\"]},"
				+ "{\"word\":\"communicated\",\"count\":1,\"sentenceIndexes\":[\"1\"]},"
				+ "{\"word\":\"console\",\"count\":1,\"sentenceIndexes\":[\"6\"]},"
				+ "{\"word\":\"could\",\"count\":1,\"sentenceIndexes\":[\"2\"]},"
				+ "{\"word\":\"defined\",\"count\":1,\"sentenceIndexes\":[\"2\"]},"
				+ "{\"word\":\"determine\",\"count\":1,\"sentenceIndexes\":[\"3\"]},"
				+ "{\"word\":\"displayed\",\"count\":1,\"sentenceIndexes\":[\"6\"]},"
				+ "{\"word\":\"each\",\"count\":1,\"sentenceIndexes\":[\"3\"]},"
				+ "{\"word\":\"entire\",\"count\":1,\"sentenceIndexes\":[\"3\"]},"
				+ "{\"word\":\"essentially\",\"count\":1,\"sentenceIndexes\":[\"1\"]},"
				+ "{\"word\":\"example\",\"count\":1,\"sentenceIndexes\":[\"2\"]},"
				+ "{\"word\":\"final\",\"count\":1,\"sentenceIndexes\":[\"6\"]},"
				+ "{\"word\":\"fish\",\"count\":3,\"sentenceIndexes\":[\"2\"]},"
				+ "{\"word\":\"following\",\"count\":1,\"sentenceIndexes\":[\"5\"]},"
				+ "{\"word\":\"for\",\"count\":2,\"sentenceIndexes\":[\"2\",\"3\"]},"
				+ "{\"word\":\"form\",\"count\":1,\"sentenceIndexes\":[\"1\"]},"
				+ "{\"word\":\"format\",\"count\":1,\"sentenceIndexes\":[\"6\"]},"
				+ "{\"word\":\"found\",\"count\":2,\"sentenceIndexes\":[\"3\",\"4\"]},"
				+ "{\"word\":\"how\",\"count\":1,\"sentenceIndexes\":[\"3\"]},"
				+ "{\"word\":\"included\",\"count\":1,\"sentenceIndexes\":[\"5\"]},"
				+ "{\"word\":\"index\",\"count\":1,\"sentenceIndexes\":[\"4\"]},"
				+ "{\"word\":\"is\",\"count\":2,\"sentenceIndexes\":[\"1\",\"4\"]},"
				+ "{\"word\":\"json\",\"count\":1,\"sentenceIndexes\":[\"6\"]},"
				+ "{\"word\":\"list\",\"count\":1,\"sentenceIndexes\":[\"0\"]},"
				+ "{\"word\":\"many\",\"count\":1,\"sentenceIndexes\":[\"3\"]},"
				+ "{\"word\":\"meaning\",\"count\":1,\"sentenceIndexes\":[\"1\"]},"
				+ "{\"word\":\"must\",\"count\":1,\"sentenceIndexes\":[\"6\"]},"
				+ "{\"word\":\"not\",\"count\":1,\"sentenceIndexes\":[\"5\"]},"
				+ "{\"word\":\"object\",\"count\":1,\"sentenceIndexes\":[\"6\"]},"
				+ "{\"word\":\"often\",\"count\":1,\"sentenceIndexes\":[\"1\"]},"
				+ "{\"word\":\"or\",\"count\":2,\"sentenceIndexes\":[\"4\",\"5\"]},"
				+ "{\"word\":\"output\",\"count\":1,\"sentenceIndexes\":[\"6\"]},"
				+ "{\"word\":\"paragraph\",\"count\":2,\"sentenceIndexes\":[\"0\",\"3\"]},"
				+ "{\"word\":\"position\",\"count\":2,\"sentenceIndexes\":[\"4\"]},"
				+ "{\"word\":\"provide\",\"count\":1,\"sentenceIndexes\":[\"4\"]},"
				+ "{\"word\":\"readable\",\"count\":1,\"sentenceIndexes\":[\"6\"]},"
				+ "{\"word\":\"result\",\"count\":2,\"sentenceIndexes\":[\"5\",\"6\"]},"
				+ "{\"word\":\"return\",\"count\":1,\"sentenceIndexes\":[\"0\"]},"
				+ "{\"word\":\"same\",\"count\":2,\"sentenceIndexes\":[\"1\",\"6\"]},"
				+ "{\"word\":\"sample\",\"count\":1,\"sentenceIndexes\":[\"6\"]},"
				+ "{\"word\":\"sentence\",\"count\":1,\"sentenceIndexes\":[\"4\"]},"
				+ "{\"word\":\"set:\",\"count\":1,\"sentenceIndexes\":[\"5\"]},"
				+ "{\"word\":\"should\",\"count\":1,\"sentenceIndexes\":[\"5\"]},"
				+ "{\"word\":\"shown\",\"count\":1,\"sentenceIndexes\":[\"6\"]},"
				+ "{\"word\":\"stem\",\"count\":1,\"sentenceIndexes\":[\"2\"]},"
				+ "{\"word\":\"take\",\"count\":1,\"sentenceIndexes\":[\"0\"]},"
				+ "{\"word\":\"text\",\"count\":1,\"sentenceIndexes\":[\"0\"]},"
				+ "{\"word\":\"their\",\"count\":1,\"sentenceIndexes\":[\"2\"]},"
				+ "{\"word\":\"this\",\"count\":2,\"sentenceIndexes\":[\"0\",\"3\"]},"
				+ "{\"word\":\"time\",\"count\":1,\"sentenceIndexes\":[\"3\"]},"
				+ "{\"word\":\"total\",\"count\":1,\"sentenceIndexes\":[\"3\"]},"
				+ "{\"word\":\"unique\",\"count\":4,\"sentenceIndexes\":[\"0\",\"1\",\"2\",\"3\"]},"
				+ "{\"word\":\"using\",\"count\":1,\"sentenceIndexes\":[\"2\"]},"
				+ "{\"word\":\"what\",\"count\":1,\"sentenceIndexes\":[\"4\"]},"
				+ "{\"word\":\"with\",\"count\":1,\"sentenceIndexes\":[\"1\"]},"
				+ "{\"word\":\"word\",\"count\":8,\"sentenceIndexes\":[\"0\",\"1\",\"2\",\"3\",\"4\",\"5\"]},"
				+ "{\"word\":\"your\",\"count\":2,\"sentenceIndexes\":[\"5\",\"6\"]}]}";
	}
}
