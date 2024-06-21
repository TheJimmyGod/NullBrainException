$(function (){
    // [추가] 버튼 누르면 첨부 파일 추가 가능
    let i = 0;
    $("#btnAdd").click(function (){

        if(i < 5)
        {
            $("#files").append(`<div class="input-group mb-2">
   <input class="form-control col-xs-3" type="file" id="imageList" name="upfile${i}" accept="image/jpeg, image/png, image/jpg"/>
   <button type="button" class="btn" onclick="$(this).parent().remove()"><a id="btnColor">삭제</a></button>
    </div>`);
            i++;
        }
        else
        {
            alert("5개까지 넣을 수 있습니다.");
        }
    });
    //
    // // Summernote 추가
    // $("#content").summernote({
    //     height: 300
    // });

});

function Back(){
    history.back();
}