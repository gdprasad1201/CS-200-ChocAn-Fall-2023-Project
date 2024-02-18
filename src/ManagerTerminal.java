package project4;

import java.io.IOException;
import java.util.Scanner;

/**
 * ManagerTerminal class. Generates menu for user to perform action on records.
 * @author Ardise Hamilton
 */
public class ManagerTerminal {
	private int selection;
	private Scanner input = new Scanner(System.in);
	private int attempts = 3;

	/**
	 * Verifies manager password.
	 * @return
	 * @throws IOException
	 */
	public boolean verifyManager() throws IOException {
		System.out.println("Enter Manager Password:");
		String password = input.nextLine();
		if (password.equals("team17")) {
			System.out.println("Password Accepted.");
			return true;
		} 
		else {
			System.out.println("Password Incorrect.");
			attempts--;
			if (attempts == 0) {
				System.out.println("Too many incorrect attempts. Exiting program.");
				return false;
			}
			System.out.println("You have " + attempts + " attempts remaining.");
			return verifyManager();
		}
	}

	/**
	 * Constructor method. Generates menu for user to perform action on records.
	 * @param records
	 * @throws IOException
	 */
	public ManagerTerminal(ManageRecords records) throws IOException {
		if (!verifyManager())
			return;
		System.out.println("Select Option:");
		System.out.println("\n\t (1) Run Main Accounting Procedure");
		System.out.println("\n\t (2) Print individual report");
		System.out.println("\n\t (3) Exit");
		this.selection = Integer.valueOf(input.nextLine());
		switch (selection) {
			case 1:
				printBothReports(records);
				break;
			case 2:
				System.out.println("Select Report Type:");
				System.out.println("\n\t (1) Member Report");
				System.out.println("\n\t (2) Provider Report");
				System.out.println("\n\t (3) Exit");
				this.selection = Integer.valueOf(input.nextLine());
				switch (selection) {
					case 1:
						printReport(this.selection, records);
						break;
					case 2:
						printReport(this.selection, records);
						break;
					case 3:
						return;
					default:
						System.out.println("Invalid entry.");
				}
				break;
			case 3:
				return;
			default:
				System.out.println("Invalid entry. Please try again.");
		}
	}

	/**
	 * Prints both reports.
	 * @param records
	 * @throws IOException
	 */
	private void printBothReports(ManageRecords records) throws IOException {
		for (int i = 0; i < records.returnMemberRecordSize(); i++) {
			new Summary().printMemberReport(records.returnMemberRecords().get(i));
		}
		for (int i = 0; i < records.returnProviderRecordSize(); i++) {
			new Summary().printProviderReport(records.returnProviderRecords().get(i));
		}
	}

	/**
	 * Prints specified report.
	 * @param s
	 * @param records
	 * @throws IOException
	 */
	private void printReport(int s, ManageRecords records) throws IOException {
		if (s == 1) {
			for (int i = 0; i < records.returnMemberRecordSize(); i++) {
				new Summary().printMemberReport(records.returnMemberRecords().get(i));			}
		}
		if (s == 2) {
			for (int i = 0; i < records.returnProviderRecordSize(); i++) {
				new Summary().printProviderReport(records.returnProviderRecords().get(i));
			}
		}
	}
}
