let review_uuid = '';
const commentContainer = document.getElementById('comment-container');

document.addEventListener('DOMContentLoaded', function() {
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
                        <h4>${data.memberNickname}</h4>
                        <p>${new Date(data.createDate).toLocaleDateString('ko-KR')}</p>
                    </div>
                </li>
                <li>
                    <a href="#" class="icon fa-thumbs-up">
                        <span class="label">thumbsUp</span>
                        <span class="count">0</span>
                    </a>
                </li>
            </ul>
            <div>
                <p>${data.commentContent}</p>
            </div>
            <hr class="major"/>
        `;

    if(commentContainer.firstChild) {
        commentContainer.insertBefore(commentElement, commentContainer.firstChild);
    } else {
        commentContainer.appendChild(commentElement);
    }
}

function commentSubmit(email) {
    console.log("email",email);
    console.log("commentContent",commentContent);
    console.log("reviewId",review_uuid);

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