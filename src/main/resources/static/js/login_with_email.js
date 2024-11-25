window.onload = function () {
  const emailLoginFormDiv = document.getElementById("email-login-div");
  const loginDivCollection =
    document.getElementsByClassName("login-button-div");
  const loginDiv = loginDivCollection[0];
  const emailLoginButton = document.querySelector(".login-button");

  if (!emailLoginFormDiv || !loginDiv || !emailLoginButton) {
    console.error("One or more elements could not be found in the DOM.");
    return;
  }

  // Show email login form and hide other elements on button click
  emailLoginButton.addEventListener("click", () => {
    const isHidden = getComputedStyle(emailLoginFormDiv).display === "none";

    console.log(isHidden);

    if (isHidden) {
      // Show the form and hide other elements
      emailLoginFormDiv.style.display = "flex";
      emailLoginButton.style.display = "none";
      loginDiv.style.display = "none";
    } else {
      // Hide the form and show other elements
      emailLoginFormDiv.style.display = "none";
      emailLoginButton.style.display = "block";
      loginDiv.style.display = "flex";
    }
  });
};
