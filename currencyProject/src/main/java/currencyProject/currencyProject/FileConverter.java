package currencyProject.currencyProject;

import java.io.IOException;
import java.util.List;

import org.jdom2.*;
import org.jdom2.input.*;

public class FileConverter {

	String filename; 

	public FileConverter(String filename) {
		this.filename= filename;
	}

	public Double getRate(String currency) {
		
		Document document = FileConverter.getSAXParsedDocument(filename);
		Element rootNode = document.getRootElement();
		List<Element> currencyList =rootNode.getChildren().get(2).getChildren().get(0).getChildren();

		for(Element e : currencyList) {
			if(e.getAttributeValue("currency").equals(currency.toUpperCase()))
					return Double.parseDouble(e.getAttributeValue("rate"));
		}
		return -1.0;
	}
	
		private static Document getSAXParsedDocument(final String fileName)
		{
			SAXBuilder builder = new SAXBuilder();
			Document document = null;
			try
			{
				document = (Document) builder.build(fileName);
			}
			catch (JDOMException | IOException e)
			{
				e.printStackTrace();
			}
			return document;
		}

	}
