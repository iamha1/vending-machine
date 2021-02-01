package com.techelevator;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class SalesReport implements Closeable {
	
	private Map<String, Integer> quantityOfEachProductSold = new HashMap<>();
	private BigDecimal totalBalance = new BigDecimal("0");
	private File logFile;
	private PrintWriter writer;

	
	public SalesReport (String path) throws IOException {
		//time stamp file name
		path = createTimeStampFileName(path);
		this.logFile = new File(path);
		
		if (this.logFile.exists()) {
			writer = new PrintWriter(new FileWriter(this.logFile, true));  // true says we will be appending
			this.write("SALES REPORT");

		} else {
			writer = new PrintWriter(this.logFile);
		}
	}
	
	public String createTimeStampFileName(String path) {
		String stringBuilder = "";
		stringBuilder = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy-HH-mm-ss-a---"));
		stringBuilder += path;
		return stringBuilder;
		
	}
	
	public void countSales(Vendable v) {
		String productName = v.getProductName();
		BigDecimal price = v.getPrice();
		totalBalance = totalBalance.add(price);
		
		boolean bool = quantityOfEachProductSold.containsKey(productName);
		if (bool == false) {
			quantityOfEachProductSold.put(productName, 1);	
		}
		else { 
			Integer productQuantity = 1 + quantityOfEachProductSold.get(productName);	
			quantityOfEachProductSold.put(productName, productQuantity);	
		}	

	}
	
	public void writeSalesReport() {
		write(generateSalesReport());
		
	}
	
	
	public String generateSalesReport() {
		
		String stringBuilder = "Products & Quantity Sold\n";
		
		  for (Map.Entry<String, Integer> e: quantityOfEachProductSold.entrySet()) {
			  stringBuilder += e.getKey() + " | " + e.getValue()+ "\n";	  
		  }
		  		
		stringBuilder += ("*************SALES REPORT************\n");
		stringBuilder += "Total Balance: ";
		stringBuilder += Purchasing.currencyFormat(totalBalance);
	
		return stringBuilder;
		
	}
	
	public void write(String logMessage) {
		writer.println(logMessage);
		writer.flush();
	}
	
	@Override
	public void close() throws IOException {
		writer.close();

	}

}

