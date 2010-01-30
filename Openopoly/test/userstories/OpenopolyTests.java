package userstories;


import easyaccept.EasyAcceptFacade;
import java.util.ArrayList;
import java.util.List;
import openopoly.facade.OpenopolyFacade;

public class OpenopolyTests {

    public static void main(String[] args) throws Exception {

        List<String> files = new ArrayList<String>();

        //Put the us1.txt file into the "test scripts" list

        //Milestone 1
        files.add("us1.txt");
        files.add("us2.txt");
        files.add("us3.txt");
        files.add("us4.txt");

        //Milestone 2
        files.add("us5.txt");
        files.add("us6.txt");
        files.add("us7.txt");
        files.add("us8.txt");
        files.add("us9.txt");


        //Teste
//        files.add("teste.txt");


        //Instantiate the Monopoly Game façade

        OpenopolyFacade gameFacade = new OpenopolyFacade();

        //Instantiate EasyAccept façade

        EasyAcceptFacade eaFacade = new EasyAcceptFacade(gameFacade, files);

        //Execute the tests

        eaFacade.executeTests();

        //Print the tests execution results

        System.out.println(eaFacade.getCompleteResults());
    }
}
