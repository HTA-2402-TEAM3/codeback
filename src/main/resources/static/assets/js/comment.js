let review_uuid = '';
const commentContainer = document.getElementById('comment-container');

document.addEventListener('DOMContentLoaded', function () {
    const pathSegments = window.location.pathname.split('/'); // 경로를 '/'로 나누기
    const uuid = pathSegments[pathSegments.length - 1]; // 마지막 요소가 UUID

    console.log(uuid); // UUID 출력
    review_uuid = uuid;
});

function renderComment(data) {
    console.log(data);
    const commentElement = document.createElement('div');
    commentElement.innerHTML = `
           
    <ul>
        <li>
            <div>
                <h4>${data.memberNickname}</h4
            </div>
        </li>
        <li>
            <a href="#" class="icon fa-thumbs-up">
                <span class="label">thumbsUp</span>
                <span class="count">0</span>
            </a>
        </li>
    </ul>
    <div class="comment_box">
        <div>
            <p>${new Date(data.createDate).toLocaleDateString('ko-KR')}</p>
        </div>

    </div>
    <div id="comment1">
        <p>${data.commentContent}</p>
    </div>
    <div class="comment_delete">
        <a class="icon fa-pencil" id="modifyIcon"
           onClick="modifyComment(${data.id}, ${data.commentContent})"></a>
        <a class="icon fa-trash"
           onclick="deleteComment(${data.id})"></a>
    </div>
    <hr class="major"/>`;

    if (commentContainer.firstChild) {
        commentContainer.insertBefore(commentElement, commentContainer.firstChild);
    } else {
        commentContainer.appendChild(commentElement);
    }
}

function commentSubmit(email) {
    console.log("email", email);
    console.log("commentContent", commentContent);
    console.log("reviewId", review_uuid);

    fetch(`/api/review/comment/save`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            codeReviewId: review_uuid,
            content: commentContent,
            memberEmail: email
        })
    }).then(resp => {
        if (!resp.ok) {
            throw new Error("fail to fetch");
        }
        return resp.json();
    }).then(resp => {
        renderComment(resp)
        editor.setHTML('');
    }).catch(error => {
        console.error(error);
    });
}

function getMemberInComment() {
    commentSubmit("keyy1315@naver.com");


    // fetch(`/api/member/info`)
    //     .then(response => {
    //         if (!response.ok) {
    //             throw new Error('Network response was not ok');
    //         }
    //         return response.json();
    //     })
    //     .then(data => {
    //         console.log("data ::::", data.email);
    //         email = data.email;
    //         commentSubmit(email)
    //     })
    //     .catch(error => {
    //         console.error('Fetch error:', error);
    //     });
}

function deleteComment(commentID) {
    console.log("commentID", commentID);
    if (confirm("댓글을 정말 삭제하시겠습니까?")) {
        fetch(`/api/review/comment/${commentID}`, {
            method: 'DELETE'
        })
            .then(resp => {
                if (resp.ok) {
                    alert("삭제되었습니다.");
                    location.reload();
                } else {
                    throw new Error("err");
                }
            }).catch(error => {
            console.error(error);
        })
    }
}

function updateComments(commentId, content) {
    console.log("updateComments() : ", commentId);
    console.log("updateComments() : ", content);

    fetch(`/api/review/comment/update`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: commentId,
            content: content,
        })
    }).then(resp => {
        if (!resp.ok) {
            throw new Error("fail to fetch");
        }
        return resp.json();
    }).then(resp => {
        window.location.href = '/review/';
    }).catch(error => {
        console.error(error);
    });
}

function modifyComment(commentId, commentContent) {
    console.log("commentContent : "+commentContent);


    const comment1 = document.getElementById('comment1');
    comment1.innerHTML = '';

    const modifyIcon = document.getElementById('modifyIcon');

    const turndownService = new TurndownService();
    const markdown = turndownService.turndown(commentContent);

    const editorDiv = document.createElement('div');
    editorDiv.id = 'commentEditor';
    comment1.appendChild(editorDiv);

    const commentEditor = toastui.Editor;

    const editorAtComment = new commentEditor({
        el: document.querySelector('#commentEditor'),
        height: '300px',
        initialEditType: 'markdown',
        previewStyle: 'vertical'
    });

    editorAtComment.setMarkdown(markdown); // toastui Editor에 content 설정

    modifyIcon.onclick = function () {
        const newContent = editorAtComment.getHTML();
        updateComments(commentId, newContent);
    }
}