package XMLconfig;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXMLDomParser {

	private static final String FILENAME = "src" + File.separator + "main" + File.separator + "java" + File.separator
			+ "XMLconfig" + File.separator + "config.xml";
	private static Configuration configuration;

	public static Configuration getConfiguration() {
		return configuration;
	}

	public static void setConfiguration(Configuration configuration) {
		ReadXMLDomParser.configuration = configuration;
	}

	public void readXML() {
		List<String> listTypes = new ArrayList<>();

		// Instantiate the Factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			// process XML securely, avoid attacks like XML External Entities (XXE)
			dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

			// parse XML file
			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse(new File(FILENAME));

			doc.getDocumentElement().normalize();

			// get <staff>
			NodeList list = doc.getElementsByTagName("Configuration");
			Node node = list.item(0);
			Element element = (Element) node;

			// get text

			String idioma = element.getElementsByTagName("Idioma").item(0).getTextContent();
			NodeList tiposNodeList = element.getElementsByTagName("TipoPregunta");
			String timeout = element.getElementsByTagName("TimeoutPreguntas").item(0).getTextContent();
			String nummax = element.getElementsByTagName("NumMaxPreguntas").item(0).getTextContent();
			String ruta = element.getElementsByTagName("RutaXMLKahoots").item(0).getTextContent();
			String countdown = element.getElementsByTagName("CountdownWaitingroom").item(0).getTextContent();

			// Fill object

			listTypes.add(tiposNodeList.item(0).getTextContent());
			listTypes.add(tiposNodeList.item(1).getTextContent());
			listTypes.add(tiposNodeList.item(2).getTextContent());
			listTypes.add(tiposNodeList.item(3).getTextContent());
			configuration = new Configuration(idioma, listTypes, Integer.valueOf(timeout), Integer.valueOf(nummax),
					ruta, Integer.valueOf(countdown));

			System.out.println(configuration);

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

	}

}