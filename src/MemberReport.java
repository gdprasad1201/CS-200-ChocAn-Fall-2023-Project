package project4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * MemberReport class. Generates member report.
 * @author Vishak Vikranth
 */
public class MemberReport {
	private LocalDate now = LocalDate.now();
	
	/**
	 * Prints member report.
	 * @param memberRecord
	 * @throws IOException
	 */
	public void print(MemberRecord memberRecord) throws IOException {
		File report = new File(memberRecord.getName() + "_" + now.format(DateTimeFormatter.ofPattern("MM-dd-yyyy")) + ".txt");
		if (report.exists()) report.delete();
		report.createNewFile();
		FileWriter writer = new FileWriter(report);

		System.out.println("MEMBER REPORT " + now.format(DateTimeFormatter.ofPattern("MM-dd-yyyy")) + "\n");

		System.out.println("Name: " + memberRecord.getName() + "\n");
		writer.append("Name: " + memberRecord.getName() + "\n");
		writer.flush();

		System.out.println("Number: " + memberRecord.getNumber() + "\n");
		writer.append("Number: " + memberRecord.getNumber() + "\n");
		writer.flush();

		System.out.println("Address: " + memberRecord.getStreetAddress() + ", " + memberRecord.getCity() + ", " + memberRecord.getState() + ", " + memberRecord.getZip() + "\n");
		writer.append("Address: " + memberRecord.getStreetAddress() + ", " + memberRecord.getCity() + ", " + memberRecord.getState() + ", " + memberRecord.getZip() + "\n");
		writer.flush();

		System.out.println("Status: " + memberRecord.returnStatus() + "\n");
		writer.append("Status: " + memberRecord.returnStatus() + "\n");
		writer.flush();

		System.out.println("Service Records: \n");
		writer.append("Service Records: \n");
		writer.flush();

		for (int i = 0; i < memberRecord.returnServices().size(); i++) {
			ServiceRecord serviceRecord = memberRecord.returnServices().get(i);

			System.out.println(i + 1 + ".\n");

			System.out.println("\tService Date: " + serviceRecord.getServiceDate() + "\n");
			writer.append("\tService Date: " + serviceRecord.getServiceDate() + "\n");
			writer.flush();

			System.out.println("\tDate/Time Received: " + serviceRecord.getDateTimeReceived() + "\n");
			writer.append("\tProvider Name: " + serviceRecord.getProviderName() + "\n");
			writer.flush();

			System.out.println("\tProvider Name: " + serviceRecord.getProviderName() + "\n");
			writer.append("\tService Name: " + serviceRecord.getServiceName() + "\n");
			writer.flush();

			System.out.println("\n");
		}

		writer.close();
	}
}
