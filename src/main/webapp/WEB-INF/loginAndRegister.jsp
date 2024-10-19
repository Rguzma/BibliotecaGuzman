<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<form action="/user/create" method="POST"">
			<fieldset>
				<legend>
					Nuevo Usuario
				</legend>
				<p>
        			<label for="usuario">Usuario</label>
        			<input id="usuario" name="usuario"/>
    			</p>
    			<p>
       				 <label for="nombre">Nombre</label>
        				<errors path="nombre"/>
       				 <input id="nombre" name="nombre"/>
    			</p>
    			<p>
        				<label for="password">Password</label>
        				<input id="password" name="password"/>
    			</p>
    			<p>
        				<label for="email">Email</label>
     
        				<input id="email" name="email"/>
    			</p>   
    			<p>
       					<label for="numero">Número</label>    
        				<input id="numero" name="numero"/>
    			</p>
    			<p>
        				<label for="administrador">No es administrador</label>     
        				<input type="checkbox" id="administrador" name="administrador" path="administrador" value= 0 />
        		<dv>
        		</dv>
        				<label for="administrador">Sí es administrador</ label>    
        				<input type="checkbox" id="administrador" name="administrador" path="administrador" value=1 />
    			</p>
				    <button type="submit" value="Submit">Registrar</button>

			</fieldset>
		</form>
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