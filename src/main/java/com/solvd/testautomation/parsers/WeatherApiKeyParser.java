package com.solvd.testautomation.parsers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;

import java.io.File;

public class WeatherApiKeyParser extends BaseXmlParser<String> {

    private static final Logger LOGGER = LogManager.getLogger(WeatherApiKeyParser.class);

    public WeatherApiKeyParser(File file) {
        super(file);
    }

    @Override
    protected String parseElementValue(Element element) {
        return element.getTextContent();
    }

    @Override
    protected String getDefaultElementValue() {
        return "Weather API key placeholder";
    }

    @Override
    protected void handleFileNotFound() {
        LOGGER.error("Settings file not found");
    }

    @Override
    protected void handleElementNotFound(String elementName, int index) {
        LOGGER.error("{} element not found at index {} in the settings file.", elementName, index);
    }

    @Override
    protected void handleParsingError(String elementName, Exception e) {
        LOGGER.error("Error parsing {} in XML file: {}", elementName, e.getMessage(), e);
    }
}