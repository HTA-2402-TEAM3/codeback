// popup.js
document.querySelector("#show-signup-form").addEventListener("click", function (e) {
    e.preventDefault(); // 기본 링크 동작 방지
    document.querySelector(".login-form").style.display = "none";
    document.querySelector(".signup-form").style.display = "block";
});

document.querySelector("#show-login-form").addEventListener("click", function (e) {
    e.preventDefault(); // 기본 링크 동작 방지
    document.querySelector(".signup-form").style.display = "none";
    document.querySelector(".login-form").style.display = "block";
});

// 팝업 열기/닫기 기능 유지
document.querySelector("#show-login").addEventListener("click", function () {
    document.querySelector(".popup").classList.add("active");
});

document.querySelector(".popup .close-btn").addEventListener("click", function () {
    document.querySelector(".popup").classList.remove("active");
});