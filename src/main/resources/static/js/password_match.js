function password_match(event) {
  // Prevent the default form submission
  event.preventDefault();

  // Get password values
  const password = document.getElementById("password").value;
  const confirmPassword = document.getElementById("confirm_password").value;

  // Check if passwords match
  if (password !== confirmPassword) {
    alert("Passwords do not match. Please try again.");
  } else {
    // Submit the form if passwords match
    document.getElementById("registration-form").submit();
  }
}
