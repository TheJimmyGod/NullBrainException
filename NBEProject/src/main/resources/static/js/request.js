$(function(){

    $('.home').click(function(){
        location.href = 'http://localhost:8080/nbe/home';
    });

    $('.post').click(function(){
        location.href = 'http://localhost:8080/post/list';
    });

    $('.cart').click(function(){
        location.href = `http://localhost:8080/cart?userId=1`;
    });

    $('.recent').click(function(){
        location.href = 'http://localhost:8080/nbe/recent';
    });

    $('.user').click(function(){
        location.href = 'http://localhost:8080/mypage/detail';
    });

    $('.accButton').click(function (){

    });
});

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

        url = `http://localhost:8080/nbe/prodList?category1=${encodeURIComponent(category1)}&category2=${encodeURIComponent(category2)}`;
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
        <a href="http://localhost:8080/shop/detail/${goods.productId}"><img src="${goods.image}" class="card-img-top embed-responsive-item"></a>
        <p>제품 설명<br>${goods.price}</p>
        </div>
        `);
    });
    $(".product-list").html(div.join("\n"));
}

$(document).ready(function(){
    $('#accButton').click(function(){
        $('.category1').toggleClass('hidden');
        $('.category2-man').addClass('hidden');
        $('.category2-woman').addClass('hidden');
    });
});


function cancel(mId, image, name, price){
        $('.card').css({
            "display": "flex"
        })
        $('.card-img .img').attr('src',image);
        $('.card .card-title').text(name);
        $('.card .card-price').text(price);

        $('.card-btn').click(function(){
           $.ajax({
               url: "/cancel?mId=" + mId,
               method: "GET"
           }).done(function (){
               alert("취소요청")
               location.href = "/request"
           })
        });

}