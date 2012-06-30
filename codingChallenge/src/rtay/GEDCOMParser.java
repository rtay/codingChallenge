package rtay;

public class GEDCOMParser {

	/**
	 * @param args COmmand-line arguments
	 */
	public static void main(String[] args) {
		// Validate arguments
		if(args.length != 2) {
			// Filename not provided or invalid arguments
			System.out.println("Syntax: GEDCOMParser <inputFile.txt> <outputFile.xml>");
			return;
		}
			
		// setup filename aliases
		final String inputFilePath = args[0];
		final String outputFilePath = args[1];
		
		XmlTag gedcom = new XmlTag("gedcom");
		
		// parse file into objects
				
		
		// output objects into XML string
		System.out.println(gedcom.toString());
	}

}
