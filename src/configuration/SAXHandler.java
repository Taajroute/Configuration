/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author manel
 */
public class SAXHandler extends DefaultHandler{
    
 
  
  Configuration config = null;
  String content = null;
  public void startElement(String uri, String localName,
                           String qName, Attributes attributes)                           throws SAXException {
	       
   switch(qName){
     //Create a new Configuration object when the start tag is found
     case "configuration":
       config = new Configuration();
       Integer  idmode= new Integer(attributes.getValue("id"));
       config.Id_Mode = idmode;
       break;
     case "gtin":
        Integer gtinsize= new Integer(attributes.getValue("size"));
        config.Gtin_Size = gtinsize;
       break;
     case "Batch" :
         Integer batchsize = new Integer(attributes.getValue("size"));
          config.Batch_Size = batchsize;
       break;
     case "hidden_message" :
          Integer messagesize= new Integer (attributes.getValue("size"));
          config.Hidden_Message_Size = messagesize;
       break;
             
        
   }
 }

  public void endElement(String uri, String localName,
                    String qName) throws SAXException {
    switch(qName){
    
    case "configuration":
     System.out.println("Fin lecture balise configuration");
     break;
   //For all other end tags the configuration has to be updated.
   case "gtin":
       config.Gtin=content;
       break;
       
    case "seed":
    BigInteger seedcont;
    seedcont = new BigInteger(content);
    config.Seed=seedcont;
    break;
        
    case "start":
        BigInteger startcont;
        startcont=new BigInteger(content);
        config.Start = startcont;
        break;
    case "end":
        BigInteger endcont;
        endcont= new BigInteger(content);
        config.End = endcont;
        break;
    case "max":
        BigInteger maxcont;
        maxcont = new BigInteger (content);
        config.Max = maxcont;
        break;
    case "fab":
        SimpleDateFormat format = new SimpleDateFormat("MM/yyyy");
        try {
             Date datefab;
             datefab = format.parse(content);
             config.Date_Fabrication = datefab;
			      
			} catch (java.text.ParseException e) {
 
				System.out.print("Erreur date fabrication");
			}
        break;
          case "exp":
               SimpleDateFormat form;
               form = new SimpleDateFormat("MM/yyyy");
        try {
             Date datexp;
             datexp = form.parse(content);
             config.Date_Experation = datexp;
			      
			} catch (java.text.ParseException e) {
 
				System.out.print("Erreur date fabrication");
			}
        
        break; 
         case "Batch":
        config.Batch=content;
        break;       
        case "hidden_message":
        config.Hidden_Message=content;
        break; 
        case "Code-Barres":
        config.Code_Barres_Type=content;
        break; 
    }
  }
  public void characters(char[] ch, int start, int length)
    throws SAXException {
    content = String.copyValueOf(ch, start, length).trim();
    }

    private int BigInteger(String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

