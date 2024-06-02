/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.unipar.pdv.desktop;

import br.unipar.pdv.desktop.telas.VendaPanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author vinid
 */
public class PDVDesktop extends JFrame {
    
    public PDVDesktop() {
        VendaPanel vendaPanel = new VendaPanel();
        add(vendaPanel);  // Adiciona o painel de venda ao frame
        setTitle("Sistema de Vendas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) {
         SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PDVDesktop().setVisible(true);
            }
        });
    }
}
