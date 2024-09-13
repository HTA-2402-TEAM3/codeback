

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
            console.log('Success:', data);
            window.location.href = '/'; // 원하는 페이지로 이동
            // 성공 시 추가 작업 수행 (예: 사용자에게 알림 표시)
        })
        .catch((error) => {
            console.error('Error:', error);
            // 오류 시 추가 작업 수행 (예: 사용자에게 오류 메시지 표시)
        });
}