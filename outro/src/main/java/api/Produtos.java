package api;

import static java.lang.Double.parseDouble;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import jee.api.GenericRest;
import jee.dao.GenericModel;
import models.ProdutoDao;
import models.dto.Pedido;
import models.dto.Produto;
import utils.Result;
import utils.transform.Render;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
public class Produtos extends GenericRest<Produto> {

    @EJB
    ProdutoDao dao;

    @Override
    public GenericModel getModel() {
        return dao;
    }

    @Override
    public Produto getDtoToSave(MultivaluedMap<String, String> form) {
        Produto c = new Produto();
        c.setNome(form.getFirst("produto.nome"));
        c.setDescricao(form.getFirst("produto.descrico"));
        c.setQuantidade(parseDouble(form.getFirst("produto.quantidade")));
        c.setValor(parseDouble(form.getFirst("produto.valor")));
        return c;
    }

    @Override
    public void setDtoToSave(Produto dto, MultivaluedMap<String, String> form) {
        dto.setNome(form.getFirst("produto.nome"));
        dto.setDescricao(form.getFirst("produto.descrico"));
        dto.setQuantidade(parseDouble(form.getFirst("produto.quantidade")));
        dto.setValor(parseDouble(form.getFirst("produto.valor")));
    }
    
    @Path("/{id}")
    @GET
    public Response findById(@PathParam("id") long id) {
    	
        Produto obj = (Produto) getModel().findById(id);
        if (obj == null) {
            return Response.ok(Render.JSON(Result.ERROR(3))).type("application/json").build();
        }
        return Response.ok(Render.JSON(Result.OK(obj))).type("application/json").build();
    }
    
}