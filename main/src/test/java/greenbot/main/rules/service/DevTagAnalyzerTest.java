package greenbot.main.rules.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import greenbot.rule.model.Tag;

public class DevTagAnalyzerTest {
	
	private final TagAnalyzer devTagAnalyzer = new TagAnalyzer();
	
	@ParameterizedTest
	@CsvFileSource(resources = "/dev-tags.csv")
	void withCsvSource(String key, String value, Boolean outcome) {
		Tag tag = Tag.builder().key(key).value(value).build();
		assertEquals(outcome, devTagAnalyzer.isDevTagPresent(Arrays.asList(tag)));
	}
}
