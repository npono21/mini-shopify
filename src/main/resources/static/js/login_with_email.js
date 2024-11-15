function toggleEmailForm() {
  const emailLoginFormDiv = document.getElementById("email-login-div");
  const goBackIconDiv = document.getElementById("go-back-div");
  const emailLoginButton = document.getElementsByClassName("login-button");
  const socialLoginDiv = document.getElementsByClassName("social-login-div");

  if (
    emailLoginFormDiv.style.display === "none" ||
    emailLoginFormDiv.style.display === ""
  ) {
    // Show the form and hide the button
    emailLoginFormDiv.style.display = "flex";
    if (emailLoginButton.length > 0) {
      emailLoginButton[0].style.display = "none";
      socialLoginDiv[0].style.display = "none";
      goBackIconDiv.style.display = "none";
    }
  } else {
    // Hide the form and show the button
    emailLoginFormDiv.style.display = "none";
    if (emailLoginButton.length > 0) {
      emailLoginButton[0].style.display = "inline";
    }
  }
}
