package project4;

import java.io.IOException;
import java.util.Scanner;

/**
 * ProviderTerminal class. Generates menu for user to perform action on records.
 * @author Ardise Hamilton
 */
public class ProviderTerminal {
	private int memberNumber = 000000000;
	private String memberName;
	private int providerNumber;
	private int serviceCode;
	private int attempts = 3;
	private Scanner input = new Scanner(System.in);

	/**
	 * Verifies the provider number is valid.
	 * @param number
	 * @param records
	 * @return
	 */
	public boolean verifyProvider(ManageRecords records) {
		//verify the provider is real/active in database
		System.out.println("Please enter provider number: ");
		providerNumber = Integer.valueOf(input.nextLine());
		if (records.searchProvider(providerNumber) == -1) {
			System.out.println("Invalid Provider Number. Please enter again.\n");
			attempts--;
			if (attempts == 0) {
				System.out.println("Too many incorrect attempts. Exiting program.\n");
				return false;
			}
			System.out.println("You have " + attempts + " attempts remaining.\n");
			providerNumber = Integer.valueOf(input.nextLine());
			return verifyProvider(records);
		}
		else
			return true;
	}

	/**
	 * Verifies the member number is valid.
	 * @param number
	 * @param records
	 * @return
	 */
	public String verifyMember(ManageRecords records) {
		System.out.println("Please enter member number: ");
		memberNumber = Integer.valueOf(input.nextLine());
		while (records.returnMemberRecords().get(records.searchMember(memberNumber)).returnStatus() == null || records.returnMemberRecords().get(records.searchMember(memberNumber)).returnStatus().equals("Suspended")) {
			if (records.returnMemberRecords().get(records.searchMember(memberNumber)).returnStatus().equals("Suspended")) {
				System.out.println("Member" + records.returnMemberRecords().get(records.searchMember(memberNumber)).getName() + " is suspended\n");
				System.out.println("Is this the intended member: " + records.returnMemberRecords().get(records.searchMember(memberNumber)).getName() + "? (Y/N)\n");
				String answer = input.nextLine();
				if (answer.equals("Y")) 
					return records.returnMemberRecords().get(records.searchMember(memberNumber)).getName();
				else if (answer.equals("N"))
					return verifyMember(records);
				else
					return verifyMember(records);
			}
			System.out.println("Member Number is Invalid, please enter again.\n");
			memberNumber = Integer.valueOf(input.nextLine());
		}
		System.out.println("Member Number Verified");

		String name = records.returnMemberRecords().get(records.searchMember(memberNumber)).getName();
		System.out.println("Is this the intended member: " + name + "? (Y/N)\n");
		String answer = input.nextLine();
		if (answer.equals("Y")) 
			return name;
		else if (answer.equals("N"))
			return verifyMember(records);
		else
			return verifyMember(records);
	}

	/**
	 * Constructor method. Generates menu for user to perform action on records.
	 * @param records
	 * @throws IOException
	 */
	public ProviderTerminal (ManageRecords records) throws IOException {
		if (!verifyProvider(records))
			return;

		System.out.println("Select an option: ");
		System.out.println("\n\t" + "(1) Access Service Records");
		System.out.println("\n\t" + "(2) Access Provider Forms");
		System.out.println("\n\t" + "(3) Access member directory");
		System.out.println("\n\t" + "(4) Access provider directory");
		System.out.println("\n\t" + "(5) Exit");
		System.out.println("\nSelection: ");
		int selection = Integer.valueOf(input.nextLine());
		switch(selection) {
			case 1:
				accessServiceRecords(records);
				break;
			case 2:
				accessProviderForms(records);
				break;
			case 3:
				System.out.println("\nMember Number\t\t\tMember Name\t\t\tMember Status\n");
				for (int i = 0; i < records.returnMemberRecordSize(); i++) {
					System.out.println(records.returnMemberRecords().get(i).getNumber() + "\t\t\t" + records.returnMemberRecords().get(i).getName() + "\t\t\t" + records.returnMemberRecords().get(i).returnStatus());
				}
				break;
			case 4:
				System.out.println("\nProvider Number\t\t\tProvider Name\n");
				for (int i = 0; i < records.returnProviderRecordSize(); i++) {
					System.out.println(records.returnProviderRecords().get(i).getNumber() + "\t\t\t" + records.returnProviderRecords().get(i).getName());
				}
				break;
			case 5:
				return;
			default:
				System.out.println("Invalid selection.");
		}
	}

