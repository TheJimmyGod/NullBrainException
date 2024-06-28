let i = 0;
let dataArr = [];
var popup = null;
$(document).ready(function (){
    $('[id*="addressUpdate"]').each(function (index, element){
        $(element).click(activate);
    });
});
$(function (){
    // [추가] 버튼 누르면 첨부 파일 추가 가능
    $("#addAddress").click(function (){

        if(i < 3)
        {
            if(popup === null || popup.closed)
                popup = window.open("createAddress", "주소 추가", "width=700, height=800, left=100, top=50");

        }
        else
        {
            alert("3개까지 넣을 수 있습니다.");
        }
    });

    $("#submitUpdate").click(function (){
        let $nickName = $("#nickname");
        let $phoneNum = $("#phoneNum");
        let $imageFile = $("#file");

        let phoneRegex = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
        let regex = /^[가-힣a-zA-Z]+$/;

        if($nickName.val().trim() === "")
        {
            alert("닉네임을 입력해주십시오.");
            return;
        }

        if(!regex.test($nickName.val().trim()))
        {
            alert("올바르지 않은 형식의 닉네임입니다.");
            return;
        }

        if($phoneNum.val().trim() === "")
        {
            alert("휴대폰 번호를 입력해주십시오.");
            return;
        }
        if(!phoneRegex.test($phoneNum.val().trim()))
        {
            alert("올바르지 않은 형식의 휴대폰 번호입니다.");
            return;
        }
        let isDefault = false;
        for (let dataArrElement of dataArr) {
            if(dataArrElement["isDefault"] === true)
            {
                isDefault = true;
                dataArrElement["isDefault"] = 1;
            }
            else
                dataArrElement["isDefault"] = 0;
        }
        
        if(isDefault === false)
        {
            alert("반드시 배송지 하나는 기본으로 설정하셔야 합니다.");
            return;
        }

        let formData = new FormData();

        formData.append("addresses", JSON.stringify(dataArr));
        formData.append("nickname", $nickName.val().trim());
        formData.append("phone", $phoneNum.val().trim());
        formData.append("file", $imageFile[0].files[0]);

        $.ajax({
            type: 'POST',
            url: 'update',
            contentType: false,
            processData: false,
            data: formData,
            success: function(response) {
                alert('정상적으로 저장이 되었습니다.');
            },
            error: function(xhr, status, error) {
                alert('에러가 발생했습니다.');
            }
        });
    });
});

function activate(){
    let elementIndex = $(this).attr("id").match(/\d+/);
    var data = dataArr.at(elementIndex);
    var dataToSend = {
        "index": elementIndex,
        "name":data["name"],
        "detailAdd": data["detail_addr"],
        "streetAdd": data["street_addr"],
        "isDefault": data["isDefault"]
    };
    if(popup === null || popup.closed)
    {
        popup = window.open("updateAddress", "주소 수정", "width=700, height=800, left=100, top=50");
        popup.onload = function() {
            popup.ReceiveDataFromParent(dataToSend);
        };
    }
}

function updateData(index, data) {
    if(data["isDefault"] === true)
    {
        for(let z = 0; z < dataArr.length; ++z)
        {
            dataArr[z]["isDefault"] = false;
        }
    }

    dataArr[index]["name"] = data["name"];
    dataArr[index]["detail_addr"] = data["detail_addr"];
    dataArr[index]["street_addr"] = data["street_addr"];
    dataArr[index]["isDefault"] = data["isDefault"];

    if(dataArr.length === 1)
    {
        data["isDefault"] = true;
    }

    let group = $("#addressGroup");

    while (group[0].firstChild){
        group[0].removeChild(group[0].firstChild);
    }

    for (let z = 0; z < dataArr.length; ++z)
    {
        if(dataArr[z]['isDefault'] === true)
        {
            group.append(`
        <tr th:id="address_num${z}">
            <td>
            <div class="col-12 col-sm-12 col-lg-12 p-2 addressTable">
            <b th:id="'basic_add${z}'">${dataArr[z]['name']}</b>
            <b style="color: blue" th:id="'isDefault${z}'">(기본배송지)</b>
            <p style="color: gray; font-size: 10px;" th:id="'street_add${z}'">${dataArr[z]['street_addr']}</p>
            <p style="color: gray; font-size: 10px;" th:id="'detailed_add${z}'">${dataArr[z]['detail_addr']}</p>
            <button type="button" class="btn" id="addressUpdate${z}" style="float: right; width: 75px;"><a id="btnColor">수정</a></button>
            </div>
            </td>
        </tr>
        `);
        }
        else
        {
            group.append(`
        <tr th:id="address_num${z}">
            <td>
            <div class="col-12 col-sm-12 col-lg-12 p-2 addressTable">
            <b th:id="'basic_add${z}'">${dataArr[z]['name']}</b>
            <p style="color: gray; font-size: 10px;" th:id="'street_add${z}'">${dataArr[z]['street_addr']}</p>
            <p style="color: gray; font-size: 10px;" th:id="'detailed_add${z}'">${dataArr[z]['detail_addr']}</p>
            <button type="button" class="btn" id="addressUpdate${z}" style="float: right; width: 75px;"><a id="btnColor">수정</a></button>
            </div>
            </td>
        </tr>
        `);
        }
    }
    $('[id*="addressUpdate"]').each(function (index, element){
        $(element).on("click", activate);
    });
}

