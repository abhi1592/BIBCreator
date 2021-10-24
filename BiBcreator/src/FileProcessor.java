/*
 * Submitted by- 
 * Abhishek Abhishek -1933848
 * And
 * Vikas Dhiman - 1930791
 */

import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;


public class FileProcessor
{  
		private static int InvalidCount = 0;
		public static void processFilesForValidation(Scanner sc, File ieee,File acm,File nj,int j) 
		{	
			int artCount = 1;
			String[] fieldName = new String[11]; //array of fields
			String[] fieldData = new String[11]; //array of data
			try
			{
				while(sc.hasNextLine()) 
				{
					sc.useDelimiter("},"); //getting }
					StringTokenizer info = new StringTokenizer(sc.next(), "={"); //tokenizing at the same time
					artCount++;
					
					if(info.hasMoreElements())
					{
						info.nextElement();
					}
					else
					{
						break;
					}
					if(info.hasMoreElements())
					{
						String t = info.nextToken().trim().replaceAll("(?m)^[ \t]*\r?\n", "");
						StringTokenizer f = new StringTokenizer(t,"\n");
						f.nextToken();
						fieldName[0] = f.nextToken();
					}
					else {
						break;
					}
					
					if(info.countTokens() == 1)//checking if data is there
					{
						fieldData[0] = info.nextToken();
					}
					else 
					{
						throw new FileInvalidException("Error: Detected Empty Field!\n========================="
								+ ":\nProblem detected with file: Latex"+j+".bib \n"
								+ "File is invalid: Field "+fieldName[0].trim()+" of article "+(artCount-1)+" is Empty. Processing stopped at this point."
								+ "Other empty fields may be present");
					}
					
					for(int i = 1; i<11; i++) //reading further
					{
						info = new StringTokenizer(sc.next(), "={");
						
						fieldName[i] = (String) info.nextElement();
						
						if(info.countTokens() == 1) //not empty
						{
							fieldData[i] = info.nextToken();
						}
						else
						{
							throw new FileInvalidException("Error: Detected Empty Field!\n========================="
									+ "\nProblem detected with file: Latex"+j+".bib \n"
									+ "File is invalid: Field "+fieldName[i].trim()+" of article "+(artCount-1)+" is Empty. Processing stopped at this point."
									+ "Other empty fields may be present");	
					    }
					}
					
					PrintWriter p = null; 
					//IEEE
					try 
					{
						p = new PrintWriter(new FileOutputStream(ieee,true));
					}
					catch (FileNotFoundException e) 
					{
						System.out.print("File not found.");
						System.exit(0);
					}
					
	
					String[] authors = null; 
					for(int i = 0; i <= 10; i++) //looping in all fields
					{
						if(fieldName[i].trim().equalsIgnoreCase("author"))//ignoring case sensitivity
						{
							authors = fieldData[i].split(" and"); //editing according to the format
						}
					}
					
					for (int i = 0; i<authors.length;i++)// in all names of authors for one article
					{
						p.print(authors[i]);
						
						if(i<authors.length-1) //not the end
						{
							p.print(",");
						}
						else //at the end
						{
							p.print(". ");
						}
				
					}
					//getting rest of the fields
					for(int i = 0; i <= 10; i++)
					{
						if(fieldName[i].trim().equalsIgnoreCase("title"))//getting the index
						{
							p.print("\""+fieldData[i]+"\", ");//getting data in data array with the index
						}
					}
					
					for(int i = 0; i <= 10; i++)
					{
						if(fieldName[i].trim().equalsIgnoreCase("journal"))
						{
							p.print(fieldData[i]+", ");
						}
					}
					
					for(int i = 0; i <= 10; i++)
					{
						if(fieldName[i].trim().equalsIgnoreCase("volume"))
						{
							p.print("vol. "+fieldData[i]+", ");
						}
					}
					
					for(int i = 0; i <= 10; i++)
					{
						if(fieldName[i].trim().equalsIgnoreCase("number"))
						{
							p.print("no.  "+fieldData[i]+", ");
						}
					}
					for(int i = 0; i <= 10; i++)
					{
						if(fieldName[i].trim().equalsIgnoreCase("pages"))
						{
							p.print("p.   "+fieldData[i]+", ");
						}
					}
					for(int i = 0; i <= 10; i++)
					{
						if(fieldName[i].trim().equalsIgnoreCase("month"))
						{
							p.print(fieldData[i]+" ");
						}
					}
					for(int i = 0; i <= 10; i++)
					{
						if(fieldName[i].trim().equalsIgnoreCase("year"))
						{
							p.println(fieldData[i]+".\n");
							p.close();
						}
					}
					
					//ACM
					try 
					{
						p = new PrintWriter(new FileOutputStream(acm, true));
					} 
					catch (FileNotFoundException e)
					{
						System.out.print("File not found");
						System.exit(0);
					}	
					
					for(int i = 0; i <= 10; i++)
					{
						if(fieldName[i].trim().equalsIgnoreCase("author")) {
							p.print("["+(artCount-1)+"]\t"+authors[i]+" et al. ");
						}
					}
					
					for(int i = 0; i <= 10; i++)
					{
						if(fieldName[i].trim().equalsIgnoreCase("year"))
						{
							p.print(fieldData[i]+". ");
						}
					}
					
					for(int i = 0; i <= 10; i++)
					{
						if(fieldName[i].trim().equalsIgnoreCase("title"))
						{
							p.print(fieldData[i]+". ");
						}
					}
					
					for(int i = 0; i <= 10; i++)
					{
						if(fieldName[i].trim().equalsIgnoreCase("journal"))
						{
							p.print(fieldData[i]+". ");
						}
					}
					
					for(int i = 0; i <= 10; i++)
					{
						if(fieldName[i].trim().equalsIgnoreCase("volume"))
						{
							p.print(fieldData[i]+",");
						}
					}
					
					for(int i=0; i<=10; i++)
					{
						if(fieldName[i].trim().equalsIgnoreCase("number"))
						{
							p.print(fieldData[i]+" ");
						}
					}
					
					for(int i=0; i<= 10; i++)
					{
						if(fieldName[i].trim().equalsIgnoreCase("year"))
						{
							p.print("("+fieldData[i]+"), ");
						}
					}
					
					for(int i= 0; i<= 10; i++)
					{
						if(fieldName[i].trim().equalsIgnoreCase("pages"))
						{
							p.print(fieldData[i]+". ");
						}
					}
					
					for(int i=0; i<= 10; i++)
					{
						if(fieldName[i].trim().equalsIgnoreCase("DOI"))
						{
							p.println("DOI:https://doi.org/"+fieldData[i]+".\n");
							p.close();
						}
					}
				
					
					//nj
					
					try 
					{
						p = new PrintWriter(new FileOutputStream(nj, true));
					}
					catch (FileNotFoundException e)
					{
						System.out.print("File not found");
						System.exit(0);
					}
					
					for (int i= 0; i<authors.length;i++)
					{
						p.print(authors[i]);
						if(i<authors.length-1) //not the end of array
						{
							p.print(" & ");
						}
						else //at the end of array
						{
							p.print(". ");
						}
					}
					
					for(int i=0; i<= 10; i++)
					{
						if(fieldName[i].trim().equalsIgnoreCase("title")) 
						{
							p.print(fieldData[i]+". ");
						}
					}
					for(int i = 0; i <= 10; i++)
					{
						if(fieldName[i].trim().equalsIgnoreCase("journal"))
						{	
							p.print(fieldData[i]+". ");
						}
				
					}
					
					for(int i = 0; i <= 10; i++)
					{
						if(fieldName[i].trim().equalsIgnoreCase("volume"))
						{
							p.print(fieldData[i]+", ");
						}
					}
					
					for(int i = 0; i <= 10; i++)
					{
						if(fieldName[i].trim().equalsIgnoreCase("pages"))
						{
							p.print(fieldData[i]);
						}
					}
				
					for(int i= 0; i<= 10; i++)
					{
						if(fieldName[i].trim().equalsIgnoreCase("year")) 
						{
							p.println("("+fieldData[i]+").\n");
							p.close();
						}
							
					}
				}
			}
			
			catch (FileInvalidException e) //case of any invalid file
			{
				System.out.println();
				System.out.println(e.getMessage());
				ieee.delete();
				nj.delete();
				acm.delete();
				InvalidCount++;//files found with empty fields
				
				System.out.println();
				
			}
		}
	
