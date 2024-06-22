document.addEventListener("DOMContentLoaded", function() {
    const urlParams = new URLSearchParams(window.location.search);
    const message = urlParams.get('message');
    if (message) {
        alert(message);
    }
});

function submitForm(userId) {
    const form = document.getElementById(`gradeForm-${userId}`);
    form.submit();
}

