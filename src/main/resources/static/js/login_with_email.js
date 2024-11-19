window.onload = function () {
  const emailLoginFormDiv = document.getElementById("email-login-div");
  const goBackIconDiv = document.getElementById("go-back-div");
  const emailLoginButton = document.querySelector(".login-button");
  const socialLoginDiv = document.querySelector(".social-login-div");

  if (!emailLoginFormDiv || !goBackIconDiv || !emailLoginButton || !socialLoginDiv) {
    console.error("One or more elements could not be found in the DOM.");
    return;
  }
  // Show email login form and hide other elements
  emailLoginButton.addEventListener("click", () => {
    const isHidden = getComputedStyle(emailLoginFormDiv).display === "none";

    if (isHidden) {
      // Show the form and hide other elements
      emailLoginFormDiv.style.display = "flex";
      if (emailLoginButton.length > 0) {
        emailLoginButton[0].style.display = "none";
        socialLoginDiv[0].style.display = "none";
        goBackIconDiv.style.display = "none";
      }
    } else {
      // Hide the form and show other elements
      emailLoginFormDiv.style.display = "none";
      if (emailLoginButton.length > 0) {
        emailLoginButton[0].style.display = "block";
        socialLoginDiv[0].style.display = "flex";
        goBackIconDiv.style.display = "block";
      }
    }
  });
};
