$(function (){
    // [추가] 버튼 누르면 첨부 파일 추가 가능
    let i = 0;
    $("#btnAdd").click(function (){

        if(i < 3)
        {
            $("#files").append(`<div class="input-group mb-2">
   <input class="form-control col-xs-3" type="file" id="imageList" name="upfile${i}" accept="image/jpeg, image/png, image/jpg"/>
   <button type="button" class="btn btn-outline-danger" onclick="$(this).parent().remove()">삭제</button>
    </div>`);
            i++;
        }
        else
        {
            alert("5개까지 넣을 수 있습니다.");
        }
    });
});