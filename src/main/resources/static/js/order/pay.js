document.addEventListener("DOMContentLoaded", function() {
    const downIcon = document.querySelector('i[name="downicon"]');
    const upIcon = document.querySelector('i[name="upicon"]');
    const deliveryTitles = document.querySelectorAll('.delivery-title');
    const productImages = document.querySelectorAll('.productImage');
    const productInfos = document.querySelectorAll('.productInfo');
    const productInfos1 = document.querySelectorAll('.productInfo1');
    const deliverySections = document.querySelectorAll('.delivery');
    const boxes = document.querySelectorAll('.box');
    const hrTag = document.querySelector('hr');

    downIcon.addEventListener("click", function() {
        deliveryTitles.forEach(title => title.style.display = 'block');
        productImages.forEach(img => img.style.display = 'block');
        productInfos.forEach(info => info.style.display = 'block');
        productInfos1.forEach(info => info.style.display = 'block');
        boxes.forEach(box => box.style.display = 'block');
        deliverySections.forEach(section => section.style.display = 'block');
        downIcon.style.display = 'none';
        upIcon.style.display = 'inline';
        hrTag.style.marginTop = '0';
    });

    upIcon.addEventListener("click", function() {
        deliveryTitles.forEach(title => title.style.display = 'none');
        productImages.forEach(img => img.style.display = 'none');
        productInfos.forEach(info => info.style.display = 'none');
        productInfos1.forEach(info => info.style.display = 'none');
        boxes.forEach(box => box.style.display = 'none');
        deliverySections.forEach(section => section.style.display = 'none');
        downIcon.style.display = 'inline';
        upIcon.style.display = 'none';
        hrTag.style.marginTop = '20px'
    });
});

function showForm(formId) {
    var form = document.getElementById(formId);
    if (form.style.display === "block") {
        form.style.display = "none";
    } else {
        form.style.display = "block";
    }
}

function deleteAddress(button) {
    var addressInfo = button.closest('.addressInfo');
    addressInfo.remove();
}

function addAddress() {
    var container = document.getElementById('transformChange');
    var template = document.getElementById('address-container-template');
    var newAddress = template.cloneNode(true);
    newAddress.style.display = 'block';

    newAddress.id = '';
    container.appendChild(newAddress);
}

function openPopup() {
    var popup = document.getElementById('pop1');
    var dim = document.getElementById('dim');
    popup.style.zIndex = '1000'; // 팝업 열기: z-index를 높여서 보이도록 설정
    dim.style.zIndex = '900'; // 배경 표시: 팝업과 같이 z-index를 높여서 보이도록 설정
    popup.style.display = 'block'; // 팝업 보이기
    dim.style.display = 'block'; //
}

function closePopup() {
    var popup = document.getElementById('pop1');
    var dim = document.getElementById('dim');
    popup.style.zIndex = '-1'; // 팝업 닫기: z-index를 낮추어 숨기도록 설정
    dim.style.zIndex = '-2'; // 배경 숨기기: 팝업과 같이 z-index를 낮추어 숨기도록 설정
    popup.style.display = 'none'; // 팝업 숨기기
    dim.style.display = 'none'; // 배경 숨기기
}

//3.
document.getElementById('passwordRadio').addEventListener('change', function() {
    document.querySelector('.password-placeholder').style.display = 'none';
    document.getElementById('passwordField').style.display = 'block';
});

document.getElementById('freeEntryRadio').addEventListener('change', function() {
    document.querySelector('.password-placeholder').style.display = 'inline';
    document.getElementById('passwordField').style.display = 'none';
});

//4.
document.getElementById('easyPayButton').addEventListener('click', function() {
    document.getElementById('easyPayOptions').style.display = 'block';
    document.getElementById('cardPayOption').style.display = 'none';
});

document.getElementById('cardPayButton').addEventListener('click', function() {
    document.getElementById('easyPayOptions').style.display = 'none';
    document.getElementById('cardPayOption').style.display = 'block';
});