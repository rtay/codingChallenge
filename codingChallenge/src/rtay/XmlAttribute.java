package rtay;

/**
 * @author Raymond Tay
 * @date 05 July 2012
 * @version 1.0
 * @brief A simple abstract data structure to represent xml attributes within XmlTag
 */
public class XmlAttribute {
	
	private String name;
	private String value;
	
	//ctor
	/**
	 * @brief XmlAttribute constructor with compulsory name and value
	 * @param name	Name of the attribute
	 * @param value	Value of the attribute
	 */
	public XmlAttribute(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	/**
	 * @brief converts XmlAttribute to string
	 * @example "id=@1001" (quotes included)
	 */
	public String toString() {
		return '\"' + this.name + "=" + this.value + '\"';
	}		
}
