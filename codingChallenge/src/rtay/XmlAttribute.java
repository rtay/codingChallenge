package rtay;

public class XmlAttribute {
	
	private String name;
	private String value;
	
	//ctor
	public XmlAttribute(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public String toString() {
		return '\"' + this.name + "=" + this.value + '\"';
	}
		
}
