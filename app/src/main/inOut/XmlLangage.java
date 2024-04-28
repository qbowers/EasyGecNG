/**
 * 
 */
package inOut;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import metier.EasyGec;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import to.langage;

/**
 * <P>
 * Titre : XmlRaid.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class XmlLangage
{
  // Déclaration des variables Document et de la racine
  private static Document document;
  private static Element racine;
  private static String LANGS = "langs";
  private static String STRINGS = "strings";
  private static String STRING = "string";
  private static String ID = "id";
  private static String TEXT = "text";
  private static String NAME = "name";


  /**
   * Méthode lisant le fichier XML passé en paramètre et construisant 
   * Le vecteur contenant tous les parcours
   * @param fichier
   */
  // Méthode de lecture du fichier XML
  public  static void lecture(EasyGec easyGec, String fichier)
  {
    SAXBuilder sxb = new SAXBuilder();
    try
    {
      document = sxb.build(new File(fichier));
    }
    catch (JDOMException e)
    {
      JOptionPane.showMessageDialog(null,"Erreur de lecture du fichier : "+e.getClass().getName()+", "+e.getMessage());
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(null,"Erreur de lecture du fichier : "+e.getClass().getName()+", "+e.getMessage());
    }
    racine = document.getRootElement();
    recupereAllLangs(easyGec);
    recupereAllStrings(easyGec);
  }
  
  // Méthode récupérant toutes les orienteurs
  private static void recupereAllStrings(EasyGec easyGec)
  {
    Element result = racine.getChild(STRINGS);
    List<?> results = result.getChildren(STRING);
    Iterator<?> i = results.iterator();
    
    while (i.hasNext())
    {
        Element v = (Element) i.next();
        
        langage r = new langage();
        r.setId(v.getAttributeValue(ID));

        List<?> data = v.getChildren(TEXT);
        Iterator<?> j = data.iterator();
        while (j.hasNext())
        {
          Element d = (Element) j.next();
          r.getTexts().add(d.getText());
        }
      
      // Ajout de l'orienteur
        EasyGec.getLangages().getLangages().add(r);
    }
  }
  
  // Méthode récupérant toutes les parcours
  private static void recupereAllLangs(EasyGec easyGec)
  {
    Element pars = racine.getChild(LANGS);
    List<?> parcours = pars.getChildren(NAME);
    Iterator<?> i = parcours.iterator();
    
    while (i.hasNext())
    {
        Element v = (Element) i.next();

        easyGec.getLangs().add(v.getText());
    }
  }
  
}
