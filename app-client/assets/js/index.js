$.ajax({
    type: "GET",
    url: `${BASE_URL}/api/home`,
    headers: {
      Authorization: "Bearer " + TOKEN,
      "Content-Type": "application/json",
      "Access-Control-Allow-Origin": "*",
    },
    success: function (res) {
      const data = res.data
      $("#timeFftNum").html(data.timeFftNum)
      $("#freqFftNum").html(data.freqFftNum)
      $("#dftNum").html(data.dftNum)
      $("#nspNum").html(data.nspNum)
    },
    error: function (response) {
      if (response.status == 403) {
        backLogin();
      }
    },
  });