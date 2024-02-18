package project4.junit;

import static org.junit.Assert.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import project4.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;


class ArdiseTest {

	private ManageRecords records;
	
	@Before
	public void setUp() throws IOException {
		records = new ManageRecords();
	}
	
	@Test
	public void testVerifyManager() throws IOException {
		ManagerTerminal manager = new ManagerTerminal(records);
		assertTrue(manager.verifyManager());
	}
	
	@Test
	public void testPair() {
		Pair pair = new Pair<String, Integer>("John Doe", 5);
		assertEquals("John Doe", pair.getKey());
        assertEquals(5, pair.getValue());
	}
	
	@Test
	public void testEFTData() throws IOException {
		boolean exists = false;
		
		ProviderRecord providerRecord = new ProviderRecord();
		EFTData eft = new EFTData();
		eft.print(providerRecord, 10.00);

		String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
		File report = new File(providerRecord.getName() + "_" + date + "_EFT.txt");
		if (report.exists()) {
			exists = true;
		}

		assertTrue(exists);
	}
}
