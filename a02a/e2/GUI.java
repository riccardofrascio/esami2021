package a02a.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer, Integer>> buttons = new HashMap<>();
    private Set<Pair<Integer, Integer>> oldPositions = new HashSet<>();
    private int counter = 0;
    private int size;
    private Pair<Integer, Integer> position;
    private Pair<Integer, Integer> direction;
    
    public GUI(int size) {
        this.size=size;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(50*size, 50*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
        	if(counter == 0) {
                Random rand = new Random();
                position = new Pair<>(rand.nextInt(size), rand.nextInt(size));
                direction = new Pair<>(position.getX(), position.getY()-1);
                oldPositions.add(position);
                buttons.forEach((k, v) -> {
                    if(v.equals(position)) {
                        k.setText(Integer.toString(counter));
                    }
                });
                counter++;
            } else if(nextPos()) {
                buttons.forEach((k,v) -> {
                    if(v.equals(position)) {
                        k.setText(Integer.toString(counter));
                    }
                });
                counter++;
            } else {
                System.exit(0);
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.buttons.put(jb, new Pair<Integer,Integer>(j,i));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
        
    }

    private boolean nextPos() {
        
        if(!oldPositions.contains(direction) && direction.getX()!=size && direction.getX()!=-1 && direction.getY()!=size && direction.getY()!=-1){
            oldPositions.add(direction);
            int x = direction.getX()-position.getX()+direction.getX();
            int y = direction.getY()-position.getY()+direction.getY();
            position=direction;
            System.out.println(counter);
            direction=new Pair<>(x,y);
            return true;
        }else if(moveUp()) {
            oldPositions.add(position);
            this.position = new Pair<>(position.getX(),position.getY()-1);
            this.direction = new Pair<>(position.getX(),position.getY()-1);
            return true;
        } else if(moveRigth()) {
            oldPositions.add(position);
            this.position = new Pair<>(position.getX()+1,position.getY());
            this.direction = new Pair<>(position.getX()+1,position.getY());
            return true;
        } else if(moveDown()) {
            oldPositions.add(position);
            this.position = new Pair<>(position.getX(),position.getY()+1);
            this.direction = new Pair<>(position.getX(),position.getY()+1);
            return true;
        } else if(moveLeft()) {
            oldPositions.add(position);
            this.position = new Pair<>(position.getX()-1,position.getY());
            this.direction = new Pair<>(position.getX()-1,position.getY());
            return true;
        }
        return false;
    }

    private boolean moveUp() {
        if(position.getY()-1 >= 0 && !oldPositions.contains(new Pair<>(position.getX(),position.getY()-1))) {
            return true;
        }
        return false;
    }

    private boolean moveDown() {
        if(position.getY()+1 == size || oldPositions.contains(new Pair<>(position.getX(),position.getY()+1))) {
            return false;
        }
        return true;
    }
    
    private boolean moveRigth() {
        if(position.getX()+1 == size || oldPositions.contains(new Pair<>(position.getX()+1,position.getY()))) {
            return false;
        }
        return true;
    }

    private boolean moveLeft() {
        if(position.getX()-1 >= 0 && !oldPositions.contains(new Pair<>(position.getX()-1,position.getY()))) {
            return true;
        }
        return false;
    }
    
}
