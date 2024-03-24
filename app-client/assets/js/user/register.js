const registerFormId = "#register-form";
$(registerFormId).validate({
    rules: {
        userName: {
            required: true,
            notBlank: true
        },
        studentCode: {
            required: true,
            notBlank: true,
            studentCode: true
        },
        password: {
            required: true,
            notBlank: true,
            passwordRule: true
        },
        rePassword: {
            required: true,
            notBlank: true,
            passwordRule: true
        }
    },
    submitHandler: () => {
        const data = Form.getData(registerFormId);
        if (checkPassword(data.password, data.rePassword) == true) {
            $.ajax({
                type: "POST",
                url: BASE_URL + "/api/register",
                headers: {
                    'Content-Type':'application/json',
                    'Access-Control-Allow-Origin': '*'
                },
                data: JSON.stringify({
                    userName: data.userName,
                    password: data.password,
                    studentCode: data.studentCode
                }),
                success: (res) => {
                    window.location.replace(`/views/login.html`)
                    alert('Create an account successfully!')
                },
                error: (err) => {
                    alert(err.responseJSON.message)
                }
            })
        } else {
            alert("Confirm password is incorrect")
        }
       
        return false;
    }
})

function checkPassword(v1, v2) {
    return v1 === v2
}