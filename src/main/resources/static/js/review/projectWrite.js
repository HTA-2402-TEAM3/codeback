let projectReviewId = '';
// let loginEmail = '';

const renderFormData = new FormData();

document.addEventListener('DOMContentLoaded', function () {
    const urlParams = new URLSearchParams(window.location.search);
    projectReviewId = urlParams.get('id');

    if (projectReviewId !== '' && projectReviewId !== null) {
        renderProjectReview(projectReviewId);
    }
    getMemberEmail();
    console.log("dom function");
});

function renderProjectReview(reviewID) {
    console.log("renderProjectReview() : ", reviewID);

    fetch(`/api/project/get/${reviewID}`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json'
        }
    }).then(resp => {
        if (!resp.ok) {
            throw new Error('Network response was not ok');
        }
        return resp.json();
    }).then(data => {
        console.log(data);
        renderProjectData(data);
    })
        .catch(error => {
            console.error('Fetch error:', error);
        });
}

function renderProjectData(data) {
    // title과 content를 설정
    console.log("renderData() : " + data);

    document.getElementById('projectTitle').value = data.title;

    const turndownService = new TurndownService();
    const markdown = turndownService.turndown(data.content);

    ProjectEditor.setMarkdown(markdown); // toastui Editor에 content 설정

    // image 설정
    data.images.forEach(image => {
        const img = document.createElement('img');
        img.src = image.url;
        img.className = "appendImg";
        img.id = image.id;
        img.onclick = () => deleteDataImage(image.fileName, img.id);
        img.style.width = '180px'; // 이미지 너비 고정
        img.style.height = '180px'; // 이미지 높이 고정
        img.style.objectFit = 'cover'; // 이미지 잘림 방지
        img.style.transition = 'transform 0.2s'; // 부드러운 크기 변화

        imageContainer.appendChild(img);
    })

//     tag 설정
    const tagifyElement = document.querySelector('input[name=tags]');
    const tagify = new Tagify(tagifyElement);

    data.tags.forEach(tag => {
        tagify.addTags([tag.tag]);
    })

//     github 설정
    document.getElementById('github-url').value = data.githubUrl;
}
let deleteFileNames = [];
function deleteDataImage(fileName, imgId) {
    console.log("deleteDataImage(imgId) : " + fileName);
    deleteFileNames.push(fileName);

    const imgElement = document.getElementById(imgId);
    imgElement.remove();

    const tempFormData = new FormData();

    for (let [key, value] of formData.entries()) {
        if (key === 'imageFiles' && value.name === imgId) {
            continue;
        }
        tempFormData.append(key, value);
    }
    formData = tempFormData;
}

// function getMemberInProjectWrite() {
//     fetch(`/api/member/info`)
//         .then(response => {
//             if (!response.ok) {
//                 throw new Error('Network response was not ok');
//             }
//             return response.json();
//         })
//         .then(data => {
//             console.log("data ::::", data);
//             loginEmail = data.email;
//         })
//         .catch(error => {
//             console.error('Fetch error:', error);
//         });
// }

function projectModify(formData) {
    fetch(`/api/project/update?reviewId=${projectReviewId}`, {
        method: 'PUT',
        body: formData
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

function saveProjectReview() {
    if(checkLoginForWrite()) {
        const title = document.getElementById('projectTitle').value;
        const content = ProjectEditor.getHTML();
        const tags = tagify.value.map(tag => tag.value);
        const githubUrl = document.getElementById('github-url').value;

        formData.append('memberEmail', loginEmail);
        formData.append('title', title);
        formData.append('content', content);
        formData.append('githubUrl', githubUrl);
        tags.forEach(tag => formData.append('tags', tag));
        deleteFileNames.forEach(fileName => formData.append('fileNames', fileName));

        console.log("폼데이터: ", +formData);
        for (const [key, value] of formData.entries()) {
            console.log(`${key}: ${value}`);
        }
        if (title === null) {
            alert("제목이 없습니다.");
            document.getElementById('projectTitle').focus();
        } else if (content === null) {
            alert("내용이 없습니다.");
            document.getElementById('projectEditor').focus();
        } else if (githubUrl === null) {
            alert("깃허브 주소를 입력해주세요.");
            document.getElementById('github-url').focus();
        }

        if (projectReviewId !== null && projectReviewId !== '') {
            projectModify(formData);
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
    } else {
        window.location.href = '/project/';
    }
}
