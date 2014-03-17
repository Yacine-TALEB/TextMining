/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package textmining;


import static java.util.Arrays.asList;
import java.util.*;
import java.io.*;
import java.sql.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.annolab.tt4j.*;

/**
 *
 * @author Alucard
 */
public class Lemmatiseur {
      public Lemmatiseur()
      {
      }
      
       public ArrayList<String> getLemme(String[] p){
           List <String> b = null;
          //  final int a= p.length;
        System.setProperty("treetagger.home", "C:/Users/Alucard/docs/TextMining/TP/TreeTagger");
        TreeTaggerWrapper tt = new TreeTaggerWrapper<String>();
        final ArrayList <String> listemots = new ArrayList();
        
        
        try {
            tt.setModel("C:/Users/Alucard/docs/TextMining/TP/TreeTagger/english.par:iso8859-1");
        } catch (IOException ex) {
            Logger.getLogger(Lemmatiseur.class.getName()).log(Level.SEVERE, null, ex);
        }
                tt.setHandler(new TokenHandler<String>() {
                        public void token(String token, String pos, String lemma) {
                        	try {  
                        		listemots.add(lemma);
                                       
                                        
                        	      }
                        	catch(Exception e){
                        		e.printStackTrace();
                        	}
                              
                                                              
                        }
                 });
                
                try {
                	b=Arrays.asList(p);
					tt.process(b);
										
							
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
         
        finally {
                tt.destroy();
        }
                
       
       return listemots;
       
       }
}