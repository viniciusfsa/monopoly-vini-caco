/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Monopoly;

import java.util.ArrayList;

/**
 *
 * @author Marcus
 */
public class Tabuleiro extends ArrayList<Lugar>{

    //o lugar disso é aqui mesmo?  by caco
    //se num for vai ficar //by Marcus

   
    

    private String[] nomes_lugares = {  "Mediterranean Avenue",
                                        "Community Chest 1",
                                        "Baltic Avenue",
                                        "Income Tax",
                                        "Reading Railroad",
                                        "Oriental Avenue",
                                        "Chance 1",
                                        "Vermont Avenue",
                                        "Connecticut Avenue",
                                        "Jail - Just Visiting",
                                        "St. Charles Place",
                                        "Electric Company",
                                        "States Avenue",
                                        "Virginia Avenue",
                                        "Pennsylvania Railroad",
                                        "St. James Place",
                                        "Community Chest 2",
                                        "Tennessee Avenue",
                                        "New York Avenue",
                                        "Free Parking",
                                        "Kentucky Avenue",
                                        "Chance 2",
                                        "Indiana Avenue",
                                        "Illinois Avenue",
                                        "B & O Railroad",
                                        "Atlantic Avenue",
                                        "Ventnor Avenue",
                                        "Water Works",
                                        "Marvin Gardens",
                                        "Go To Jail",
                                        "Pacific Avenue",
                                        "North Carolina Avenue",
                                        "Community Chest 3",
                                        "Pennsylvania Avenue",
                                        "Short Line Railroad",
                                        "Chance 3",
                                        "Park Place",
                                        "Luxury Tax",
                                        "Boardwalk",
                                        "Go"};


    private int[] preços_lugares_aluguel = {2,0,4,0,0,6,0,6,8,0,
                                            10,0,10,12,0,14,0,14,16,0,
                                            18,0,18,20,0,22,22,0,24,0,
                                            26,26,0,28,0,0,35,0,50,0};

    private int[] preços_lugares_compra = {60,0,60,0,200,100,0,100,120,0,
                                           140,150,140,160,200,180,0,180,200,0,
                                           220,0,220,240,200,260,260,150,280,0,
                                           300,300,0,320,200,0,350,0,400,0};
  
    private int[] preços_lugares_aluguel_1_casa = {10,0,20,0,0,30,0,30,40,0,
                                                   50,0,50,60,0,70,0,70,80,0,
                                                   90,0,90,100,0,110,110,0,120,0,
                                                   130,110,0,150,0,0,175,0,200,0};
    
    private int[] preços_lugares_aluguel_2_casas = {30,0,60,0,0,90,0,90,100,0,
                                                    150,0,150,180,0,200,0,200,220,0,
                                                    250,0,250,300,0,330,330,0,360,0,
                                                    390,330,0,450,0,0,500,0,600,0};

    private int[] preços_lugares_aluguel_3_casas = {90,0,180,0,0,270,0,270,300,0,
                                                    450,0,450,500,0,550,0,550,600,0,
                                                    700,0,700,750,0,800,800,0,850,0,
                                                    900,800,0,100,0,0,1100,0,1400,0};

    private int[] preços_lugares_aluguel_4_casas = {160,0,320,0,0,400,0,400,450,0,
                                                    625,0,625,700,0,750,0,750,800,0,
                                                    875,0,875,925,0,975,975,0,1025,0,
                                                    1100,975,0,1200,0,0,1300,0,1700,0};
        
    private int[] preços_lugares_aluguel_Hotel =  {250,0,450,0,0,550,0,550,600,0,
                                                   750,0,750,900,0,950,0,950,1000,0,
                                                   1050,0,1050,1100,0,1150,1150,0,1200,0,
                                                   1275,1150,0,1400,0,0,1500,0,2000,0};

    private int[] preços_lugares_aluguel_Hipoteca = {30,0,30,0,100,50,0,50,60,0,
                                                     70,75,70,80,100,90,0,90,100,0,
                                                     110,0,110,120,100,130,130,75,140,0,
                                                     150,130,0,160,100,0,175,0,200,0};
    
    private int[] preços_da_casa = {50,0,50,0,0,50,0,50,50,50,0,
                                  100,0,100,100,0,100,0,100,100,0,
                                  150,0,150,150,0,150,150,0,150,0,
                                  200,150,0,200,0,0,200,0,200,0};












    public Tabuleiro(){

        for (int i = 0; i<this.nomes_lugares.length; i++){
            this.add(new Lugar(i,this.nomes_lugares[i], this.preços_lugares_compra[i]));
        }

    }

    
    
    public String getPlaceName(int placeID) throws Exception{
        return this.getLugarById(placeID).getNome();

    }

    private Lugar getLugarById(int placeID) throws Exception{

        
        if ((placeID<1) ||(placeID>this.size())){
            System.out.println("\t posicao: "+placeID);
            throw new Exception ("Place doesn't exist");
        }
        else{
            //atenção para o migué!
            return this.get(placeID-1);
        }
    }

    public String getLugarGrupo(int placeID) throws Exception {
        return this.getLugarById(placeID).getGrupo(placeID);
    }

    

   

    public int getLugarPrecoAluguel(int  placeId) throws Exception{
        if(placeId<1 ||placeId>40)
            throw new Exception("Place doesn't exist");
        else{
            int preco = preços_lugares_aluguel[placeId-1];
            if(preco==0)
                throw new Exception("This place doesn't have a rent");
            else
                return preco;

        }
    }


     public int getLugarPrecoCompra(int  placeId) throws Exception{
        if(placeId<1 ||placeId>40)
            throw new Exception("Place doesn't exist");
        else{
            int preco = preços_lugares_compra[placeId-1];
            if(preco==0)
                throw new Exception("This place can't be sold");
            else
                return preco;

        }
    }
}
