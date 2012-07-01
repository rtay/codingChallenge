package rtay;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GEDCOMParser {
	private static final String GEDCOM_FORMAT_REGEX = "(\\d+)(\\s+)(\\S+)(\\s*)(.*)";
	private static final String GEDCOM_FORMAT_ID_REGEX = "(@)(.*)(@)";
	
	/**
	 * @param args Command-line arguments
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
				
		// parse file into objects
		
		try
		{	
			XmlTag data = parseGedcomFile(inputFilePath);
			
			// output objects into XML string
			System.out.println(data.toString());
		}
		catch (IOException err) {
			System.out.println(err.getMessage());
		}	
		return;
	}

	/**
	 * @brief	Method to parse GEDCOM encoded file into XmlTag object
	 * @param filepath - path to GEDCOM file
	 * @return XmlTag object containing XML
	 */
	private static XmlTag parseGedcomFile(String filepath) throws IOException{
		String lineRead;

		Stack <XmlTag> tagStack = new Stack <XmlTag> ();
		XmlTag mainTag = new XmlTag("gedcom");
		mainTag.setLevel(0);
		tagStack.push(mainTag);
		
		// Read data from file
		FileReader fr = new FileReader(filepath);
		BufferedReader br = new BufferedReader(fr);
			
		// Regex pattern for GEDCOM file
		Pattern gedcomRegex = Pattern.compile(GEDCOMParser.GEDCOM_FORMAT_REGEX);
		
		while ( (lineRead = br.readLine())!= null) {
			// Regex line by line
			Matcher regexMatcher = gedcomRegex.matcher(lineRead);
			if(regexMatcher.find()) {
				// Acquire data from gedcom formatted line
				int level = Integer.parseInt(regexMatcher.group(1)) + 1; // Offset 1 level for <GEDCOM> main tag
				String tagOrId = regexMatcher.group(3); // TAGORID
				String data = regexMatcher.group(5); // DATA
				
				// pop tagStack until lastElement is the parent tag for current line
				while(level <= tagStack.lastElement().getLevel() ) {
					tagStack.pop();
				}				
				
				// Create new XmlTag object from current file line
				XmlTag xObj = XmlTag.createTag(level, tagOrId, data, GEDCOMParser.GEDCOM_FORMAT_ID_REGEX);
						
				// Link newly created XmlTag object to parent Tag
				tagStack.lastElement().addChildTag(xObj);
				tagStack.push(xObj);
			}
		}	
		return mainTag;
	}
}
