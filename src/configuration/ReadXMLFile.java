/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration;

import java.io.File;
import java.math.BigInteger;
import java.nio.Buffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import static jdk.nashorn.internal.objects.ArrayBufferView.buffer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author asadi
 */
public class ReadXMLFile {
     
     public static void main(String argv[]) 
     {
       
      
         
        try {
           
            Configuration  conf = new Configuration() ; 
            
            conf.Buffer_Sub_Packaging = new JIBitArray();
            
            conf.Buffer_Packaging_Methode = new JIBitArray();
             
            File fXmlFile = new File("ConfigurationBase.xml");

            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                                       .newDocumentBuilder();

            Document doc = dBuilder.parse(fXmlFile);

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());


            ReadXMLFile reader = new ReadXMLFile();
            reader.AnalyseConfiguration(doc,conf);
            System.err.println(conf);

        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
     
     
    
     
     @SuppressWarnings("empty-statement")
     private  boolean AnalyseConfiguration(Node NodeBase,Configuration conf) throws ParseException 
     {
        if (!NodeBase.hasChildNodes()) 
            return  false;

        NodeList nodeList=NodeBase.getChildNodes();
        
        if( nodeList ==null  )
            return  false;
        
        for (int count = 0; count < nodeList.getLength(); count++) 
        {
 
	Node tempNode = nodeList.item(count);
 
	// make sure it's element node.
	if (tempNode.getNodeType() == Node.ELEMENT_NODE) 
        {
 
		// get node name and value
                String Node_Name =tempNode.getNodeName();
                String Node_Value =tempNode.getTextContent();
                
                switch (Node_Name)
                {
                    case "serialisation" :
                        Parse_Serialisation( tempNode ,   conf);
                        break;
                        
                    case "gtin" :
                        conf.Gtin =Node_Value;
                        if( tempNode.hasAttributes())
                        {
                            NamedNodeMap nodeMap = tempNode.getAttributes();
                            for (int i = 0; i < nodeMap.getLength(); i++) 
                            {
				Node node = nodeMap.item(i);
                                String attr_name =node.getNodeName();
                                String attr_value =node.getNodeValue();
                                
				if( attr_name=="size" )
                                    conf.Gtin_Size=Integer.parseInt(attr_value);
                            }
                        }
                        
                    break;
                        
                    case "Batch":
                        conf.Batch =Node_Value;
                        if( tempNode.hasAttributes())
                        {
                            NamedNodeMap nodeMap = tempNode.getAttributes();
                            for (int i = 0; i < nodeMap.getLength(); i++) 
                            {
				Node node = nodeMap.item(i);
                                String attr_name =node.getNodeName();
                                String attr_value =node.getNodeValue();
                                
				if( attr_name=="size" )
                                    conf.Batch_Size=Integer.parseInt(attr_value);
                                    
                            }
                        }
                        break;
                        
                    case "fab": 
                                 conf.Date_Fabrication = new SimpleDateFormat("MM/yyyy").parse(Node_Value.trim()); 
                                                                 
                                          
                        break;
                        
                    case "exp": 
                                 conf.Date_Experation = new SimpleDateFormat("MM/yyyy").parse(Node_Value.trim()); 
                        break; 
                        
                    case "hidden_message": 
                                 conf.Hidden_Message = Node_Value.trim(); 
                                 if( tempNode.hasAttributes())
                                {
                                    NamedNodeMap nodeMap = tempNode.getAttributes();
                                    for (int i = 0; i < nodeMap.getLength(); i++) 
                                    {
                                        Node node = nodeMap.item(i);
                                        String attr_name =node.getNodeName();
                                        String attr_value =node.getNodeValue();

                                        if( attr_name=="size" )
                                            conf.Hidden_Message_Size=Integer.parseInt(attr_value.trim());
                                    }
                                }
                        break;     
                        case "Code-Barres": 
                                 conf.Code_Barres_Type = Node_Value.trim(); 
                        break;
                            
                        case "packaging-methode" :
                            Parse_packaging(tempNode ,   conf);
                            if( tempNode.hasAttributes())
                                {
                                    NamedNodeMap nodeMap = tempNode.getAttributes();
                                    for (int i = 0; i < nodeMap.getLength(); i++) 
                                    {
                                        Node node = nodeMap.item(i);
                                        String attr_name =node.getNodeName();
                                        String attr_value =node.getNodeValue();

                                        if( attr_name=="size" )
                                            conf.Packaging_Methode_Size=Integer.parseInt(attr_value.trim());
                                        if(attr_name=="mode-chiffrement")
                                            conf.P_Chiffrement=attr_value.trim();;
                                    }
                                }
                        break;
         
                }  
                // Analyse des sous racine du document XML
                AnalyseConfiguration(tempNode,conf );
		
		System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
 
	}
 
        }
     return true;   
  }
    
     
   
      boolean Parse_Serialisation( Node  NodeBase , Configuration  conf)
     {
         
         // On analyse la balise serialisation 
         if (NodeBase.getNodeType() == Node.ELEMENT_NODE) 
         {
             
           
             NodeList nodeList=NodeBase.getChildNodes();
             
             for (int count = 0; count < nodeList.getLength(); count++) 
             {
                 Node tempNode = nodeList.item(count);
                 
                // make sure it's element node.
                if (tempNode.getNodeType() == Node.ELEMENT_NODE) 
                {
                    // get node name and value
                
                    String Node_Name =tempNode.getNodeName();
                    String Node_Value =tempNode.getTextContent().trim();

                    switch (Node_Name)
                    {

                        case "seed" :
                            conf.Seed =new BigInteger(Node_Value);
                        break;
                            
                        case "start" :
                            conf.Start =new BigInteger(Node_Value);
                        break;  
                            
                        case "end" :
                            conf.End =new BigInteger(Node_Value);
                        break;
                           
                        case "max" :
                            conf.Max =new BigInteger(Node_Value);
                        break;
                         
                    }
                 
                }
            }
         }  
         
         return  true;
     }
     
      
     @SuppressWarnings("empty-statement")
    boolean Parse_packaging( Node  NodeBase , Configuration  conf)  
    {
        String a ="";
         //conf.Buffer_Sub_Packaging = new JIBitArray();
         // On analyse la balise serialisation 
         if (NodeBase.getNodeType() == Node.ELEMENT_NODE) 
         {
             
           
             NodeList nodeList=NodeBase.getChildNodes();
             
             for (int count = 0; count < nodeList.getLength(); count++) 
             {
                 Node tempNode = nodeList.item(count);
                 
                // make sure it's element node.
                if (tempNode.getNodeType() == Node.ELEMENT_NODE) 
                {
                    // get node name and value
                
                    String Node_Name =tempNode.getNodeName();
                    String Node_Value =tempNode.getTextContent().trim();
                    if( tempNode.hasAttributes())
                                {
                                    NamedNodeMap nodeMap = tempNode.getAttributes();
                                    for (int i = 0; i < nodeMap.getLength(); i++) 
                                    {
                                        Node node = nodeMap.item(i);
                                        String attr_name =node.getNodeName();
                                        String attr_value =node.getNodeValue();

                                        if( attr_name=="size" )
                                            conf.Packaging_Methode_Size=Integer.parseInt(attr_value.trim());
                                        if( attr_name=="mode-chifferement" )
                                            conf.P_Chiffrement=attr_value.trim();
                                    }
                                }

                    switch (Node_Name)
                    {

                        case "pmode" :
                            conf.Pmode = Node_Value;
                            
                            if( tempNode.hasAttributes())
                                {
                                    NamedNodeMap nodeMap = tempNode.getAttributes();
                                    for (int i = 0; i < nodeMap.getLength(); i++) 
                                    {
                                        Node node = nodeMap.item(i);
                                        String attr_name =node.getNodeName();
                                        String attr_value =node.getNodeValue();

                                        if( attr_name=="size" )
                                            conf.Pmode_Size=Integer.parseInt(attr_value.trim());
                                        
                                            byte[] conf_pmode= Base64.getDecoder().decode(conf.Pmode.trim());
                                            conf.Buffer_Packaging_Methode.Append(conf_pmode, conf.Pmode_Size);
                                           
                                             
                                    }
                                    
                                }
                                   
                        break;
                            
                        case "prandome-code" :
                            conf.Prandome = Node_Value ;
                            if( tempNode.hasAttributes())
                                {
                                    NamedNodeMap nodeMap = tempNode.getAttributes();
                                    for (int i = 0; i < nodeMap.getLength(); i++) 
                                    {
                                        Node node = nodeMap.item(i);
                                        String attr_name =node.getNodeName();
                                        String attr_value =node.getNodeValue();

                                        if( attr_name=="size" )
                                            conf.Prandome_Size=Integer.parseInt(attr_value.trim());
                                         byte[] conf_prandome= Base64.getDecoder().decode(conf.Prandome.trim());
                                            conf.Buffer_Packaging_Methode.Append(conf_prandome, conf.Prandome_Size);
                                           
                                        
                                            
                                    }
                                }
                        break;  
                            
                        case "sub-packaging" :
                             Parse_Sub_Packaging( tempNode ,   conf);
                             if( tempNode.hasAttributes())
                                {
                                    NamedNodeMap nodeMap = tempNode.getAttributes();
                                    for (int i = 0; i < nodeMap.getLength(); i++) 
                                    {
                                        Node node = nodeMap.item(i);
                                        String attr_name =node.getNodeName();
                                        String attr_value =node.getNodeValue();

                                        if( attr_name=="size" ){
                                            conf.Sub_Packaging_Size=Integer.parseInt(attr_value.trim());
                                            
                                        }
                                        if(attr_name=="mode-chiffrement"){
                                            conf.S_Chiffrement=attr_value.trim();
                                            
                                        }
                                    }
                                    System.out.println("("+conf.Sub_Packaging_Size+")["+conf.S_Chiffrement+"]");
                                }
                        break;
                           
                       
                         
                    }
                 
                }
             
             System.out.println ("===========333333333====================");
             //System.out.println ("["+conf.Buffer_Packaging_Methode.ToStringBase()+"]["+conf.Buffer_Sub_Packaging.ToStringBase()+"]");
             conf.Buffer_Packaging_Methode.Append(conf.Buffer_Sub_Packaging);
             System.out.println ("=========================================");
            }
             
             
         }  
         
        
        return  true;
    }
      boolean Parse_Sub_Packaging( Node  NodeBase , Configuration  conf)  
    {
         
         // On analyse la balise serialisation 
         if (NodeBase.getNodeType() == Node.ELEMENT_NODE) 
         {
             
           
             NodeList nodeList=NodeBase.getChildNodes();
             
             
             String a ="";
             
             for (int count = 0; count < nodeList.getLength(); count++) 
             {
                 Node tempNode = nodeList.item(count);
                 
                // make sure it's element node.
                if (tempNode.getNodeType() == Node.ELEMENT_NODE) 
                {
                    // get node name and value
                
                    String Node_Name =tempNode.getNodeName();
                    String Node_Value =tempNode.getTextContent().trim();
                    
                    
                    switch (Node_Name)
                    {
                        
                        case "pgtin" :
                            
                            conf.Pgtin = Node_Value;
                            a+= conf.Gtin.trim();
                            
                            if( tempNode.hasAttributes())
                                {
                                    NamedNodeMap nodeMap = tempNode.getAttributes();
                                    for (int i = 0; i < nodeMap.getLength(); i++) 
                                    {
                                        Node node = nodeMap.item(i);
                                        String attr_name =node.getNodeName();
                                        String attr_value =node.getNodeValue();

                                        if( attr_name=="size" )
                                    
                                           conf.Pgtin_Size=Integer.parseInt(attr_value.trim());
                                            a+="("+conf.Pgtin_Size+")";
                                            
                                            byte [] b1= Base64.getDecoder().decode(conf.Gtin.trim());
                                            conf.Buffer_Sub_Packaging.Append(b1, conf.Pgtin_Size);
                                           
                                    }
                                  
                                   
                                }
                            
                           
                       
                        break;
                           
                        case "pseed" :
                            conf.Pseed = Node_Value ;
                            a+=conf.Seed;
                            if( tempNode.hasAttributes())
                                {
                                    NamedNodeMap nodeMap = tempNode.getAttributes();
                                    for (int i = 0; i < nodeMap.getLength(); i++) 
                                    {
                                        Node node = nodeMap.item(i);
                                        String attr_name =node.getNodeName();
                                        String attr_value =node.getNodeValue();

                                        if( attr_name=="size" )
                                            conf.Pseed_Size=Integer.parseInt(attr_value.trim());
                                            a+="("+conf.Pseed_Size+")";
                                            
                                            JIBitArray tmp= new JIBitArray(conf.Pseed_Size);
                                            tmp.SetAll(false);
                                            tmp.Set(0,conf.Pseed_Size ,  conf.Seed);
                                            conf.Buffer_Sub_Packaging.Append(tmp);
                                            
                                    }
                                   
                                }
                        break;  
                            
                        case "pfab" :
                    
                        
                        conf.Pfab = Node_Value ;
                        a+=conf.Date_Fabrication;
                        if( tempNode.hasAttributes())
                                {
                                    NamedNodeMap nodeMap = tempNode.getAttributes();
                                    for (int i = 0; i < nodeMap.getLength(); i++) 
                                    {
                                        Node node = nodeMap.item(i);
                                        String attr_name =node.getNodeName();
                                        String attr_value =node.getNodeValue();

                                        if( attr_name=="size" )
                                            conf.Pfab_Size=Integer.parseInt(attr_value.trim());
                                            a+="("+conf.Pfab_Size+")";
                                            int fab = (int) conf.Date_Fabrication.getYear(); 
                                            fab= fab << 4; 
                                            fab += (int)conf.Date_Fabrication.getMonth(); 
                                            fab = fab << 5; 
                                            fab += (int)conf.Date_Fabrication.getDay();
                                            
                                            JIBitArray tmp1= new JIBitArray(conf.Pfab_Size);
                                                       tmp1.SetAll(false);
                                                       tmp1.Set(0, conf.Pfab_Size, fab);
                                                       conf.Buffer_Sub_Packaging.Append(tmp1);
                                    }
                                    
                                }
                    
                        break;
                        
                        case "pexp" :
                    
                        
                        conf.Pexp = Node_Value ;
                        a+=conf.Date_Experation;
                        if( tempNode.hasAttributes())
                                {
                                    NamedNodeMap nodeMap = tempNode.getAttributes();
                                    for (int i = 0; i < nodeMap.getLength(); i++) 
                                    {
                                        Node node = nodeMap.item(i);
                                        String attr_name =node.getNodeName();
                                        String attr_value =node.getNodeValue();

                                        if( attr_name=="size" )
                                    
                                           conf.Pexp_Size=Integer.parseInt(attr_value.trim());
                                           a+="("+conf.Pexp_Size+")";
                                            int exp = (int) conf.Date_Experation.getYear(); 
                                            exp = exp << 4; 
                                            exp += (int)conf.Date_Experation.getMonth(); 
                                            exp = exp << 5; 
                                            exp += (int)conf.Date_Experation.getDay();
                                            
                                            JIBitArray tmp2= new JIBitArray(conf.Pexp_Size);
                                                       tmp2.SetAll(false);
                                                       tmp2.Set(0, conf.Pexp_Size, exp);
                                                       conf.Buffer_Sub_Packaging.Append(tmp2);
                                    }
                                   
                                }
                    
                        break;
                        
                        case "pbatch" :
                    {
                       
                        conf.Pbatch = Node_Value;
                        a+=conf.Batch.trim();
                        if( tempNode.hasAttributes())
                                {
                                    NamedNodeMap nodeMap = tempNode.getAttributes();
                                    for (int i = 0; i < nodeMap.getLength(); i++) 
                                    {
                                        Node node = nodeMap.item(i);
                                        String attr_name =node.getNodeName();
                                        String attr_value =node.getNodeValue();

                                        if( attr_name=="size" )
                                            conf.Pbatch_Size=Integer.parseInt(attr_value.trim());
                                            a+="("+conf.Pbatch_Size+")";
                                    
                                        
                                   
                                }
                    }
                        break;
                           
                       
                         
                    }
                 
                }
            }
             System.out.println ("===========222==============================");
             System.out.println (a);
             System.out.println (conf.Buffer_Sub_Packaging.ToStringBase());
             System.out.println ("=========================================");
         }  
         
        
       
    
       
    }
         return  true;
    }
}
    

