package clueGame;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.NumberFormat;

public class BadConfigFormatException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public BadConfigFormatException(){
		super("Error: Bad Configuration");
	}
	
	public BadConfigFormatException(String message) throws FileNotFoundException{
		super("Error: Bad Configuration on file: " + message);
		PrintWriter w = new PrintWriter("logfile.txt");
		w.println("Error: Bad Configuration on file: " + message);
		w.close();
	}
	
}
