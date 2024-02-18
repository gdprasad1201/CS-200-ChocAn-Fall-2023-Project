package project4.junit;

import static org.junit.Assert.*;
import java.io.*;
import org.junit.*;
import project4.*;

public class GowthamTest {
    private ManageRecords records;
    private OperatorTerminal operator;
    private ManagerTerminal manager;
    
    @Before
    public void setUp() throws IOException {
    	records = new ManageRecords();
    }

    @Test
    public void testVerifyOperatorFailure() throws IOException {
    	System.out.println("\nInitial Login. Please ignore!\n");
    	operator = new OperatorTerminal(records);
    	System.out.println("\nFailure Verify OperatorTest Begins\n");
        assertFalse(operator.verifyOperator());
    }
    
    @Test
    public void testAddMember() throws IOException {
    	System.out.println("\nSuccess Add Member Test Begins\n");
    	operator = new OperatorTerminal(records);
    	assertEquals("John Smith", records.returnMemberRecords().get(0).getName());
    	assertEquals(123456789, records.returnMemberRecords().get(0).getNumber());
    	assertEquals("1234 Main Street", records.returnMemberRecords().get(0).getStreetAddress());
    	assertEquals("Tuscaloosa", records.returnMemberRecords().get(0).getCity());
    	assertEquals("AL", records.returnMemberRecords().get(0).getState());
    	assertEquals(35401, records.returnMemberRecords().get(0).getZip());
    	assertEquals(1, records.returnMemberRecordSize());
    }
    
    @Test
    public void testVerifyManagerFailure() throws IOException {
    	System.out.println("\nInitial Login. Please ignore!\n");
    	manager = new ManagerTerminal(records);
    	System.out.println("\nFailure Verify Manager Test\n");
    	assertFalse(manager.verifyManager());
    }
}
