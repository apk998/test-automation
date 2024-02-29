package com.solvd.testautomation.parsers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;

import java.io.File;

public class UsernameParser extends BaseXmlParser<String> {

    private final static Logger LOGGER = LogManager.getLogger(UsernameParser.class);

    public UsernameParser(File file) {
        super(file);
    }

    @Override
    protected String parseElementValue(Element element) {
        return element.getTextContent();
    }

    @Override
    protected String getDefaultElementValue() {
        return "Username placeholder";
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
