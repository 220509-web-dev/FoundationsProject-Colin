function login() {
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let error = document.getElementById("error");

    fetch('/test/auth/login', {
        method: 'POST',
        body: JSON.stringify({username,password})
    }).then(response=> response.json().then(data => {
        console.log(response.ok);
        if (response.ok) {
            let user = data;
            let header = document.getElementById("divMainHeader");
            error.hidden = true;
            header.hidden = false;
            header.innerHTML = "Welcome " + user.firstName + " " + user.lastName;
            //console.log(user);
        } else {
            error.hidden = false;
            error.innerHTML = "invalid username + password";
        }
    })).catch(()=>{
        error.hidden = false;
        error.innerHTML = "Issue attempting to log in";
    });
}

function openRegister() {
    window.location.href = 'register.html';
}

function getUsers() {
    fetch('/test/user',{
        method: 'GET'
    }).then(json=> {
        console.log(json);
        //alert("ribbit");
    }).catch(()=> {
        let error = document.getElementById("error");
        error.hidden = false;
        error.innerHTML = "error";
    });
}