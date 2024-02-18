package project4.junit;
import static org.junit.Assert.*;
import java.io.*;
import org.junit.*;
import project4.*;
public class KimberlyTest {
    @Test
    public void testMemberRecordSuccess() throws IOException {
    	System.out.println("\nSuccess Add Member Test Begins\n");
        MemberRecord member = new MemberRecord();
    	assertEquals("John Doe", member.getName());
    	assertEquals(123456789, member.getNumber());
    	assertEquals("1234 Main Street", member.getStreetAddress());
    	assertEquals("Tuscaloosa", member.getCity());
    	assertEquals("AL", member.getState());
    	assertEquals(35401, member.getZip());
    }
    
    @Test
    public void testProviderRecordSuccess() throws IOException {
        System.out.println("\nSuccess Add Provider Test Begins\n");
        ProviderRecord provider = new ProviderRecord();
    	assertEquals("John Doe",provider.getName()); 
    	assertEquals(123456789, provider.getNumber());
    	assertEquals("1234 Main Street", provider.getStreetAddress());
    	assertEquals("Tuscaloosa", provider.getCity());
    	assertEquals("AL", provider.getState());
    	assertEquals(35401, provider.getZip());
    }
    
    @Test
    public void testPair() {
        Pair pair = new Pair<String, Integer>("Test", 1);
        assertEquals("Test", pair.getKey());
        assertEquals(1, pair.getValue());
    }
}