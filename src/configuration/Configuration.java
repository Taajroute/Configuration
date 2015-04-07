/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asadi
 */
public class Configuration {

  int Id_Mode;
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
           "\n Batch "+Gtin+ "\tSize="+Batch_Size+
           "\n Hidden Message "+Hidden_Message+ "\tSize="+Hidden_Message_Size+
           "\n Code Barres Type "+Code_Barres_Type;
  }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Configuration  conf = new Configuration() ;
        System.out.println(conf);
        System.out.println("Fin de programme  ...... ");
        System.out.println("Fin de programme 4 ..... ");
    }
    
}
