<%@page contentType="text/html" pageEncoding="UTF-8"%>




<script type="text/javascript">

function inicializarPagina(){
	document.getElementById("btnSubmit").onclick = verificarYEnviar;
}

function verificarYEnviar(){
	
	var nombreUsuario = document.getElementById("txtNombre").value;
	if(nombreUsuario == ""){
		alert('Debe completar el nombre de usuario');
		return; 
	}
	if(nombreUsuario.length < 4){
		alert('El nombre de usuario debe tener al menos 4 caractéres');
		return; 
	}
	
	var password = document.getElementById("txtPass").value;
	if(password == ""){
		alert('La contraseña no puede estar vacía');
		return;
	}
	if(password.length < 6){
		alert('La contraseña debe tener al menos 6 caracteres');
		return;
	}
	var re1 = /[0-9]/;
	var re2 = /[a-z]/;
	var re3 = /[A-Z]/;
	if(!re1.test(password) || !re2.test(password) || !re3.test(password)) {
        alert('La contraseña debe tener al menos una letra mayúscula, una minúscula y un número');
        return false;
     }
	 
	var email = document.getElementById("txtMail").value;
	re1 = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
    if(!re1.test(email)){
		alert('Verique la dirección de mail');
		return;
	}
	
	document.forms["mainForm"].submit();
}	
</script>

<html>
<head>
<title>Nuevo Usuario</title>
</head>
<body onload="inicializarPagina();">
	<form name="mainForm" method="POST">
		<table>
			<tr>
				<td>Usuario:</td> <td><input type="text" maxlength="25" id="txtNombre" /> </td>
			</tr>
			<tr>
				<td>Contraseña:</td> <td><input type="password" maxlength="25" id="txtPass" /> </td>
			</tr>
			<tr>
				<td>Mail:</td> <td><input type="text" maxlength="60" id="txtMail" /> </td>
			</tr>
			<tr>
				<td> </td> <td align="right"> <input type="button" id="btnSubmit" value="Crear"/> </td>
			</tr>
		</table>
	</form>
</body>
















</html>