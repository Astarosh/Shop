import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SportEquipment {
	//Массивы для работы с данными о товарах, доступных пользователю
	public Object[] availableNames;
	public Object[] availableQuantity;
	public Object[] prices;
	private Map<String, String> goods = new LinkedHashMap<String, String>();
	public String getGoods(String s){
		return goods.get(s);
	}
	private ArrayList<String> availableThings = new ArrayList<String>();
	private ArrayList<String> availableQuantit = new ArrayList<String>();
	private ArrayList<String> price = new ArrayList<String>();
	public void readAvailable(String fileName){
	try{
    final File xmlFile = new File(System.getProperty("user.dir")
    		+ File.separator + fileName);
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document doc = db.parse(xmlFile);
    doc.getDocumentElement().normalize();
    // Получаем все узлы с именем "sportsInventory"
    NodeList nodeList = doc.getElementsByTagName("sportsInventory");
    for (int i = 0; i < nodeList.getLength(); i++) {
        //Записываем необходимые элементы в массивы.
        Node node = nodeList.item(i);
        if (Node.ELEMENT_NODE == node.getNodeType()) {
            Element element = (Element) node;
            availableThings.add(element
                    .getElementsByTagName("title").item(0)
                    .getTextContent());
            availableQuantit.add(element
                    .getElementsByTagName("available").item(0)
                    .getTextContent());
            price.add(element
                    .getElementsByTagName("price").item(0)
                    .getTextContent());
            goods.put(element
                    .getElementsByTagName("title").item(0)
                    .getTextContent(), element
            .getElementsByTagName("available").item(0)
            .getTextContent()); 
        }
    }
    } catch (ParserConfigurationException | SAXException
        | IOException ex) {
    Logger.getLogger(Shop.class.getName())
            .log(Level.SEVERE, null, ex);
    }
	availableNames = availableThings.toArray();
	availableQuantity = availableQuantit.toArray();
	prices = price.toArray();
	}
}
