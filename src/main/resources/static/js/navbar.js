console.log("navbar");
const userId = document.getElementById("user-info").dataset.userId;

document.getElementById("search-form").addEventListener("submit", function (event) {
  const value = document.getElementById("search-input").value.trim();
  const regex =  /^[a-zA-Z0-9\s]+$/;
  if (!regex.test(value)) {
    alert("Key word only have to contain number, space and text!");
    event.preventDefault();
    return;
  }
})

const historyIcon = document.getElementById("order-history-icon");
if (historyIcon) {
  historyIcon.addEventListener("click", function () {
    document.location.href = "/api/v1/order-history";
  });
}

const logOut = document.getElementById("logout-icon");
if (logOut) {
  logOut.addEventListener("click", function () {
    document.location.href = "/logout";
  });
}

const loginIcon = document.getElementById("login-icon");
if (loginIcon) {
  console.log("hihi");
  loginIcon.addEventListener("click", function () {
    document.location.href = "/login";
  });
}

const cart = document.getElementById("cart-icon");
if (cart) {
  cart.addEventListener("click", function () {
    let dropdownMenu = document.getElementById("dropdownMenu");
    productItem = document.getElementById("product-item");
    productItem.innerHTML = "";
    // get cartProductByUserId
    fetch(`http://192.168.52.196:8080/api/v1/cart/${userId}`)
      .then((response) => response.json())
      .then((res) => {
        productItem.innerHTML = "";
        if (!res || res.data.length === 0) {
          productItem.innerHTML = `<h5 style="text-align:center; color:gray; ">No products found</h5>`;
        } else {
          res.data.forEach((element) => {
            const cartItem = `
            <div class="cart-row" style="border-bottom: 1px solid #5f9ea0; display: flex; flex-direction: row; justify-content: space-between; align-items: center; padding: 8px 0">
              <div style="width: 300px; display: flex;flex-direction: row; justify-content: start;">
                <input type="checkbox" class="cart-checkbox" data-quantity="${element.quantity}" data-product-id="${element.productId}" data-price="${element.price}">
                <img onclick="goDetail(${element.productId})" style="cursor: pointer;width: 130px; height: 70px;object-fit: cover; margin: 0 15px;" src="${element.displayAvt}" alt="avt_product">
                <div style="display: flex;flex-direction: column;justify-content: space-between;">
                    <h6>${element.productName}</h6>
                    <span>${element.price.toLocaleString()}</span>
                </div>
              </div>
              <div style="display: flex;flex-direction: column; justify-content: space-between; height: 65px;">
                <input min="1" id="quan-ipnut" class="quantity-input" type="number" value =${element.quantity} style="height: 25px; width: 50px;">
                <button onclick="deleteProduct(${element.productId})" style="border-radius: 5px;border: none;font-size: 10px;background-color: #dc3545;padding: 3px;">Delete</button>
              </div>
            </div>
            <div id="stock" th:attr="data-stock=${element.numAvaiable}"></div>
            `;
            const wrapper = document.createElement("div");
            wrapper.innerHTML = cartItem;
            productItem.appendChild(wrapper);
          });
        }

        dropdownMenu.classList.toggle("show");
        attachEventsCheckbox();
      });
  });
}

function deleteProduct(productId) {
  fetch(`http://192.168.52.196:8080/api/v1/cart-product/delete/${productId}`, {
    method: "DELETE"
  }).then((response)=>response.json())
  .then((res)=>{
    alert(res.data);
    const dropdownMenu = document.getElementById("dropdownMenu");
    dropdownMenu.classList.remove("show");
  })
}

function goDetail(productId) {
  document.location.href = `/api/v1/detail/${productId}`
}

document.addEventListener("click", function (event) {
  const dropdownMenu = document.getElementById("dropdownMenu");
  const cartIcon = document.getElementById("cart-icon");

  // Nếu menu đang mở
  if (dropdownMenu.classList.contains("show")) {
    // Kiểm tra xem click có nằm trong dropdownMenu hoặc cart-icon không
    const isClickInsideMenu = dropdownMenu.contains(event.target);
    const isClickOnCartIcon = cartIcon.contains(event.target);

    // Nếu click ra ngoài cả 2 vùng trên thì ẩn menu
    if (!isClickInsideMenu && !isClickOnCartIcon) {
      dropdownMenu.classList.remove("show");
    }
  }
});

function attachEventsCheckbox() {
  const checkbox = document.querySelectorAll(".cart-checkbox");
  const quantityInput = document.querySelectorAll(".quantity-input");

  // every time you click call function again
  checkbox.forEach((cb) => {
    cb.addEventListener("change", caculator);
  });

  quantityInput.forEach((input) => {
    input.addEventListener("input", function () {
      const row = input.closest(".cart-row");
      const checkbox = row.querySelector(".cart-checkbox");
      checkbox.dataset.quantity = input.value;
      caculator(); // tính lại khi số lượng thay đổi
    });
  });
}

function caculator() {
  // debug
  let total = 0;
  const checkboxes = document.querySelectorAll(".cart-checkbox");
  checkboxes.forEach((cb) => {
    if (cb.checked) {
      const price = parseFloat(cb.dataset.price);
      const quantity = parseInt(cb.dataset.quantity);
      total += price * quantity;
    }
  });
  document.getElementById("total-price").innerText = total.toLocaleString();
}

// toConfirmOrder
const buyButton = document.getElementById("buy-button");
if (buyButton) {
  document.getElementById("buy-button").addEventListener("click", function (e) {
    e.preventDefault();
    const onlyNum = /^\d+$/;
    let quanInput = document.getElementById("quan-ipnut").value;
    if (!onlyNum.test(quanInput)) {
      alert("Quantity input only accept the number");
      return;
    }
    // check xem đã click vào ô nào chưa ?
    const selectedProducts = [];
    const checkboxes = document.querySelectorAll(".cart-checkbox");
    // const totalCost = parseInt(document.getElementById("total-price").innerHTML);
    checkboxes.forEach((cb) => {
      if (cb.checked) {
        // const row = cb.closest(".cart-row");
        const quantity = parseInt(cb.dataset.quantity);
        const productId = parseInt(cb.dataset.productId);
        selectedProducts.push({
          product_id: productId,
          quantity: quantity,
        });
      }
    });
    const stockNumber = document.getElementById("stock").dataset.stock;
    console.log(stockNumber);
    // return
    if (selectedProducts.length === 0) {
      alert("Please select least one product to pay!");
      return;
    }
    // else if(quantity >stockNumber){
    //     alert('Quantity exceeding inventory (' + stockNumber + ')');
    // }
    else {
      // redirect to order page with following item.
      fetch("http://192.168.52.196:8080/api/v1/order", {
        method: "POST",
        headers: { "Content-type": "application/json" },
        body: JSON.stringify(selectedProducts),
      })
        .then((res) => res.json())
        .then((response) => {
          if (response.status == 200) {
            window.location.href = "/api/v1/order/confirm";
          } else {
            alert(response.data);
          }
        });
    }
  });
}
