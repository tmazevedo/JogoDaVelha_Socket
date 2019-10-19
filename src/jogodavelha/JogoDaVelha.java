/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodavelha;

import com.sun.corba.se.impl.io.IIOPInputStream;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
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
public class JogoDaVelha {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner entrada = new Scanner(System.in);
        Tabuleiro tab = new Tabuleiro();
        boolean jogoContinua = true;
        int rodada = 1;
        int linha, coluna;
        File arquivo = new File("jogo.ser");
        try {
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }

            FileOutputStream fout = new FileOutputStream(arquivo);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            
            //tab.carregaJogo(arquivo);

            System.out.println("Jogador X, digite seu nome:");
            String nome = entrada.nextLine();

            System.out.println("Jogador O, digite seu nome:");
            String nome2 = entrada.nextLine();

            Jogador j1 = new Jogador(1, nome);
            Jogador j2 = new Jogador(2, nome2);

            while (jogoContinua) {
                //tab.exibeTabuleiro();
                System.out.println(tab.exibirTabuleiro());
                if (rodada % 2 == 1) {
                    System.out.println("Vez do jogador: " + j1.getNome());
                    do {
                        System.out.println("Digite linha:");
                        linha = entrada.nextInt();
                    } while (linha < 0 || (linha > 2 && linha != 9));
                    do {
                        System.out.println("Digite coluna:");
                        coluna = entrada.nextInt();
                    }while(coluna < 0 || (coluna > 2 && coluna != 9));

                    if (!tab.fazJogada(linha, coluna, j1, arquivo)) {
                        System.out.println("Jogada invalida!");
                        break;
                    }
                } else {
                    System.out.println("Vez do jogador: " + j2.getNome());
                    do {
                        System.out.println("Digite linha:");
                        linha = entrada.nextInt();
                    } while (linha < 0 || (linha > 2 && linha != 9));
                    do {
                        System.out.println("Digite coluna:");
                        coluna = entrada.nextInt();
                    }while(coluna < 0 || (coluna > 2 && coluna != 9));

                    if (!tab.fazJogada(linha, coluna, j2, arquivo)) {
                        System.out.println("Salvo com sucesso");
                        break;
                    }
                }
                rodada++;
                int i = tab.quemGanhou();

                if (i == 1) {
                    System.out.println(" O jogador " + j1.getNome() + " ganhou!");
                    jogoContinua = false;
                } else if (i == -1) {
                    System.out.println("O jogador" + j2.getNome() + " ganhou!");
                    jogoContinua = false;
                } else if (i == 0) {
                    if (!tab.verificaTabuleiro()) {
                        System.out.println("Deu velha!");
                        jogoContinua = false;
                    }
                }
            }
            //tab.exibeTabuleiro();
            System.out.println(tab.exibirTabuleiro());

        } catch (IOException iOException) {
            Logger.getLogger(JogoDaVelha.class.getName()).log(Level.SEVERE, null, iOException);
            iOException.printStackTrace();
        }
    }
}
