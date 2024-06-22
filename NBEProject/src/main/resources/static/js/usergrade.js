document.addEventListener("DOMContentLoaded", function () {
    const forms = document.querySelectorAll(".grade-form");

    forms.forEach(form => {
        form.addEventListener("submit", function () {
            alert("등급이 변경되었습니다.");
        });
    });
});
