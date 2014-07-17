package models.dto;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
	public class PK implements Serializable {
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name="pedido_id") 
		private Pedido pedido;
		
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name="produto_id")
		private Produto produto;

		public Pedido getPedido() {
			return pedido;
		}

		public void setPedido(Pedido pedido) {
			this.pedido = pedido;
		}

		public Produto getProduto() {
			return produto;
		}

		public void setProduto(Produto produto) {
			this.produto = produto;
		}
	    
	    
	}
