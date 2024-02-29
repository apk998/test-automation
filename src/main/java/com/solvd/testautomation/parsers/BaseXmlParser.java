package com.solvd.testautomation.parsers;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public abstract class BaseXmlParser<T> {

    private final File file;

    public BaseXmlParser(File file) {
        this.file = file;
    }

    public T parseElement(String elementName, int index) {
        try {
            if (!file.exists()) {
                handleFileNotFound();
                return getDefaultElementValue();
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName(elementName);

            if (index >= 0 && index < nodeList.getLength()) {
                Node elementNode = nodeList.item(index);

                if (elementNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) elementNode;
                    return parseElementValue(element);
                }
            } else {
                handleElementNotFound(elementName, index);
            }
        } catch (FileNotFoundException e) {
            handleFileNotFound();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            handleParsingError(elementName, e);
        }
        return getDefaultElementValue();
    }

    protected abstract T parseElementValue(Element element);

    protected abstract T getDefaultElementValue();

    protected abstract void handleFileNotFound();

    protected abstract void handleElementNotFound(String elementName, int index);

    protected abstract void handleParsingError(String elementName, Exception e);
}