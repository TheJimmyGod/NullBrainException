let $form =  $("#contactForm");
$(window).on('load',function() {
    var today = new Date();
    var yyyy = today.getFullYear();
    var mm = String(today.getMonth() + 1).padStart(2, '0'); // January is 0!
    var dd = String(today.getDate()).padStart(2, '0');
    var hh = String(today.getHours()).padStart(2, '0');
    var min = String(today.getMinutes()).padStart(2, '0');

    var todayDate = yyyy + '-' + mm + '-' + dd + " "+ hh + ':' + min;
    $('#date').val(todayDate);
    $('#file1').change(handleFileSelect);
    $('#file2').change(handleFileSelect);
});

$(document).ready(function (){
    $(".submit-btn").click(function (event){
        event.preventDefault();
        let regex = /^[가-힣a-zA-Z0-9\s]+$/;
        let $title = $("#title");
        let $content = $("#content");
        let $files = $("[id*='file']");


        if ($title.val().trim() === "") {
            alert("제목을 입력해주세요.");
            $title.focus();
            return false;
        }

        if(!regex.test($title.val().trim()))
        {
            alert("올바르지 않는 타이틀입니다.");
            $title.focus();
            return false;
        }

        if ($content.val().trim() === "") {
            alert("내용을 입력해주세요.");
            $content.focus();
            return false;
        }

        if ($content.val().trim().length > 300) {
            alert("내용은 최대 300자 까지입니다.");
            $content.focus();
            return false;
        }
        let isExist = false;
        $files.each(function (index, element){
            if($(element).val() !== '')
            {
                console.log($(element).val());
                isExist = true;
            }
        });
        
        if(isExist === false)
        {
            alert("하나 이상의 첨부파일이 존재 하지 않습니다.");
            return false;
        }

        $form.submit();
        return true;
    });
});

function handleFileSelect(event) {
    const fileList = $('#fileList');
    const input = event.target;
    let index = $(input).attr('id').match(/\d+/);
    let file = event.target.files[0];

    if (file) {
    let fileItem = $('<div>');
    $(fileItem).addClass('file-item');
    $(fileItem).append(`<span>${index}번 ${file.name}</span><button type="button" class="remove-btn${index}" onclick="RemoveImageFile(this)">X</button>`);
    $(fileList).append(fileItem);
}
}

function CheckImageFile(file)
{
    let f = $(file);
    let extPoint = f.val().lastIndexOf(".");
    let ext = f.val().substring(extPoint + 1, f.val().length);
    let fileType = ext.toLowerCase();
    if(fileType !== "jpg" && fileType !== "png" && fileType !== "gif"
        && fileType !== "jpeg" && fileType !== "bmp")
    {
        alert("이미지 파일만 가능합니다. " + fileType);
        return false;
    }
    else
        return true;
}

function RemoveImageFile(file)
{
    let index = $(file).attr('class').match(/\d+/);
    let input = $("#file" + index);
    $(input).val('');
    $(file).parent().children('span').remove();
    $(file).parent().remove();
}