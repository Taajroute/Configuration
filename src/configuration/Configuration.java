/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration;


import org.w3c.dom.Document;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.Buffer;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.SAXException;

/**
 *
 * @author asadi
 */
public class Configuration {

  int Id_Mode;
  BigInteger Seed ; 
  BigInteger Start ; 
  BigInteger End; 
  BigInteger Max;
  
  String Gtin;
  int Gtin_Size ;
  
  Date Date_Fabrication; 
  Date Date_Experation;
  
  String Batch;
  int Batch_Size;
  
  String Hidden_Message ;
  int Hidden_Message_Size ;
  
  String Code_Barres_Type;
  
  String Packaging_Methode;
  int Packaging_Methode_Size;
  
  String Pmode;
  int Pmode_Size;
  
  String Prandome;
  int Prandome_Size;
  
  String Sub_Packaging;
  int Sub_Packaging_Size;
  
  String Pgtin;
  int Pgtin_Size;
  
  String Pseed;
  int Pseed_Size;
  
  String Pfab;
  int Pfab_Size;
  
  String Pexp;
  int Pexp_Size;
  
  String Pbatch;
  int Pbatch_Size;
  
  String Serialisation;
  int Serialisation_Size;
  
  String buffer;
  String a;
  
  String P_Chiffrement;
  String S_Chiffrement;
  
  JIBitArray Buffer_Sub_Packaging;
  JIBitArray Buffer_Packaging_Methode;
  
 
  

  @Override
  public String toString() {
    
    return   "Configuration mode "+Id_Mode + 
            "\n Serialisation " +Serialisation+ "\tSize="+Serialisation_Size+
            "\n Seed "+Seed+
            "\n Start "+Start+
            "\n End "+End+
            "\n Max "+Max+
            "\n Gtin "+Gtin+ "\tSize="+Gtin_Size+
            "\n Date Fabrication "+Date_Fabrication+
            "\n Date Experation "+Date_Experation+
            "\n Batch "+Batch+Batch_Size+
           "\n Hidden Message "+Hidden_Message+ "\tSize="+Hidden_Message_Size+
           "\n Code Barres Type "+Code_Barres_Type+
           "\n Packaging_Methode "+Packaging_Methode+ "\tSize="+Packaging_Methode_Size+ "\tMode_chifferemnt="+P_Chiffrement+
           "\n Pmode "+Pmode+ "\tSize="+Pmode_Size+
           "\n Prandome "+Prandome+ "\tSize="+Prandome_Size+
           "\n Sub_Packaging "+Sub_Packaging+ "\tSize="+Sub_Packaging_Size+ "\tMode_Chiffrement="+S_Chiffrement+
           "\n Pgtin "+Pgtin+ "\tSize="+Pgtin_Size+
           "\n Pseed "+Pseed+ "\tSize="+Pseed_Size+
           "\n Pfab "+Pfab+ "\tSize="+Pfab_Size+
           "\n Pexp "+Pexp+ "\tSize="+Pexp_Size+
           "\n Pbatch "+Pbatch+ "\tSize="+Pbatch_Size+
           "\n Affiche "+a+
           "\n Affichage Binaire :"+Buffer_Sub_Packaging.ToStringBase()+
            "\n Affichage Binaire de Packaging-Methode :" +Buffer_Packaging_Methode.ToStringBase();
           
  }
  
  
    /**
     * @param args the command line arguments
     */
   // public static void main_3(String[] args) throws JDOMException, IOException {
     //   Configuration  conf = new Configuration() ;
       // SAXBuilder builder = new SAXBuilder();
	 // File xmlFile = new File("ConfigurationBase.xml");
 
          //Document document = (Document) builder.build(xmlFile);
          //Element rootNode = document.getRootElement();
          //List list = rootNode.getChildren("serialisation");
          //for (int i = 0; i < list.size(); i++) {
              
             // Element node = (Element) list.get(i);
  
              //System.out.println("seed : " + node.getChildText("seed"));
		  
		//}
	}

    

