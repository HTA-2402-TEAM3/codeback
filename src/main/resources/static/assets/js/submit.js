function submitForm(event) {
    event.preventDefault(); // 기본 제출을 막습니다.

    // 양식 데이터 수집
    const email = document.getElementById('email').value;

    // JSON 데이터 생성
    const jsonData = {
        email: email
    };

    fetch('/api/member/email', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json' // JSON 형식으로 전송
        },
        body: JSON.stringify(jsonData) // JSON 데이터를 문자열로 변환
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            throw new Error('Network response was not ok.');
        })
        .then(data => {
            console.log('Success:', data);
            window.location.href = '/index'; // 원하는 페이지로 이동
            // 성공 시 추가 작업 수행 (예: 사용자에게 알림 표시)
        })
        .catch((error) => {
            console.error('Error:', error);
            // 오류 시 추가 작업 수행 (예: 사용자에게 오류 메시지 표시)
        });
}