
package terzo_assegnamento;

import terzo_assegnamento.UtenteRegistrato;


public class Amministrazione {
	//la classe amministrazione gestisce tutti gli abbonamenti
	
	public UtenteRegistrato utenteregistrato;
	
	
	public void rinnova_iscrizione(UtenteRegistrato utenteregistrato) {
		if (utenteregistrato.getPortafoglio()>=100) {
			utenteregistrato.setPortafoglio(utenteregistrato.getPortafoglio()-100);
			utenteregistrato.setAbbonamento(true);
			System.out.println("l'utente "+utenteregistrato.getNickname()+" ha effettuato l'abbonamento");

		}
		else
			System.out.println("l'utente "+utenteregistrato.getNickname()+" non ha abbastanza credito per rinnovare l'abbonamento, di conseguenza non può vendere");
	}
	public void scaduto_abbonamento(UtenteRegistrato utenteregistrato) {
		utenteregistrato.setAbbonamento(false);
		
	}
	
	

};
