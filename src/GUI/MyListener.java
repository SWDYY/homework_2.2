package GUI;

import Bean.DBBean;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.ResourceBundle;

public class MyListener implements KeyListener {

    private String belongto;
    private MyJPanel[] all;
    private DBBean db;
    private init_box init;
    private List[] combox;


    public MyListener(String belongto, MyJPanel[] all, DBBean db, init_box init, List[] combox) {
        this.belongto = belongto;
        this.all = all;
        this.db = db;
        this.init = init;
        this.combox = combox;
    }


    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==116) {
            if (all != null) init.refresh(belongto, all, db);
            if (combox != null) init.refresh(combox, db);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
