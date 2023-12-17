<div class="upload-container upload-page">
	<form class="upload-form" action="<c:url value='/compress' />"
		method="post" enctype="multipart/form-data">
		<div class="file-label">
			<div class="upload-area" id="drop-area-compress"
				onclick="openFileExplorerCompress()">
				<div class="upload-icon">&#8679;</div>
				<div class="upload-text">You can drag your file</div>
				<div class="or-text">or</div>
				<label for="fichier-compress" class="browse-files"> <input
					type="file" id="fichier-compress" name="fichier"
					onchange="handleFileCompress(this.files)" /> Browse
				</label>
			</div>
		</div>

		<div class="file-info" id="file-info-compress"></div>

		<button type="submit" class="sansLabel">Send</button>
		<span class="error">${formCompress.erreurs['fichier']}</span>
		<p class="${empty formCompress.erreurs ? 'success' : 'error'}">${formCompress.resultat}</p>
	</form>
</div>