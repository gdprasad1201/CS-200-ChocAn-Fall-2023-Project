package project4.junit;

import static org.junit.Assert.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import project4.*;
import org.junit.jupiter.api.Test;

class VishakTest {

	@Test
	public void testProviderReportExists() throws IOException {
		ProviderRecord provider = new ProviderRecord();
		ProviderReport report = new ProviderReport();
		report.print(provider);	
		
		String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
		File providerReport = new File(provider.getName() + "_" + date + ".txt");
		
		assertTrue(providerReport.exists());
	}
	
	@Test
	public void testMemberReprotExists() throws IOException {
		MemberRecord member = new MemberRecord();
		MemberReport report = new MemberReport();
		report.print(member);	
		
		String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
		File memberReport = new File(member.getName() + "_" + date + ".txt");
		
		assertTrue(memberReport.exists());
	}

	@Test
	public void testValidateDateServiceRecord() throws IOException {
		ServiceRecord service = new ServiceRecord(new ManageRecords(), 598470, 123456789, "John Smith", 123456789, "Doctor Who");
        assertTrue(service.validateDate("12-31-2020"));
        assertFalse(service.validateDate("1231-2021"));
	}
}
