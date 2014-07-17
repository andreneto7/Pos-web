package models.dto;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;


@Entity(name = "produtos")
@XmlRootElement(name = "produto")
public class Produto{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String nome;
    
    private String descricao;

    private double valor;
    
    @OneToMany(mappedBy="PK.produto", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<PedidoItens> pedidos;
    


    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
    
    private double quantidade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

	public List<PedidoItens> getPedido() {
		return pedidos;
	}

	public void setPedido(List<PedidoItens> pedido) {
		this.pedidos = pedido;
	}
}
