function toggleProfileMenuDropdown() {
  const dropdownContent = document.querySelector(".dropdown-account-content");
  const newShopButton = document.querySelector("#create-shop-button");

  // Toggle dropdown visibility
  if (dropdownContent.style.display === "block") {
    dropdownContent.style.display = "none"; // Hide dropdown
    newShopButton.style.display = "block"; // Show "Create Shop" button
  } else {
    dropdownContent.style.display = "block"; // Show dropdown
    newShopButton.style.display = "none"; // Hide "Create Shop" button
  }
}

window.onclick = function (event) {
  const dropdownContent = document.querySelector(".dropdown-account-content");
  const dropdownButton = document.querySelector(".dropdown-account-button");
  const newShopButton = document.querySelector("#create-shop-button");

  // Close dropdown if the user clicks outside the button and dropdown
  if (
    !event.target.matches(".dropdown-account-button") &&
    !event.target.matches(".dropdown-account-content") &&
    !event.target.matches("#create-shop-button")
  ) {
    if (dropdownContent) {
      dropdownContent.style.display = "none"; // Hide dropdown
      newShopButton.style.display = "block"; // Show "Create Shop" button
    }
  }
};
