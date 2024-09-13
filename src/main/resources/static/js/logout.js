document.addEventListener('DOMContentLoaded', (event) => {
    changeButton();
});
function changeButton() {

    fetch(`/api/member/info`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log("data ::::", data);
            document.getElementById('notification-button').style.display='block';
            document.getElementById('show-login').style.display = 'none';
            document.getElementById('logout-button').style.display = 'block';
        })
        .catch(error => {
            document.getElementById('notification-button').style.display='none';
            document.getElementById('show-login').style.display = 'block';
            document.getElementById('logout-button').style.display = 'none';
            console.error('Fetch error:', error);
        });
}



// 페이지 로드 시 access_token 확인


function logout() {

    // 양식 데이터 수집

    const url = `/api/member/logout`; // GET 요청 URL

    // AJAX 요청
    fetch(url, {
        method: 'GET', // GET 방식으로 변경
        headers: {
            'Content-Type': 'application/json' // 필요에 따라 Content-Type 설정
        }
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            throw new Error('Network response was not ok.');
        })
        .then(data => {
            window.location.reload(true);
            // 성공 시 추가 작업 수행 (예: 사용자에게 알림 표시)
        })
        .catch((error) => {
            console.error('Error:', error);
            // 오류 시 추가 작업 수행 (예: 사용자에게 오류 메시지 표시)
        });
}