/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package textmining;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;


public class IndexDoc {


	public HashMap index= new HashMap();
	
	public void getIndexDoc(String chemin, int NumDoc){
		try{
			FileReader fr ;
		    
			fr = new FileReader(chemin);
		 /*  le fichier est stocké sur br  */
		BufferedReader br=new BufferedReader(fr);
		
		String[] mots;
		String line = null;
		int freq=0;
		int id=0;
			
		/*  nous allons parcourir tout le document */
		while (id<NumDoc-1 ){ 
			line=br.readLine() ;
			id++;
			}
		    
			/* nous n'allons récuperer que les mots ici  */
			String[] titre=line.split("\t");
			//System.out.println(" "+id+ titre[0]);
			mots=line.split(" ");
	        mots[0]=mots[0].substring(mots[0].lastIndexOf("\t")+1);
	        String Acomp;
	        //System.out.println();
	        for (int j=0;j<mots.length;j++ )
	        {	
	        	
	        	
	        	freq=0;
	        	Acomp=mots[j];
	        	/*  nous calculons sa fréquence dans le document  */
	        	for(int k=0;k<mots.length ;k++)
	        	{ 
	        		if(Acomp.equals(mots[k])) freq++;
	        		
	        	}
	        	/*Index du document */
	        	 index.put(mots[j], freq);
	        	 	        	
	        }
	        
	       
					
		
		
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
}

