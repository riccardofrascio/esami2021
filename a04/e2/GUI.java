package a04.e2;

import javax.swing.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer, Integer>> buttons = new HashMap<>();
    private Optional<Pair<Integer, Integer>> oldPos = Optional.empty();
    private final JButton quit = new JButton("QUIT");
    Logics logic;
        
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        logic=new LogicsImpl(size);
        
        JPanel grid = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,grid);
        this.getContentPane().add(BorderLayout.SOUTH,quit);
        
        quit.addActionListener(e -> {
            System.out.println(logic.getValue());
        	System.exit(0);
       	});
        
        ActionListener al = e -> {
            var jb = (JButton)e.getSource();
            if(oldPos.equals(Optional.empty()) || logic.getNear(oldPos.get()).contains(buttons.get(jb))) {
                logic.click(buttons.get(jb));
        	    jb.setEnabled(false);
                oldPos=Optional.of(buttons.get(jb));
            }
            
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++) {
                final JButton jb = new JButton("  ");
                this.buttons.put(jb, new Pair<>(j, i));
                grid.add(jb);
                jb.addActionListener(al);
            }
        }
        updateView();
        this.setVisible(true);
    }

    private void updateView() {
        buttons.forEach((k, v) -> {
            if(logic.getOperations().keySet().contains(v)) {
                k.setText(logic.getOperations().get(v));
            }
            if(logic.getValues().keySet().contains(v)) {
                k.setText(Integer.toString(logic.getValues().get(v)));
            }
        });
    }
}