/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author asadi
 */
public class Configuration {

  String Id_Mode;
  String Seed ; 
  String Start ; 
  String End; 
  String Max;
  
  String Gtin;
  String Gtin_Size ;
  
  String Date_Fabrication; 
  String Date_Experation;
  
  String Batch;
  String Batch_Size;
  
  String Hidden_Message ;
  String Hidden_Message_Size ;
  
  String Code_Barres_Type;
 
  

  @Override
  public String toString() {
    return "Configuration mode "+Id_Mode + 
           "\n Seed "+Seed+
           "\n Start "+Start+
           "\n End "+End+
           "\n Max "+Max+
           "\n Gtin "+Gtin+ "\tSize="+Gtin_Size+
           "\n Date Fabrication "+Date_Fabrication+
           "\n Date Experation "+Date_Experation+
           "\n Batch "+Batch+ "\tSize="+Batch_Size+
           "\n Hidden Message "+Hidden_Message+ "\tSize="+Hidden_Message_Size+
           "\n Code Barres Type "+Code_Barres_Type;
  }
  
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      try {
          Configuration  conf = new Configuration() ;
          BigInteger Hidden_Message = new BigInteger(Hidden_Message);
          System.out.println(conf);
          System.out.println("Fin de programme  ...... ");
          
          System.out.println("Fin de programme 4 ..... ");
          System.out.println("Fin de programme 5 ..... ");
          System.out.println("Fin de programme 6 ..... ");
          System.out.println("Fin de programme 7 ..... ");
          
          System.out.println("Test mise à jour ...");
          System.out.println("Test mise à jour II ...");
          
          
          
          SAXParserFactory parserFactor = SAXParserFactory.newInstance();
          SAXParser parser = parserFactor.newSAXParser();
          SAXHandler handler = new SAXHandler();
          
          
          
          parser.parse( new FileInputStream("ConfigurationBase.xml"),handler);
          
          //Printing the list of employees obtained from XML
          
          System.err.println(handler.emp);
          
      } catch (FileNotFoundException ex) {
          Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
      } catch (SAXException ex) {
          Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
          Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
      } catch (ParserConfigurationException ex) {
          Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
}
