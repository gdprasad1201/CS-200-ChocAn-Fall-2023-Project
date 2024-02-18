package project4;

import java.io.IOException;

/** 
 * Prints both a provider and member reports. 
 * @author Rajiv Yalamanchili
 */
public class Summary {
    /**
     * Prints provider report.
     * @param providerRecord
     * @throws IOException
     */
    public void printProviderReport(ProviderRecord providerRecord) throws IOException {
        new ProviderReport().print(providerRecord);
    }

    /**
     * Prints member report.
     * @param memberRecord
     * @throws IOException
     */
    public void printMemberReport(MemberRecord memberRecord) throws IOException {
        new MemberReport().print(memberRecord);
    }
}