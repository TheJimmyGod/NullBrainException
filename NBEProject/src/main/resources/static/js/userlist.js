document.addEventListener("DOMContentLoaded", function() {
    const urlParams = new URLSearchParams(window.location.search);
    const message = urlParams.get('message');
    if (message) {
        alert(message);
    }
});



    // 적용 버튼을 눌렀을 때 alert 적용 완료 팝업창 기능
document.addEventListener('DOMContentLoaded', function () {
    const applyButtons = document.querySelectorAll('.apply-button');
    applyButtons.forEach(button => {
        button.addEventListener('click', function (event) {
            alert("적용 완료");
        });
    });
});



