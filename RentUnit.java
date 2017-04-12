import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

public class RentUnit {
	//������ ��� ������ � ������, �������� ���-�� �� ������
	public Object[] rentedNames;
	//������ ��� ������ � ����������� ������� ������
	public Object[] rentedQuantity;
	//������ ��� ������ � ��������� ������
	private Object[] units;
	public Object[] getUnits(){
		return units;
	}
	private ArrayList<String> rentedBy = new ArrayList<String>();
	private ArrayList<String> rentedQuant = new ArrayList<String>();
	private ArrayList<String> rentedItemTitl = new ArrayList<String>();
	public void readRented(String fileName){
		try{
	    final File xmlFile = new File(System.getProperty("user.dir")
	    		+ File.separator + fileName);
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    DocumentBuilder db = dbf.newDocumentBuilder();
	    Document doc = db.parse(xmlFile);
	    //��������� ������������ �� �����������, �� �������������
	    doc.getDocumentElement().normalize();
	    //�������� ��� ���� � ������ "sportsInventory"
	    NodeList nodeList = doc.getElementsByTagName("sportsInventory");

	    for (int i = 0; i < nodeList.getLength(); i++) {
	        //������� ���������� �� ������� �� ��������� ���������
	    	//��������� ������ � ����������� �������
	        Node node = nodeList.item(i);
	        if (Node.ELEMENT_NODE == node.getNodeType()) {
	            Element element = (Element) node;
	            for(int j = 0; j < element
	                    .getElementsByTagName("name").getLength(); j++){
	                rentedBy.add(element
	                        .getElementsByTagName("name").item(j)
	                        .getTextContent());
	            }
                rentedQuant.add(element
	                    .getElementsByTagName("rentedQuantity").item(0)
	                    .getTextContent());
	            rentedItemTitl.add(element
	                    .getElementsByTagName("title").item(0)
	                    .getTextContent());
	            
	        }
	    }
	    } catch (ParserConfigurationException | SAXException
	        | IOException ex) {
	    Logger.getLogger(Shop.class.getName())
	            .log(Level.SEVERE, null, ex);
	    }
		rentedNames = rentedBy.toArray();
		rentedQuantity = rentedQuant.toArray();
		units = rentedItemTitl.toArray();
		}
}
