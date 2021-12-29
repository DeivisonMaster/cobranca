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
	$('[rel="tooltip"]').tooltip(); // habilitando tooltip
});