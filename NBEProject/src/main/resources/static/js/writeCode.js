let writeImageMap = new Map();
let startIndex = 0;
$(function (){
    // [추가] 버튼 누르면 첨부 파일 추가 가능
    $("#btnAdd").click(function (){
        if($("#files").children().length >= 5)
        {
            alert("최대 5개까지 추가 가능합니다.");
        }

        else
        {
            if($("#files").children().length >= 1)
            {
                let fail = false;
                $("[name^='upfile']").each(function (index, element){
                    if($(this).get(0).files[0] === undefined)
                    {
                        alert("먼저 파일을 선택해주십시요");
                        fail = true;
                    }
                });
                if(fail)
                {
                    fail = false;
                    return;
                }
            }
            $("#files").append(`<div class="input-group mb-2">
   <input class="form-control col-xs-3" type="file" id="imageList" name="upfile${startIndex}" accept="image/jpeg, image/png, image/jpg" onchange="Display(this, ${startIndex})"/>
    </div>`);
            startIndex++;
        }
    });
});

$(document).ready(function (){
    $(document).on('click', "[class*='delBtn']",function ()
    {
        let _code = parseInt($(this).attr("id").match(/\d+/));
        DeleteShortcut(_code);
    });

    $(document).on('mouseenter', "[class*='delBtn']",function ()
    {
        let _code = parseInt($(this).attr("id").match(/\d+/));
        $("[name='upfile" + _code + "']").addClass("selectInput");
    });
    $(document).on('mouseleave', "[class*='delBtn']",function ()
    {
        let _code = parseInt($(this).attr("id").match(/\d+/));
        $("[name='upfile" + _code + "']").removeClass("selectInput");
    });
});

function DeleteShortcut(index)
{
    $("#shortcut" + index).parent().remove();
    $("[name='upfile" + index + "']").parent().remove();
    writeImageMap.set(index, {"active":false, "line":null})
    $(`#shortcutBlock${index}`).remove();
}

function Display(input ,index)
{
    let file = input.files[0];
    let $exist = $(`#shortcut${index}`);
    if(input.files.length === 0)
    {
        if($exist != null)
        {
            DeleteShortcut(index);
        }
        return;
    }

    var img = URL.createObjectURL(file);
    if(img === null)
    {
        alert("이미지에 오류가 발생했습니다.");
        return;
    }
    else
    {
        if($exist != null)
            $exist.parent().remove();
        var $newShortcut = $('<img>');

        $newShortcut.addClass('col-lg-2');
        $newShortcut.addClass('col-sm-2');
        $newShortcut.addClass('col-2');
        $newShortcut.addClass(`delBtn${index}`);
        $newShortcut.addClass("selectImage");
        $newShortcut.attr('id', `shortcut${index}`);

        $newShortcut.attr('src', img);

        $newShortcut.css('width', '150px');
        $newShortcut.css('height', '150px');
        $newShortcut.css('visibility', 'visible');
        $newShortcut.css('float','left');
        $newShortcut.css('margin-right','2%');

        let line = $(`<span id="shortcutBlock${index}"></span>`);
        $(line).append($newShortcut);
        var $container = $("#shortcutGroup");
        $container.children().each(function (index,element){
            $(this).remove();
        });

        writeImageMap.set(index, {"active":true, "line":line});
        for (let z = 0; z < writeImageMap.size; z++) {
            if(writeImageMap.get(z)["active"] === false)
                continue;
            else
                $container.append(writeImageMap.get(z)["line"]);
        }
    }

}
