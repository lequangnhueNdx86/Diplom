const timeFftForm = "#time-fft-form";
var hstData = {}
fetchData();

$("#submit-btn").click( () => {
    const input = Form.getData(timeFftForm);
    let allUndefined = true;
    Object.keys(input).forEach((key) => {
        if (input[key] === '' || input[key] === undefined) {
            input[key] = '0'
        } else {
            allUndefined = false;
        }   
    })
    if (allUndefined) {
        $("#confirm-btn").click()
    } else {
        const payload = {
            inputList: [input['x_0'], input['x_1'], input['x_2'], input['x_3'], input['x_4'], input['x_5'], input['x_6'], input['x_7']]
        };
        calc(payload)
    }   
  },
);

$("#yes-btn").click(() => {
    calc({inputList: ['0', '0', '0', '0', '0', '0', '0', '0']})
})

function calc(payload) {
    $.ajax({
        type: "POST",
        url: BASE_URL + "/api/fft/time/calc",
        headers: {
          Authorization: "Bearer " + TOKEN,
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
        },
        data: JSON.stringify(payload),
        success: function (res) {
          const matrix = res.data.matrixResult;
          const input = matrix.map(r => r[0]);
          const result = res.data.listResult;
          $(".tooltip_").addClass("display")
          setInput(input, true)
          setResult(result, true)
          setMatrix(matrix)
          $("#submit-btn").addClass("visually-hidden")
          $("#reset-btn").addClass("visually-hidden")
          $("#retry-btn").removeClass("visually-hidden")
          return true
        },
        error: function (err) {
          console.log(err);
          
        },
      }).then((result) => {
        if (result) {
            fetchData();
        }
      }).catch((err) => {
        console.log(err);
      });
}

$("#retry-btn").click(() => {
    retry()    
})

function setInput(arr, display) {
    $('.input_').addClass("visually-hidden")
    setValue("x_0", arr[0])
    setValue("x_4", arr[1])
    setValue("x_2", arr[2])
    setValue("x_6", arr[3])
    setValue("x_1", arr[4])
    setValue("x_5", arr[5])
    setValue("x_3", arr[6])
    setValue("x_7", arr[7])
    arr = ["x_0", "x_1", "x_2", "x_3", "x_4", "x_5", "x_6", "x_7"]
    if (display == true) {
        arr.forEach(v => $(`#${v}_text`).removeClass("visually-hidden"))
    } else {
        arr.forEach(v => $(`#${v}_text`).addClass("visually-hidden"))
    }
    
}

function setResult(arr, display) {
    arr.forEach((v, idx) => {
        $(`#X_${idx}`).html(v)
    });
    if (display == true) {
        for (let i = 0; i <= 7; i++) {
            $(`#X_${i}`).removeClass("visually-hidden")
        }
    } else {
        for (let i = 0; i <= 7; i++) {
            $(`#X_${i}`).addClass("visually-hidden")
        }
    }
   
}

function setMatrix(matrix) {
    for (let i = 0; i < 8; i++) {
        for (let j = 0; j < 8; j++) {
            $(`#T${i}${j}`).html(matrix[i][j])
        }
    }
}

function setValue(v, val) {
    $(`#${v}_text`).html(val);
}

function retry() {
    window.location.replace("/views/fft/time-fft.html");
}

function fetchData() {
    $.ajax({
        type: "GET",
        url: BASE_URL + "/api/fft/time",
        headers: {
          Authorization: "Bearer " + TOKEN,
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
        },
        success: function (res) {
            if (res.data != null) {
                setHstData(res.data)
                hstData = res.data
            }
        },
        error: function (err) {
          console.log(err);
          
        },
      })
}

function setHstData(data) {
    $("#no").html(data.no)
    const input = data.matrixResult.map(r => r[0]);
    arr = [input[0], input[4], input[2], input[6], input[1], input[5], input[3], input[7]]
    arr.forEach((v, i) => {
        $(`#x${i}_hst`).html(v)
    })
    data.listResult.forEach((v,i) => {
        $(`#X${i}_hst`).html(v)
    })
}

function viewDetail() {
    const matrix = hstData.matrixResult;
    const input = matrix.map(r => r[0]);
    const result = hstData.listResult;
    $(".tooltip_").addClass("display")
    setInput(input, true)
    setResult(result, true)
    setMatrix(matrix)
    $("#submit-btn").addClass("visually-hidden")
    $("#reset-btn").addClass("visually-hidden")
    $("#retry-btn").addClass("visually-hidden")
    $("#new-btn").removeClass("visually-hidden")
}
