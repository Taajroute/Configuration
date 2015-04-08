/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration;

import java.util.ArrayList;
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
       emp.Id_Mode = attributes.getValue("id");
       break;
     case "gtin":
       emp.Gtin_Size = attributes.getValue("size");
       break;
     case "Batch" :
         emp.Batch_Size = attributes.getValue("size");
       break;
     case "hidden_message" :
         emp.Hidden_Message_Size = attributes.getValue("size");
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
    emp.Seed=content;
    break;
        
    case "start":
        emp.Start=content;
        break;
      case "end":
        emp.End=content;
        break;
          case "max":
        emp.Max=content;
        break;
           case "fab":
        emp.Date_Fabrication=content;
        break;
          case "exp":
        emp.Date_Experation=content;
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
}

