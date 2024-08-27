
function submit(){
    const email = 'chaeunkang@github.com';
    const title = document.getElementById('titleInput').value;
    const content = editor.getHTML();

    if (languageValue === '' || title=== '' || content === '<p><br></p>') {
        alert('모든 내용을 입력해주세요');
        return;
    }

    console.log(`제목: ${title}`);
    console.log(`내용: ${content}`);
    console.log(`언어: ${languageValue}`);

    fetch(`/api/review/save`,{
        method: 'POST',
        headers: {
            'Content-Type':'application/json'
        },
        body: JSON.stringify({
            memberEmail: email,
            title: title,
            content: content,
            codeLanguageCategoryId: languageValue
        })
    }).then(resp => {
        if(!resp.ok) {
            throw new Error("fail to fetch");
        }
        return resp.json();
    }).then(resp => {
        if(resp.status === "success") {
            window.location.href = '/review/';
        }
    }).catch(error => {
        console.error(error);
    });
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
    if(checkbox.checked) {
        languageValue = checkbox.value;
    } else {
        languageValue = '';
    }
    console.log(`선택된 체크박스의 값: ${checkbox.checked ? checkbox.value : '없음'}`);
}

// const Editor = toastui.Editor;

// const editor = new Editor({
//     el: document.querySelector('#editor'),
//     height: '500px',
//     initialEditType: 'markdown',
//     previewStyle: 'vertical'
// });

// document.getElementById('submit').addEventListener('click', () => {
//     const content = editor.getHTML();
//     console.log(content);
// })