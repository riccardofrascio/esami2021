package a01a.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer, Integer>> buttons = new HashMap<>();
    private Logics logic;
    boolean finish = true;

    public GUI(int size) {
        logic = new LogicsImpl();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(50*size, 50*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
        	var button = (JButton)e.getSource();
        	buttons.forEach((k, v) -> {
                if(k.equals(button)) {
                    logic.click(v);
                }
            });
            button.setText("1");
            updateView();
            if(isFinish()) {
            buttons.forEach((k, v) -> k.setEnabled(false));
            }
             
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.buttons.put(jb,new Pair<Integer,Integer>(j,i));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }

    private void updateView() {
        var cells = logic.getCells(); 
        if(cells.equals(Optional.empty())) {

        } else {
            buttons.forEach((k,v) -> {
                if(cells.get().contains(v)) {
                    k.setText("*");
                }
            });
        }
    }

    private boolean isFinish() {
        finish=true;
        buttons.forEach((k, v) -> {
            if(!k.getText().equals("*")) {
                finish=false;
            }
        });
        return finish;
    }

    
}
