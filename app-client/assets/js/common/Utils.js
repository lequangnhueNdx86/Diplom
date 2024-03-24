const BASE_URL = "http://localhost:8081";

const credential = JSON.parse(sessionStorage.getItem("credential"));
const TOKEN = credential?.token || '';
const USERNAME = credential?.username || '';
const ROLE = credential?.role || '';

  // set login username
 $(".login-username").html(USERNAME)
$("#role").html(ROLE)

const getUrlVars = () => {
    var vars = {}, hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++)
    {
        hash = hashes[i].split('=');
        if(hash[0] === undefined || hash[1] === undefined) return undefined
        vars[hash[0]] = hash[1];
    }
    return vars;
}