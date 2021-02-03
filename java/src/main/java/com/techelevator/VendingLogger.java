package com.techelevator;

import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VendingLogger implements Closeable {
	
	private File logFile;
	private PrintWriter writer;

	
	public VendingLogger (String path) throws IOException {
		this.logFile = new File(path);
		
		if (this.logFile.exists()) {
			writer = new PrintWriter(new FileWriter(this.logFile, true));  // true says we will be appending
			this.write("TRANSACTIONS LOG");

		} else {
			writer = new PrintWriter(this.logFile);
		}
	}
	
	public static String getDateTime() {
		String stringBuilder = "";
		stringBuilder = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss a"));
		return stringBuilder;
	}

	public void write(String logMessage) {
		writer.println(logMessage);
		writer.flush();
	}
	
	public void log(String logMessage) {
		String stringBuilder = "";
		stringBuilder = getDateTime() + " ";
		stringBuilder += logMessage;
				
		writer.println(stringBuilder);
		writer.flush();
	}
	
	@Override
	public void close() throws IOException {
		writer.close();

	}

}
