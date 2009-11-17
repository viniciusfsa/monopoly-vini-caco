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

    private int Posicao=0;
    private String Nome="";
    private double Preco=0;

    public Lugar(int Posicao, String Nome, double Preco) {

        this.Nome= Nome;
        this.Posicao = Posicao;
        this.Preco=Preco;
    }


    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public int getPosicao() {
        return Posicao;
    }

    public void setPosicao(int Posicao) {
        this.Posicao = Posicao;
    }

    public double getPreco() {
        return Preco;
    }

    public void setPreco(double Preco) {
        this.Preco = Preco;
    }


}
