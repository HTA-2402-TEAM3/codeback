let review_uuid = '';
const commentContainer = document.getElementById('comment-container');
// let loginEmail = '';

document.addEventListener('DOMContentLoaded', function () {
    const pathSegments = window.location.pathname.split('/'); // 경로를 '/'로 나누기
    // 마지막 요소가 UUID
    review_uuid = pathSegments[pathSegments.length - 1];
    hiddenIcon();
});

const escapeHtml = (unsafe) => {
    return unsafe
        .replace(/&/g, "&amp;")
        .replace(/</g, "&lt;")
        .replace(/>/g, "&gt;")
        .replace(/"/g, "&quot;")
        .replace(/'/g, "&#039;");
}


async function commentSubmit(mapping) {
    const loginEmail = await getMemberEmail();

    if (loginEmail === '' || loginEmail === undefined || loginEmail === null) {
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
            editor.setHTML('');
            location.reload();
        }).catch(error => {
            console.error(error);
        });
    }
}

async function hiddenIcon() {
    const loginEmail = await getMemberEmail();
    const codeReviewWriterEmail = document.getElementById("codeReviewWriter").dataset.email;

    if (codeReviewWriterEmail === loginEmail) {
        const codeReviewIcon = document.querySelector('.icon_view');
        codeReviewIcon.removeAttribute("hidden");
    }

    const comments = document.querySelectorAll('#commentElements')
    comments.forEach(comment => {
        const memberInfo = comment.querySelector('.memberInfo').dataset.email;
        if (memberInfo === loginEmail) {
            const IconElement = comment.querySelector('.comment_delete');
            IconElement.removeAttribute("hidden");

        }
    })
}

async function deleteComment(mapping, commentID) {
    const loginEmail = await getMemberEmail();
    if (loginEmail === '' || loginEmail === undefined || loginEmail === null) {
        alert("로그인 해주세요");
    } else {
        if (confirm("댓글을 정말 삭제하시겠습니까?")) {
            fetch(`/api/${mapping}/comment/${commentID}?memberEmail=${loginEmail}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json', // 필요한 경우
                },
            }).then(resp => {
                if (!resp.ok) {
                    throw new Error("fail to fetch");
                }
                return resp.json();
            }).then(resp => {
                alert(resp.message);
                location.reload();
            }).catch(error => {
                console.error(error);
            });
        }
    }
}

async function updateComments(mapping, commentId, content) {
    const loginEmail = await getMemberEmail();
    if (loginEmail === '' || loginEmail === undefined || loginEmail === null) {
        alert("로그인 해주세요");
    } else {
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
            alert(resp.message);
            location.reload();
        }).catch(error => {
            console.error(error);
        });
    }
}

let lastCommentId;
let lastCommentContent;

function modifyComment(mapping, commentId, commentContent) {
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
