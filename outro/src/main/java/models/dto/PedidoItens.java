package models.dto;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.xml.bind.annotation.XmlRootElement;


@Entity(name = "pedido_itens")
@XmlRootElement(name = "itempedido")
public class PedidoItens {
	
	@Id
	@Embedded
	private PK PK = new PK();
	private double quantidade;
	private double total;

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public PK getPK() {
		return PK;
	}

	public void setPK(PK pK) {
		PK = pK;
	}
}
