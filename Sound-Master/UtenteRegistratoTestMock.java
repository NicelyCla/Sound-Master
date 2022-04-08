package terzo_assegnamento;

import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*; 
import static org.hamcrest.CoreMatchers.instanceOf;


class UtenteRegistratoTestMock {

	UtenteRegistrato uRegObj = new UtenteRegistrato();
	@Before
	public void create() {
		uRegObj = mock(UtenteRegistrato.class);
		when(uRegObj.registra()).thenReturn(uRegObj);
	}
	
	@Test
	void testRegistra() {
		assertThat(uRegObj,instanceOf(Utenti.class));//verifico che sia istanza della super classe Utenti
	}


}
