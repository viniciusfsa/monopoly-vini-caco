/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Monopoly.Cards;

/**
 *
 * @author Marcus
 */
public class Card {

    protected  int numberCard;
    protected  String NameCard;
    protected  String Descricao;

    public Card(int numberCard, String Descricao, String NameCard) {
        this.numberCard = numberCard;
        this.NameCard = NameCard;
        this.Descricao = Descricao;
    }

    public String getDescricao() {
        return Descricao;
    }

    public int getNumberCard() {
        return numberCard;
    }
 
   
}
