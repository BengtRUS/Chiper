package cipher;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

public class main {

	
	
	
	public static void main(String[] args) {
		String codedWay = "C:/Users/Kamurin/Desktop/mastercoded.txt";
		String decodedWay = "C:/Users/Kamurin/Desktop/master.txt";
		String newWay = "C:/Users/Kamurin/Desktop/masternew.txt";
		Map<Character, Integer> mas = new HashMap<Character,Integer>();
		Map<String, Integer> binmas = new HashMap<String,Integer>();
		Map<Character,Character> charmap = new HashMap<Character,Character>();
		int step=3;
		System.out.println("Analysing...");
		analyse(mas,decodedWay);
		analysebin(binmas,decodedWay);
		System.out.println("OK");
		System.out.println("Coding ...");
		code(step,codedWay,decodedWay);
		System.out.println("OK");
		System.out.println("Decoding...");
		createTable(charmap,mas,codedWay);
		modifyTable(charmap,decodedWay,codedWay);
		decode(charmap, codedWay,newWay);
		System.out.println("OK");
		int counter=0;
		for(Entry<Character, Character> i : charmap.entrySet()){
			System.out.println(i.getKey()+" "+i.getValue());
			if(i.getKey()==(char)(i.getValue()+step)) counter++ ;
		 }
		System.out.println(((double)counter/33.0)*100.0+"% correct");

	}
	
	static void createTable(Map<Character,Character> charmap, Map<Character, Integer> mas, String codedWay){
		//Map<Character,Character> charmap = new HashMap<Character,Character>();
		for(char j='а'; j<='я';j++) {
			  charmap.put(j, j);
			   
		   }
		Map<Character,Integer> codedMas = new HashMap<Character,Integer>();
		analyse(codedMas,codedWay);
		for(char j='а'; j<='я';j++)
		 {
		 int value = mas.get(j);
		 
		 System.out.println(j + ":" + value);
		 }
		Stack<Character> norm = new Stack<Character>();
		Stack<Character> coded = new Stack<Character>();
		createSortStack(mas, norm);
		createSortStack(codedMas, coded);
		while(!coded.isEmpty()){
			charmap.put(coded.pop(),norm.pop() );
		}
		
	}
	
	static void decode(Map<Character,Character> charmap, String codedWay, String newWay){
		try(FileWriter writer = new FileWriter(newWay, false))
        {	
		   FileInputStream fstream = new FileInputStream(codedWay);
		   BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		   String strLine;
		   writer.write("");
		   while ((strLine = br.readLine()) != null){
				 for(int i=0;i<strLine.length();i++) {
					   char letter = strLine.charAt(i);
					   if(letter>='А'&&letter<='Я'){
						   for(char j='А'; j<='Я'; j++) {
							   if (letter==j) {
								   writer.append((char) (charmap.get((char)(letter+32))-32));
							   }
						   }
					   } else if(letter>='а'&&letter<='я'){
						   writer.append((char) (charmap.get(letter)));
					   } else writer.append(letter);
					   
				 }
		   }
            
             
            writer.flush();
            br.close();
        }
        catch(IOException e){
             
            System.out.println("Error");
        }
	}
	
	
	static void code(int sdv, String codedWay, String decodedWay){
		try(FileWriter writer = new FileWriter(codedWay, false))
        {
			FileInputStream fstream = new FileInputStream(decodedWay);
			   BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			   String strLine;
			   writer.write("");
			while ((strLine = br.readLine()) != null){
				 for(int i=0;i<strLine.length();i++) {
					   char letter = strLine.charAt(i);
					   char newletter = (char)(letter+sdv);
					   if(letter>='А'&&letter<='Я'){
						   if(newletter<='Я') writer.append(newletter);
						   	else writer.append((char) ('А'+newletter-'Я'-1));
					   } else if(letter>='а'&&letter<='я'){
						   if(newletter<='я') writer.append(newletter);
						   	else writer.append((char) ('а'+newletter-'я'));
					   } else  {
						   writer.append(letter);
					   }
				   	}
	             writer.append('\n');
	            
			}
			writer.flush();
			br.close();
			}
        catch(IOException e){
             
            System.out.println("Error");
        } 
    }


	
	
