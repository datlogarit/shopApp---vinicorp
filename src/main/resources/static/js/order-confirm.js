console.log("order-confirm-log");
const btnBuyConfirm = document.getElementById("buy-confirm-button");

btnBuyConfirm.addEventListener("click", function () {
  const nameInput = document.getElementById("name-input").value.trim();
  const addressInput = document.getElementById("address-input").value.trim();
  const phonenumberInput = document
    .getElementById("phonenumber-input")
    .value.trim();
  console.log(nameInput);
  console.log(addressInput);
  console.log(phonenumberInput);
  const selected = document.querySelector('input[name="radioDefault"]:checked');
  const payMethod = selected ? selected.value : null;
  if (!payMethod) {
    alert("Please choose the pay mothod");
    return;
  } else if (
    nameInput === "" ||
    addressInput === "" ||
    phonenumberInput === ""
  ) {
    alert("Please fill out all required fields");
  } else {
    var listProduct = window && window.RAW_ITEMS ? window.RAW_ITEMS : [];
    var userId = window && window.USER_ID;
    console.log("[order-confirm] listProduct:", listProduct);
    fetch(`http://localhost:8080/api/v1/invoice`, {
      method: "POST",
      headers: { "Content-type": "application/json" },
      body: JSON.stringify({
        customer_id: userId,
        pay_method: payMethod,
        full_name: nameInput,
        address: addressInput,
        phone_number: phonenumberInput,
        list_product: listProduct,
      }),
    })
      .then((response) => response.json())
      .then((res) => {
        if (res.status == 200) {
          alert("order successfully");
          document.location.href = "/api/v1/order-history";
        } else {
          alert("have error occurred");
        }
      });
  }
});
