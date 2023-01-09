package a02b.e2;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer, Integer>> buttons = new HashMap<>();
    Logics logic;
    
    public GUI(int size) {
        logic = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(50*size, 50*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
        	logic.click();
            updateView();
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.buttons.put(jb, new Pair<Integer,Integer>(j,i));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        updateView();
        
        this.setVisible(true);
    }
    
    private void updateView() {
        buttons.forEach((k, v) -> {
            k.setText("");
            if(logic.getPosition().equals(v)) {
                k.setText("*");
            }
            if(logic.getRigth().contains(v)) {
                k.setText("R");
            }
            if(logic.getLeft().contains(v)) {
                k.setText("L");
            }
        });
    }
}
