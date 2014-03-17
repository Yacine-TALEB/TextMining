/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package textmining;
import java.util.*;
import java.io.*;
/**
 *
 * @author Alucard
 */
public class RepVect {
   
    
    /*Nous allons générer la représentaiton vectorielle pour un document donné 
     * Le paramètre ici est le numéro du doc
     * 
      */
    public static HashMap hm=new HashMap();
    
    public static void main (String[] args)
            
            
    {   HashMap h=new HashMap();
        try{
            /*
             * Nous chargeons l'index 
              */
          FileInputStream fis=new FileInputStream("indexOptim.ser");
          ObjectInputStream ois= new ObjectInputStream(fis);
           h=(HashMap)ois.readObject();  
           HashMap t=new HashMap();
           
           /*
            * Parcourir toute les clés de h ( càd tout l'index)
            */
          for (Object k : h.keySet())
          {
              t=(HashMap)h.get(k);
              Iterator i=t.keySet().iterator();   
           
              /*iterator sur l'ensemble des clés*/
              while(i.hasNext())
              {  Object p=i.next();
                 int tf=(Integer)t.get(p);
                 
                 /* Le nombre de documents est 1334
                  *   T.SIZE(), nous donne le nombre de docs ds lesquels le terme apparait !
                  */
                 int idf=(1334 / t.size() );
                 
                 double tfIdf=tf* Math.log(idf);
                 //Nouv HashMap pour stocker le tout 
                 HashMap temp=new HashMap();
                 
                 temp.put(k,tfIdf);
                 
                 if( ! hm.containsKey(p)) 
                 hm.put(p,temp.clone());
                 else 
                 {  //si cette clé est déjà contenue, on fusionne les contenus 
                     temp.putAll((HashMap)hm.get(p));
                     hm.put(p, temp.clone());
                 };
              }
          }
        File fichier = new File("RVectorielle.arff");
        fichier.createNewFile();
        FileWriter fw=new FileWriter(fichier);	
         fw.write("@relation RepresentationVectorielle");
         fw.write("\n");
        for(Object k :h.keySet())
        {   
        
        
        fw.write("@attribute "+ k +" numeric");
        fw.write("\n");
        
        }
        fw.write("@data");
        fw.write("\n");
        for(Object k: hm.keySet())
        {
            HashMap temp=new HashMap();
            temp=(HashMap)hm.get(k);
            
            for(Object kk:h.keySet())  
            {
                if(temp.containsKey(kk)) 
                fw.write(temp.get(kk) +",");
                else fw.write(0+",");
            }fw.write("\n");
        }
            
        
        fw.close();
        
    }
        catch(Exception e)
        {
        e.printStackTrace();
        }
   
    
    
    }
    
    
    
    
}
