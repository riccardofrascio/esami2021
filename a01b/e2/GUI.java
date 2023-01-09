package a01b.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer, Integer>> buttons = new HashMap<>();
    private int size;
    private Logics logic;
    
    public GUI(int size) {
        this.size=size;
        logic=new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(50*size, 50*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
        	var button = (JButton)e.getSource();
            buttons.forEach((k, v) -> {
                if(button.equals(k)) {
                    if(logic.click(v)) {
                        k.setText(Integer.toString(logic.getNumber()));
                        compute();
                    }
                }     
            });
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.buttons.put(jb, new Pair<>(j,i));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }
    
    private void compute() {
        if(logic.getNumber() == 3) {
            var triangle = logic.getTriangle();
            buttons.forEach((k,v) -> {
                if(triangle.contains(v)) {
                    k.setText("*");
                }
                k.setEnabled(false);
            });
        }
    }
}
