/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package textmining;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class Indexation implements Serializable {
	public Map<String, HashMap> map ;

	public HashMap index= new HashMap();
	
	public void getIndex(String chemin){
		try{
			FileReader fr ;
		
			fr = new FileReader(chemin);
		 /*  le fichier est stocké sur br  */
		BufferedReader br=new BufferedReader(fr);
		
		String[] mots;
		String line;
		int freq=0;
		int id=0;
		/* cet hashmap sera utilisé pr stocker les fréq de chaque mot*/
		HashMap DocEnCours=new HashMap();
		/* tmp sera utilisé pour fusionner les 2 hashmaps index + docencours */
		HashMap tmp= new HashMap();
		/*  nous allons parcourir tout le document */
		while ((line=br.readLine()) != null){ 
			
			id++;
			/* nous n'allons récuperer que les mots ici  */
			//String[] titre=line.split("\t");
			//System.out.println(" "+id+ titre[0]);
			mots=line.split(" ");
	        mots[0]=mots[0].substring(mots[0].lastIndexOf("\t")+1);
	        String Acomp;
	        //System.out.println();
	        for (int j=0;j<mots.length;j++ )
	        {	
	        	
	        	
	        	/* pour chaque mot, de chaque document faire ...*/
	        	DocEnCours.clear();
	        	freq=0;
	        	Acomp=mots[j];
	        	/*  nous calculons sa fréquence dans le document actuel ici */
	        	for(int k=0;k<mots.length ;k++)
	        	{ 
	        		if(Acomp.equals(mots[k])) freq++;
	        		
	        	}
	        	
	        	 //if (tmp ==null) 
	        	     DocEnCours.put(id,freq );
	        	     
	        	           	     
	        	 //else 
	        		//if( (Integer) tmp.get(id)> freq)
	        	  //	DocEnCours.put(id,freq );
	        		
	        		
	        	if(! this.index.containsKey(mots[j]))
	        	{
	        		
	        	this.index.put(mots[j],DocEnCours.clone());
	        	}
	        	 		        	
	      	else{
	      		/*Si le mot existe déjà dans la table de Hashage
	      		 * on fusionne les tables correspodant à ce mot*/
	      		tmp.clear();
		       
		        	tmp=(HashMap) this.index.get(mots[j]);
		        	
		        	DocEnCours.putAll(tmp) ;
	        				        		
	        	this.index.put(mots[j], DocEnCours.clone());
	        	}
	        }
	        
	       
					
		
		}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*NB
		 * ici nous faisons une conversion vers une TreeMap
		 * pour obtenir un index ordonné alphabetiquement ! s
		 * pour des raisons de performance les HahsMap ne font pas de tri */
		
		
		map = new TreeMap<String, HashMap>(index);
		//Object []ar=  map.keySet().toArray();
	             
      try{     
    FileOutputStream fos = new FileOutputStream("index.ser");
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    oos.writeObject(this.index);
    oos.close();
      }
      catch(Exception e){
          e.printStackTrace();
      }
}
	
	
}

