package models.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import models.dto.relationships.PedidoItensId;



@Entity(name = "pedido_itens")
@XmlRootElement(name = "itempedido")
@IdClass(PedidoItensId.class)
public class PedidoItens {
	@Id
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;
	
	@Id
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "produto_id")
	private Produto produto;
	private double quantidade;
	private double total;
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
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
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}
