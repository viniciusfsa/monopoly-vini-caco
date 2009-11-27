/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Monopoly;

/**
 *
 * @author Marcus
 */
public class Lugar {

  
    private String Nome="";
    private int Preco=0;
    private String dono = "bank";

    public Lugar(int Posicao, String Nome, int Preco) {
        this.Nome= Nome;        
        this.Preco=Preco;
    }


    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

   

    

    public double getPreco() {
        return Preco;
    }

    public void setPreco(int Preco) {
        this.Preco = Preco;
    }

    public String getGrupo(int placeID) {
        if(placeID==1||placeID==3){
            return "purple";}
        else if(placeID==6||placeID==8||placeID==9)
            return "light blue";
        else if(placeID==4||placeID==38)
            return "tax";
        else if(placeID==5||placeID==15||placeID==25||placeID==35)
            return "railroad";
        else if(placeID==7||placeID==22||placeID==36)
            return "chance";
        else if(placeID==10||placeID==20||placeID==30||placeID==40)
            return "corner";
        else if(placeID==11||placeID==13||placeID==14)
            return "pink";
        else if(placeID==12||placeID==28)
            return "utility";
        else if(placeID==16||placeID==18||placeID==19)
            return "orange";
        else if(placeID==21||placeID==23||placeID==24)
            return "red";
        else if(placeID==26||placeID==27||placeID==29)
            return "yellow";
        else if(placeID==31||placeID==32||placeID==34)
            return "green";
        else if(placeID==37||placeID==39)
            return "indigo";
        else
            return "chest";
    }

    public int getPrecoCompra() {
        return this.Preco;
    }

    

    public String getDono() {
        return dono;
    }


}
