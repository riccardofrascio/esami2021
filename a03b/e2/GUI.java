package a03b.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer, Integer>> buttons = new HashMap<>();
    private int counter = 0;
    private int size;
    private Logics logic;
    
    public GUI(int size) {
        this.size = size;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(50*size, 50*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
        	logic.click();
            updateView();
            if(logic.isOver()) {
                System.exit(0);
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
            	var pos = new Pair<>(j,i);
                final JButton jb = new JButton(" ");
                this.buttons.put(jb, new Pair<Integer,Integer>(j,i));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        logic=new LogicsImpl(size);
        updateView();
        this.setVisible(true);
    }
    
    private void updateView() {
        buttons.forEach((k,v) -> {
            if(v.equals(logic.getPosition())) {
                k.setText(Integer.toString(counter++));
            }
        });
    }
}
