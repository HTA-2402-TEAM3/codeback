async function getMemberEmail() {
    let loginEmail = '';
    try {
        const response = await fetch(`/api/member/info`);
        if (response.status === 401) {
            console.log('Not logged in');
            return '';
        } else if (!response.ok) {
            console.log('Network response was not ok');
            return '';
        }
        const data = await response.json();
        loginEmail = data.email;
    } catch (error) {
        console.error('Fetch error:', error);
        return '';
    }
    return loginEmail;
}


async function valiMember(location) {
    const loginEmail = await getMemberEmail();

    console.log(loginEmail);
    if (loginEmail !== null && loginEmail !== undefined && loginEmail !== '') {
        window.location.href = `/${location}/write`;
    } else {
        alert("로그인 해주세요.");
        window.location.href = '/';
    }
}

async function valiMemberComment(type) {
    const loginEmail = await getMemberEmail();

    console.log(loginEmail);
    if (loginEmail === null || loginEmail === '' || loginEmail === undefined) {
        alert("로그인 해주세요.");
    } else {
        commentSubmit(type);
    }
}

async function checkLoginForWrite() {
    const loginEmail = await getMemberEmail();

    if (loginEmail === null || loginEmail === '' || loginEmail === undefined) {
        alert("로그인 해주세요.");
        return false;
    } else {
        return true;
    }
}