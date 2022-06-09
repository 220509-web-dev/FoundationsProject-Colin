function register() {
    let error = document.getElementById("error");
    let header = document.getElementById("divMainHeader");

    let firstName = document.getElementById("firstName").value;
    let lastName = document.getElementById("lastName").value;
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let email = document.getElementById("email").value;

    if (username && password) {
        error.hidden = true;
        fetch('/test/auth/register', {
            method: 'POST',
            body: JSON.stringify({firstName,lastName,username,password,email})
        }).then(response=> response.json().then(data => {
            if (response.ok) {
                header.hidden = false;
                header.innerHTML = "Welcome " + data.firstName + " " + data.lastName;
            } else {
                error.hidden = false;
                error.innerHTML = "username already exists"   
            }
        }));
    } else {
        error.hidden = false;
        error.innerHTML = "username and password are required"
    }

}