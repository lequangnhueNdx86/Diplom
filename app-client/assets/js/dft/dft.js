const dftFormId = "#dft-form";

var currentFormVal = {}
fetchData();

$(dftFormId).validate({
  rules: {
    a1: {
      required: true,
    },
    om1: {
        required: true,
      },
    a2: {
        required: true,
    },
    om2: {
        required: true,
    }
  },
  messages: {
    a1: {
      required: "This field is required",
    },
    om1: {
        required: "This field is required",
    },
    a2: {
        required: "This field is required",
    },
    om2: {
        required: "This field is required",
    }
  },

  submitHandler: (form) => {
    const dft = Form.getData(dftFormId);
    const payload = {
      "a1": parseFloat(dft["a1"]),
      "om1": parseFloat(dft["om1"]),
      "a2": parseFloat(dft["a2"]),
      "om2": parseFloat(dft["om2"]),
      "type": parseInt($("input[name='type']:checked").val())
    }
    if (parseFloat(dft["om1"]) === parseFloat(dft["om2"])) {
      currentFormVal = payload
      $("#confirm-btn").click();
    } else {
      calc(payload)
    }
    
    return false;
  },
})

$("#yes-btn").click(() => {
  calc(currentFormVal)
})

$("#retry-btn").click(() => {
    retry()    
})

function retry() {
    window.location.replace("/views/dft/dft.html");
}

function fetchData() {
    $.ajax({
        type: "POST",
        url: BASE_URL + "/api/dft/getAll",
        headers: {
          Authorization: "Bearer " + TOKEN,
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
        },
        success: function (res) {
            if (res.data != null) {
                setHstData(res.data)
            }
        },
        error: function (err) {
          console.log(err);
          
        },
      })
}

function setHstData(data) {
    let html = ''
    data.forEach(v => {
        html += `
        <div class="card card-text">
            <div class="m-lg-4" style="font-size: 0.9em;">
                <div class="text-start modal-title text-danger">No. ${v.no}</div>
                <div class="text-start">${v.func1}</div>
                <div class="text-start">${v.func2}</div>
                <div class="text-start text-success">
                    <span class="bi-check-circle-fill" ></span>
                    <label class="form-check-label">
                    ${v.type == 0 ? 'Low pass filter' : 'High pass filter'}
                    </label>
                </div>
                <button type="button" data-bs-toggle="modal" data-bs-target="#detail" class="btn btn-danger mt-2" style="font-size: 0.8em;" onclick="viewDetail('${v.id}')">View</button>
            </div>
        </div>`
        
    });
    $('#hstData').html(html)
}

function viewDetail(id) {
    $.ajax({
        type: "POST",
        url: BASE_URL + "/api/dft/get",
        headers: {
          Authorization: "Bearer " + TOKEN,
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
        },
        data: JSON.stringify({
          "id": id
        }),
        success: function (res) {
            const data = res.data;
            let html = `
                <div class="col-4" style="font-size: 0.9em;">
                <label class="card-title form-label">Input</label>
                <div class="row m-lg-3" style="font-size: 1.1em;">
                    <label class="text-start">${data.func1}</label>
                </div>
                
                <div class="row m-lg-3" style="font-size: 1.1em;">
                    <label class="text-start">${data.func2}</label>
                </div>

                <div class="row m-lg-3" style="font-size: 1.1em;">
                    <label class="text-start">h(0) = h(2) = α = ${data.alpha}</label>
                </div>

                <div class="row m-lg-3" style="font-size: 1.1em;">
                    <label class="text-start">h(1) = β = ${data.beta}</label>
                </div>
            
                <fieldset class="row mt-3">
                    <div class="col-sm-10">
                    <div class="form-check">
                        <span class="bi-check-circle-fill"></span>
                        <label class="form-check-label" style="font-size: 0.9em;">
                        ${data.type == 0 ? 'Low pass filter' : 'High pass filter'}
                        </label>
                    </div>
                    </div>
                </fieldset>
                </div>
                <div class="col-8" style="font-size: 0.9em;">
                <div class="row">
                <label class="form-label card-title">Output</label>
                <div>
                    <label>${data.type == 0 ? 'Low pass filter input signal and output signal' : 'High pass filter input signal and output signal'}</label>
                    <img src="${BASE_URL + data.imageList[0]}" width='600px' height='300px'>
                </div>
                <div>
                    <label class='mt-2'>${data.type == 0 ? 'Frequency response of the low pass filter' : 'Frequency response of the high pass filter'}</label>
                    <img src="${BASE_URL + data.imageList[1]}" width='600px' height='300px'>
                </div>
                </div>
                </div>
                `
            $('#detail-body').html(html)
            $('#item-no').html(`No. ${data.no}`)
        },
        error: function (err) {
          if (err.status == 403) {
            backLogin();
          }
        },
      })
}

function calc(payload) {
  $.ajax({
    type: "POST",
    url: BASE_URL + "/api/dft/calc",
    headers: {
      Authorization: "Bearer " + TOKEN,
      "Content-Type": "application/json",
      "Access-Control-Allow-Origin": "*",
    },
    data: JSON.stringify(payload),
    success: function (res) {
      $('#create-form').addClass("visually-hidden")
      $('#after-register').removeClass("visually-hidden")
      $('#after-register-image').removeClass("visually-hidden")
      $('#func_1').html(res.data.func1)
      $('#func_2').html(res.data.func2)
      $('#h_line1').html(`h(0) = h(2) = α = ${res.data.alpha}`)
      $('#h_line2').html(`h(1) = β = ${res.data.beta}`)
      $('#filter_type').html(res.data.type == 0 ? 'Low pass filter' : 'High pass filter')
      $('#image1').attr("src", BASE_URL  + res.data.imageList[0])
      $('#image1-label').html(res.data.type == 0 ? 'Low pass filter input signal and output signal' : 'High pass filter input signal and output signal');
      $('#image2').attr("src", BASE_URL  + res.data.imageList[1])
      $('#image2-label').html(res.data.type == 0 ? 'Frequency response of the low pass filter' : 'Frequency response of the high pass filter')
    },
    error: function (err) {
      if (err.status == 403) {
        backLogin();
      }
    },
  })
}

