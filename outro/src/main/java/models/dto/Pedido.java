package models.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "pedidos")
@XmlRootElement(name = "pedido")
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String nomesolicitante;
    
    private String cpfsolicitante;

   @Temporal(TemporalType.DATE)
    private Date datasolicitacao;
    
    private String endereco;
    
    private double valor;
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "pedido")
    private List<PedidoItens> produtos;
    


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomeSolicitante() {
		return nomesolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomesolicitante = nomeSolicitante;
	}

	public String getCpfSolicitante() {
		return cpfsolicitante;
	}

	public void setCpfSolicitante(String cpfSolicitante) {
		this.cpfsolicitante = cpfSolicitante;
	}

	public Date getDataSolicitacao() {
		return datasolicitacao;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		datasolicitacao = dataSolicitacao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public void setProdutos(List<PedidoItens> itens){
		this.produtos = itens;
		System.err.println(itens);
	}

   }
