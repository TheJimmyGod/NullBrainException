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
