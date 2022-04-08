package terzo_assegnamento;
import java.util.Random;

public class Utenti {
	private UtenteRegistrato utenteregistrato;
 
	public String getSaltString() { //Funzione per stringhe casuali
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        int randomnumber = rnd.nextInt(10 - 5 + 1) + 5;
        
        while (salt.length() < (randomnumber)) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
	
	
	public UtenteRegistrato registra() {

		return utenteregistrato;
		
	}
};