	/**
	 * Accesses the service records for the member.
	 * @param records
	 * @throws IOException
	 */
	public void accessServiceRecords(ManageRecords records) throws IOException {
		System.out.println("Select an option: ");
		System.out.println("\n\t (1) Add a service record");
		System.out.println("\n\t (2) Delete all service records");
		System.out.println("\n\t (3) Delete a specific service record from the service records directory");
		System.out.println("\n\t (4) Access service directory");
		System.out.println("\n\t (5) Edit service records");
		System.out.println("\n\t (6) Exit");

		System.out.println("\nSelection: ");
		int selection = Integer.valueOf(input.nextLine());
		switch(selection) {
			case 1:
				serviceCode = getServiceCode(records);
				addToServiceRecord(records, serviceCode);
				break;
			case 2:
				deleteServiceRecord(records);
				break;
			case 3:
				System.out.println("\nPlease input the service code of the service record you wish to delete: ");
				serviceCode = getServiceCode(records);
				deleteAServiceFromServiceRecord(records, serviceCode);
				break;
			case 4:
				System.out.println("\nService Code\t\t\tService\t\t\tService Fee\n");
				for (int i = 0; i < records.returnServiceDirectory().size(); i++) {
					System.out.println(records.returnServiceDirectory().elementAt(i).getKey() + "\t\t\t" + records.returnServiceDirectory().elementAt(i).getValue() + "\t\t\t$" + records.returnServiceFeeDirectory().elementAt(i).getValue());
				}
				break;
			case 5:
				System.out.println("\nPlease input the service code of the service record you wish to edit: ");
				serviceCode = getServiceCode(records);
				editServiceRecord(records, serviceCode);
				break;
			case 6:
				return;
			default:
				System.out.println("Invalid selection.");
		}
	}

	/**
	 * Accesses the provider forms for the provider.
	 * @param records
	 * @throws IOException
	 */
	public void accessProviderForms(ManageRecords records) throws IOException {
		System.out.println("Select an option: ");
		System.out.println("\n\t (1) Add a provider form");
		System.out.println("\n\t (2) Edit a provider form");
		System.out.println("\n\t (3) Delete all provider forms");
		System.out.println("\n\t (4) Delete a specific provider form from the provider forms directory");
		System.out.println("\n\t (5) Access service directory");
		System.out.println("\n\t (6) Exit");

		System.out.println("\nSelection: ");
		int selection = Integer.valueOf(input.nextLine());
		switch(selection) {
			case 1:
				addToProviderForm(records, getServiceCode(records));
				break;
			case 2:
				System.out.println("\nPlease input the service code of the provider form you wish to edit: ");
				editProviderForm(records, getServiceCode(records));
				break;
			case 3:
				deleteProviderForm(records);
				break;
			case 4:
				System.out.println("\nPlease input the service code of the provider form you wish to delete: ");
				deleteAProviderForm(records, getServiceCode(records));
				break;
			case 5:
				System.out.println("\nService Code\t\t\tService\t\t\tService Fee\n");
				for (int i = 0; i < records.returnServiceDirectory().size(); i++) {
					System.out.println(records.returnServiceDirectory().elementAt(i).getKey() + "\t\t\t" + records.returnServiceDirectory().elementAt(i).getValue() + "\t\t\t$" + records.returnServiceFeeDirectory().elementAt(i).getValue());
				}
				break;
			case 6:
				return;
			default:
				System.out.println("Invalid selection.");
		}
	}