function receiveData(data) {

    if(data["isDefault"] === true)
    {
        for(let z = 0; z < dataArr.length; ++z)
        {
            dataArr[z]["isDefault"] = false;
        }
    }

    dataArr.push(data);

    if(dataArr.length === 1)
    {
        data["isDefault"] = true;
    }

    let group = $("#addressGroup");

    while (group[0].firstChild){
        group[0].removeChild(group[0].firstChild);
    }

    for (let z = 0; z < dataArr.length; ++z)
    {
        if(dataArr[z]['isDefault'] === true)
        {
            group.append(`
        <tr th:id="address_num${z}">
            <td>
            <div class="col-12 col-sm-12 col-lg-12 p-2 addressTable">
            <b th:id="'basic_add${z}'">${dataArr[z]['name']}</b>
            <b style="color: blue" th:id="'isDefault${z}'">(기본배송지)</b>
            <p style="color: gray; font-size: 10px;" th:id="'street_add${z}'">${dataArr[z]['street_addr']}</p>
            <p style="color: gray; font-size: 10px;" th:id="'detailed_add${z}'">${dataArr[z]['detail_addr']}</p>
            <button type="button" class="btn" id="addressUpdate${z}" style="float: right; width: 75px;"><a id="btnColor">수정</a></button>
            </div>
            </td>
        </tr>
        `);
        }
        else
        {
            group.append(`
        <tr th:id="address_num${z}">
            <td>
            <div class="col-12 col-sm-12 col-lg-12 p-2 addressTable">
            <b th:id="'basic_add${z}'">${dataArr[z]['name']}</b>
            <p style="color: gray; font-size: 10px;" th:id="'street_add${z}'">${dataArr[z]['street_addr']}</p>
            <p style="color: gray; font-size: 10px;" th:id="'detailed_add${z}'">${dataArr[z]['detail_addr']}</p>
            <button type="button" class="btn" id="addressUpdate${z}" style="float: right; width: 75px;"><a id="btnColor">수정</a></button>
            </div>
            </td>
        </tr>
        `);
        }

    }
    $('[id*="addressUpdate"]').each(function (index, element){
        $(element).on("click", activate);
    });
    i++;
}

function initialize(data)
{
    let group = $("#addressGroup");

    while (group[0].firstChild){
        group[0].removeChild(group[0].firstChild);
    }
    dataArr.push(data);

    for (let z = 0; z < dataArr.length; ++z)
    {
        if(dataArr[z]['isDefault'] === true)
        {
            group.append(`
        <tr th:id="address_num${z}">
            <td>
            <div class="col-12 col-sm-12 col-lg-12 p-2 addressTable">
            <b th:id="'basic_add${z}'">${dataArr[z]['name']}</b>
            <b style="color: blue" th:id="'isDefault${z}'">(기본배송지)</b>
            <p style="color: gray; font-size: 10px;" th:id="'street_add${z}'">${dataArr[z]['street_addr']}</p>
            <p style="color: gray; font-size: 10px;" th:id="'detailed_add${z}'">${dataArr[z]['detail_addr']}</p>
            <button type="button" class="btn" id="addressUpdate${z}" style="float: right; width: 75px;"><a id="btnColor">수정</a></button>
            </div>
            </td>
        </tr>
        `);
        }
        else
        {
            group.append(`
        <tr th:id="address_num${z}">
            <td>
            <div class="col-12 col-sm-12 col-lg-12 p-2 addressTable">
            <b th:id="'basic_add${z}'">${dataArr[z]['name']}</b>
            <p style="color: gray; font-size: 10px;" th:id="'street_add${z}'">${dataArr[z]['street_addr']}</p>
            <p style="color: gray; font-size: 10px;" th:id="'detailed_add${z}'">${dataArr[z]['detail_addr']}</p>
            <button type="button" class="btn" id="addressUpdate${z}" style="float: right; width: 75px;"><a id="btnColor">수정</a></button>
            </div>
            </td>
        </tr>
        `);
        }
    }
    $('[id*="addressUpdate"]').each(function (index, element){
        $(element).on("click", activate);
    });
}


function loadImage(input){
    let file = input.files[0];

    var img = URL.createObjectURL(file);
    if(img === null)
    {
        console.log("Error!");
        return;
    }
    else
    {
        var $dummy = $('#dummyProfile');
        console.log($dummy);
        $dummy.remove();

        var $newProfile = $('<img>');

        $newProfile.addClass('ms-4');
        $newProfile.addClass('mt-3');

        $newProfile.addClass('col-lg-4');
        $newProfile.addClass('col-sm-6');
        $newProfile.addClass('col-12');
        $newProfile.attr('id', "dummyProfile");

        $newProfile.attr('src', img);

        $newProfile.css('width', '120px');
        $newProfile.css('height', '120px');
        $newProfile.css('visibility', 'visible');
        $newProfile.css('float','left');
        $newProfile.css('margin-right', '10%');

        var $container = $("#profile");
        $container.append($newProfile);
    }

}