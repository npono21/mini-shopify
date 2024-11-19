window.onload = function () {
  const emailLoginFormDiv = document.getElementById("email-login-div");
  const goBackIconDiv = document.getElementById("go-back-div");
  const emailLoginButton = document.getElementsByClassName("login-button")[0];
  const socialLoginDiv = document.getElementsByClassName("social-login-div")[0];

  emailLoginButton.onclick = function () {
    const isHidden = getComputedStyle(emailLoginFormDiv).display === "none";

    if (isHidden) {
      // Show the form and hide other elements
      emailLoginFormDiv.style.display = "flex";
      emailLoginButton.style.display = "none";
      socialLoginDiv.style.display = "none";
      goBackIconDiv.style.display = "none";
    } else {
      // Hide the form and show other elements
      emailLoginFormDiv.style.display = "none";
      emailLoginButton.style.display = "block";
      socialLoginDiv.style.display = "flex";
      goBackIconDiv.style.display = "block";
    }
  };
};

// function toggleEmailForm() {
//   const emailLoginFormDiv = document.getElementById("email-login-div");
//   const goBackIconDiv = document.getElementById("go-back-div");
//   const emailLoginButton = document.getElementsByClassName("login-button");
//   const socialLoginDiv = document.getElementsByClassName("social-login-div");

//   const isHidden = getComputedStyle(emailLoginFormDiv).display === "none";

//   if (isHidden) {
//     // Show the form and hide other elements
//     emailLoginFormDiv.style.display = "flex";
//     if (emailLoginButton.length > 0) {
//       emailLoginButton[0].style.display = "none";
//       socialLoginDiv[0].style.display = "none";
//       goBackIconDiv.style.display = "none";
//     }
//   } else {
//     // Hide the form and show other elements
//     emailLoginFormDiv.style.display = "none";
//     if (emailLoginButton.length > 0) {
//       emailLoginButton[0].style.display = "block";
//       socialLoginDiv[0].style.display = "flex";
//       goBackIconDiv.style.display = "block";
//     }
//   }
// }