	static void analyse(Map<Character,Integer> mas, String way) {
		try{
			   FileInputStream fstream = new FileInputStream(way);
			   BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			   String strLine;
			   char letter;
			   String s;
			   for(char j='а'; j<='я';j++) {
					  mas.put(j, 0);
					   
				   }
			   while ((strLine = br.readLine()) != null){
				   for(int i=0;i<strLine.length();i++) {
					   letter = strLine.charAt(i);
					   boolean ll = false;
					   for(char j='А'; j<='Я'; j++) {
						   if (letter==j) {
							   letter=(char)((int)j+32);
							   ll=true;
						   }
					   }
					  for(char j='а'; j<='я';j++) {
						  if (letter==j) ll=true;
						   
					   }
					  if (ll==false) continue;
						   mas.put(letter, mas.get(letter)+1);
				   	}
				   
			   }
			   br.close();
			}catch (IOException e){
			   System.out.println("Error");
			}
		
	}
	
	
	 static void createSortStack(Map<Character, Integer> mas, Stack<Character> s){
		 for(int k=0; k<=32; k++){
			 int min = Integer.MAX_VALUE;
			 char save = 0;
			 for(char j='а'; j<='я'; j++){
				 if(mas.get(j)<min) {
					 min=mas.get(j);
					 save=j;
				 } 
			 }
			 s.push(save);
			 mas.put(save, Integer.MAX_VALUE);
		 }
	 }
	 
	 
	 static void decodebin(Map<Character, Integer> mas, String codedWay, String newWay){
			Map<String,String> charmap = new HashMap<String,String>();
			for(char j='а'; j<='я';j++) {
				for(char k='a'; k<='я';k++){
					String key = Character.toString(j).concat(Character.toString(k));
					charmap.put(key,key);
				}
				  
				   
			   }
			
			
		}
	 static void analysebin(Map<String,Integer> mas, String way) {
			try{
				   FileInputStream fstream = new FileInputStream(way);
				   BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				   String strLine;
				   String s;
				   for(char j='а'; j<='я';j++) {
						for(char m='а'; m<='я';m++){
							mas.put(Character.toString(j)+(Character.toString(m)), 0);
						}
						  
						   
					   }
				   while ((strLine = br.readLine()) != null){
					   for(int i=0;i<strLine.length()-1;i++) {
						   char c1=strLine.charAt(i);
						   char c2=strLine.charAt(i+1);
						   for(char j='А'; j<='Я'; j++) {
							   if (c1==j) {
								   c1=(char)((int)j+32);
							   }
							   if (c2==j) {
								   c2=(char)((int)j+32);
							   }
						   }
						   s = Character.toString(c1).concat(Character.toString(c2));
						   for(char j='а'; j<='я';j++) {
								for(char k='a'; k<='я';k++){
									String key = Character.toString(j).concat(Character.toString(k));
									if(s.equals(key)){
										int t=0;
										try{
											t = mas.get(key);
										}
										catch(NullPointerException e){
											continue;
										}
										mas.put(key, t+1);
									}
									
								}
								  
								   
							   }
					   }
				   }
				   br.close();

				}catch (IOException e){
				   System.out.println("Error");
				}
			
		}
	 static void showmasbin(Map<String,Integer> mas){
		 for(Map.Entry<String, Integer> i : mas.entrySet()){
			 System.out.println(i.getKey()+" "+i.getValue());
		 }
			
	 }


	 static void createSortBinStack(Map<String, Integer> mas, Stack<String> s){
		 for(Map.Entry<String, Integer> k : mas.entrySet()){
			 int min = Integer.MAX_VALUE;
			 String save = "";
			 for(Map.Entry<String, Integer> j : mas.entrySet()){
				 if(j.getValue()<min) {
					 min=j.getValue();
					 save=j.getKey();
				 } 
			 }
			 s.push(save);
			 mas.put(save, Integer.MAX_VALUE);
		 }
	 }
	 
	 static void decodeBin(Map<String, Integer> mas, String codedWay, String newWay){
			Map<String,String> charmap = new HashMap<String,String>();
			for(char j='а'; j<='я';j++) {
				for(char m='а'; m<='я';m++){
					charmap.put(Character.toString(j)+(Character.toString(m)), Character.toString(j)+(Character.toString(m)));
				}   
			   }
			Map<String,Integer> codedMas = new HashMap<String,Integer>();
			analysebin(codedMas,codedWay);
			//////////////
			Stack<String> norm = new Stack<String>();
			Stack<String> coded = new Stack<String>();
			createSortBinStack(mas, norm);
			createSortBinStack(codedMas, coded);
			for(int i=0; i<5; i++){
				System.out.println(coded.pop()+" "+norm.pop());
			}
			while(!coded.isEmpty()){
				charmap.put(coded.pop(),norm.pop() );
			}
			
			
			//////////////////////
			/* try(FileWriter writer = new FileWriter(newWay, false))
		        {	
				   FileInputStream fstream = new FileInputStream(codedWay);
				   BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				   String strLine;
				   writer.write("");
				   while ((strLine = br.readLine()) != null){
						 for(int i=0;i<strLine.length()-1;i++) {
							   char letter1 = strLine.charAt(i);
							   char letter2 = strLine.charAt(i+1);
							   if((letter1<='я'&&letter1>='а')||(letter1>='А'&&letter1<='Я')||(letter2<='я'&&letter2>='а')||(letter2>='А'&&letter2<='Я')){
								   if(letter1>='А'&&letter1<='Я'){
									   for(char j='А'; j<='Я'; j++) {
										   if (letter1==j) {
											   letter1 = (char)(((int)letter1+32));
										   }
									   }
								 
								   
								   }
								   if(letter2>='А'&&letter2<='Я'){
									   for(char j='А'; j<='Я'; j++) {
										   if (letter2==j) {
											   letter2 = (char)(((int)letter2+32));
										   }
									   }
								 
								   
								   }
								   String s = Character.toString(letter1)+Character.toString(letter2);
								   for(Map.Entry<String, String> j : charmap.entrySet()){
									   if(s.equals(j.getKey())){
										   writer.write(j.getValue().charAt(0));
									   }
								   }
							   } else writer.write(letter1);
						 }
				   }
		            
		             
		            writer.flush();
		        }
		        catch(IOException e){
		             
		            System.out.println("Error");
		        } */
			
		}
	 
	 static void modifyTable(Map<Character,Character> charmap, String decodedWay, String codedWay){
		 Map<String, Integer> mas = new HashMap<String,Integer>();
		 analysebin(mas,decodedWay);
		 Map<String,String> bincharmap = new HashMap<String,String>();
			Map<String,Integer> codedMas = new HashMap<String,Integer>();
			analysebin(codedMas,codedWay);
			//////////////
			Stack<String> norm = new Stack<String>();
			Stack<String> coded = new Stack<String>();
			createSortBinStack(mas, norm);
			createSortBinStack(codedMas, coded);
			for(int i=0; i<5; i++){
				bincharmap.put(coded.pop(),norm.pop());
			}
			 for(Entry<String, String> i : bincharmap.entrySet()){
				 charmap.put((i.getValue().charAt(0)),i.getKey().charAt(0));
				 charmap.put((i.getValue().charAt(1)),i.getKey().charAt(1));
			 }
		 
	 }

}
