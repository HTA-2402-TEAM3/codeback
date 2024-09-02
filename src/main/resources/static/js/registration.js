// 페이지가 로드될 때 자동으로 호출
document.addEventListener('DOMContentLoaded', () => {
    registerMember();
});
function registerMember() {

    // 양식 데이터 수집
    const queryString = window.location.search;

    // 쿼리 파라미터로 데이터 생성
    const queryParams = new URLSearchParams(queryString);
    const url = `/api/member/registration?${queryParams}`; // GET 요청 URL

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
            console.log('Success:', data);
            signIn(data);
            window.location.href = '/'; // 원하는 페이지로 이동
            // 성공 시 추가 작업 수행 (예: 사용자에게 알림 표시)
        })
        .catch((error) => {
            console.error('Error:', error);
            // 오류 시 추가 작업 수행 (예: 사용자에게 오류 메시지 표시)
        });
}

function signIn(data) {

    // AJAX 요청
    fetch('/api/member/signin', {
        method: 'POST', // POST
        headers: {
            'Content-Type': 'application/json' // 필요에 따라 Content-Type 설정
        },
        body: JSON.stringify(data) // JSON 데이터를 문자열로 변환
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            throw new Error('Network response was not ok.');
        })
        .then(data => {
            console.log('Success:', data);
            // 성공 시 추가 작업 수행 (예: 사용자에게 알림 표시)
        })
        .catch((error) => {
            console.error('Error:', error);
            // 오류 시 추가 작업 수행 (예: 사용자에게 오류 메시지 표시)
        });
}