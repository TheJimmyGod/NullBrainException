// 페이지 로드 시 실행될 함수


// 전체 선택 체크박스 클릭 시 모든 체크박스 선택/해제
function selectAll(selectAll) {
    const checkboxes = document.getElementsByName('chk');
    checkboxes.forEach((checkbox) => {
        checkbox.checked = selectAll.checked;
        updateDelItems(checkbox); // 전체 선택 시에도 updateDelItems 호출
    });
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
