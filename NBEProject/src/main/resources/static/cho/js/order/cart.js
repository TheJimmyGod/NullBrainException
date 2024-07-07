// 페이지 로드 시 실행될 함수
$(function(){
    $('.checkbox').change(function(){
        const item = $('.cart-item');
        let selectedPrice = 0;
        item.each(function (){
            console.log('hello')

            if($(this).find('.checkbox').is(':checked')){
                selectedPrice += parseInt($(this).find('.price').text());
            }
        });

        $('.totalPrice').text(selectedPrice);
    })
});

// 전체 선택 체크박스 클릭 시 모든 체크박스 선택/해제
function selectAll(selectAll) {
    const checkboxes = document.getElementsByName('chk');
    checkboxes.forEach((checkbox) => {
        checkbox.checked = selectAll.checked;
    });
    const items = document.getElementsByClassName('cart-item');
    Array.from(items).forEach((e) => {
       let p = parseInt(e.getElementsByClassName('price').item(0).innerHTML);
       totalPrice += p;
    });

    document.getElementsByClassName('totalPrice').item(0).innerHTML=totalPrice;
}


// 선택된 아이템들을 삭제할 때 호출되는 함수
function deleteSelected() {
    const ids = $('.checkbox:checked').map(function() {
        return $(this).val();
    }).get();

    console.log('Selected IDs for deletion:', ids); // 디버깅 메시지 추가

    if (ids.length > 0) {
        $('.selected').empty();
        var item = $('<input type="hidden" name="selectedItem">');
        item.val(ids);
        // 폼에 아이템 ID 설정
        $('.selected').append(item);
        $('#delete').submit();
    } else {
        alert('삭제할 항목을 선택해주세요.');
    }
}

function checkout(){
    const ids = $('.checkbox:checked').map(function() {
        return parseInt($(this).val());
    }).get();

    console.log('Selected IDs for deletion:', ids); // 디버깅 메시지 추가

    if (ids.length > 0) {
        $('.selected').empty();
        var item = $(`<input type="hidden" name="selectedItem" value=${ids}>`);
        // 폼에 아이템 ID 설정
        $('.selected').append(item);
        $('#pay').submit();
    } else {
        alert('삭제할 항목을 선택해주세요.');
    }
}

$(function(){
    $('.home').click(function(){
        location.href = 'http://localhost:8080/nbe/home';
    });

    $('.post').click(function(){
        location.href = 'http://localhost:8080/post/list';
    });

    $('.cart').click(function(){
        location.href = `http://localhost:8080/cart?userId=1`;
    });

    $('.recent').click(function(){
        location.href = 'http://localhost:8080/nbe/recent';
    });

    $('.user').click(function(){
        location.href = 'http://localhost:8080/mypage/detail';
    });

    $('.accButton').click(function (){

    });
});

function category() {
    $('.category1').toggleClass('hidden');
}

