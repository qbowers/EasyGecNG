package ihm;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JButton;
import java.io.File;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.text.PlainDocument;
import javax.swing.text.html.HTMLDocument;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import javafx.geometry.Pos;
import metier.EasyGec;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IhmSmiley extends JDialog
{
  /**
   * 
   */
  private static final long serialVersionUID = 7632301975013806226L;
  public AudioClip applause = Applet.newAudioClip(getClass().getResource("/sons/applause.wav"));
  public AudioClip encouragement = Applet.newAudioClip(getClass().getResource("/sons/encouragement.wav"));
  public AudioClip ohno = Applet.newAudioClip(getClass().getResource("/sons/ohno.wav"));
  public static Dimension dimEcran = Toolkit.getDefaultToolkit().getScreenSize(); // r�cup�ration de la dimension de l'�cran  //  @jve:decl-index=0:
  private ImageIcon icon;
  private JEditorPane editorPane;
  private boolean pause = false;
  private JButton btnNewButton;

  /**
   *
   * @param numberMissed integer of how many checkpoints a participant missed on the guessed course
   * @param missedStr string representation, \n-separated, of missed checkpoints for the graphic to display. Rendered in editorPane in reloadPage()
   * @param temps elapsed time, rendered at the bottom of the screen in 'lblTime'
   * @param courseName course name (string), rendered in editorPane in reloadPage()
   * @param easyGec instance of EasyGec, used for how long to delay the screen
   */
  public IhmSmiley(int numberMissed, String missedStr, String temps, String courseName, EasyGec easyGec)
  {
    setAlwaysOnTop(true);    
    setUndecorated(true);
    
    btnNewButton = new JButton("");
    btnNewButton.addMouseListener(new MouseAdapter() 
    {
      @Override
      public void mouseClicked(MouseEvent e) 
      {
        if(!pause)
        {
          pause = true;
          icon = new ImageIcon(IhmSmiley.class.getResource("/icones/pause.png"));
        
          Image img = icon.getImage();
          Image newimg = img.getScaledInstance(btnNewButton.getSize().height, btnNewButton.getSize().height, java.awt.Image.SCALE_SMOOTH);
          icon = new ImageIcon(newimg);  
          btnNewButton.setIcon(icon);
        }
        else
        {
          dispose();
        }
      }
    });
    btnNewButton.setIcon(new ImageIcon(IhmSmiley.class.getResource("/icones/glassy-smiley-good-green.png")));
    getContentPane().add(btnNewButton, BorderLayout.CENTER);

    setSize(dimEcran.width, dimEcran.height);
    btnNewButton.setSize(dimEcran.height, dimEcran.height);
    
    if(numberMissed<1)
    {
      icon = new ImageIcon(IhmSmiley.class.getResource("/icones/glassy-smiley-good-green.png"));
    }
    else
    {
      icon = new ImageIcon(IhmSmiley.class.getResource("/icones/glassy-smiley-bad.png"));
    }
    Image img = icon.getImage();
    Image newimg = img.getScaledInstance(btnNewButton.getSize().width, btnNewButton.getSize().height, java.awt.Image.SCALE_SMOOTH);
    icon = new ImageIcon(newimg);  
    btnNewButton.setIcon(icon);
    
    editorPane = new JEditorPane();
    editorPane.setPreferredSize(new Dimension(220, 20));

    //only render missed controls list if the course is incomplete
    if(missedStr.length()>0) {
    getContentPane().add(editorPane, BorderLayout.WEST);
  }
    
    JLabel lblTime = new JLabel("PM");
    lblTime.setFont(new Font("Tahoma", Font.PLAIN, 60));
    lblTime.setHorizontalAlignment(SwingConstants.CENTER);
    lblTime.setPreferredSize(new Dimension(14, 100));
    getContentPane().add(lblTime, BorderLayout.SOUTH);

    if(temps.compareTo("0:00:00")!=0)
    {
      lblTime.setText(temps);
    }
    else
    {
      lblTime.setText("");
    }

    reLoadPage(numberMissed, missedStr, courseName);
    this.setVisible(true);
    try
    {
      Thread.sleep(easyGec.getTempo()*1000);
      //Thread.sleep(4000);
    }
    catch (InterruptedException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    if(!pause)
    {
      dispose();
    }
  }

  
  private void reLoadPage(int numberMissed, String missedStr, String courseName)
  {
    try
    {
      editorPane.setDocument(new PlainDocument());
      Font currentFont = editorPane.getFont();
      Font font = currentFont.deriveFont(48f);
      editorPane.setFont(font);
      editorPane.setText(courseName+ "\n________\nMissed\nControls:\n" + missedStr);
    }
    catch (Exception et)
    {
      System.err.format("Impossible de charger la page", et.getMessage());
    }
    if(numberMissed<1)
    {
      applause.play();
    }
    else
    {
      ohno.play();
    }
  }


  public void setBoutonIcone(JButton bouton, ImageIcon imageIcon)
  {
    bouton.setIcon(imageIcon);
  }
}
