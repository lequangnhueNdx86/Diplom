const nspFormId = "#nsp-form";
fetchData();

$(nspFormId).validate({
  rules: {
    frequency: {
      required: true,
    },
    noiseFrequency: {
        required: true,
      },
    numberOfSamples: {
        required: true,
    },
    samplingRate: {
        required: true,
    }
  },

  submitHandler: (form) => {
    const nsp = Form.getData(nspFormId);
    $.ajax({
      type: "POST",
      url: BASE_URL + "/api/nsp/calc",
      headers: {
        Authorization: "Bearer " + TOKEN,
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
      },
      data: JSON.stringify({
        "frequency": parseInt(nsp["frequency"]),
        "noiseFrequency": parseInt(nsp["noiseFrequency"]),
        "numberOfSamples": parseInt(nsp["numberOfSamples"]),
        "samplingRate": parseInt(nsp["samplingRate"])
      }),
      success: function (res) {
        $('#create-form').addClass("visually-hidden")
        $('#after-register').removeClass("visually-hidden")
        $('#after-register-image').removeClass("visually-hidden")
        $('#r_frequency').html(`Frequency: ${res.data.frequency} (Hz)`)
        $('#r_noiseFreq').html(`Noise Frequency: ${res.data.noiseFrequency} (Hz)`)
        $('#r_numSample').html(`Number of Samples: ${res.data.numberOfSamples}`)
        $('#r_samplingRate').html(`Sampling Rate: ${res.data.samplingRate}`)
        $('#image1').attr("src", BASE_URL  + res.data.imageList[0])
        $('#image1-label').html('Sine wave');
      },
      error: function (err) {
        if (err.status == 403) {
          backLogin();
        }
      },
    })
    return false;
  },
})

$("#retry-btn").click(() => {
    retry()    
})

function retry() {
    window.location.replace("/views/nsp/nsp.html");
}

function fetchData() {
    $.ajax({
        type: "POST",
        url: BASE_URL + "/api/nsp/getAll",
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
                <div class="text-start">Frequency: ${v.frequency} (Hz)</div>
                <div class="text-start">Noise Frequency: ${v.noiseFrequency} (Hz)</div>
                <div class="text-start">Number of Samples: ${v.numberOfSamples}</div>
                <div class="text-start">Sampling Rate: ${v.samplingRate}</div>
                <button type="button" data-bs-toggle="modal" data-bs-target="#detail" class="btn btn-danger mt-2" style="font-size: 0.8em;" onclick="viewDetail('${v.id}')">View</button>
            </div>
        </div>`
        
    });
    $('#hstData').html(html)
}

function viewDetail(id) {
    $.ajax({
        type: "POST",
        url: BASE_URL + "/api/nsp/get",
        headers: {
          Authorization: "Bearer " + TOKEN,
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
        },
        data: JSON.stringify({
          "id": id
        }),
        success: function (res) {
            const v = res.data;
            let html = `
                <div class="col-4" style="font-size: 0.9em;">
                <label class="card-title form-label">Input</label>
                <div class="row m-lg-3" style="font-size: 1.1em;">
                    <label class="text-start">Frequency: ${v.frequency} (Hz)</label>
                </div>
                
                <div class="row m-lg-3" style="font-size: 1.1em;">
                    <label class="text-start">Noise Frequency: ${v.noiseFrequency} (Hz)</label>
                </div>
                <div class="row m-lg-3" style="font-size: 1.1em;">
                <label class="text-start">Number of Samples: ${v.numberOfSamples}</label>
                </div>
                
                <div class="row m-lg-3" style="font-size: 1.1em;">
                    <label class="text-start">Sampling Rate: ${v.samplingRate}</label>
                </div>
                </div>
                <div class="col-8" style="font-size: 0.9em;">
                <div class="row">
                <label class="form-label card-title">Output</label>
                <div>
                    <div><label>Sine wave</label></div>
                    <img src="${BASE_URL + v.imageList[0]}" width='640px' height='480px'>
                </div>
            </div>
                </div>
                </div>
                `
            $('#detail-body').html(html)
            $('#item-no').html(`No. ${v.no}`)
        },
        error: function (err) {
          if (err.status == 403) {
            backLogin();
          }
        },
      })
}

