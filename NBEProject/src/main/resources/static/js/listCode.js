let slideIndex = 0;

$(function(){
  // 페이징 헤더
  $("[name='pageRows']").change(function(){
    //alert($(this).val());  // 확인용
    $("[name='frmPageRows']").attr({
      "method": "POST",
      "action": "/post/pageRows",
    }).submit();

  });
});
$(document).ready(function (){
  var $arr = $('[id*=' + 'slideshow-' + ']').get();
  var $likeArr = $('[id*=' + 'like-' + ']').get();
  $arr.forEach(function (element){
    var suffix = element.id.match(/\d+/);
    showSlides(slideIndex, suffix);
  });

  $likeArr.forEach(function (element){
    var suffix = element.id.match(/\d+/);
    $("#like-" + suffix).click(function (){

      const data = {
        "post_id": suffix,
        "user_id": logged_id.id
      };

      let $postID = $(this).attr("data-post-id");
      let $func=$(this);

      $.ajax({
        url: '/post/list/'+ $postID,
        type: "POST",
        data: data,
        cache: false,
        success: function(data, status){
          if(status == "success"){
            if($func.find("#heart").css("color")==="rgb(0, 0, 0)")
            {

              $func.find("#heart").css("color", "rgb(255, 0, 0)");
              $func.find("#heart").removeClass("far fa-heart");
              $func.find("#heart").addClass("fas fa-heart");
            }
            else
            {
              $func.find("#heart").css("color", "rgb(0, 0, 0)");
              $func.find("#heart").removeClass("fas fa-heart");
              $func.find("#heart").addClass("far fa-heart");
            }
          }
        },
      });
    });
  });

});

// Thumbnail image controls
function currentSlide(id) {
  var suffix = $(id).parent().attr("id").match(/\d+/);
  let n = $(id).attr('id').match(/\d+/);

  showSlides(slideIndex = n, suffix);
}

function showSlides(n, id) {
  let i;
  let $show = $("#" +'slideshow-' + id);
  let $dotDiv = $("#"+'dot-'+id);

  let $slides = $show.find('.mySlides');
  let $dots = $dotDiv.find("dot");
  if (n > $slides.length) {slideIndex = 1}
  if (n < 1) {slideIndex = $slides.length}
  for (i = 0; i < $slides.length; i++) {
    $slides.eq(i).css("display","none");
  }
  for (i = 0; i < $dots.length; i++) {
    $dots.eq(i).removeClass(" active");
  }
  $slides.eq(slideIndex-1).css("display","block");
  $dots.eq(slideIndex-1).addClass(" active");
}