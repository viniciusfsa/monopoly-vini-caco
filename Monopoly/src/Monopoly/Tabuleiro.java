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

    //o lugar disso é aqui mesmo?
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

    public Tabuleiro(){

        for (int i = 0; i<this.nomes_lugares.length; i++){
            this.add(new Lugar(i,this.nomes_lugares[i], 0));
        }

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
        return this.getLugarById(placeID).getGrupo();
    }

    public int getLugarPrecoAluguel(int placeID) throws Exception{
        return this.getLugarById(placeID).getPrecoAluguel();
    }

    public int getLugarPreçoCompra(int placeID) throws Exception{
        return this.getLugarById(placeID).getPrecoCompra();
    }



    public String getLugarDono(int placeID) throws Exception {
        return this.getLugarById(placeID).getDono();
    }
    

}
