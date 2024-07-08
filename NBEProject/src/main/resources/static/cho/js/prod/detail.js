$(document).ready(function() {

    // 별점 표시
    $('.review').each(function() {
        let rating = parseInt($(this).find('.rating-score').text()); // 평점 가져오기
        let stars = $(this).find('.star'); // 별 요소들

        // 전체 별 채우기
        for (let i = 0; i < rating; i++) {
            stars.eq(i).addClass('active');
        }
    });

    let stars = $('.rating-total').find('.star');
    let totalValue = $('.reviewRate').text();
    for (let i = 0; i < totalValue; i++) {
        stars.eq(i).addClass('active');
    }


    // 수량 옵션
    $('#decrease').click(function() {
        let currentValue = parseInt($('#counter').val());
        if (currentValue > 0) {
            $('#counter').val(currentValue - 1);
        }
    });

    $('#increase').click(function() {
        let currentValue = parseInt($('#counter').val());
        $('#counter').val(currentValue + 1);
    });
    // 수량 옵션 end




    $(".shopping").click(function (){
        $(".cont").removeClass('show');
    })

    $(".carting").click(function (){
        location.href = "/cart";
    })



    $(window).click(function(event) {
        if (event.target === modal[0]) {
            modal.css("display", "none");
        }
    });

    // 모달 end



    // 리뷰 접기 펼치기 기능
    $('.review-btn').click(function(){
        visible();
    })
    // 리뷰 접기 펼치기 기능 end

});
// 장바구니 담기
function addCart(){

    let goods = {
        goodsNo: $('input[name="goodsNo"]').val(),
        name: $('input[name="g_name"]').val(),
        price: $('input[name="price"]').val(),
        image: $('input[name="image"]').val(),
    };

    let user = {
        userId: $('input[name="u_id"]').val(),
        username: $('input[name="username"]').val(),
        name: $('input[name="name"]').val(),
        phone: $('input[name="phone"]').val(),
        birth: $('input[name="birth"]').val(),
        email: $('input[name="email"]').val(),
        streetAddr: $('input[name="streetAddr"]').val(),
        detailAddr: $('input[name="detailAddr"]').val(),
    };
    let info = {
        amount: parseInt($('input[name="amount"]').val()),
        opt: $('#opt option:selected').val(),
        goods: goods,
        user:   user,
    };
    $.ajax({
        type: 'POST',
        url: '/cart/plus',
        data: JSON.stringify(info),
        dataType: "text",
        contentType: 'application/json',
        success: function(data, status) {
            if(status == "success" && data == "goCart"){
                $('.add').toggleClass('show');
            }

            if(status == "success" && data == "exist"){
                $('.exist').toggleClass('show');
            }
        },
    })


}

// 장바구니 end


// 구매버튼
function buy(){
    //구매 버튼 클릭

    let goods = {
        goodsNo: $('input[name="goodsNo"]').val(),
        name: $('input[name="g_name"]').val(),
        price: $('input[name="price"]').val(),
        image: $('input[name="image"]').val(),
    }

    let user = {
        userId: $('input[name="u_id"]').val(),
        username: $('input[name="username"]').val(),
        name: $('input[name="name"]').val(),
        phone: $('input[name="phone"]').val(),
        birth: $('input[name="birth"]').val(),
        email: $('input[name="email"]').val(),
        streetAddr: $('input[name="streetAddr"]').val(),
        detailAddr: $('input[name="detailAddr"]').val(),
    }
    let info = {
        amount: parseInt($('input[name="amount"]').val()),
        opt: $('#opt option:selected').val(),
        goods: goods,
        user:   user,
    }
    $.ajax({
        type: 'POST',
        url: '/cart/buy',
        data: JSON.stringify(info),
        dataType: "text",
        contentType: 'application/json',
        success: function(data, status) {
            (status == "success") && (location.href=`/cart?cartId=${data}`);
        },
    });



}
// 구매, 장바구니 정보 전송 end


// 쿠키 생성 및 토글

function visible(){
    $('.rev').toggleClass('hidden');
    if($.cookie("review").val() == "OK"){
        $.cookie("review", "NO")
    }
    else $.cookie("review", "OK")
}