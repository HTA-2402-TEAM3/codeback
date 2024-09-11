let review_uuid = '';
const commentContainer = document.getElementById('comment-container');
let loginEmail = '';

document.addEventListener('DOMContentLoaded', function () {
    const pathSegments = window.location.pathname.split('/'); // 경로를 '/'로 나누기
    // 마지막 요소가 UUID
    review_uuid = pathSegments[pathSegments.length - 1];

    getMemberInComment()
});

// function renderComment(mapping, data) {
//     console.log(mapping);
//
//
//     const commentID = data.id;
//     const commentContent = data.commentContent;
//
//     const commentElement = document.createElement('div');
//     commentElement.innerHTML = `
//
//     <ul>
//         <li>
//             <div>
//                 <h4>${data.nickname}</h4
//             </div>
//         </li>
//         <li>
//             <a href="#" class="icon fa-thumbs-up">
//                 <span class="label">thumbsUp</span>
//                 <span class="count">0</span>
//             </a>
//         </li>
//     </ul>
//     <div class="comment_box">
//         <div>
//             <p>${new Date(data.createDate).toLocaleDateString('ko-KR')}</p>
//         </div>
//
//     </div>
//     <div id="${data.id}">
//         ${data.commentContent}
//     </div>
//
//     <div class="comment_delete">
//         <a class="icon fa-pencil" id="modify${data.id}"
//            onClick="modifyComment('${mapping}','${commentID}', '${escapeHtml(commentContent)}')"></a>
//         <a class="icon fa-trash"
//            onclick="deleteComment('${mapping}','${commentID}')"></a>
//     </div>
//     <hr class="major"/>`;
//
//     if (commentContainer.firstChild) {
//         commentContainer.insertBefore(commentElement, commentContainer.firstChild);
//     } else {
//         commentContainer.appendChild(commentElement);
//     }
// }

const escapeHtml = (unsafe) => {
    return unsafe
        .replace(/&/g, "&amp;")
        .replace(/</g, "&lt;")
        .replace(/>/g, "&gt;")
        .replace(/"/g, "&quot;")
        .replace(/'/g, "&#039;");
}

function commentSubmit(mapping) {

    console.log("email", loginEmail);
    console.log("commentContent", commentContent);
    console.log("reviewId", review_uuid);

    if(loginEmail === '' || loginEmail === undefined) {
        alert("로그인 해주세요");
    } else {
        fetch(`/api/${mapping}/comment/save`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                reviewId: review_uuid,
                content: commentContent,
                memberEmail: loginEmail
            })
        }).then(resp => {
            if (!resp.ok) {
                throw new Error("fail to fetch");
            }
            return resp.json();
        }).then(resp => {
            console.log(resp);
            editor.setHTML('');
            location.reload();
        }).catch(error => {
            console.error(error);
        });
    }
}

function hiddenIcon() {
    const codeReviewWriterEmail = document.getElementById("codeReviewWriter").dataset.email;
    console.log("hiddenIcon() email val: ", codeReviewWriterEmail);

    if(codeReviewWriterEmail === loginEmail) {
        const codeReviewIcon = document.querySelector('.icon_view');
        codeReviewIcon.removeAttribute("hidden");
    }

    const comments = document.querySelectorAll('#commentElements')
    comments.forEach(comment => {
        const memberInfo = comment.querySelector('.memberInfo').dataset.email;
        console.log("memberInfo.value: ", memberInfo);
        if(memberInfo === loginEmail) {
            const IconElement = comment.querySelector('.comment_delete');
            IconElement.removeAttribute("hidden");

        }
    })
}

function getMemberInComment() {
    fetch(`/api/member/info`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log("data ::::", data);
            loginEmail = data.email;
            hiddenIcon();
        })
        .catch(error => {
            console.error('Fetch error:', error);
        });
}

function deleteComment(mapping,commentID) {
    console.log(mapping);

    console.log("commentID", commentID);
    if (confirm("댓글을 정말 삭제하시겠습니까?")) {
        fetch(`/api/${mapping}/comment/${commentID}?memberEmail=${loginEmail}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json', // 필요한 경우
            },
        }).then(resp => {
            console.log(resp);
            alert(resp.message);
            location.reload();
        }).catch(error => {
            console.error(error);
        })
    }
}

function updateComments(mapping,commentId, content) {
    console.log(mapping);

    console.log("updateComments() : ", commentId);
    console.log("updateComments() : ", content);

    fetch(`/api/${mapping}/comment/update`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: commentId,
            content: content,
            memberEmail: loginEmail
        })
    }).then(resp => {
        if (!resp.ok) {
            throw new Error("fail to fetch");
        }
        return resp.json();
    }).then(resp => {
        console.log(resp);

        alert(resp.message);
        location.reload();
    }).catch(error => {
        console.error(error);
    });
}

let lastCommentId;
let lastCommentContent;

function modifyComment(mapping, commentId, commentContent) {
    console.log(mapping);

    console.log("commentID : " + commentId);
    console.log("commentContent : " + commentContent);

    if (lastCommentId === null) {
        lastCommentId = commentId;
        lastCommentContent = commentContent;
    } else if (lastCommentId !== commentId) {
        const comment = document.getElementById(lastCommentId);
        if (comment) {
            comment.innerHTML = lastCommentContent;
        }
        lastCommentId = commentId;
        lastCommentContent = commentContent;
    }
    const comment = document.getElementById(`${commentId}`);
    comment.innerHTML = '';

    const turndownService = new TurndownService();
    const markdown = turndownService.turndown(commentContent);

    const modifyIcon = document.getElementById(`modify${commentId}`);

    const editorDiv = document.createElement('div');
    editorDiv.id = 'commentEditor';
    comment.appendChild(editorDiv);

    const commentEditor = toastui.Editor;

    const editorAtComment = new commentEditor({
        el: document.querySelector('#commentEditor'),

        toolbarItems: [
            ['heading', 'bold', 'italic', 'strike'],
            ['hr', 'quote'],
            ['ul', 'ol', 'task', 'indent', 'outdent'],
            ['table', 'link'],
            ['code', 'codeblock'],
            ['scrollSync'],
        ],
        autofocus: false,
        height: '300px',
        initialEditType: 'markdown',
        previewStyle: 'vertical'
    });

    editorAtComment.setMarkdown(markdown); // toastui Editor에 content 설정

    modifyIcon.onclick = function () {
        const newContent = editorAtComment.getHTML();
        updateComments(mapping, commentId, newContent);
    }
}
