let $form =  $("#reviewForm");
$(window).on('load',function() {
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


function RemoveImageFile(file) {
    let index = $(file).attr('class').match(/\d+/);
    let input = $("#file" + index);
    $(input).val('');
    $(file).parent().children('span').remove();
    $(file).parent().remove();
}



    // JavaScript 코드
    const stars = document.querySelectorAll('.rating .star');
    let value
    stars.forEach(star => {
        star.addEventListener('click', function () {
            value = parseInt(star.getAttribute('data-value'));
            stars.forEach(s => {
                if (parseInt(s.getAttribute('data-value')) <= value) {
                    s.classList.add('active');
                } else {
                    s.classList.remove('active');
                }
            });
            console.log("Rating: " + value);
            $("input[name='rate']").val(value);
        });
    });


