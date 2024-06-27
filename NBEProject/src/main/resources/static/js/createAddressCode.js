let $name = $("#name");
let $address = $("#address");
let $address2 = $("#address2");
let $subName = $("#subName");
let $street = $("#path");
let $basicAdd = $("#basic");
let $streetSelector = $("#pathName");
let regex = /^[가-힣a-zA-Z]+$/;
let new_regex = /^[a-zA-Zㄱ-힣0-9]*$/;
let streetRegex = /(([가-힣]+(d|d(,|.)d|)+(읍|면|동|가|리))(^구|)((d(~|-)d|d)(가|리|)|))([ ](산(d(~|-)d|d))|)|(([가-힣]|(d(~|-)d)|d)+(로|길))/;
let previous = "";
const dataArr = new Set();
const allDataArr = new Set();
let addAddressDataArr = [];
$(function (){
    $("#submitAddress").click(function (event){

        event.preventDefault();
        if ($name.val().trim() === "") {
            alert("이름을 입력해주세요.");
            $name.focus();
            return false;
        }

        if (!regex.test($name.val().trim())) {
            alert("올바른 형식의 이름이 아닙니다.");
            $name.focus();
            return false;
        }

        if($street.val().trim() === ""){
            alert("도로명주소를 입력해주세요.");
            $street.focus();
            return false;
        }

        if(!streetRegex.test($street.val().trim())) {
            alert("올바른 형식의 도로명주소가 아닙니다.");
            $street.focus();
            return false;
        }

        if ($address2.val().trim() === "") {
            alert("상세주소를 입력해주세요.");
            $address2.focus();
            return false;
        }

        let newName = $name.val().trim();
        if($subName.val().trim() !== "")
        {
            newName = newName.concat("(" + $subName.val().trim() + ")");
        }
        let data = {
            "name": newName,
            "detail_addr": $address.val().trim() + " " + $address2.val().trim(),
            "street_addr": $street.val().trim(),
            "isDefault": $basicAdd.is(":checked"),
        };

        window.opener.receiveData(data);

        window.close();
    });
});

$(document).ready(function (){

    function updateData(ms)
    {
        return new Promise(resolve=>
        {
            // setTimeout(함수, ms); // ms(milliseconds) 이후에 함수호출
            setTimeout(()=>{
                if($street.is(":focus"))
                {
                    pathSearch($street.val().trim());
                }
                else
                {
                    if($street.val().trim() === "")
                        resetPath();
                }
                setTimeout(()=>{updateData(ms)}); // 무한루프도 재귀호출도 아님
            }, ms);
        });
    }


    async function autoSearch()
    {
        await updateData(1);
    }

    autoSearch();

    $("#pathName").change(function (){
        let val = this.value;
        if(val === "")
            return;
        $('option.selected').removeClass('selected');
        $street.val(val);
        $address.val(addAddressDataArr.find(x=>x.key.includes(val)).value);
        $(this).find('option:selected').addClass('selected');
    });
});

function pathSearch(street){

    if(previous !== $street.val().trim())
        previous = $street.val().trim();
    else
        return;
    if(street === null)
    {
        resetPath();
        return;
    }

    if(street === "")
    {
        resetPath();
        return;
    }
    $streetSelector.prop("disabled",false);
    if(new_regex.test(street))
    {
        //let keyWord = encodeURIComponent(street);
        let keyWord = encodeURIComponent(street);
        let url = `https://business.juso.go.kr/addrlink/addrLinkApiJsonp.do?resultType=json&confmKey=devU01TX0FVVEgyMDI0MDYyNzA5MjE0MzExNDg2OTQ=&currentPage=1&countPerPage=1000&keyword=${keyWord};`
        $.ajax({
            url: url,
            type: "POST",
            crossDomain:true,
            dataType:"jsonp",
            cache: false,
            success: function(data, status){
                readData(data);
            },
            error: function (error){
                console.log("Error: " + error);
            }
        });
    }
}

function readData(jsonObj){
    dataArr.clear();
    $streetSelector.empty();
    for(item of jsonObj.results.juso){
        let road = item.rn + " " + item.buldMnnm;
        if(!allDataArr.has(road))
        {
            allDataArr.add(road);
            addAddressDataArr.push({key: road, value: item.jibunAddr});
        }

    }

    sortArr(allDataArr);
    for (let data of allDataArr) {
        if(data.includes($street.val().trim()))
        {
            if(!dataArr.has(data))
            {
                dataArr.add(data);
            }
        }
    }
    sortArr(dataArr);
    $streetSelector.append($('<option>').text("<도로명주소 선택란>").attr('value', ""));
    for (let data of dataArr) {
        var $data = $('<option>').text(data).attr('value', data);
        $streetSelector.append($data);
    }
}
function sortArr(set) {
    const entries = [];
    for (const item of set) {
        entries.push(item);
    }
    set.clear();
    for (const entry of entries.sort()) {
        set.add(entry);
    }
    return set;
}

function resetPath(){
    $streetSelector.empty();
    $streetSelector.append($('<option>').text("도로명 주소 입력해주세요").attr('value', ""));
    $streetSelector.prop("disabled",true);
}