document.addEventListener("DOMContentLoaded", function() {
    var forms = document.querySelectorAll("form[id^='updatePayStatusForm']");
    forms.forEach(function(form) {
        form.addEventListener("submit", function(event) {
            event.preventDefault();
            var status = form.querySelector("#orderStatus").value;
            var paymentUid = form.querySelector("#paymentUid").value;
            var purchaseId = form.querySelector("#purchaseId").value;
            if (status === 'CANCEL_OK') {
                cancel(paymentUid, purchaseId);
            } else {
                form.submit();
            }
        });
    });
});

function cancel(paymentUid, purchaseId) {
    console.log("Sending cancel request with paymentUid:", paymentUid, "and purchaseId:", purchaseId);
    $.ajax({
        url: '/admin/updatePayStatus',
        method: 'POST',
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: {
            imp_uid: paymentUid,
            status: 'CANCEL_OK',
            purchaseId: purchaseId
        },
        success: function(response) {
            alert('결제가 취소되었습니다.');
            location.reload();
        },
        error: function(xhr, status, error) {
            console.error("Error during cancel request:", xhr.responseText);
            alert('결제 취소에 실패했습니다. 오류 메시지: ' + xhr.responseText);
        }
    });
}
