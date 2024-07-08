$(document).ready(function() {

    // 스크롤 유지


    // var location = $('.reviews-section').offsetTop;
    // $(window).scrollTo(0, location);
    // $('.review-block').removeClass('hidden');


    // 스크롤 유지 end



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


    // 장바구니 모달창
    var modal = $(".cont");
    var shop = $(".shopping");
    var cart = $(".carting");

    shop.click(function (){
        modal.removeClass('show');
    })

    cart.click(function (){
        location.href = "/cart";
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
        })

    });

    //구매 버튼 클릭
    $('.buy-now').click(function(){
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
            url: '/cart/buy',
            data: JSON.stringify(data),
            dataType: "text",
            contentType: 'application/json',
            success: function(data, status) {
                (status == "success") && (location.href=`/cart?cartId=${data}`);
            },
        });


    });
    // 구매, 장바구니 정보 전송 end
    // 리뷰 보기 기능
    $('.toggle-btn').click(function() {
        var content = $(this).prev('.review-content');
        content.toggleClass('expanded'); // 'expanded' 클래스를 토글하여 내용을 확장 또는 축소

        if (content.hasClass('expanded')) {
            $(this).text('접기'); // 버튼 텍스트 변경
        } else {
            $(this).text('더보기'); // 버튼 텍스트 변경
        }
    });
    // 리뷰 더보기 기능
    var reviewContent = $('.review-content');
    var toggleLink = $('.toggle-link');

    if (reviewContent[0].clientHeight < reviewContent[0].scrollHeight) {
        toggleLink.parent().show(); // 일부분만 보이게 될 부분 표시
    }

    toggleLink.on('click', function(e) {
        e.preventDefault();
        reviewContent.toggleClass('expanded');
        if (reviewContent.hasClass('expanded')) {
            toggleLink.text('리뷰 접기');
        } else {
            toggleLink.text('리뷰 펼치기');
        }
    });
    // 리뷰 더보기 기능 end






    $(window).click(function(event) {
        if (event.target === modal[0]) {
            modal.css("display", "none");
        }
    });

    // 모달 end



    // 리뷰 접기 펼치기 기능
    $('.review-btn').click(function(){
        $('.review-block').toggleClass('hidden');
    })
    // 리뷰 접기 펼치기 기능 end

});
