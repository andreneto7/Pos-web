var listaProdutos = null;

function executarRequisicaoAjax(url, metodo, dados, tipoDados, sucesso, erro) {
	jQuery.ajax({
		url : url,
		type : metodo,
		data : dados,
		dataType : tipoDados,
		success : function(data) {
			sucesso(data);
		},
		error : function() {
			erro();
		}
	});

}

$(document).ready(
		function() {
			
			//$("#forms").load("forms/pedido.form");

			listarPedidos();
			obterListaProdutos();

			$("#incluir").hide();
			$("#visualizar").hide();
			$("#visualizacao-produtos").hide();
			$("#adicionarProduto").click(function() {
				$("#listagem").hide("fast");
				$("#incluir").show("fast");
			});

			$("#visualizar-pedido-produtos").click(function() {
				$("#visualizar-pedido").parent().removeClass("active");
				$(this).parent().addClass("active");
				$("#visualizacao-pedido").hide("fast");
				$("#visualizacao-produtos").show("fast");
			});

			$("#visualizar-pedido").click(
					function() {
						$("#visualizar-pedido-produtos").parent().removeClass(
								"active");
						$(this).parent().addClass("active");
						$("#visualizacao-pedido").show("fast");
						$("#visualizacao-produtos").hide("fast");
					});

			$("#adicionarItemPedido").click(
					function() {
						var linhaItem = $($("#produtos tbody tr")[0]).clone()
								.appendTo("#produtos");
						$(".lista-produtos").change(
								function() {
									var valor = $(
											$(this).find("option:selected")[0])
											.attr("data-preco");
									alert(valor);
									$(
											$(this).parent().parent().find(
													".valor-unitario")[0])
											.text(valor);

								});
					});
			
			$("#finalizarPedido").click(function(){
						salvarPedido();									 
			});
			
		});

function salvarPedido(){
	function fecharErro(){
		$("#mensagem").fadeOut();
	}
	
	function sucessoRequisicao(dados){
		var erro = dados.Exception;
		var mensagem = "Gravado com sucesso!";
		if (erro){
			mensagem = erro;
		}
		$("#mensagem").removeClass();
		
		$("#mensagem").text(mensagem);
		if (!erro){
			$("#mensagem").addClass("alert alert-success");
		}else{
			$("#mensagem").addClass("alert alert-danger");
		}
		$("#mensagem").show("slow");
		setTimeout(fecharErro, 6000); // 6 segundos
	}
	
	var dados =	$("#pedido-cadastro").find(".form-control").serialize();
	var itens = $("#produtos").find(".form-control").serialize();
	executarRequisicaoAjax('api/pedidos/', 'POST', dados + '&' + itens, 'json',
			sucessoRequisicao, function() {
				alert("Erro ao processar requisição.");
			});
}

function listarPedidos() {
	executarRequisicaoAjax('api/pedidos/', 'GET', null, 'json',
			mostrarListaPedidos, function() {
				alert("Erro ao processar requisição.");
			});

}

function obterListaProdutos() {
	executarRequisicaoAjax('api/produtos/', 'GET', null, 'json',
			listarProdutos, function() {
				alert("Erro ao processar requisição.");
			});

}

function obterPedidoPeloId(id) {
	executarRequisicaoAjax('api/pedidos/' + id, 'GET', null, 'json',
			obterDadosPedido, function() {
				alert("Erro ao processar requisição.");
			});
}

function obterDadosPedido(data) {

	var dados = data.Content;
	var idpedido = dados.id;
	$("#cpf-visualizar").val(dados.cpfsolicitante);
	$("#nome-visualizar").val(dados.nomesolicitante);
	$("#data-visualizar").val(dados.datasolicitacao);
	$("#endereco-visualizar").val(dados.endereco);

	$("#listagem").hide("fast");
	$("#visualizar").show("fast");
}

function mostrarListaPedidos(data) {
	var content = data.Content;
	for ( var x = 0; x < content.length; x++) {

		var record = content[x];

		var linha = "<tr class=\"data\"data>\"<td>"
				+ record.cpfsolicitante
				+ "</td>"
				+ "<td>"
				+ record.nomesolicitante
				+ "</td>"
				+ "<td>"
				+ record.datasolicitacao
				+ "</td>"
				+ "<td><button class=\"btn btn-danger btn-lg visualizar\" data-pedido=\""
				+ record.id
				+ "\"><span class=\"glyphicon glyphicon-search\"></span></button></td></tr>";
		jQuery("#pedidos tbody").append(linha);
	}

	$(".visualizar").click(function() {
		var idpedido = $(this).attr("data-pedido");
		obterPedidoPeloId(idpedido);
	});
}

function listarProdutos(data) {
	var content = data.Content;
	for ( var x = 0; x < content.length; x++) {
		var record = content[x];
		$($(".lista-produtos")[0]).append(
				"<option data-preco=\"" + record.valor + "\" value=\""
						+ record.id + "\">" + record.nome + "</option>");
	}
}