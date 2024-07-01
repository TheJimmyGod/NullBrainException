$(function(){
    let item;
    // 여성의류 버튼 클릭 이벤트
    $('.woman').click(function() {
        $('.category2-woman').toggleClass('show');
        $('.category2-man').removeClass('show');
    });

    // 남성의류 버튼 클릭 이벤트
    $('.man').click(function() {
        $('.category2-man').toggleClass('show');
        $('.category2-woman').removeClass('show');
    });

    // 다른 영역 클릭 시 드롭다운 숨김 처리
    $(document).click(function(event) {
        if (!$(event.target).closest('.btn, .category2-man, .category2-woman').length) {
            $('.category2-woman, .category2-man').removeClass('show');
        }
    });


    $(".item").on("click", function(){
        let url;
        let category1;
        let category2;
        item=$(this).text();
        if($(this).hasClass("man")){
            category1="남성의류";
            category2=item

        }else {
            category1="여성의류";
            category2=item;
        }

        url = `http://localhost:8080/nbe/list?category1=${encodeURIComponent(category1)}&category2=${encodeURIComponent(category2)}`;
        location.href=url;

    });


});