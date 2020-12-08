package GUI;

import Bean.DBBean;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ResourceBundle;

public class MyListener implements KeyListener {

    private String belongto;
    private MyJPanel[] all;
    private DBBean db;
    private init_box init;


    public MyListener(String belongto, MyJPanel[] all, DBBean db, init_box init) {
        this.belongto = belongto;
        this.all = all;
        this.db = db;
        this.init = init;
    }


    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==116) {
            System.out.println("123");
            init.refresh(belongto, all, db);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
