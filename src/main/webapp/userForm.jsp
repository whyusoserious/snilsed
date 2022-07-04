<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	crossorigin=anonymous">
<meta charset="UTF-8">
<title>Форма добавления СНИЛС</title>
</head>
<body>

	<div class = "container col-md-5 py-3">
		<div class ="card">
			<div class = "card-body">
				<form action="insert" method="post">
					<caption>
						<h2>
							Добавить СНИЛС
						</h2>
					</caption>
					
					<label>Введите номер СНИЛС:</label> 
					<input id ="snils" type="text" name="number" autocomplete="on"/ required ="required">
					<input id="submit" type ="submit" class="btn btn-success" disabled="true"></button>
				</form>
			</div>
		</div>
	</div>

</body>
<script
  src="https://code.jquery.com/jquery-3.6.0.min.js"
  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
  crossorigin="anonymous"></script>
<script src="https://unpkg.com/imask"></script>
<script src="validateSnils.js"></script>
<script src="main.js"></script>
</html>