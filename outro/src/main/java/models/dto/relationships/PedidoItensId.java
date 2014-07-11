package models.dto.relationships;

import java.io.Serializable;

public class PedidoItensId implements Serializable {
	private long pedido;
	private long produto;

	public long getPeido() {
		return pedido;
	}

	public long getProduto() {
		return produto;
	}

	public void setPedido(int pedido) {
		this.pedido = pedido;
	}

	public void setProduto(int produto) {
		this.produto = produto;
	}

	@Override
	public int hashCode() {
		return ((int)pedido + (int)produto);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PedidoItensId) {
			PedidoItensId pedidoItemId = (PedidoItensId) obj;
			return pedidoItemId.pedido == produto && pedidoItemId.pedido == pedido;
		}

		return false;
	}
}
