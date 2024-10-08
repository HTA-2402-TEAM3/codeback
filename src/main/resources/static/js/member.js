window.addEventListener('load', function() {
    noticeCount();
});

function getMemberInfo() {
    fetch(`/api/member/info`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            const email = data.email;
            submit(email);
        })
        .catch(error => {
            console.error('Fetch error:', error);
        });
}

function noticeCount() {
    fetch(`/api/notification/count`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            const notificationElement = document.getElementById("notification-count");

            if (data === 0) {
                notificationElement.style.display = "none"; // 알림이 0일 경우 숨기기
            } else {
                notificationElement.style.display = "flex"; // 알림이 1 이상일 경우 보이게
                notificationElement.innerText = data > 99 ? '99+' : data; // 100개 이상일 경우 "99+"로 표시
            }
        })
        .catch(error => {
            console.error('Fetch error:', error);
        });
}
