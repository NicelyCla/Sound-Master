package terzo_assegnamento;

import javax.persistence.*;

@Entity
@Table(name = "BRANO")

public class Brano {
	@Id
    @Column(name = "titolo")
	private String titolo;

    @Column(name = "categoria")
	private String categoria;
    
    @Column(name = "caricataDa")
	private String caricataDa;
    
    @Column(name = "portale")
	private String portale;
    
    @Column(name = "prezzo")
	private int prezzo;
	
	public Brano(String titolo, String categoria, String caricataDa, String portale) {
		this.setTitolo(titolo);
		this.setCategoria(categoria);
		this.setCaricataDa(caricataDa);
		this.setPortale(portale);
		this.setPrezzo(0);
		
	}


	public String getPortale() {
		return portale;
	}


	public void setPortale(String portale) {
		this.portale = portale;
	}


	public String getTitolo() {
		return titolo;
	}


	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public String getCaricataDa() {
		return caricataDa;
	}


	public void setCaricataDa(String caricataDa) {
		this.caricataDa = caricataDa;
	}


	public int getPrezzo() {
		return prezzo;
	}


	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}
	
};