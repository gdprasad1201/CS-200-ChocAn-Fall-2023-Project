package project4.junit;

import static org.junit.Assert.*;
import java.io.*;
import org.junit.*;
import project4.*;

public class DylanTest {
	ManageRecords records;
	
	@Before
	public void setUp() throws IOException {
		records = new ManageRecords();
	}
	
	@Test
	public void testValidateDateServiceRecord() throws IOException {
		ServiceRecord serviceRecord = new ServiceRecord(records, 598470, 123456789, "John Doe", 123456789, "Martin Luther");
		assertTrue(serviceRecord.validateDate(serviceRecord.getServiceDate()));
	}
	
	@Test
	public void testValidateDateProviderForm() throws IOException {
		ProviderForm providerForm = new ProviderForm(records, 598470, 123456789, "John Doe", 123456789);
		assertTrue(providerForm.validateDate(providerForm.getServiceDate()));
	}
	
	@Test
	public void testVerifyManager() throws IOException {
		ManagerTerminal manager = new ManagerTerminal(records);
		assertTrue(manager.verifyManager());
	}
}
