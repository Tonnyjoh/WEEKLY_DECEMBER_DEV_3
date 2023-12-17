
		<form id="form" method="post" action="connexion">
			<p id="cancel">X</p>
				<input required type="email" id="emailClient" name="emailClient" value="<c:out value="${client.email}"/>" placeholder="Email" size="30" maxlength="60" />
				<span class="error">${form.erreurs['emailClient']}</span>
				<br />
				<input required type="password" id="mdpClient" name="mdpClient" value="<c:out value="${client.mdpass}"/>" placeholder="Password"  size="30" maxlength="60" />
				<span class="error">${form.erreurs['mdpClient']}</span>
			<input type="submit" value="Login" /> 
			
		</form>
