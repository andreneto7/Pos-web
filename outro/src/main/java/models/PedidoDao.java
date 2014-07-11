/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jee.dao.GenericModel;
import models.dto.Pedido;

/**
 *
 * @author itakenami
 */
@Stateless
public class PedidoDao extends GenericModel<Pedido> {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected Class<Pedido> getEntityClass() {
        return Pedido.class;
    }
    
}
