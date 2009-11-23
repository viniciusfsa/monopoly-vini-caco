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

    public static void main(String[] args) {
        Comandos c = new Comandos();
        System.out.println(c.getCmds());
         Iterator<String> it = cmds.iterator();
         while(it.hasNext())
            System.out.println(it.next());
    }

    public Comandos() {
        cmds.clear();
        initComandos();
    }



    public static void initComandos(){
        cmds.add("roll");
        cmds.add("status");
        cmds.add("quit");
    }

    
  
    public List getCmds() {
        return cmds;
      

    }



    public void showComandos(){
        Iterator<String> a = cmds.iterator();
        while(a.hasNext())
            System.out.print("["+a.next()+"]");

        System.out.println("\n");
    }
}
