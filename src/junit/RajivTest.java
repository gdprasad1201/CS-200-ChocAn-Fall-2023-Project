package project4.junit;

import static org.junit.Assert.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import project4.*;
import org.junit.jupiter.api.Test;

class RajivTest {

	@Test
	public void testSummaryProviderPrint() throws IOException {
		Summary summary = new Summary();
		ProviderRecord provider = new ProviderRecord();
		summary.printProviderReport(provider);
		
		String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
		File providerReport = new File(provider.getName() + "_" + date + ".txt");
		
		assertTrue(providerReport.exists());
	}
	
	@Test
	public void printEFTData() throws IOException {		
		ProviderRecord providerRecord = new ProviderRecord();
		EFTData eft = new EFTData();
		eft.print(providerRecord, 234.00);

		String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
		File report = new File(providerRecord.getName() + "_" + date + "_EFT.txt");

		assertTrue(report.exists());	
	}
	
	@Test
	public void testDeleteMember() throws IOException {
		ManageRecords records = new ManageRecords();
		System.out.println("\nDelte Member Test Begins\n");
    	OperatorTerminal operator = new OperatorTerminal(records);
 
    	assertEquals(1, records.returnMemberRecordSize());
    	
    	operator = new OperatorTerminal(records);    	
    	assertEquals(0, records.returnMemberRecordSize());
	}
}
