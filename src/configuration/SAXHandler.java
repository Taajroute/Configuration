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
    
 
  //List<Configuration> empList = new ArrayList<>();
  Configuration emp = null;
  String content = null;
  public void startElement(String uri, String localName,
                           String qName, Attributes attributes)                           throws SAXException {
	       
   switch(qName){
     //Create a new Employee object when the start tag is found
     case "configuration":
       emp = new Configuration();
       Integer  idmode= new Integer(attributes.getValue("id"));
       emp.Id_Mode = idmode;
       break;
     case "gtin":
        Integer gtinsize= new Integer(attributes.getValue("size"));
        emp.Gtin_Size = gtinsize;
       break;
     case "Batch" :
         Integer batchsize = new Integer(attributes.getValue("size"));
          emp.Batch_Size = batchsize;
       break;
     case "hidden_message" :
          Integer messagesize= new Integer (attributes.getValue("size"));
          emp.Hidden_Message_Size = messagesize;
       break;
             
        
   }
 }

  public void endElement(String uri, String localName,
                    String qName) throws SAXException {
    switch(qName){
    //Add the employee to list once end tag is found
    case "configuration":
     System.out.println("Fin lecture balise configuration");
     break;
   //For all other end tags the employee has to be updated.
   case "gtin":
       emp.Gtin=content;
       break;
       
    case "seed":
    BigInteger seedcont;
    seedcont = new BigInteger(content);
    emp.Seed=seedcont;
    break;
        
    case "start":
        BigInteger startcont;
        startcont=new BigInteger(content);
        emp.Start = startcont;
        break;
    case "end":
        BigInteger endcont;
        endcont= new BigInteger(content);
        emp.End = endcont;
        break;
    case "max":
        BigInteger maxcont;
        maxcont = new BigInteger (content);
        emp.Max = maxcont;
        break;
    case "fab":
        SimpleDateFormat format = new SimpleDateFormat("MM/yyyy");
        try {
             Date datefab;
             datefab = format.parse(content);
             emp.Date_Fabrication = datefab;
			      
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
             emp.Date_Experation = datexp;
			      
			} catch (java.text.ParseException e) {
 
				System.out.print("Erreur date fabrication");
			}
        
        break; 
         case "Batch":
        emp.Batch=content;
        break;       
        case "hidden_message":
        emp.Hidden_Message=content;
        break; 
        case "Code-Barres":
        emp.Code_Barres_Type=content;
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

