$(function (){

    $("#submitAddress").click(function (event){

        event.preventDefault();

        let $name = $("#name");
        let $address = $("#address");
        let $subName = $("#subName").val().trim();
        let $street = $("#path");
        let $basicAdd = $("#basic");

        let regex = /^[가-힣a-zA-Z]+$/;
        let streetRegex = /(([가-힣]+(d|d(,|.)d|)+(읍|면|동|가|리))(^구|)((d(~|-)d|d)(가|리|)|))([ ](산(d(~|-)d|d))|)|(([가-힣]|(d(~|-)d)|d)+(로|길))/;

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

        if ($address.val().trim() === "") {
            alert("상세주소를 입력해주세요.");
            $address.focus();
            return false;
        }
        let newName = $name.val().trim();
        if($subName.trim() !== "")
        {
            newName = newName.concat("(" + $subName + ")");
        }

        let data = {
            "name": newName,
            "detail_addr": $address.val().trim(),
            "street_addr": $street.val().trim(),
            "isDefault": $basicAdd.is(":checked"),
        };

        window.opener.receiveData(data);

        window.close();
    });
});