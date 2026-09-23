package pakotne;

import java.text.DecimalFormat;
import java.util.Scanner;

public class GalvenaKlase {
	
		
		public static Scanner scan = new Scanner(System.in);
		

		
		
		
		
		
		
	
		
	
		static int [] KriterijuSvars(String [] kriteriji, int [] kriterijaSvars) {
		int maxSvars = 100, sk = 1;
		double atlSvars;
		for(int i=0; i<kriteriji.length; i++) {
			do {
				System.out.println("Ievadi "+(i+1)+". kritēriju");
				kriteriji[i] = scan.nextLine().trim();
			} while(!kriteriji[i].matches("^[\\p{L} ]+$"));
			
			do {
				System.out.println("Ievadi "+(i+1)+". kritērija svaru (max: "+maxSvars+")");
				while(!scan.hasNextInt()) {
					System.out.println("Ievadi "+(i+1)+". kritērija svaru");
					scan.next();
				}
				kriterijaSvars[i] = scan.nextInt();
				/* Minimālā KATRA ATLIKUŠĀ kritērija svars ir 5
				 * kopējai svaru vērtībai ir jābūt 100 (ne mazāk, ne vairāk)
				*/
			
				atlSvars = (maxSvars - kriterijaSvars[i]) / (double)(kriteriji.length - sk);
			} while(kriterijaSvars[i]>maxSvars || kriterijaSvars[i]<5 || 
				  (i != kriteriji.length-1 && kriterijaSvars[i] == maxSvars) ||
				  (i == kriteriji.length-1 && (maxSvars - kriterijaSvars[i])  > 0) 
				  || atlSvars < 5);
			maxSvars -= kriterijaSvars[i];
			sk++;
			scan.nextLine();
		  }
		return kriterijaSvars;
		}
		static double [] KritVertejums(int[][] kriterijaVertejums,int[] kriterijaSvars, String [] studenti, String [] kriteriji, double [] semestraVertejums) {
			 double rezultats;
      		for(int i=0; i<studenti.length; i++) {
      			rezultats=0;
      			for(int j=0; j<kriteriji.length; j++) {
      				rezultats += ((double) kriterijaSvars[j]/100)*kriterijaVertejums[i][j];
      			}
      			semestraVertejums[i] = rezultats;
      		  }
      		return semestraVertejums;
		}
      		static int [][] Aprekini(int [][] kriterijaVertejums,String [] studenti, String [] kriteriji ) {
		// Norāda vērtējumu kādu ieguvis katrs audzēknis par katru kritēriju
		for(int i=0; i<kriterijaVertejums.length; i++) {
			for(int j=0; j<kriterijaVertejums[i].length; j++) {
				do {
					System.out.println("Ievadi "+studenti[i]+" vērtējumu par kritēriju "+kriteriji[j]);
					while(!scan.hasNextInt()) {
						System.out.println("Ievadi "+studenti[i]+" vērtējumu par kritēriju "+kriteriji[j]);
						scan.next();
						
					}
					kriterijaVertejums[i][j] = scan.nextInt();
				}while(kriterijaVertejums[i][j]<0 || kriterijaVertejums[i][j]>10);
			}
			
		  }
		return kriterijaVertejums;
		}
		
		static void GalVertejumsIzvad(String [] studenti, String [] kriteriji, double [] semestraVertejums, int [][] kriterijaVertejums, int [] kriterijaSvars) {
		DecimalFormat df = new DecimalFormat("0.#");
		// Gala vērtējumu izvadīšana
		for(int i=0; i<studenti.length; i++) {	
			for(int j=0; j<kriteriji.length; j++) {
				System.out.println("Studenta "+studenti[i]+" vērtējums par kritēriju "+kriteriji[j]+" ir "+kriterijaVertejums[i][j]+", kura svars ir "+kriterijaSvars[j]);
			}
			System.out.println("Semestra vērtējums ir "+df.format(semestraVertejums[i])+" balles"
					+ "\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
		}
		scan.close();
		}
		
		public static void main(String[] args) {
		
			Scanner scan = new Scanner(System.in);
	char izvele;
	int kritSk;
	int studSk;
	do {
		System.out.println("Cik studentiem aprēķināsi gala vērtējumu?");
		while(!scan.hasNextInt()) {
			System.out.println("Cik studentiem aprēķināsi gala vērtējumu?");
			scan.next();
		}
		studSk = scan.nextInt();
	}while(studSk<1);
	String[] studenti = new String[studSk];
	
	
		
		do {
			System.out.println("Kāds būs kritēriju skaits?");
			while(!scan.hasNextInt()) {
				System.out.println("Kāds būs kritēriju skaits?");
				scan.next();
			}
			kritSk = scan.nextInt();
		}while(kritSk<1);	
		scan.nextLine();
		String[] kriteriji = new String[kritSk];
		int[] kriterijaSvars = new int[kritSk];
		int[][] kriterijaVertejums = new int[studSk][kritSk];
		double[] semestraVertejums = new double[studSk];
		
		for(int i=0; i<studenti.length; i++) {
			do {
				System.out.println("Ievadi "+(i+1)+". studentu");
				studenti[i] = scan.nextLine().trim();
			} while(!studenti[i].matches("^[\\p{L} ]+$"));
		}
		     do {
		         System.out.println(
		                	           
		                 "1-Pievienot Kritēriju svaru\n" +
		                 "2-Pievienot Kriterija vērtējumu\n" +
		                 "3-Aprēkināt skolēna gala vērtējumus\n"+
		                 "4-Parādīt skolēna gala vērtējumus\n" +
		                 "x-Iziet");
		         
		         izvele = scan.next().charAt(0);
		         izvele = Character.toLowerCase(izvele);

		         switch (izvele) {
		                           
		             case '1':
		            	 KriterijuSvars(kriteriji, kriterijaSvars);	          
		                 break;
		             case '2':
		            	 
		            	 KritVertejums(kriterijaVertejums,kriterijaSvars, studenti,kriteriji, semestraVertejums);
		                 break;
		             case'3':
		            Aprekini(kriterijaVertejums, studenti, kriteriji);
		            	 break;
		            	 
		             case'4':
		            	 GalVertejumsIzvad(studenti, kriteriji, semestraVertejums, kriterijaVertejums, kriterijaSvars);
		            	 break;
		            	
		             case 'x':
		            	
		            	 System.out.println("Kalkulators aizverts!");
		                 break;
		             default:
		 				System.out.println("Drabība nepastāv!");
		         }

		     }while(izvele != 'x');
		}
	}