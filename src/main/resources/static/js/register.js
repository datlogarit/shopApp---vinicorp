document.getElementById("form-register").addEventListener("submit", function (e) {
    e.preventDefault();
    //validation
    let phone = document.getElementById("phone").value.trim();
    let password = document.getElementById("password").value.trim();
    let retypePassword = document.getElementById("retype-password").value.trim();
    let fullName = document.getElementById("fullname").value.trim();
    let address = document.getElementById("address").value.trim();
    if (password == "" || retypePassword == "" || fullName == "" || address == "") {
        alert("Please fill in requred fields");
        return;
    }
    const regex = /^\+?[0-9]\d{1,14}$/;
    if (phone == null || phone == "" || !regex.test(phone)) {
        alert("Please enter a valid phone number: only contain character '+'' or number, length from 1 to 14 char");
        e.preventDefault();
        return;
    }
    if (password != retypePassword) {
        alert("Password and retype password not match");
        return;
    }
    console.log("fetching");
    fetch(`http://192.168.52.196:8080/api/v1/user/signUp`,{
        method:"POST",
        headers:{"Content-type":"application/json"},
        body:JSON.stringify({
            full_name: fullName,
            phone_number:phone,
            password:password,
            address:address
        })
    }).then((response)=>response.json())
    .then((res)=>{
        alert(res.data);
        if (res.message == "success") {
            document.location.href = "/login";
        }
        document.getElementById("form-register").reset();
    })
})