	/**
	 * Verifies the member number is valid.`
	 * @param records
	 * @return
	 * @throws IOException
	 */
	public int getServiceCode(ManageRecords records) throws IOException {
		System.out.println("\nPlease enter service code (6 digits): ");
		this.serviceCode = Integer.valueOf(input.nextLine());
		while (records.lookUpServiceName(serviceCode).equals("Invalid Service Code") && String.valueOf(serviceCode).length() != 6) {
			System.out.println("\nInvalid Service Code. Please enter again and make sure it is 6 digits long.");
			System.out.println("\nPlease enter service code (6 digits): ");
			serviceCode = Integer.valueOf(input.nextLine());
		}
		System.out.println("\nService Code Verified");

		System.out.println("\nIs this the intended service: " + records.lookUpServiceName(serviceCode) + "? (Y/N)");
		String answer = input.nextLine();
		if (answer.equals("Y")) 
			return serviceCode;
		else if (answer.equals("N"))
			return getServiceCode(records);
		else
			return getServiceCode(records);
	}

	/**
	 * Adds the service record for the member.
	 * @param records
	 * @param serviceCode
	 * @throws IOException
	 */
	private void addToServiceRecord(ManageRecords records, int serviceCode) throws IOException {
		//should call the service record constructor which
		//will populate with data
		memberName = verifyMember(records);
		records.returnMemberRecords().get(records.searchMember(memberNumber)).addService(new ServiceRecord(records, serviceCode, memberNumber, memberName, providerNumber, records.returnProviderRecord(providerNumber).getName()));
		//add the created service record to the member record
	}

	/**
	 * Edits the service record for the member.
	 * @param records
	 * @param serviceCode
	 * @throws IOException
	 */
	public void editServiceRecord(ManageRecords records, int serviceCode) throws IOException {
		//should call the service record constructor which
		//will populate with data
		memberName = verifyMember(records);
		records.returnMemberRecords().get(records.searchMember(memberNumber)).deleteService(serviceCode);
		records.returnMemberRecords().get(records.searchMember(memberNumber)).addService(new ServiceRecord(records, serviceCode, memberNumber, memberName, providerNumber, records.returnProviderRecord(providerNumber).getName()));
		//add the created service record to the member record
	}

	/**
	 * Deletes all service records from the members service record.
	 * @param records
	 */
	private void deleteServiceRecord(ManageRecords records) {
		//delete the service record from the members service record
		records.returnMemberRecords().get(records.searchMember(memberNumber)).deleteServices();
	}

	/**
	 * Deletes a specific service record from the members service record.
	 * @param records
	 * @param serviceNumber
	 */
	private void deleteAServiceFromServiceRecord(ManageRecords records, int serviceNumber) {
		memberName = verifyMember(records);
		records.returnMemberRecords().get(records.searchMember(memberNumber)).deleteService(serviceNumber);
	}
	
	/**
	 * Adds the provider form for the provider.
	 * @param records
	 * @param serviceCode
	 * @throws IOException
	 */
	private void addToProviderForm(ManageRecords records, int serviceCode) throws IOException {
		//this will call the provider form constructor which
		//will take information from provider and populate form
		memberName = verifyMember(records);
		records.returnProviderRecord(providerNumber).addInfo(new ProviderForm(records, serviceCode, memberNumber, memberName, providerNumber));
		//add the created form to the list of provider records
	}

	/**
	 * Edits the provider form for the provider.
	 * @param records
	 * @param serviceCode
	 * @throws IOException
	 */
	private void editProviderForm(ManageRecords records, int serviceCode) throws IOException {
		//this will call the provider form constructor which
		//will take information from provider and populate form
		memberName = verifyMember(records);
		records.returnProviderRecord(providerNumber).deleteAForm(serviceCode);
		records.returnProviderRecord(providerNumber).addInfo(new ProviderForm(records, serviceCode, memberNumber, memberName, providerNumber));
		//add the created form to the list of provider records
	}

	/**
	 * Deletes all provider forms from the list of provider forms.
	 * @param records
	 */
	private void deleteProviderForm(ManageRecords records) {
		//delete the provider form from the list of provider forms
		records.returnProviderRecord(providerNumber).deleteInfo();
	}

	/**
	 * Deletes a specific provider form from the list of provider forms.
	 * @param records
	 * @param serviceNumber
	 */
	private void deleteAProviderForm(ManageRecords records, int serviceNumber) {
		memberName = verifyMember(records);
		records.returnProviderRecord(providerNumber).deleteAForm(serviceNumber);
	}
}