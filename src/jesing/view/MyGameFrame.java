package jesing.view;


import jesing.snake.HandleSnake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class MyGameFrame extends JFrame {
    private boolean isStart;
    private HandleSnake handle;
    private PaintThread thread;
    public MyGameFrame(){
        this.isStart=false;
        init();
    }
    public void init(){
        this.handle=new HandleSnake();
        getContentPane().setBackground(Color.GRAY);//背景颜色
        setBounds(450,200,860,860);//窗口大小
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("snake game jesing 按R重新开始");
        addKeyListener(new Keyboard());
        this.thread=new PaintThread();
        thread.start();
    }
    class PaintThread extends Thread{
        @Override
        public void run() {
            while (true){
                repaint();
                if (!handle.go()){break;}
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class Keyboard extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode()==82){
                handle=new HandleSnake();
                if (!thread.isAlive()){
                    thread=new PaintThread();
                    thread.start();
                }
            }
            switch (e.getKeyCode()){
                case 38:handle.top();break;
                case 40:handle.down();break;
                case 37:handle.left();break;
                case 39:handle.right();break;
                default:break;
            }
        }
    }
    @Override
    public void paint(Graphics g) {//画图方法
        super.paint(g);
        handle.draw(g);
    }
}
