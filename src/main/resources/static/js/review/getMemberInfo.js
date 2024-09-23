
function getMemberEmail() {
    fetch(`/api/member/info`)
        .then(response => {
            if (!response.ok) {
                loginEmail = '';
                console.log('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            loginEmail = data.email;
        })
        .catch(error => {
            loginEmail = '';
            console.error('Fetch error:', error);
        });
}

function valiMember(location) {
    console.log(loginEmail);
    if(loginEmail!==null && loginEmail!==undefined && loginEmail !== '') {
        window.location.href=`/${location}/write`;
    } else {
        alert("로그인 해주세요.");
        window.location.href='/';
    }
}

function valiMemberComment(type) {
    console.log(loginEmail);
    if(loginEmail === null || loginEmail === '' || loginEmail === undefined) {
        alert("로그인 해주세요.");
    } else {
        commentSubmit(type);
    }
}

function checkLoginForWrite() {
    if(loginEmail === null || loginEmail === '' || loginEmail === undefined) {
        alert("로그인 해주세요.");
        return false;
    } else {
        return true;
    }
}