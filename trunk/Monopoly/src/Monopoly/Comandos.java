package Monopoly;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Marcus
 */
public class Comandos {

    static List<String> cmds = new ArrayList<String>();

    public Comandos() {
        cmds.add("Jogar");
        cmds.add("Sair");

    }

    
    public List<String> getCmds() {
        return cmds;
    }

    public void showComandos(){
        Iterator<String> a = cmds.iterator();
        while(a.hasNext())
            System.out.print("["+a.next()+"]");

        System.out.println("\n");
    }
}
