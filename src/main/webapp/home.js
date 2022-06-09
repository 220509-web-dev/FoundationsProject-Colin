function home() {
    window.location.href = 'index.html';
}

function getUser() {
    let error = document.getElementById("error");
    let result = document.getElementById("result");

    let userId = document.getElementById("userId").value;
    let username = document.getElementById("username").value;
    let path = "/test/user"

    if (userId) {
        path = path + "?id=" + userId;
    } else if (username) {
        path = path + "?username=" + username;
    }

    fetch(path,{
        method: 'GET'
    }).then(response=>response.json().then(data =>{
        console.log(data);
        userList = data;
        let resultHTML = `
        <table>
        <thaed>
            <tr>
                <th>id</th>
                <th>first name</th>
                <th>last name</th>
                <th>username</th>
                <th>email</th>
            </tr>
        </thaed>
        <tbody>`

        console.log(userList.length)
        for (let x = 0; x < userList.length; x++) {
            resultHTML += '<tr>'
            resultHTML += '<th>'+ userList[x].id +'</th>'
            resultHTML += '<th>'+ userList[x].firstName +'</th>'
            resultHTML += '<th>'+ userList[x].lastName +'</th>'
            resultHTML += '<th>'+ userList[x].username +'</th>'
            resultHTML += '<th>'+ userList[x].email +'</th>'
            resultHTML += '</tr>'
        }
        resultHTML += '</tbody></table>'
        result.hidden = false;
        result.innerHTML = resultHTML;

    })).catch(()=> {
        let error = document.getElementById("error");
        error.hidden = false;
        error.innerHTML = "error";
    });

}