package easy;


import easyaccept.EasyAcceptFacade;
import java.util.ArrayList;
import java.util.List;

/**
 * Testes da Milestone 1
 * @author Marcus
 */
public class Milestone1 {

    /**
     * Executa os testes da milestone 1
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        List<String> files = new ArrayList<String>();
        //Put the us1.txt file into the "test scripts" list
        files.add("us1.txt");
        files.add("us2.txt");
        files.add("us3.txt");
        files.add("us4.txt");
        files.add("us5parte.txt");
        //Instantiate the Monopoly Game façade
        UserStoriesFacade monopolyGameFacade = new UserStoriesFacade();
        //Instantiate EasyAccept façade
        EasyAcceptFacade eaFacade = new EasyAcceptFacade(monopolyGameFacade, files);
        //Execute the tests
        eaFacade.executeTests();    
        //Print the tests execution results
        System.out.println(eaFacade.getCompleteResults());
    }
}

