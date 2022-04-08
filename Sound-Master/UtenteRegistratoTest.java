package terzo_assegnamento;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class UtenteRegistratoTest {

	@Test
	void testRegistra() {
	    
	   UtenteRegistrato utenteregistrato = new UtenteRegistrato();
	   utenteregistrato.registra();
	   assertEquals(true, utenteregistrato instanceof UtenteRegistrato);
	}

	
	@Test
	void testInserisci_brano() {
		Brano brano = new Brano(null, null, null, null);
		UtenteRegistrato utenteregistrato = new UtenteRegistrato();
		brano = utenteregistrato.inserisci_brano(null, null, null);
		assertEquals(true, brano instanceof Brano);
	}

}
