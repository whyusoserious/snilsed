$('#snils').keyup(function() {
	$('#submit').attr('disabled', $(this).val().length < 14? true : false);
});
