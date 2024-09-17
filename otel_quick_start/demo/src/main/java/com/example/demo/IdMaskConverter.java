package com.example.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;

@Plugin(name = "IdMaskConverter", category = "Converter")
@ConverterKeys({ "spi", "trscId" })
public class IdMaskConverter extends LogEventPatternConverter {

	private static final String ID_REGEX = "([0-9]{4})";
	private static final Pattern ID_PATTERN = Pattern.compile(ID_REGEX);
	private static final String ID_REPLACEMENT = "XXXX";



	protected IdMaskConverter(String name, String style) {
		super(name, style);
	}



	public static IdMaskConverter newInstance(String[] options) {
		return new IdMaskConverter("spi", Thread.currentThread().getName());
	}



	@Override
	public void format(LogEvent event, StringBuilder outputMessage) {

		String message = event.getMessage().getFormattedMessage();
		String maskedMessage = message;
		try {
			maskedMessage = mask(message);
		} catch (Exception e) {
			System.out.println("Failed While Masking");
			maskedMessage = message;
		}
		outputMessage.append(maskedMessage);
	}



	private String mask(String message) {
		Matcher matcher = null;
		StringBuffer buffer = new StringBuffer();
		matcher = ID_PATTERN.matcher(message);
		maskMatcher(matcher, buffer, ID_REPLACEMENT);

		return buffer.toString();
	}



	private StringBuffer maskMatcher(Matcher matcher, StringBuffer buffer, String maskStr) {
		while (matcher.find()) {
			matcher.appendReplacement(buffer, maskStr);
		}
		matcher.appendTail(buffer);
		return buffer;
	}

}