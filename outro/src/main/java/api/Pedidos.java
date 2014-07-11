package api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import models.PedidoDao;
import models.ProdutoDao;
import models.dto.Pedido;
import models.dto.PedidoItens;
import models.dto.Produto;
import jee.api.GenericRest;
import jee.dao.GenericModel;

import utils.Result;
import utils.transform.Render;


@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
public class Pedidos extends GenericRest<Pedido> {
	
    @EJB
    PedidoDao dao;
    @EJB
    ProdutoDao daoproduto;

    @Override
    public GenericModel getModel() {
        return dao;
    }

    @Override
    public Pedido getDtoToSave(MultivaluedMap<String, String> form) {
    	System.out.println(form.toString());
    	Pedido pedido = new Pedido();
        pedido.setCpfSolicitante(form.getFirst("pedido.cpfsolicitante"));
        pedido.setNomeSolicitante(form.getFirst("pedido.nomesolicitante"));
        pedido.setEndereco(form.getFirst("pedido.endereco"));
        pedido.setDataSolicitacao(new Date(Date.parse(form.getFirst("pedido.datasolicitacao"))));
        pedido.setValor(Double.parseDouble(form.getFirst("pedido.valor")));
        
        List<PedidoItens> itenspedido = new ArrayList<PedidoItens>();
        List<String> lista = form.get("item.produto");
        for (int x = 0; x < lista.size(); x++){
        	PedidoItens item = new PedidoItens();
        	System.out.println("Criando item...");
        	long codproduto = Long.parseLong(lista.get(x));
        	System.out.println("Obtendo o produto..");
        	double quantidade = Double.parseDouble(form.get("item.quantidade").get(x));
        	Produto produto = (Produto)daoproduto.findById(codproduto);
        	item.setProduto(produto);
        	item.setPedido(pedido);
        	item.setQuantidade(quantidade);
        	item.setTotal(quantidade * produto.getValor());
        	itenspedido.add(item);
        }
        pedido.setProdutos(itenspedido);
        return pedido;
    }
    
    @Override
    public void setDtoToSave(Pedido dto, MultivaluedMap<String, String> form) {
    	dto.setCpfSolicitante(form.getFirst("pedido.cpfsolicitante"));
    	dto.setNomeSolicitante(form.getFirst("pedido.nomesolicitante"));
    	dto.setEndereco(form.getFirst("pedido.endereco"));
    	dto.setDataSolicitacao(new Date(Date.parse(form.getFirst("pedido.datasolicitacao"))));
    	dto.setValor(Double.parseDouble(form.getFirst("pedido.valor")));
    }
    
    @Path("/{id}")
    @GET
    public Response findById(@PathParam("id") long id) {
    	
        Pedido obj = (Pedido) getModel().findById(id);
        if (obj == null) {
            return Response.ok(Render.JSON(Result.ERROR(3))).type("application/json").build();
        }
        return Response.ok(Render.JSON(Result.OK(obj))).type("application/json").build();
    }
    
    @Path("/{nome}")
    @GET
    public Response findByName(@PathParam("nome") String nome) {
    
            return Response.ok(Render.JSON(Result.ERROR(3))).type("application/json").build();
       
    }
    
}