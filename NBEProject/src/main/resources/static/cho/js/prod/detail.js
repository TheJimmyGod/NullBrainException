$(document).ready(function() {

    // 스크롤 유지


    // var location = $('.reviews-section').offsetTop;
    // $(window).scrollTo(0, location);
    // $('.review-block').removeClass('hidden');


    // 스크롤 유지 end
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

    // 구매, 장바구니 정보 전송
    $('.buy-now').click(function(){
        $('.order_frm').submit()
    });
    // 장바구니 모달창
    var modal = $(".cont");
    var shop = $(".shopping");
    var cart = $(".carting");

    shop.click(function (){
        modal.toggleClass('show');
    })

    cart.click(function (){
        location.href = "http://localhost:8080/cart?userId=1";
    })

    $('.cart').click(function(){
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
        let data = {
            amount: parseInt($('input[name="amount"]').val()),
            opt: $('#opt option:selected').val(),
            goods: goods,
            user:   user,
        }
        $.ajax({
            type: 'POST',
            url: '/cart/plus',
            data: JSON.stringify(data),
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
        });


    });
    // 구매, 장바구니 정보 전송 end
    // 리뷰 보기 기능
    $('.review-btn').click(function (){
        $('.review-block').toggleClass('hidden');
        // if($.cookie('review')){
        //     $.removeCookie('review');
        // }else {
        //     $.cookie('review','ok');
        // }
    });
    // 리뷰 더보기 기능
    $('.review-body').each(function() {
        var $content = $(this).find('.review-text');
        var $moreBtn = $(this).find('.more-btn');

        if ($content[0].scrollHeight > $content.innerHeight()) {
            $moreBtn.show();
        }

        $moreBtn.click(function() {
            if ($content.css('max-height') !== 'none') {
                $content.css('max-height', 'none');
                $moreBtn.text('닫기');
            } else {
                $content.css('max-height', '100px');
                $moreBtn.text('...더보기');
            }
        });
    });
    // 리뷰 더보기 기능 end






    $(window).click(function(event) {
        if (event.target === modal[0]) {
            modal.css("display", "none");
        }
    });
    $.removeCook
    // 모달 end



    // 리뷰 접기 펼치기 기능

    // 리뷰 접기 펼치기 기능 end

});