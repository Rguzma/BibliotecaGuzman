<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Biblioteca Guzman Mesen</title>
</head>
<body>
<h1>Bienvenid@ a la Biblioteca Guzmán Mesén</h1>
<form method="POST" action="/validateUser">
			<fieldset>
				<legend>
					Login
				</legend>
				<div>
					<label for="user_o_email">Usuario o Email: </label>
					<input type="text" id="user_o_email" name="user_o_email" />
				</div>
				<div>
					<label for="userPassword"> Password: </label>
					<input type="password" id="userPassword" name="userPassword" />
				</div>
				<div>
					<button type="submit">
						Ingresar
					</button>
				</div>
				<div>
					<c:out value="${loginErrorMessage}"></c:out>
				</div>
			</fieldset>
		</form>
</body>
</html>