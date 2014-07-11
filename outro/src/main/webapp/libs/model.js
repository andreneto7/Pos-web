var pedidoModelo = Backbone.Model.extend({
    defaults: {
			"pedido.nomesolicitante":"",
			"pedido.cpfsolicitante":"",
			"pedido.datasolicitacao":"",
			"pedido.endereco":"",
			"pedido.valor":0
    }
});