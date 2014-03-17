/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package textmining;

import java.io.*;
import java.util.*;

/**
 *
 * @author Alucard
 */

/**
 *  Cet autre classe d'indexation va servir
 *  à construire un index dénué de mots vides
 *  et contenant uniquement des lemmes 
 * 
 * Pour ce faire, on récupère l'index serialisé
 * Et on le vide des StopWords, puis on fait 
 * Passer les termes au Tokenizer 
  */



public class IndexOptim {
    
    public static HashMap index;
    public static void main(String[] args)
    {   index=new HashMap();
         
        try{
        FileInputStream fis=new FileInputStream("index.ser");
        ObjectInputStream ois= new ObjectInputStream(fis);
        
        HashMap h=new HashMap();
        h=(HashMap)ois.readObject();
        FileReader fr;
        
        /* Récuperation des stop words
         */
        fr=new FileReader("C:/Users/Alucard/docs/TextMining/TP/StopWords.txt");
	BufferedReader br=new BufferedReader(fr);
        
        ArrayList<String> SWords =new ArrayList();
        ArrayList<String> Lemmes =new ArrayList();
        
        /*
         *Récuperation des stop words dans SWords
         */
        while (br.readLine() != null){
				SWords.add(br.readLine());
					}
        
       // ArrayList removeList=new ArrayList();
        
       System.out.println(h.keySet().size());
       
        /* Eliminer l'entrée de l'index si c'est un mot vide 
         */
       // System.out.println("size of KEYSET" + " "+ h.keySet().size());
        for(int i=0;i<SWords.size();i++)
                                {   
                                    String p=SWords.get(i);
                                    
                                  
                         if(h.containsKey(p)) h.remove(p);
                         
                                         
                                }     
        //System.out.println("size of KEYSET After Elimination" + " "+ h.keySet().size());
                                     
         String[] lemma = new String[h.keySet().size()];
        
        /* Recuperation des nouvelles clés
         * pour procéder à la lemmatisation
         */
        int k=0;
        for(Object key : h.keySet())
        {    
            lemma[k]=(String)key; 
            k++; 
            
        }
        //System.out.println("size of Lemma" + " "+ k);
        
       
        /*
         * Appel treeTagger 
                  */
        Lemmatiseur l=new Lemmatiseur();
        Lemmes=l.getLemme(lemma);
       // System.out.println("size of LemmES" + " "+ Lemmes.size());
        
        k=0;
        
        h.remove("");
        
        
        
        for(Object key : h.keySet())
        {    
            String s=(String)key;
            if(s.length()>3)
            index.put(Lemmes.get(k), (HashMap)h.get(key));
             k++;
             
                                  
        }
        System.out.println(index.size());
        /* Ecriture du nouvel index dans "IndexOptim.ser"
         */
        FileOutputStream fos= new FileOutputStream("indexOptim.ser");
        ObjectOutputStream oos= new ObjectOutputStream(fos);
        oos.writeObject(index);
        oos.close();
        }
        catch(Exception e)
        {e.printStackTrace();
        }
    }
    
    
    
    
    
    
}
