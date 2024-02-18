package project4;

import java.util.Scanner;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * ServiceRecord class. Stores information about a service provided by a provider to a member.
 * @author Dylan Melnick
 */
public class ServiceRecord {
	
	private String serviceName;
	private String serviceDate;
	private String dateTimeReceived;
	private double serviceFee;
	private int providerNumber;
	private int memberNumber;
	private String memberName;
	private String providerName;
	private int serviceCode;
	private String comment;
	private Scanner input = new Scanner(System.in);

	/**
	 * Constructor method. Prompts user for information and sets corresponding variables.
	 * @param records
	 * @param serviceCode
	 * @param memberNumber
	 * @param memberName
	 * @param providerNumber
	 * @throws IOException
	 */
	public ServiceRecord(ManageRecords records, int serviceCode, int memberNumber, String memberName, int providerNumber, String providerName) throws IOException {
		System.out.println("SERVICE RECORD\n");
		System.out.println("\nEnter Date service was provided (MM-DD-YYYY): ");
		serviceDate = input.nextLine();
		while (!validateDate(serviceDate)) {
			System.out.println("Invalid date format. Please enter date in MM-DD-YYYY format and ensure date is valid: ");
			serviceDate = input.nextLine();
		}

		dateTimeReceived = new SimpleDateFormat("MM-dd-yyyy hh::mm::ss").format(Calendar.getInstance().getTime());

		this.providerNumber = providerNumber;
		this.providerName = providerName;

		this.memberNumber = memberNumber;
		this.memberName = memberName;

		this.serviceCode = serviceCode;
		serviceFee = records.lookUpFee(serviceCode);
		serviceName = records.lookUpServiceName(serviceCode);

		System.out.println("\nWould you like to add a comment? (Y/N)");
		String response = input.nextLine();
		while (!response.matches("[YyNn]")) {
			System.out.println("Invalid response. Please enter Y or N.");
			response = input.nextLine();
		}

		if (response.matches("[Yy]")) {
			System.out.println("Enter Comments (100 characters): ");
			comment = input.nextLine();	
			while (comment.length() > 100) {
				System.out.println("Comments must be less than 100 characters. Please re-enter comments: ");
				comment = input.nextLine();
			}
		}
		else comment = "N/A";
	}

	/**
	 * Validates date format.
	 * @param date
	 * @return
	 */
	public boolean validateDate(String date) {
		return date.matches("^(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-(19|20)\\d\\d$");
	}	
	
	/**
	 * Returns service date entered by provider.
	 * @return
	 */
	public String getServiceDate() {
		return serviceDate;
	}

	/**
	 * Returns date-time form was received by computer.
	 * @return
	 */
	public String getDateTimeReceived() {
		return dateTimeReceived;
	}
	
	/**
	 * Returns member number.
	 * @return
	 */
	public int getProviderNumber() {
		return providerNumber;
	}

	/**
	 * Returns member name.
	 * @return
	 */
	public String getProviderName() {
		return providerName;
	}

	/**
	 * Returns member number.
	 * @return
	 */
	public int getMemberNumber() {
		return memberNumber;
	}

	/**
	 * Returns member name.
	 * @return
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * Returns service code.
	 * @return
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * Returns seervice fee.
	 * @return
	 */
	public double getServiceFee() {
		return serviceFee;
	}

	/**
	 * Returns service name.
	 * @return
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Returns service code.
	 * @return
	 */
	public int getServiceCode() {
		return serviceCode;
	}

	/**
	 * Displays service record.
	 */
	public void displayErrorMessage() {
		System.out.println("ERROR: Nonexistent service code was entered.\n");
	}
}
