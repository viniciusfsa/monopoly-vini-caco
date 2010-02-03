package openopoly.tests;


//import userstories.*;
import easyaccept.EasyAcceptFacade;
import java.util.ArrayList;
import java.util.List;
import openopoly.facade.OpenopolyFacade;

public class OpenopolyTests {

    public static void main(String[] args) throws Exception {

        List<String> files = new ArrayList<String>();

        //Milestone 1
//        files.add("res/us01.txt");
//        files.add("res/us02.txt");
//        files.add("res/us03.txt");
//        files.add("res/us04.txt");
//
//        //Milestone 2
//        files.add("res/us05.txt");
//        files.add("res/us06.txt");
//        files.add("res/us07.txt");
//        files.add("res/us08.txt");
//        files.add("res/us09.txt");

        //Milestone 3
        files.add("res/us10.txt");
        files.add("res/us11.txt");
        files.add("res/us12.txt");


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
