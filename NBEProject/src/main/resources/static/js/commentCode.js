$(function(){
    // 글 [삭제] 버튼
    $("#btnDel").click(function(){
        let answer = confirm("삭제하시겠습니까?");
        answer && $("form[name='frmDelete']").submit();
    });

    // 현재 글의 댓글들을 불러온다
    loadComment(id);

    $("#btn_comment").click(function(){
        const content = $("#input_comment").val().trim();

        // 검증
        if(!content){
            alert("댓글 입력을 하세요");
            $("#input_comment").focus();
            return;
        }

        // 전달할 parameter 준비 (POST)
        const data = {
            "post_id": id,
            "user_id": logged_id.id,
            "content": content,
        };

        $.ajax({
            url: "/post/comment/write/" + id,
            type: "POST",
            data: data,
            cache: false,
            success: function(data, status){
                if(status === "success"){
                    if(data.status !== "OK"){
                        alert(data.status);
                        return;
                    }
                    loadComment(id);  // 댓글목록 다시 업데이트
                    $("#input_comment").val('');
                }
            },
        });
    });

});

// 특정 글(post_id) 의 댓글 목록 읽어오기
function loadComment(post_id){
    $.ajax({
        url: "/post/comment/list/" + post_id,
        type: "POST",
        data: {},
        cache: false,
        success: function(data, status){
            if(status === "success"){
                // 서버쪽 에러 메세지 있는경우
                if(data.status !== "OK"){
                    alert(data.status);
                    return;
                }

                buildComment(data);  // 댓글 화면 렌더링

                // ★댓글목록을 불러오고 난뒤에 삭제에 대한 이벤트 리스너를 등록해야 한다
                addDelete();
            }
        },
    });

}

function buildComment(result){
    $("#cmt_cnt").text(result.count);   // 댓글 총 개수

    const out = [];
    result.data.forEach(comment => {
        let id = comment.id;
        let content = comment.content.trim();
        let regdate = comment.regdate;

        let user_id = parseInt(comment.user.id);
        let name = comment.user.name;

        let profile = '/upload/' + comment.user.profileimage;
        let img = new Image();
        img.src = profile;

        // 삭제버튼 여부
        const delBtn = (logged_id.id !== user_id) ? '' : `
            <i class="btn fa-solid fa-delete-left text-danger" style="float: right" data-bs-toggle="tooltip" data-cmtdel-id="${id}"  title="삭제"></i>
        `;

        const row = `
            <tr>
                <td><span><img class="col-lg-1 col-sm-1 col-1" style="height: 24px; width: 24px;" th:if="${comment.user.profileimage}" src="${profile}" th:alt="'None"/>
                <strong>${name}</strong><br></span></td>
                <td><span>${content}</span></td>
                <td><span><small class="text-secondary" style="float: right">${regdate}</small></span></td>
                <td><span>${delBtn}</span></td>
            </tr>
        `;
        out.push(row);
    });

    $("#cmt_list").html(out.join('\n'));
}


// 댓글 삭제 버튼이 눌렸을때, 해당 댓글 삭제하는 이벤트를 등록
function addDelete(){
    $("[data-cmtdel-id]").click(function(){
        if(!confirm("댓글을 삭제하시겟습니까?")) return;

        // 삭제할 댓글의 id
        const comment_id = $(this).attr("data-cmtdel-id");

        $.ajax({
            url: "/post/comment/delete/" + id,
            type: "POST",
            cache: false,
            data: {"id": comment_id},
            success: function(data, status){
                if(status == "success"){
                    if(data.status !== "OK"){
                        alert(data.status);
                        return;
                    }

                    // 삭제후에도 댓글 목록 불러와야 한다
                    loadComment(id);
                }
            },
        });

    });
}