package project4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * ProviderReport class. Generates provider report and EFT data file for provider.
 * @author Vishak Vikranth
 */
public class ProviderReport {
	private static DecimalFormat df = new DecimalFormat("$0.00");
	private LocalDate now = LocalDate.now();
	private int totalConsultations = 0;
	private double totalFee = 0;

	/**
	 * Prints provider report and EFT data file for provider.
	 * @param providerRecord
	 * @throws IOException
	 */
	public void print(ProviderRecord providerRecord) throws IOException {
		String date = now.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
		File report = new File(providerRecord.getName() + "_" + date + ".txt");
		if (report.exists()) report.delete();
		report.createNewFile();
		FileWriter writer = new FileWriter(report);
		
		System.out.println("PROVIDER REPORT " + date + "\n");

		writer.append("PROVIDER REPORT " + date + "\n\n");
		writer.flush();

		System.out.println("Name: " + providerRecord.getName() + "\n");
		writer.append("Name: " + providerRecord.getName() + "\n");
		writer.flush();

		System.out.println("Number: " + providerRecord.getNumber() + "\n");
		writer.append("Number: " + providerRecord.getNumber() + "\n");
		writer.flush();

		System.out.println("Address: " + providerRecord.getStreetAddress() + ", " + providerRecord.getCity() + ", " + providerRecord.getState() + ", " + providerRecord.getZip() + "\n");
		writer.append("Address: " + providerRecord.getStreetAddress() + ", " + providerRecord.getCity() + ", " + providerRecord.getState() + ", " + providerRecord.getZip() + "\n");
		writer.flush();

		System.out.println("Provder Forms: \n");
		writer.append("Provider Forms: \n");
		writer.flush();

		for (int i = 0; i < providerRecord.returnForms().size(); i++) {
			ProviderForm providerForm = providerRecord.returnForms().get(i);

			System.out.println(i + 1 + ".\n");

			System.out.println("\tConsultation Date: " + providerForm.getServiceDate() + "\n");
			writer.append("Consultation Date: " + providerForm.getServiceDate() + "\n");
			writer.flush();

			System.out.println("\tDate/Time Received: " + providerForm.getDateTimeReceived() + "\n");
			writer.append("Member Name: " + providerForm.getMemberName() + "\n");
			writer.flush();

			System.out.println("\tMember Number: " + providerForm.getMemberNumber() + "\n");
			writer.append("Service Name: " + providerForm.getServiceName() + "\n");
			writer.flush();

			System.out.println("\tService Code: " + providerForm.getServiceCode() + "\n");
			writer.append("Service Code: " + providerForm.getServiceCode() + "\n");
			writer.flush();

			System.out.println("\tFee to be paid: " + df.format(providerForm.getServiceFee()) + "\n");
			writer.append("Fee to be paid: " + df.format(providerForm.getServiceFee()) + "\n\n");
			writer.flush();

			totalFee += providerForm.getServiceFee();
			totalConsultations++;
		}

		System.out.println("Total number of consultations: " + totalConsultations + "\n");
		writer.append("Total number of consultations: " + totalConsultations + "\n");
		writer.flush();

		System.out.println("Total fees for the week of " + date + ": " + df.format(totalFee) + "\n");
		writer.append("Total fees for the week of " + date + ": " + df.format(totalFee) + "\n");
		writer.flush();

		writer.close();

		EFTData eftData = new EFTData();
		eftData.print(providerRecord, totalFee);
	}
}