	public static void main(String[] args) 
	{
			System.out.println("Welcome to the bibCreator! \n");
		
				Scanner [] sc = new Scanner[10];//creating scanner for all files
				int i = 0;
				try 
				{
					for(i = 1; i <= 10; i++)
					{	
						sc[i-1] = new Scanner(new FileInputStream("Latex"+i+".bib")); 
					}
				}
				catch(FileNotFoundException e)
				{
					System.out.println("Could not open input file "+"Latex"+i+".bib"+"."									 
										+ "\n\nProgram will exit.");
					for(int j= 1; j< i;j++)
					{
						sc[j-1].close();	
					}
					System.exit(0);
				} 		
				
				
				File [] IEEEf = new File[10];
				File [] ACMf = new File[10];
				File [] NJf= new File[10];
				
				
				for(int j=0; j < 10; j++) 
				{
					IEEEf[j] = new File("IEEE"+(j+1)+".json");//naming with its number of latex file
					ACMf[j] = new File("ACM"+(j+1)+".json");
					NJf[j] = new File("NJ"+(j+1)+".json");	
				}
			
				PrintWriter [] p = new PrintWriter[10];
				
				
				for(int I=0; I<10; I++) 
				{		
					try 
					{
						p[I] = new PrintWriter(new FileOutputStream(IEEEf[I]));
						
						p[I].close();				
					}
					catch(FileNotFoundException e)
					{
						System.out.println(IEEEf[I]+"could not be opened");	
						for(int j=0; j<=I; j++) 
						{
							IEEEf[j].delete();
							ACMf[j].delete(); 
							NJf[j].delete(); 	
						}
						
						for(int j= 1; j<= 10;j++)
						{
							sc[j-1].close();	//closing sc
						}
						
						System.out.println("Program will exit.");
						System.exit(0);	
					}
				}
				
				for(int I=0; I<10; I++) 
				{
				
					try 
					{
						p[I] = new PrintWriter(new FileOutputStream(ACMf[I]));
						p[I].close();
					}
					catch(FileNotFoundException e)
					{
						System.out.println(ACMf[I]+"couldn't be opened");	
						for(int j=0; j<=I; j++) 
						{
							IEEEf[j].delete();
							ACMf[j].delete(); 
							NJf[j].delete(); 	
						}
						for(int j = 1; j <= 10;j++)
						{
							sc[j-1].close();	
						}
						System.out.println("Program will exit.");
						System.exit(0);	
					}
				}
			

				for(int I=0; I<10; I++) 
				{
				    try 
					{
						p[I]= new PrintWriter(new FileOutputStream(NJf[I]));
						p[I].close();
					}
					catch(FileNotFoundException e)
					{
						System.out.println(NJf[I]+"couldn't be opened");
						for(int j=0; j<=I; j++) 
						{
							IEEEf[j].delete();
							ACMf[j].delete(); 
							NJf[j].delete(); 	
						}
						for(int j = 1; j <= 10;j++)
						{
							sc[j-1].close();	
						}
						System.out.println("Program will exit.");
						System.exit(0);	
					}
				}
					for(int j= 0; j<=9; j++) 
					{
						processFilesForValidation(sc[j],IEEEf[j],ACMf[j],NJf[j],(j+1));
					
					}
					
					System.out.print("A total of "+InvalidCount+" files were invalid, and could not be processed. All other "+(10-InvalidCount)+
							" files have been created.\n\n"
							+ "Please enter the name of one of the files that you need to review: ");
					//opening created files
					Scanner SC = new Scanner(System.in);
					
					String input;
					input = SC.next();
					BufferedReader OpenFile = null;
					
					
					try 
					{
						OpenFile = new BufferedReader(new FileReader(input));
					}
					
					catch(FileNotFoundException e)
					{
						System.out.println("\nCould not open input file.File does not exist; possibly it could not be created"
								+ "\n\n However you will be allowed another cchance to enter another file name. ");
						input = SC.next();
						try 
						{
							OpenFile = new BufferedReader(new FileReader(input));
						}
						catch(FileNotFoundException e2)
						{
							System.out.println("\nCould not open input file again! Either file doesn't exist or could not be created."
									+ "\n Soory! I am unable to display your desired files! Program will exit!");
							System.exit(0);
						}
					}
					
					SC.close();
					
					System.out.println("\nHere are the contents of the successfully created json File"+input+"\n");
					
					String s = null;
					
					//reading a file
					try 
					{
						s= OpenFile.readLine();	
					}
					catch(IOException e)
					{
						e.getMessage();
					}
					
					
					while(s != null)
					{
						try 
						{
							System.out.println(s);
							s= OpenFile.readLine();					
						}
						catch(IOException e)
						{
							e.getMessage();
						}
					
					}
				
				System.out.print("\n\n======== ========= ========== =======\nGoodbye! Hope you have enjoyed creating the needed files using BibCreator!");		
				System.exit(0);
	}
}
