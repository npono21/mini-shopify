var create_shop_modal = document.getElementById("create-shop-modal");
var create_shop_btn = document.getElementById("create-shop-button");

// Get the <span> element that closes the modal, this is the (x)
// We need to get the [0] because getElementsByClassName returns a collection of HTML element
var close_modal_span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal
create_shop_btn.onclick = function () {
  create_shop_modal.style.display = "block";
};

// When the user clicks on <span> (x), close the modal
close_modal_span.onclick = function () {
  create_shop_modal.style.display = "none";
};

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
  if (event.target == create_shop_modal) {
    create_shop_modal.style.display = "none";
  }
};
