package common;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class DataReader {

	private static ArrayList<String> headerColumns;
	private DataReader() {}
	
	private static void init() {
		if(headerColumns == null) {
			headerColumns = new ArrayList<String>();
		}
	}
	
	private static ArrayList<String> SplitRow(String input) {
		ArrayList<String> result = new ArrayList<String>();
		int start = 0;
		boolean inQuotes = false;
		for (int current = 0; current < input.length(); current++) {
		    if (input.charAt(current) == '\"') inQuotes = !inQuotes;
		    else if (input.charAt(current) == ',' && !inQuotes) {
		        result.add(input.substring(start, current));
		        start = current + 1;
		    }
		}
		result.add(input.substring(start));
		return result;
	}
	
	private static String buildDateString(String input) throws Exception {
		String[] tokens = input.split("[/|.|\\s]");
		if(tokens.length != 4) throw new Exception("Invalid date: " + input);
		
		return tokens[0] + "/" + tokens[1] + "/" + tokens[2];
	}
	
	public static void readHeaderColumns(String headerValue) {
		init();
		if(!headerColumns.isEmpty()) return;
		StringTokenizer tokens = new StringTokenizer(headerValue, ",");
		while(tokens.hasMoreTokens()) {
			headerColumns.add(tokens.nextToken().toLowerCase());
		}
	}
	
	public static String getValueByColumn(String row, String columnName, String lookupValueType) throws Exception {
		init();
		String value = "<null>";		
		if(row == null) throw new Exception("The row parameter is null");
		
		ArrayList<String> tokens = SplitRow(row);
		
		if(tokens.size() != headerColumns.size())
		{
			String message = "Bad row:\n";
			for(String token : tokens) {
				message += token + "\n";
			}
			throw new Exception(message);
		}
		
		
		for(int i = 0 ; i < headerColumns.size() ; i++) {
			if(headerColumns.get(i).equals(columnName)) {
				
				if(lookupValueType.equals("Date")) {
					value = buildDateString(tokens.get(i));
				}
				else {
					value = tokens.get(i)
							.replace("\"", "")
							.replace(",", "")
							.trim();
				}

				break;
			}
		}
		
		return value;
	}
	
}
