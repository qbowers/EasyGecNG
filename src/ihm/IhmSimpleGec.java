package ihm;

import metier.EasyGec;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class IhmSimpleGec extends JFrame {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                }

                try {
                    IhmSimpleGec frame = new IhmSimpleGec();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public IhmSimpleGec() {
        setPreferredSize(new Dimension(560, 570));
        setMinimumSize(new Dimension(650, 570));
        setMaximumSize(new Dimension(570, 1000));
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent arg0)
            {
                Enregistrer();
                System.exit(0);
            }
        });
        setTitle("SimpleGec");
    }

    private void Enregistrer()
    {
        int retour = JOptionPane.showConfirmDialog(IhmSimpleGec.this, EasyGec.getLangages().getText("45", EasyGec.getLang()),
                EasyGec.getLangages().getText("46", EasyGec.getLang()), JOptionPane.ERROR_MESSAGE, JOptionPane.YES_NO_CANCEL_OPTION);
        if(retour == 0)
        {
            //btnEnregistrer.doClick();
        }
    }
}