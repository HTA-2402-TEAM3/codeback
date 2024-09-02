let reviewId = '';

document.addEventListener('DOMContentLoaded', function() {
    const urlParams = new URLSearchParams(window.location.search);
    reviewId = urlParams.get('id');

    if (reviewId !== null && reviewId !== '') {
        renderCodeReview(reviewId);
    }
});

function modify(email,title,content) {
    fetch(`/api/review/update`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: reviewId,
            memberEmail: email,
            title: title,
            content: content,
            codeLanguageCategoryId: languageValue
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

function submit(email) {
    const title = document.getElementById('titleInput').value;
    const content = editor.getHTML();

    if (languageValue === '' || title === '' || content === '<p><br></p>') {
        alert('모든 내용을 입력해주세요');
        return;
    }

    console.log(`제목: ${title}`);
    console.log(`내용: ${content}`);
    console.log(`언어: ${languageValue}`);

    if (reviewId !== null && reviewId !== '') {
        modify(email,title,content);
    } else {
        fetch(`/api/review/save`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                memberEmail: email,
                title: title,
                content: content,
                codeLanguageCategoryId: languageValue
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
}

function getMemberInfo() {
    submit("keyy1315@naver.com");


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
    //         submit(email)
    //     })
    //     .catch(error => {
    //         console.error('Fetch error:', error);
    //     });
}

let languageValue = '';

function selectOnlyOne(checkbox) {
    const checkboxes = document.querySelectorAll('input[type="checkbox"]');

    // 현재 체크박스가 체크되면 나머지를 해제합니다.
    if (checkbox.checked) {
        checkboxes.forEach((cb) => {
            if (cb !== checkbox) {
                cb.checked = false;
            }
        });
    }
    if (checkbox.checked) {
        languageValue = checkbox.value;
    } else {
        languageValue = '';
    }
    console.log(`선택된 체크박스의 값: ${checkbox.checked ? checkbox.value : '없음'}`);
}

function renderCodeReview(reviewId) {
    console.log("renderCodeReview() : "+reviewId);

    fetch(`/api/review/get/${reviewId}`,{
        method: 'GET',
        headers: {
            'Accept': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            renderData(data);
        })
        .catch(error => {
            console.error('Fetch error:', error);
        });
}

function renderData(data) {
    // title과 content를 설정
    console.log("renderData() : "+data);

    document.getElementById('titleInput').value = data.title;

    const turndownService = new TurndownService();
    const markdown = turndownService.turndown(data.content);

    editor.setMarkdown(markdown); // toastui Editor에 content 설정

    // languageName을 체크박스에 맞게 설정
    const checkboxes = document.querySelectorAll('.checkbox-group input[type="checkbox"]');
    checkboxes.forEach(checkbox => {
        if (data.codeLanguageCategory.id.includes(checkbox.value)) {
            checkbox.checked = true;
        }
    });
    languageValue = data.codeLanguageCategory.id;
}

