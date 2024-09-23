function getMemberEmail() {
    let loginEmail = '';
    fetch(`/api/member/info`)
        .then(response => {
            if (response.status === 401) {
                loginEmail = '';
                console.log('Not logged in');
            } else if (!response.ok) {
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
    return loginEmail;
}

function valiMember(location) {
    const loginEmail = getMemberEmail();

    console.log(loginEmail);
    if (loginEmail !== null && loginEmail !== undefined && loginEmail !== '') {
        window.location.href = `/${location}/write`;
    } else {
        alert("로그인 해주세요.");
        window.location.href = '/';
    }
}

function valiMemberComment(type) {
    const loginEmail = getMemberEmail();

    console.log(loginEmail);
    if (loginEmail === null || loginEmail === '' || loginEmail === undefined) {
        alert("로그인 해주세요.");
    } else {
        commentSubmit(type);
    }
}

function checkLoginForWrite() {
    const loginEmail = getMemberEmail();

    if (loginEmail === null || loginEmail === '' || loginEmail === undefined) {
        alert("로그인 해주세요.");
        return false;
    } else {
        return true;
    }
}