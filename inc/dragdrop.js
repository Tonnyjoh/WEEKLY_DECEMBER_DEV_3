document.addEventListener("DOMContentLoaded", function () {
  // Upload simple
  let fileInput = document.querySelector("#fichier");
  let uploadArea = document.querySelector("#drop-area");
  let fileInfo = document.querySelector("#file-info");

  fileInput.addEventListener("change", handleFileChange);

  function openFileExplorer() {
    fileInput.click();
  }

  function handleFileChange() {
    const files = fileInput.files;
    if (files.length > 0) {
      const fileName = files[0].name;
      fileInfo.textContent = `File selected : ${fileName}`;
    }
  }

  uploadArea.addEventListener("dragover", function (e) {
    e.preventDefault();
    uploadArea.classList.add("dragover");
  });

  uploadArea.addEventListener("dragleave", function () {
    uploadArea.classList.remove("dragover");
  });

  uploadArea.addEventListener("drop", function (e) {
    e.preventDefault();
    uploadArea.classList.remove("dragover");

    const files = e.dataTransfer.files;
    fileInput.files = files;
    handleFileChange();
  });

  uploadArea.addEventListener("dragenter", function (e) {
    e.preventDefault();
  });

  uploadArea.addEventListener("dragexit", function (e) {
    e.preventDefault();
  });

  // Upload multiple
  let fileInputMul = document.querySelector("#fichiers");
  let uploadAreaMul = document.querySelector("#drop-area-mul");
  let fileInfoMul = document.querySelector("#file-info-mul");

  fileInputMul.addEventListener("change", handleFileChangeMul);

  function openFileExplorerMul() {
    fileInputMul.click();
  }

  function handleFileChangeMul() {
    const files = fileInputMul.files;
    if (files.length > 0) {
      const fileNames = Array.from(files).map(file => file.name);
      fileInfoMul.textContent = `Files selected: ${fileNames.join(', ')}`;
    }
  }

  uploadAreaMul.addEventListener("dragover", function (e) {
    e.preventDefault();
    uploadAreaMul.classList.add("dragover");
  });

  uploadAreaMul.addEventListener("dragleave", function () {
    uploadAreaMul.classList.remove("dragover");
  });

  uploadAreaMul.addEventListener("drop", function (e) {
    e.preventDefault();
    uploadAreaMul.classList.remove("dragover");

    const files = e.dataTransfer.files;
    fileInputMul.files = files;
    handleFileChangeMul();
  });

  uploadAreaMul.addEventListener("dragenter", function (e) {
    e.preventDefault();
  });

  uploadAreaMul.addEventListener("dragexit", function (e) {
    e.preventDefault();
  });

  // Upload compress
  let fileInputCompress = document.querySelector("#fichier-compress");
  let uploadAreaCompress = document.querySelector("#drop-area-compress");
  let fileInfoCompress = document.querySelector("#file-info-compress");

  fileInputCompress.addEventListener("change", handleFileChangeCompress);

  function openFileExplorerCompress() {
    fileInputCompress.click();
  }

  function handleFileChangeCompress() {
    const files = fileInputCompress.files;
    if (files.length > 0) {
      const fileName = files[0].name;
      fileInfoCompress.textContent = `File selected : ${fileName}`;
    }
  }

  uploadAreaCompress.addEventListener("dragover", function (e) {
    e.preventDefault();
    uploadAreaCompress.classList.add("dragover");
  });

  uploadAreaCompress.addEventListener("dragleave", function () {
    uploadAreaCompress.classList.remove("dragover");
  });

  uploadAreaCompress.addEventListener("drop", function (e) {
    e.preventDefault();
    uploadAreaCompress.classList.remove("dragover");

    const files = e.dataTransfer.files;
    fileInputCompress.files = files;
    handleFileChangeCompress();
  });

  uploadAreaCompress.addEventListener("dragenter", function (e) {
    e.preventDefault();
  });

  uploadAreaCompress.addEventListener("dragexit", function (e) {
    e.preventDefault();
  });
});
