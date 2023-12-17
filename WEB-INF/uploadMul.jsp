<div class="upload-container upload-page">
  <form
    class="upload-form"
    action="<c:url value='/uploadMul' />"
    method="post"
    enctype="multipart/form-data">
    <div class="file-label">
      <div
        class="upload-area"
        id="drop-area-mul"
        onclick="openFileExplorerMul()">
        <div class="upload-icon">&#8679;</div>
        <div class="upload-text">You can drag your file</div>
        <div class="or-text">or</div>
        <label for="fichiers" class="browse-files-mul">
          <input
            type="file"
            id="fichiers"
            name="fichiers"
            multiple
            onchange="handleFileMul(this.files)" />
          Browse
        </label>
      </div>
    </div>

    <div class="file-info" id="file-info-mul"></div>

    <button type="submit">Send</button>
    <span class="error">${formMul.erreurs['fichier']}</span>
    <p class="${empty formMul.erreurs ? 'success' : 'error'}">${formMul.resultat}</p>
  </form>
</div>
