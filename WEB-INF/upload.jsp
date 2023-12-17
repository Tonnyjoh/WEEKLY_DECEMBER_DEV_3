<div class="upload-container upload-page">
  <form
    class="upload-form"
    action="<c:url value='/upload' />"
    method="post"
    enctype="multipart/form-data">
    <div class="upload-area" id="drop-area" onclick="openFileExplorer()">
      <div class="upload-icon">&#8679;</div>
      <div class="upload-text">You can drag your file</div>
      <div class="or-text">or</div>
      <label class="browse-files">
        <input
          type="file"
          id="fichier"
          name="fichier"
          onchange="handleFile(this.files)" />
        Browse
      </label>
    </div>

    <div class="file-info" id="file-info"></div>

    <button type="submit">Send</button>
    <span class="error">${formUpload.erreurs['fichier']}</span>
    <p class="${empty formUpload.erreurs ? 'success' : 'error'}">${formUpload.resultat}</p>
  </form>
</div>
