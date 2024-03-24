
const loginFormId = "#login-form";
$(loginFormId).validate({
    rules: {
        userName: {
            required: true,
            notBlank: true
        },
        password: {
            required: true,
            notBlank: true,
            passwordRule: true
        }
    },
    submitHandler: () => {
        const user = Form.getData(loginFormId);
        $.ajax({
            type: "POST",
            url: BASE_URL + "/api/login",
            headers: {
                'Content-Type':'application/json',
                'Access-Control-Allow-Origin': '*'
            },
            // crossDomain: true,
            data: JSON.stringify(user),
            success: function(response) {
                // save credential in session st
                sessionStorage.setItem("credential", JSON.stringify(response));
                // redirect to index page
                window.location.replace(`/views/index.html`)
            },
            error: function(data) {
                alert("Username or password is incorrect!!!")
                console.log(data.status);
            }
        })
        return false;
    }
})