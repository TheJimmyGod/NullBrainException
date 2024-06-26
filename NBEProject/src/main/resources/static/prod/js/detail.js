$(document).ready(function() {

    // 스크롤 유지

    if($.cookie('review')){
        var location = $('.reviews-section').offsetTop;
        $(window).scrollTo(location);
        $('.review-block').removeClass('hidden');
    }

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
    $('.cart-button').click(function(){
        $('#opt').submit();
    });

    $('.buy-button').click(function(){
        $('#opt').submit();
    });
    // 구매, 장바구니 정보 전송 end
    // 리뷰 보기 기능
    $('.review-btn').click(function (){
        $('.review-block').toggleClass('hidden');
        if($.cookie('review')){
            $.removeCookie('review');
        }else {
            $.cookie('review','ok');
        }
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


    // 장바구니 모달창
    var modal = $("#modal");
    var btn = $(".btn-cart");
    var shop = $(".no-cart");
    var cart = $(".go-cart");

    btn.click(function() {
        modal.css("display", "block");
    });

    shop.click(function() {
        modal.css("display", "none");
    });

    $(window).click(function(event) {
        if (event.target === modal[0]) {
            modal.css("display", "none");
        }
    });
    $.removeCook
    // 모달 end



    // 리뷰 접기 펼치기 기능
    if($.cookie('review')){

    }
    // 리뷰 접기 펼치기 기능 end

});