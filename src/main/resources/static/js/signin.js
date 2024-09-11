let isLoggedIn = false; // 로그인 상태를 관리하는 변수

// 버튼 클릭 이벤트 리스너
document.getElementById('authButton').addEventListener('click', function() {
    isLoggedIn = !isLoggedIn; // 로그인 상태 토글

    const buttonText = isLoggedIn ? '로그아웃' : '로그인'; // 로그인 상태에 따른 텍스트 설정
    this.textContent = buttonText; // 버튼 텍스트 변경

    // 추가적인 로그인/로그아웃 로직을 여기에 추가할 수 있습니다.
    if (isLoggedIn) {
        console.log('사용자가 로그인했습니다.');
    } else {
        console.log('사용자가 로그아웃했습니다.');
    }
});

localStorage.setItem('isLoggedIn', isLoggedIn);

window.onload = function() {
    const savedLoginStatus = localStorage.getItem('isLoggedIn') === 'true';
    isLoggedIn = savedLoginStatus;
    const buttonText = isLoggedIn ? '로그아웃' : '로그인';
    document.getElementById('authButton').textContent = buttonText;
};