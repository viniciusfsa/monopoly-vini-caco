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
//        files.add("res/us1.txt");
//        files.add("res/us2.txt");
//        files.add("res/us3.txt");
//        files.add("res/us4.txt");

        //Milestone 2
//        files.add("res/us5.txt");
//        files.add("res/us6.txt");
//        files.add("res/us7.txt");
//        files.add("res/us8.txt");
//        files.add("res/us9.txt");

        //Milestone 3
        files.add("res/us10.txt");



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
