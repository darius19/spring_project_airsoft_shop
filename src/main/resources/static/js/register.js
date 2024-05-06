let loginsec;
let loginlink;
let registerlink;
let forgetlink;
let backToLogIn;
let regFailedMessage;
const onLoad = () => {

  loginsec = document.querySelector(".login-section");
  loginlink = document.querySelector(".login-link");
  registerlink = document.querySelector(".register-link");
  forgetlink = document.querySelector(".forget-link");
  backToLogIn = document.querySelector(".backto-link");
  regFailedMessage = document.querySelector(".user-reg-failed-message");


  registerlink.addEventListener("click", () => {
    loginsec.classList.add("active");
  });
  loginlink.addEventListener("click", () => {
    loginsec.classList.remove("active");
    // setTimeout(()=> window.location.href="/login",1000);
  });

  forgetlink.addEventListener("click", () => {
    loginsec.classList.add("forget");
  });

  backToLogIn.addEventListener("click", () => {
    loginsec.classList.remove("forget");
  });

  if (yourObject !== null && yourObject === ""){
    document.querySelector(".login-section").classList.remove("active");
  }



};

window.addEventListener("load",onLoad);
