package openopoly.board;

import openopoly.*;

/**Classe que representa uma propriedade do tabuleiro
 *
 * @author Lucas
 * @author Sergio
 */
public class Property implements Block {

    private String propName;
    private int posGB;
    private Player owner;
    private int price, rent[], mortgage, house;
    private String group;
    private int houseQtd = 0;
    private boolean hotel = false;

    /**
     * O construtor da classe tem a função de inicializar
     * os atributos da propriedade.
     *
     * @param posGBoard posição no tabuleiro
     * @param propName nome do lugar
     * @param owner Player proprietário
     * @param price valor da propriedade
     * @param rent0 valor do aluguel sem casa
     * @param rent1 valor do aluguel com uma casa
     * @param rent2 valor do aluguel com duas casas
     * @param rent3 valor do aluguel com três casas
     * @param rent4 valor do aluguel com quatro casas
     * @param rentHotel valor do aluguel com um hotel
     * @param mortgage valor da hipoteca
     * @param house valor da construção de uma habitação
     * @param group grupo
     */
    public Property(int posGBoard, String propName, Player owner, int price, int rent0, int rent1, int rent2, int rent3, int rent4, int rentHotel, int mortgage, int house, String group) {
        this.posGB = posGBoard;
        this.propName = propName;
        this.owner = owner;
        rent = new int[6];
        this.price = price;
        this.rent[0] = rent0;
        this.rent[1] = rent1;
        this.rent[2] = rent2;
        this.rent[3] = rent3;
        this.rent[4] = rent4;
        this.rent[5] = rentHotel;
        this.mortgage = mortgage;
        this.house = house;
        this.group = group;
    }

    /**
     * Esse método retorna o aluguel atual
     * @return o aluguel atual
     */
    public int getCurrentRent() {
        if(hotel){
            return rent[5];
        }else{
            return rent[0];
        }
    }
    /**
     * Esse método retorna o aluguel atual com as regras de construção, ou seja,
     * caso o jogador tenha o monopolio do grupo o dobro do aluguel será cobrado.
     * @return o aluguel atual
     */
    public int getCurrentRentBuildRules() {
        if(hotel){
            return rent[5];
        }else{
            if(getOwner().hasMonopolyGroup(this.getGroup()) && (this.getHouseQtd() == 0)){
                return rent[0]*2;
            }else{
                return rent[houseQtd];
            }
        }
    }

    /**
     * Esse método adiciona uma casa a propriedade
     */
    public void addHouses(){
        houseQtd++;
        if(houseQtd == 5){
            hotel = true;
        }
    }

    /**
     * Esse método remove uma casa da propriedade
     */
    public void removeHouses(){
        if(houseQtd > 0){
            houseQtd--;
            if(houseQtd < 5){
                hotel = false;
            }
        }
    }

    //Getters and Setters
    public String getGroup() {
        return group;
    }

    public int getHouse() {
        return house;
    }

    public int getMortgage() {
        return mortgage;
    }

    public int getPrice() {
        return price;
    }

    public int[] getRent() {
        return rent;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public String getPropName() {
        return propName;
    }

    public int getPosGB() {
        return posGB;
    }

    public int getHouseQtd() {
        return houseQtd;
    }

    public boolean hasHotel() {
        return hotel;
    }

    public void setGroup(String group) {
       this.group = group;
    }

    public boolean isGoToJail() {
        return false;
    }

    public boolean isMortgageable() {
        return true;
    }



}
