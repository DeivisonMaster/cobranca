$('#idDlgConfirmaExclusao').on('show.bs.modal', function(event) {
	var btn = $(event.relatedTarget);
	var idTitulo = btn.data('idtitulo');
	var descricaoTitulo = btn.data('descricao');

	var objModal = $(this);
	var form = objModal.find('form');
	var action = form.data('urlbase');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + idTitulo);
	objModal.find('.modal-body span').html('Tem certeza que deseja excluir o titulo <strong>' + descricaoTitulo + '</strong>?');
});

$(document).ready(function (){
	habilitaTooltip();
	habilitaMaskMoney();
	atualizaStatus();
});

function atualizaStatus(){
	$('.js-atualizaStatus').on('click', function(event){
		event.preventDefault();
		
		var btnReceber = $(event.currentTarget);
		var urlRequest = btnReceber.attr('href');
		
		$.ajax({
			url: urlRequest,
			method: 'PUT',
			success: function(e){
				var idTitulo = btnReceber.data('idtitulo');
				$('[data-idstatus=' + idTitulo + ']').html('<span class="label label-success">' + e + '</span>');
				btnReceber.hide();
			},
			fail: function(e){
				alert('Erro');
			}
		});
	});
}

function habilitaTooltip(){
	$('[rel="tooltip"]').tooltip();
}

function habilitaMaskMoney(){
	$('.jsValor').maskMoney({
		decimal: ',',
		thousands: '.',
		allowZero: true
	});
}