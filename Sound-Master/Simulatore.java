package terzo_assegnamento;
import java.util.*;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Simulatore {

	public static void main(String[] args) {

			//Hibernate
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory factory = cfg.buildSessionFactory();
			Session session = factory.openSession();
			//
			
			
			Random rnd = new Random();
			
		
			String portali[] = {"Spotify","ITunes","Google Music","Apple Music", "Cocco's Production"};//lista portali
			ArrayList<UtenteRegistrato> utentiregistrati = new ArrayList<UtenteRegistrato>();
			ArrayList<Brano> branicaricati = new ArrayList<Brano>();
			ArrayList<Brano> brani_di_questo_utente;//mi servirà per vedere nell'immediato quali brani ha inserito l'utente
			UtenteRegistrato temp = new UtenteRegistrato();
			Amministrazione rinnovo_abbonamenti = new Amministrazione();
			
			

			int controllore = 0,x = 0, prezzo=0,portale=-1, dado=0, y=0;
			int tempo = 0;//variabile utilizzata per tenere traccia del tempo e rinnovare gli abbonamenti
			String titolo, categoria;
			Scanner input = new Scanner (System.in);
			
			System.out.println("Benvenuto in SoundMaster");
			System.out.println("Digita 1 per registrare un Utente");
			System.out.println("Digita 2 per visualizzare la lista di Utenti Registrati");
			System.out.println("Digita 3 per simulare l'upload di una canzone da parte di un utente");
			System.out.println("Digita 4 per visualizzare i brani caricati");
			System.out.println("Digita 5 per simulare la messa in vendita di un brano da parte di un utente");
			System.out.println("Digita 6 per simulare la gestione del brano da parte di un utente");


			while (controllore >= 0) {
				
				if(tempo>2) {//2 iterazioni per convenzione corrispondono ad un anno passato
					if(!utentiregistrati.isEmpty()) {
						for(x=0;x<utentiregistrati.size();x++) {
							rinnovo_abbonamenti.scaduto_abbonamento(utentiregistrati.get(x));
							rinnovo_abbonamenti.rinnova_iscrizione(utentiregistrati.get(x));
							tempo = 0;
						}
					}
					tempo = 0;
					
				}
				System.out.print("input: ");
				controllore = input.nextInt();
				if(controllore == 1) {//viene generato un utente o una casa discografica, con tutti i campi casuali.
					x=0;
					utentiregistrati.add(temp.registra()); //Un nuovo utente si registra
					rinnovo_abbonamenti.rinnova_iscrizione(utentiregistrati.get(utentiregistrati.size()-1));
					/*Ho aggiunto questi controlli per completezza, naturalmente è molto improbabile che succeda*/
					for (x=0;x<utentiregistrati.size()-1;x++) { //scorre la lista e se trova nickname o email uguali, l'utente non viene inserito
						if(utentiregistrati.get(utentiregistrati.size()-1).getNickname()==utentiregistrati.get(x).getNickname()) {
							utentiregistrati.remove(utentiregistrati.size()-1);
							System.out.println("Non è stato possibile inserire L'utente. Username già registrato");
						}
						else if(utentiregistrati.get(utentiregistrati.size()-1).getNickname()==utentiregistrati.get(x).getNickname()) {
							utentiregistrati.remove(utentiregistrati.size()-1);
							System.out.println("Non è stato possibile inserire L'utente. indirizzo Email già registrato");
						}
								
					}
					session.beginTransaction();
					session.persist(utentiregistrati.get(utentiregistrati.size()-1)); //registra l'utente nel database
					session.getTransaction().commit();
					
					System.out.println("Utente registrato correttamente");
				}
				else if(controllore == 2) {
					x = 0;
					System.out.println("Lista Utenti Registrati:");
					for (x=0;x<utentiregistrati.size();x++) {
						System.out.println("- Nickname: "+utentiregistrati.get(x).getNickname());
						System.out.println("- Email: "+utentiregistrati.get(x).getEmail());
						System.out.println("- Numero: "+utentiregistrati.get(x).getTelefono());
						System.out.println("- Casa Discografica: "+utentiregistrati.get(x).getcasaDiscografica());
						System.out.println("- Anno registrazione: "+utentiregistrati.get(x).getAnno_iscrizione());
						System.out.println("- Portafoglio: "+utentiregistrati.get(x).getPortafoglio());
						
						System.out.println("");

					}

				}
				else if(controllore == 3) {
					System.out.println("-> Digita 1 per upload e vendita casuale (utile per test)");//gli utenti inseriscono un numero casuali di canzoni, salvo abilitazione e credito disponibile
					System.out.println("-> Digita 2 per upload e vendita manuale");
					System.out.print("->input: ");
					controllore = input.nextInt();
					
					if (controllore == 1) {
						dado = rnd.nextInt(10)+1;
						for(x = 0; x<dado;x++) {
							session.beginTransaction();
							branicaricati.add(utentiregistrati.get(rnd.nextInt(utentiregistrati.size()-1 - 0 + 1) + 0).inserisci_brano(temp.getSaltString(), temp.getSaltString(), portali[rnd.nextInt(portali.length)]));
							session.persist(branicaricati.get(branicaricati.size()-1)); //registra l'utente nel database
							session.getTransaction().commit();

						}
							//System.out.println("Sono stati caricati "+dado+" brani");
						for(y = 0; y<utentiregistrati.size();y++) {
							brani_di_questo_utente = utentiregistrati.get(y).brani_inseriti();
							
							if(!brani_di_questo_utente.isEmpty()) {
								System.out.print(utentiregistrati.get(y).getNickname()+" ha inserito ");	
								System.out.println(brani_di_questo_utente.size() + " brani");
								
							}
						}

						
					}
					
					else if (controllore == 2) {
						controllore = -1;
						while(controllore>utentiregistrati.size()-1 || controllore<0) {//scelgo l'utente fra quelli in lista
							System.out.print("-->Scegli un utente da 0 a ");
							System.out.println(utentiregistrati.size()-1+": ");
							controllore = input.nextInt();
							
						}
						input.nextLine();  // Consuma la newline derivata dal nextInt
						
						
						System.out.print("--> Titolo: ");
						titolo = input.nextLine();
						
						System.out.print("--> Categoria: ");
						categoria = input.nextLine();
						
						while(portale>portali.length-1 || portale<0) {//scelgo i portali
							for(x = 0; x<portali.length;x++) {
								System.out.print(x +"="+portali[x]+", ");
							}
							System.out.println("");
							System.out.print("--> Portale: ");
							
							while(portale<0 || portale>portali.length-1)
								portale = input.nextInt();

						}
						branicaricati.add(utentiregistrati.get(controllore).inserisci_brano(titolo, categoria, portali[portale]));
						session.beginTransaction();
						session.persist(branicaricati.get(branicaricati.size()-1)); //registra l'utente nel database
						session.getTransaction().commit();
					}
				}
				else if(controllore == 4) {
					System.out.println("Lista Brani Caricati:");
					for (x=0;x<branicaricati.size();x++) {
						System.out.println("- Titolo: "+branicaricati.get(x).getTitolo());
						System.out.println("- Categoria: "+branicaricati.get(x).getCategoria());
						System.out.println("- Portale: "+branicaricati.get(x).getPortale());
						System.out.println("- Caricata da: "+branicaricati.get(x).getCaricataDa());
						System.out.println("- Prezzo: "+branicaricati.get(x).getPrezzo());
						System.out.println("");

					}
				}
				
				else if(controllore == 5) {
					System.out.println("scegli l'utente");
					
					while(controllore>utentiregistrati.size()-1 || controllore<0) {//scelgo l'utente fra quelli in lista
						System.out.print("-->Scegli un utente da 0 a ");
						System.out.print(utentiregistrati.size()-1+": ");
						controllore = input.nextInt();
					}
					brani_di_questo_utente = utentiregistrati.get(controllore).brani_inseriti();
					
					if(!brani_di_questo_utente.isEmpty()) {
						System.out.println("I brani inseriti da questo utente:");
						for(x = 0; x<brani_di_questo_utente.size();x++) {
							System.out.println(brani_di_questo_utente.get(x).getTitolo());
						}
					}
					else{
						System.out.println("Questo Utente non ha inserito brani");
						continue;
					}
					
					input.nextLine();//consuma la newline del nextInt
					System.out.print("nome del brano da vendere: ");
					titolo = input.nextLine();
					for (x = 0; x<brani_di_questo_utente.size(); x++){
						if (titolo.equals(brani_di_questo_utente.get(x).getTitolo())) {
							System.out.print("stabilisci il prezzo: ");
							prezzo = input.nextInt();
							utentiregistrati.get(controllore).metti_in_vendita(brani_di_questo_utente.get(x), prezzo);
						}
					}
					
				}
				else if(controllore==6) {//simulo la gestione del brano da parte dell'utente
					System.out.println("scegli l'utente");
					
					while(controllore>utentiregistrati.size()-1 || controllore<0) {//scelgo l'utente fra quelli in lista
						System.out.print("-->Scegli un utente da 0 a ");
						System.out.print(utentiregistrati.size()-1+": ");
						controllore = input.nextInt();
					}
					brani_di_questo_utente = utentiregistrati.get(controllore).brani_inseriti();
					if(!brani_di_questo_utente.isEmpty()) {
						System.out.println("I brani inseriti da questo utente:");
						for(x = 0; x<brani_di_questo_utente.size();x++) {
							System.out.println(brani_di_questo_utente.get(x).getTitolo());
						}
					}
					else{
						System.out.println("Questo Utente non ha inserito brani");
						continue;
					}
					
					input.nextLine();
					System.out.print("nome del brano da modificare: ");
					titolo = input.nextLine();
					for (x = 0; x<brani_di_questo_utente.size(); x++){
						if (titolo.equals(brani_di_questo_utente.get(x).getTitolo())) {
							System.out.println("Lasciare i campi vuoti se non si desidera modificare l'attributo");
							System.out.print("cambiare nome: ");
							titolo=input.nextLine();
							System.out.print("cambiare categoria: ");
							categoria=input.nextLine();
							utentiregistrati.get(controllore).gestisci_brano(brani_di_questo_utente.get(x),titolo,categoria);
							System.out.println("Brano modificato correttamente");
						}
					}
					
				}
				tempo++;

			}
	}

}
