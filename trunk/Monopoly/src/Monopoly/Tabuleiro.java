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
                                        "Jail",
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


    static private int[] preços_lugares_aluguel = {2,0,4,0,0,6,0,6,8,0,10,0,10,12,0,14,0,14,16,0,
                                            18,0,18,20,0,22,22,0,24,0,26,26,0,28,0,0,35,0,50,0};

    

    public Tabuleiro(){

        for (int i = 0; i<this.nomes_lugares.length; i++){
            this.add(new Lugar(i,this.nomes_lugares[i], 0));
        }

    }

    public void SetAluguel(int idLocal, int novoPreco){
        preços_lugares_aluguel[idLocal-1]=novoPreco;
    }
    
    public String getPlaceName(int placeID) throws Exception{
        return this.getLugarById(placeID).getNome();

    }

    private Lugar getLugarById(int placeID) throws Exception{

        
        if ((placeID<1) ||(placeID>this.size())){
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

    

    public int getLugarPreçoCompra(int placeID) throws Exception{
        return this.getLugarById(placeID).getPrecoCompra();
    }

    public int getLugarPrecoAluguel(int  placeId) throws Exception{
        if(placeId<1 ||placeId>40)
            throw new Exception("Place doesn't exist");
        else{
            int preco = preços_lugares_aluguel[placeId-1];
            if(preco==0)
                throw new Exception("This place doesn't have a rent");
            else
                return preços_lugares_aluguel[placeId -1];

        }
    }

}
