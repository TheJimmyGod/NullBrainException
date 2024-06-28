// 페이지 로드 시 실행될 함수
$(document).ready(function(){
    $("#delItems").empty(); // 초기화 시 delItems 비우기
});

$(document).ready(function(){
    $("#selectItems").empty(); // 초기화 시 selectItems 비우기
});

// 전체 선택 체크박스 클릭 시 모든 체크박스 선택/해제
function selectAll(selectAll) {
    const checkboxes = document.getElementsByName('chk');
    checkboxes.forEach((checkbox) => {
        checkbox.checked = selectAll.checked;
        updateDelItems(checkbox); // 전체 선택 시에도 updateDelItems 호출
    });
}

// 개별 체크박스 상태 변경 시 선택된 아이템들을 관리하는 함수
function updateDelItems(checkbox) {
    const itemId = checkbox.value;
    if (checkbox.checked) {
        console.log(`Adding item with ID: ${itemId}`);
        if ($(`#delItems input[value='${itemId}']`).length == 0) {
            $("#delItems").append(`<input type='hidden' name='delitem' value='${itemId}'>`);
            // console.log(`deleteId`)
        }
        if ($(`#selectItems input[value='${itemId}']`).length == 0) {    // 아이템을 담아서 pay.html 에 보내려하고 하는 div
            $("#selectItems").append(`<input type='hidden' name='selectitem' value='${itemId}'>`);
            // console.log(`selectId`)
        }
    } else {
        console.log(`Removing item with ID: ${itemId}`);
        $(`#delItems input[value='${itemId}']`).remove();
        $(`#selectItems input[value='${itemId}']`).remove();
    }
}

// 선택된 아이템들을 삭제할 때 호출되는 함수
function deleteSelected() {
    const ids = $("#delItems input[name='delitem']").map(function() {
        return $(this).val();
    }).get();

    console.log('Selected IDs for deletion:', ids); // 디버깅 메시지 추가

    if (ids.length > 0) {
        $("#delItemIds").val(ids.join(",")); // 폼에 아이템 ID 설정
        document.getElementById('deleteForm').submit();
    } else {
        alert('삭제할 항목을 선택해주세요.');
    }
}

function checkout(){
    const ids = $("#selectItems input[name='selectitem']").map(function() {
        return $(this).val();
    }).get();

    console.log('Selected IDs for deletion:', ids); // 디버깅 메시지 추가

    if (ids.length > 0) {
        $("#selectedItemIds").val(ids.join(",")); // 폼에 아이템 ID 설정
        document.getElementById('paymentForm').submit();
    } else {
        alert('선택할 항목을 선택해주세요.');
    }
}

// function checkout(userId) {
//     // 선택된 아이템들의 ID를 배열로 모읍니다.
//     const selectedItems = [];
//     $('#selectItem input[name="selectedItemIds"]').each(function() {
//         selectedItems.push($(this).val());
//     });
//
//     // URL을 조합합니다. 예: /pay?userId=1
//     const url = `/pay?userId=${userId}`;
//
//     // AJAX 요청을 통해 서버에 선택된 아이템들의 ID를 전송합니다.
//     $.ajax({
//         type: "POST",
//         url: url,
//         data: JSON.stringify(selectedItems),
//         contentType: "application/json",
//         success: function(response) {
//             // 서버에서 처리한 결과에 따라 다음 작업을 수행합니다.
//             console.log("결제 처리 완료:", response);
//             // pay.html로 이동하거나 필요한 동작을 수행합니다.
//             window.location.href = url; // 예시로 pay.html로 이동하는 방식입니다.
//         },
//         error: function(error) {
//             console.error("결제 처리 중 오류 발생:", error);
//             // 오류 처리 로직을 추가합니다.
//         }
//     });
// }   // xml, controller, service, repository 고치 (상품의 정보들과, userid 봐야댐, 그리고 delete xml 참조하기)