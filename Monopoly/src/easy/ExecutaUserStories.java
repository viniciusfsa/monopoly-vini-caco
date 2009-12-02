/**
 *
 * @author Marcus
 */

package easy;


import easyaccept.EasyAcceptFacade;
import java.util.ArrayList;
import java.util.List;

public class ExecutaUserStories {

    public static void main(String[] args) throws Exception {

        List<String> files = new ArrayList<String>();
        //Put the us1.txt file into the "test scripts" list
        files.add("us1.txt");
        files.add("us2.txt");
        files.add("us3.txt");
        files.add("us4.txt");
        //Instantiate the Monopoly Game façade
        facade monopolyGameFacade = new facade();
        //Instantiate EasyAccept façade
        EasyAcceptFacade eaFacade = new EasyAcceptFacade(monopolyGameFacade, files);
        //Execute the tests
        eaFacade.executeTests();    
        //Print the tests execution results
        System.out.println(eaFacade.getCompleteResults());
    }
}
