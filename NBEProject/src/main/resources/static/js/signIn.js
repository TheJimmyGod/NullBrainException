let $street = $('[name="street_search"]');
let $streetSelector = $('[name="streetName"]');
let $address = $('[name="street_addr"]');
let new_regex = /^[가-힣0-9\s]*$/;
let previous = "";
const dataArr = new Set();
const allDataArr = new Set();
let addAddressDataArr = [];

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

    $streetSelector.change(function (){
        let val = this.value;
        if(val === "")
        {
            return;
        }
        $('option.selected').removeClass('selected');
        $street.val(val);
        $address.val(addAddressDataArr.find(x=>x.key.includes(val)).value);
        $address.text($address.val());
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
        let url = `https://business.juso.go.kr/addrlink/addrLinkApiJsonp.do?resultType=json&confmKey=devU01TX0FVVEgyMDI0MDgxMTE0MTgyNDExNTAwMzE=&currentPage=1&countPerPage=1000&keyword=${keyWord};`
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
    $streetSelector.append($('<option>').text("<도로명주소 확인하십시오>").attr('value', ""));
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