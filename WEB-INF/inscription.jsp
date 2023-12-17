
<form method="post" action="inscription" id="form2">
	<div id="userInfo">

		<input type="text" id="nomClient" name="nomClient"
			value="<c:out value="${client.nom}"/>" size="30" maxlength="30"
			placeholder="Name" /> <span class="error">${formInscription.erreursIn['nomClient']}</span>
		<br /> <input required type="email" id="emailClient" name="emailClient"
			value="<c:out value="${client.email}"/>" size="30"
			placeholder="Email" maxlength="60" /> <span class="error">${formInscription.erreursIn['emailClient']}</span>
		<br /> <input required type="password" id="mdpClient" name="mdpClient"
			value="<c:out value="${client.mdpass}"/>" size="30" maxlength="60"
			placeholder="Password" /> <span class="error">${formInscription.erreursIn['mdpClient']}</span>
		<br />
		<p id="DoneBloc">
			<input type="submit" value="Done" name="ok">
		</p>
	</div>

</form>
