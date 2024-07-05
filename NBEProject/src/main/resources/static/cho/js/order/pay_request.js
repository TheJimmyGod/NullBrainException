

function requestPay() {
    const pg = $('input[name="pg"]').val();
    const pay_method = $('input[name="payMethod"]').val();
    const m_id = $('input[name="mId"]').val();
    const name = $('input[name="name"]').val();
    const amount = $('input[name="amount"]').val();
    const email = $('input[name="email"]').val();
    const buyer_name = $('input[name="buyerName"]').val();
    const tel = $('input[name="tel"]').val();
    const addr = $('input[name="addr"]').val();
    const IMP = window.IMP;
    IMP.init("imp77731808");


    IMP.request_pay({
        pg: pg,
        pay_method: pay_method,
        merchant_uid: m_id,   // 주문번호
        name: name,
        amount: 110,                         // 숫자 타입
        buyer_email: email,
        buyer_name: buyer_name,
        buyer_tel: tel,
        buyer_addr: "whckdtjd456@naver.com",
    }, function (rsp) { // callback
       if(rsp.success) {
           $.ajax({   // 결제 성공시 호출
               url: "/payment",
               method: "POST",
               headers: {"Content-Type" : "application/json"},
               data: JSON.stringify({
                    imp_uid: rsp.imp_uid,
                    merchant_uid: rsp.merchant_uid,
                    amount: rsp.amount
               }),
           }).done(function(data){
               alert("결제에 성공하셨습니다.");
               location.href = "/cart";

           })
       } else {alert("결제에 실패 하였습니다.")}
    });

}