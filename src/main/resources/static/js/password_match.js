function password_match() {
  const form = document.getElementById("registration-form");

  const password = document.getElementById("password").value;
  const confirmPassword = document.getElementById("confirm_password").value;

  if (password !== confirmPassword) {
    form.preventDefault();
    alert("Passwords do not match. Please try again.");
  }
}
