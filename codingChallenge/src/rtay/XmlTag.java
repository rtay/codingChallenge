package rtay;

import java.util.Vector;

/**
 * 
 * @author Raymond Tay
 * @brief	Class to represent an Xml tag
 *
 */
public class XmlTag {
	private final char LEVEL_DELIMITER = '\n';
	private String name = null;
	private Vector <XmlAttribute> attribute = new Vector <XmlAttribute>();
	private XmlTag childTag = null;
	private String body = ""; // show empty of not specified
	
	// ctor
	public XmlTag(String name) {
		this.setName(name);
	}
	
	// setters
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAttribute(Vector <XmlAttribute> attribute) {
		this.attribute = attribute;
	}
	
	public void setChildTag(XmlTag childTag) {
		this.childTag = childTag;
	}
	
	// methods
	/**
	 * @brief	Display Xmltag data as actual Xml string
	 * @return	Xml formatted string
	 */
	public String toString() {
		// Generate Xml opening tag string
		String xmlString = "";
		xmlString += "<";
		xmlString += this.name;
		
		// Concatenate all attributes
		for(XmlAttribute xa : this.attribute) {
			xmlString += " ";
			xmlString += xa.toString();
		}
		xmlString += ">";
		
		// Generate Xml body string 
		if(this.childTag != null) {
			// there is another level below current tag
			xmlString += LEVEL_DELIMITER; 
			// show lower level tag (recursively)
			xmlString += this.childTag.toString();
			xmlString += LEVEL_DELIMITER;
		}
		else {
			// show body text without delimiter (end of recursion)
			xmlString += this.body;
		}
		
		// Generate Xml closing tags
		xmlString += "</";
		xmlString += this.name;
		xmlString += ">";
		
		return xmlString;
	}
}
