package project4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 * EFTData class. Generates EFT data file for provider. This class is not used in the current version of the program, but is included for future use.
 * @author Rajiv Yalamanchili
 */
public class EFTData {
	private LocalDate now = LocalDate.now();
	
	/**
	 * Prints EFT data file for provider.
	 * @param providerRecord
	 * @param totalAmount
	 * @throws IOException
	 */
	public void print(ProviderRecord providerRecord, double totalAmount) throws IOException {
		String date = now.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
		System.out.println(providerRecord.getName() + "_" + date + "_EFT.txt");
		File report = new File(providerRecord.getName() + "_" + date + "_EFT.txt");
		if (report.exists()) report.delete();
		report.createNewFile();
		FileWriter writer = new FileWriter(report);

		String time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());

		System.out.println("EFT DATA " + date + "\n");
		writer.append("Current Date and Time: " + time);
		writer.flush();

		System.out.println("Provider Name: " + providerRecord.getName() + "\n");
		writer.append("\nProvider Name: " + providerRecord.getName() + "\n");
		writer.flush();
		
		System.out.println("Provider Number: " + providerRecord.getNumber() + "\n");
		writer.append("\nProvider Number: " + providerRecord.getNumber() + "\n");
		writer.flush();

		System.out.println("Total Owed: " + totalAmount + "\n");
		writer.append("\nTotal Owed: " + totalAmount + "\n");
		writer.flush();

		writer.close();
	}
}
