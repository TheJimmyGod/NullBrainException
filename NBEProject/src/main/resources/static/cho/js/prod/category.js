$(function(){
    let item;

    $('#accButton').click(function(){
        $('.category1').toggleClass('hidden');
        $('.category2-man').addClass('hidden');
        $('.category2-woman').addClass('hidden');
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

        url = `/nbe/prodList?category1=${encodeURIComponent(category1)}&category2=${encodeURIComponent(category2)}`;
        location.href=url;

    });


});
//
// function showWomen(){
//     $('.category2-woman').toggleClass('show');
//     $('.category2-man').removeClass('show');
// }
//
// function showMen(){
//     $('.category2-man').toggleClass('show');
//     $('.category2-woman').removeClass('show');
// }

$(function(){
    let item;
    $(".man").click(function(){
        $(".category2-man").removeClass("hidden");
        $(".category2-woman").addClass("hidden");
    });

    $(".woman").click(function(){
        $(".category2-man").addClass("hidden");
        $(".category2-woman").removeClass("hidden");
    });

    $(".item").on("click", function(){
        let url;
        let category2;
        let category3;
        item = $(this).text();
        if($(this).hasClass("man")){
            category2 = "남성의류";
            category3 = item;
        } else {
            category2 = "여성의류";
            category3 = item;
        }

        url = `/nbe/prodList?category1=${encodeURIComponent(category1)}&category2=${encodeURIComponent(category2)}`;
        location.href=url;

        $.ajax({
            url: url,
            type: "GET",
            cache: false,
            success: function(data, status){
                (status == "success") && showImage(data);
            }
        });
    });
});

function showImage(data){
    var div = [];
    data.forEach(goods => {
        div.push(`
        <div class="product">
        <a href="/shop/detail/${goods.productId}"><img src="${goods.image}" class="card-img-top embed-responsive-item"></a>
        <p>제품 설명<br>${goods.price}</p>
        </div>
        `);
    });
    $(".product-list").html(div.join("\n"));
}

