let projectReviewId = '';
let loginEmail = '';

document.addEventListener('DOMContentLoaded', function () {
    getMemberInProjectWrite();
});

document.addEventListener('DOMContentLoaded', function() {
    const urlParams = new URLSearchParams(window.location.search);
    projectReviewId = urlParams.get('id');

    if (projectReviewId !== null && projectReviewId !== '') {
        renderCodeReview(projectReviewId);
    }
});
function getMemberInProjectWrite() {
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
        })
        .catch(error => {
            console.error('Fetch error:', error);
        });
}

function saveProjectReview() {
    const title = document.getElementById('projectTitle').value;
    const content = ProjectEditor.getHTML();
    const tags = tagify.value.map(tag => tag.value);
    const githubUrl = document.getElementById('github-url').value;

    console.log(`제목: ${title}`);
    console.log(`내용: ${content}`);
    console.log("이메일: "+loginEmail);
    console.log("태그들 : "+tags);
    console.log("깃헙 주소 : ",githubUrl);

    formData.append('memberEmail',loginEmail);
    formData.append('title',title);
    formData.append('content',content);
    formData.append('githubUrl',githubUrl);
    tags.forEach(tag => formData.append('tags', tag));

    console.log("폼데이터: ",+formData);
    for (const [key, value] of formData.entries()) {
        console.log(`${key}: ${value}`);
    }

    if (projectReviewId !== null && projectReviewId !== '') {
        modify(loginEmail, title, content);
    } else {
        fetch(`/api/project/save`, {
            method: 'POST',
            body: formData,
        }).then(resp => {
            if (!resp.ok) {
                throw new Error("fail to fetch");
            }
            return resp.json();
        }).then(resp => {
            window.location.href = '/project/';
        }).catch(error => {
            console.error(error);
        });
    }

}
