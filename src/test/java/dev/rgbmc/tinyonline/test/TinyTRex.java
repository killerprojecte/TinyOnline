package dev.rgbmc.tinyonline.test;

import dev.rgbmc.tinyonline.TinyOnline;
import dev.rgbmc.tinyonline.games.TRex;
import dev.rgbmc.tinyonline.utils.ArrowKey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

public class TinyTRex {
    private static Timer timer;
    public static void main(String[] args) throws Exception {
        TRex tRex = new TRex();
        tRex.startGame();
        JFrame jFrame = new JFrame("T-Rex");
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                timer.cancel();
                tRex.stopGame();
                TinyOnline.stopEnvironment();
                System.exit(0);
            }
        });
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED) {
                    //System.out.println("Pressed: " + e.getKeyCode());
                    if (e.getKeyCode() == 38) {
                        tRex.downKey(ArrowKey.UP);
                    } else if (e.getKeyCode() == 40) {
                        tRex.downKey(ArrowKey.DOWN);
                    } else if (e.getKeyCode() == 10) {
                        tRex.restart();
                    }
                } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                    //System.out.println("Released: " + e.getKeyCode());
                    if (e.getKeyCode() == 38) {
                        tRex.upKey(ArrowKey.UP);
                    } else if (e.getKeyCode() == 40) {
                        tRex.upKey(ArrowKey.DOWN);
                    }
                }
                return false;
            }
        });
        Canvas canvas = new Canvas() {
            @Override
            public void paint(Graphics g) {
                if (TinyOnline.isBrowserOpen()) {
                    g.drawImage(tRex.getCurrentScreen(), 0, 0, null);
                }
            }

            @Override
            public void repaint() {
                if (TinyOnline.isBrowserOpen()) {
                    getGraphics().drawImage(tRex.getCurrentScreen(), 0, 0, null);
                }
            }
        };
        jFrame.add(canvas);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setMinimumSize(new Dimension(128, 128));
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!TinyOnline.isBrowserOpen()) {
                    this.cancel();
                    return;
                }
                jFrame.invalidate();
                canvas.repaint();
                jFrame.repaint();
                jFrame.revalidate();
            }
        }, 1L, 1L);
    }
}
