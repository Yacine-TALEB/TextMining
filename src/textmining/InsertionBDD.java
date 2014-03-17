/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package textmining;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.swing.text.html.HTMLDocument.Iterator;

import static java.util.Arrays.asList;

import org.annolab.tt4j.*;
/**
 *
 * @author Alucard
 */
public class InsertionBDD {

    /**
     * @param args the command line arguments
     */
   
public static void main(String[] args){
		
      		Connection connexion;
		Statement st=null;
		ResultSet resultat=null;
			try {
                            /*Connexion à la base de données */
				//Class.forName("com.mysql.jdbc.Driver");
				connexion = DriverManager.getConnection("jdbc:mysql://localhost/TM","root","root");
				st = connexion.createStatement();
				System.out.println("Connexion réussie !");
						}
		catch (Exception e){
				System.out.println("Connexion à MySql écouchée !");
							}
			try {
				//resultat=st.executeQuery(" Select * From Document");
				
				try{
					
				//ArrayList motsVides;
				int i;
				//creation du flux
				FileReader fr=new FileReader("C:/Users/Alucard/docs/TextMining/TP/mini20-train.txt");
				BufferedReader br=new BufferedReader(fr);
				FileReader fr2=new FileReader("C:/Users/Alucard/docs/TextMining/TP/StopWords.txt");
				BufferedReader br2=new BufferedReader(fr2);
				try{
                                    /**Appel tree tagger
                                     * Voir la classe Lemmatiseur 
                                     */
					Lemmatiseur lem=new Lemmatiseur();
                                        ArrayList<String> lemme;
					String line;
					String[] mots;
                                        
					ArrayList<String> SWords =new ArrayList();
					//String[] mots2;
					String[] nomDocs;
                                        
                                        
                                        /*  Le champ ID doc a pour valeur initial 10000, et s'incremente de 10000
                                         */
					int ndoc=10000;
					int nmot=0;
                                        
                                        /*mots vides*/
					while ((line=br2.readLine()) != null){
					
						SWords.add(br2.readLine());
					}
						
						
						
						
					/*Lecture du fichier ligne par ligne */	
					while ((line=br.readLine()) != null){ 
						nmot=0;
                                                
						/*Récupération de chaque mot par ligne*/
				        mots=line.split(" ");
                                        
                                        /*Sert à récuperer la classe du document actuel */
				       nomDocs=line.split("\t");
					System.out.println(nomDocs);
                                        
                                        /*Petite astuce pour récuperer le premier mot 
                                         >> Voir description de la méthode *String.split()*
                                         */
                                        
				        mots[0]=mots[0].substring(mots[0].lastIndexOf("\t")+1);
                                        
                                        /*Execution de la méthode getLemme , voir classe Lemmatiseur
                                         */
                                        lemme=lem.getLemme(mots);
                                        /*Insertion dans la table DOCUMENT*/
				        st.executeUpdate("insert into document values('"+ndoc+"','"+nomDocs[0]+"')");
				        for( i=0; i< mots.length; i++){
				       	nmot ++;
				          // System.out.print(mots[i]);
				           int id=nmot+ndoc;
					    /*Insertion dans la table mot*/      
				           
                                           st.executeUpdate("insert into mot values('"+id+"','"+mots[i]+"','"+ndoc+"')");
                                           /*Insertion dans la table lemme
                                            */
                                          if(!lemme.isEmpty()) st.executeUpdate("insert into lemme values('"+lemme.get(i) +"','"+id+"')");
				            
				       							 }
									ndoc += 10000;	} 
						
					//lecture et affichage des données
					
				}
				finally{
					fr.close();
				}
                             }
			catch(IOException e){
			System.out.println(e);
			}
				
				
				
		} catch (/*SQL*/Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**/
	}

}