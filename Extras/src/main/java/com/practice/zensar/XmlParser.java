package com.practice.zensar;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filter;
import org.jdom2.input.SAXBuilder;
import org.jdom2.util.IteratorIterable;

public class XmlParser {

	public static final String BASE_PATH = "/Users/swapnilsarwade/Desktop/Java Programs/";
	
	public static void main(String[] args) throws JDOMException, IOException {
		File xmlFile = new File(BASE_PATH + "Formatted_406224.xml");
		SAXBuilder saxBuilder = new SAXBuilder();
		Document doc = saxBuilder.build(xmlFile);
		doc.getRootElement().getDescendants(new Filter<Element>() {

			@Override
			public List<Element> filter(List<?> content) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Element filter(Object content) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean matches(Object content) {
				if (content instanceof Element) {
					return ((Element)content).getName().equalsIgnoreCase("documentCommandOperand");
				}
				return false;
			}

			@Override
			public Filter<? extends Object> negate() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Filter<? extends Object> or(Filter<?> filter) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Filter<Element> and(Filter<?> filter) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <R> Filter<R> refine(Filter<R> filter) {
				// TODO Auto-generated method stub
				return null;
			}
			
		});
		doc.getRootElement().getChild("documentCommandOperand");
	}
	
	private void getElementOrder(Element rootElement) {
		Map<String, Object> orderMap = new HashMap();
		IteratorIterable<Content> descendants = rootElement.getChild("documentCommandOperand").getDescendants();
		
		for (Iterator iterator = descendants.iterator(); iterator.hasNext();) {
			Content type = (Content) iterator.next();
			
		}
	}
	
}
