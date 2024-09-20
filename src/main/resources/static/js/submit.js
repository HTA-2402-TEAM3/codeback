function submitForm(event) {
    event.preventDefault(); // 기본 제출을 막습니다.

    let emailElement = document.getElementById('email');
    let email = emailElement ? emailElement.value : null; // 'email' 요소가 있을 경우 값을 가져오기, 없으면 null 할당


    if (email === "") {
        // 'email' 값이 빈 문자열일 경우
        let signupEmailElement = document.getElementById('signup-email');
        email = signupEmailElement ? signupEmailElement.value : null; // 'signup-email'의 값을 이메일에 할당, 없으면 null
    }

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
            window.location.href = '/index'; // 원하는 페이지로 이동
            // 성공 시 추가 작업 수행 (예: 사용자에게 알림 표시)
        })
        .catch((error) => {
            console.error('Error:', error);
            // 오류 시 추가 작업 수행 (예: 사용자에게 오류 메시지 표시)
        });
}