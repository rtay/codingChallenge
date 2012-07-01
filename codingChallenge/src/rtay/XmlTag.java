package rtay;

import java.util.Vector;

/**
 * 
 * @author Raymond Tay
 * @brief	Class to represent an Xml tag
 *
 */
public class XmlTag {
	private static final char LEVEL_DELIMITER = '\n';
	private String name = null; // compulsory 
	private Vector <XmlTag> childTags;
	private Vector <XmlAttribute> attributes;
	private String value = ""; // show empty if not specified
	private int level = 0;
	
	// ctor
	public XmlTag(String name) {
		this.childTags = new Vector <XmlTag>();
		this.attributes = new Vector <XmlAttribute>();
		this.setName(name);
	}
	
	public XmlTag(String name, String value) {
		this.childTags = new Vector <XmlTag>();
		this.attributes = new Vector <XmlAttribute>();
		this.setName(name);
		this.setValue(value);
	}
	
	// setters
	public void setName(String name) {
		this.name = name;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public void setLevel(int level) {
		this.level = level;		
	}
	
	public void addAttribute(XmlAttribute attribute) {
		this.attributes.add(attribute);
	}
	
	public void addChildTag(XmlTag childTag) {
		this.childTags.add(childTag);
	}
	
	// getters
	public String getName() {
		return this.name;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	// methods
	/**
	 * @brief	Display Xmltag data as actual Xml string
	 * @return	Xml formatted string
	 */
	public String toString() {
		String xmlString = "";
		
		// Generate tabs for opening tag
		for(int i=0; i< level; i++) {
			xmlString += '\t';
		}
		
		// Generate Xml opening tag string
		xmlString += "<"; // Start opening tag
		xmlString += this.name;
	
		// Concatenate all attributes
		for(XmlAttribute xa : this.attributes) {
			xmlString += " ";
			xmlString += xa.toString();
		}
		
		if(this.childTags.size()>0 && this.value.length() > 0) {
			// show value as attribute if set and contains child tags
			xmlString += " ";
			xmlString += "value=";
			xmlString += '\"';
			xmlString += this.value;
			xmlString += '\"';
		}
		xmlString += ">"; // End of opening tag
		
		// Generate Xml body string 
		if(this.childTags.size() == 0) {
			// show value in innerXML without delimiter (end of recursion)
			xmlString += this.value;
		}
		else
		{
			for(XmlTag childTag: this.childTags) {
				// there is another level below current tag
				xmlString += LEVEL_DELIMITER; 
				// show lower level tag (recursively)
				xmlString += childTag.toString();
			}
			xmlString += LEVEL_DELIMITER;
			
			// Generate tab for closing tags (only if newline)
			for(int i=0; i< level; i++) {
				xmlString += '\t';
			}
		}
			
		// Generate Xml closing tags
		xmlString += "</"; // start of closing tag
		xmlString += this.name;
		xmlString += ">"; // end of closing tag
		
		return xmlString;
	}
	
	// static factory method
	public static XmlTag createTag(int level, String tagOrId, String data, String tagOrIdRegex) {
		XmlTag newObj;
		if(tagOrId.matches(tagOrIdRegex)) {
			// tagOrID is an ID, Data is the tagName
			newObj = new XmlTag(data);
			newObj.addAttribute(new XmlAttribute("id", tagOrId));
		}
		else {
			// tagORID is a tag, Data is value
			newObj = new XmlTag(tagOrId, data);
			
		}
		newObj.setLevel(level);
		return newObj;
	}	
}
