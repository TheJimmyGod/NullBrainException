$(function(){
    let item;
    $(".man").click( function(){
        $(".category2-man").css({
            "display": "block"
        })
        $(".category2-woman").css({
            "display": "none"
        });
        
    });

    $(".woman").click( function(){
        $(".category2-man").css({
            "display": "none"
        })
        $(".category2-woman").css({
            "display": "block"
        });
        
    });
    

    $(".item").on("click", function(){
        let url;
        let category2;
        let category3
        item=$(this).text();
        if($(this).hasClass("man")){
            category2="남성의류";
            category3=item
            
        }else {
            category2="여성의류";
            category3=item;
            }

        url = `http://localhost:8080/shop/prods?category1=${encodeURIComponent("패션의류")}&category2=${encodeURIComponent(category2)}&category3=${encodeURIComponent(category3)}`

        $.ajax({
            url: url,
            type: "GET",
            cache: false,
            success: function(data, status){
                (status == "success") && showImage(data)
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
        <p>제품 6 설명<br>${goods.price}</p>
        </div>
        `);
    });
    $(".product-list").html(div.join("\n"));
}