window.addEventListener("load", function () {
  const goBackDiv = document.getElementById("go-back-div");
  goBackDiv.style.cursor = "pointer";
  goBackDiv.onclick = function () {
    window.history.back();
  };
});
