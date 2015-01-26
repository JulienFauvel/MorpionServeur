package util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ConfigFile {

	public static final String CFG_PATH = System.getProperty("user.dir")
			+ "/cfg/config.xml";

	private File file;
	private int numberPlayer;
	private int timer;
	private int timeOut;
	private int waitDelay;
	private boolean variableTurn;
	private boolean initialState;

	public ConfigFile() {
		file = new File(ConfigFile.CFG_PATH);
		numberPlayer = Integer.parseInt(getElementXML("nbjoueur"));
		timer = Integer.parseInt(getElementXML("timer"));
		timeOut = Integer.parseInt(getElementXML("timeout")) * 1000;
		waitDelay = Integer.parseInt(getElementXML("delaiattente")) * 1000;
		variableTurn = Boolean.parseBoolean(getElementXML("tourvariable"));
		initialState = Boolean.parseBoolean(getElementXML("etatinitial"));
	}

	public int getNumberPlayer() {
		return numberPlayer;
	}

	public int getTimer() {
		return timer;
	}

	public int getTimeOut() {
		return timeOut;
	}

	public int getWaitDelay() {
		return waitDelay;
	}

	public boolean isVariableTurn() {
		return variableTurn;
	}

	public boolean isInitialState() {
		return initialState;
	}

	private String getElementXML(String parameter) {

		try {
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = docBuilder.parse(file);

			return doc.getElementsByTagName(parameter).item(0).getTextContent();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
