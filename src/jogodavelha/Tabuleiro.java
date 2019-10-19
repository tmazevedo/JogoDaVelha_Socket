/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodavelha;

import java.io.File;
import java.io.Serializable;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileReader;
import java.io.ObjectInputStream;

/**
 *
 * @author luiso
 */
public class Tabuleiro implements Serializable {

    private int[][] tabuleiro = new int[3][3];
    //private int posicao;
    private int X = 1, O = -1;

    public Tabuleiro() {
        //limparTabuleiro();
        this.tabuleiro = new int[3][3];
    }

    public void limparTabuleiro() {
        /* int linha, col;
         for(linha = 0; linha < 3; linha++){
         for(col = 0; col < 3; col++){
         tabuleiro[linha][col] = 0;
         }
         }
         */
        tabuleiro = new int[3][3];
    }

    public String exibirTabuleiro() {
        StringBuilder builder = new StringBuilder();
        for (int[] is : tabuleiro) {
            for (int i : is) {
                builder.append("|_").append(i != 0 ? ((i == 1) ? "X" : "O") : " ").append("_|");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public void exibeTabuleiro() {
        System.out.println();
        for (int linha = 0; linha < 3; linha++) {

            for (int coluna = 0; coluna < 3; coluna++) {

                if (tabuleiro[linha][coluna] == -1) {
                    System.out.print(" X ");
                }
                if (tabuleiro[linha][coluna] == 1) {
                    System.out.print(" O ");
                }
                if (tabuleiro[linha][coluna] == 0) {
                    System.out.print("   ");
                }

                if (coluna == 0 || coluna == 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
    }

    public boolean fazJogada(int linha, int coluna, Jogador jog, File arquivo) throws FileNotFoundException, IOException {
        if (jog.getId() == 1) {
            if (linha == 9 && coluna == 9) {
                salvaJogo(arquivo);
                return false;
            } else {
                tabuleiro[linha][coluna] = X;
                return true;
            }
        } else if (jog.getId() == 2) {
            if (linha == 9 && coluna == 9) {
                salvaJogo(arquivo);
                return false;
            } else {
                tabuleiro[linha][coluna] = O;
                return true;
            }
        }
        return false;
    }

    public void salvaJogo(File arquivo) throws FileNotFoundException, IOException {
        FileOutputStream fout = new FileOutputStream(arquivo);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(tabuleiro);
        oos.close();
        fout.close();
        System.out.println("Salvo com sucesso");
    }

    public void carregaJogo(File arquivo) throws FileNotFoundException, IOException, ClassNotFoundException {
        if (arquivo.exists()) {
            FileInputStream fin = new FileInputStream(arquivo);
            ObjectInputStream ois = new ObjectInputStream(fin);
            while (true) {
                Tabuleiro t = (Tabuleiro) ois.readObject();
                System.out.println(t);
            }
        }
    }

    public boolean verificaTabuleiro() {
        for (int[] t : tabuleiro) {
            for (int i : t) {
                if (i == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public int quemGanhou() {
        if (tabuleiro[0][0] == X && tabuleiro[0][1] == X && tabuleiro[0][2] == X) {
            return X;
        } else if (tabuleiro[1][0] == X && tabuleiro[1][1] == X && tabuleiro[1][2] == X) {
            return X;
        } else if (tabuleiro[2][0] == X && tabuleiro[2][1] == X && tabuleiro[2][2] == X) {
            return X;
        } else if (tabuleiro[0][0] == X && tabuleiro[1][0] == X && tabuleiro[2][0] == X) {
            return X;
        } else if (tabuleiro[0][1] == X && tabuleiro[1][1] == X && tabuleiro[2][1] == X) {
            return X;
        } else if (tabuleiro[0][2] == X && tabuleiro[1][2] == X && tabuleiro[2][2] == X) {
            return X;
        } else if (tabuleiro[0][0] == X && tabuleiro[1][1] == X && tabuleiro[2][2] == X) {
            return X;
        } else if (tabuleiro[0][2] == X && tabuleiro[1][1] == X && tabuleiro[2][0] == X) {
            return X;
        } //-----------------
        else if (tabuleiro[0][0] == O && tabuleiro[0][1] == O && tabuleiro[0][2] == O) {
            return O;
        } else if (tabuleiro[1][0] == O && tabuleiro[1][1] == O && tabuleiro[1][2] == O) {
            return O;
        } else if (tabuleiro[2][0] == O && tabuleiro[2][1] == O && tabuleiro[2][2] == O) {
            return O;
        } else if (tabuleiro[0][0] == O && tabuleiro[1][0] == O && tabuleiro[2][0] == O) {
            return O;
        } else if (tabuleiro[0][1] == O && tabuleiro[1][1] == O && tabuleiro[2][1] == O) {
            return O;
        } else if (tabuleiro[0][2] == O && tabuleiro[1][2] == O && tabuleiro[2][2] == O) {
            return O;
        } else if (tabuleiro[0][0] == O && tabuleiro[1][1] == O && tabuleiro[2][2] == O) {
            return O;
        } else if (tabuleiro[0][2] == O && tabuleiro[1][1] == O && tabuleiro[2][0] == O) {
            return O;
        }
        return 0;
    }

}
