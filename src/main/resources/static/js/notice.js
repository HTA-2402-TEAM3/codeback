function getNotification() {
    fetch('/api/notification', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            throw new Error('Network response was not ok.');
        })
        .then(data => {
            const notificationList = document.getElementById('notificationList');
            data.forEach(notification => {
                const listItem = document.createElement('li');

                if (notification.read === true) {
                    listItem.classList.add('blurred');
                }

                // 날짜 div
                const dateDiv = document.createElement('div');
                dateDiv.classList.add('notification-date');

                // 메시지와 버튼을 묶는 div
                const messageButtonDiv = document.createElement('div');
                messageButtonDiv.classList.add('message-button-notice');

                // 메시지 div
                const messageDiv = document.createElement('div');
                messageDiv.classList.add('notification-message');

                const buttonContainer = document.createElement('div');
                buttonContainer.classList.add('button-container'); // 버튼을 묶는 클래스 추가

                const deleteButton = document.createElement('button');
                let notificationID = notification.id;

                const fadeButton = document.createElement('button');

                fadeButton.textContent = '읽음';
                fadeButton.addEventListener('click', function () {
                    console.log("success");
                    readNotice(notificationID);
                    listItem.classList.add('blurred');
                });

                deleteButton.textContent = '삭제';
                deleteButton.addEventListener('click', function () {
                    deleteNotice(notificationID);
                    listItem.remove();
                });

                // 날짜 파싱
                const isoDate = `${notification.createDate}`;
                const date = new Date(isoDate);
                const options = {
                    year: 'numeric',
                    month: 'long',
                    day: 'numeric',
                    hour: '2-digit',
                    minute: '2-digit',
                    second: '2-digit',
                    hour12: true
                };

                const formattedDate = date.toLocaleString('ko-KR', options).replace(/GMT.*$/, '').trim();

                // 날짜와 메시지를 분리하여 각 div에 추가
                dateDiv.textContent = formattedDate;
                messageDiv.textContent = `${notification.message}`;

                // 버튼을 묶는 div에 버튼 추가 (읽음 버튼이 먼저)
                buttonContainer.appendChild(fadeButton);
                buttonContainer.appendChild(deleteButton);

                // 메시지와 버튼을 묶는 div에 메시지와 버튼 추가
                messageButtonDiv.appendChild(messageDiv);
                messageButtonDiv.appendChild(buttonContainer);

                // li 요소에 날짜 div와 메시지&버튼 div 추가
                listItem.appendChild(dateDiv);
                listItem.appendChild(messageButtonDiv);

                notificationList.appendChild(listItem);
            });
        })
        .catch(error => console.error('Error fetching notifications:', error));
}



function deleteNotice(id) {

    fetch(`/api/notification/${id}`, {
        method: 'DELETE', //
        headers: {
            'Content-Type': 'application/json' // 필요에 따라 Content-Type 설정
        }
    })
        .then(response => {
            if (response.ok) {
                console.log(`리소스 가 성공적으로 삭제되었습니다.`);
            } else {
                console.error(`삭제 실패: ${response.status} - ${response.statusText}`);
            }
        })
        .catch(error => {
            console.error('오류 발생:', error);
        });
}

function readNotice(id) {
    console.log("first")
    fetch(`/api/notification/${id}`, {
        method: 'PATCH', // PATCH 메소드로 변경
        headers: {
            'Content-Type': 'application/json' // JSON 데이터 전송을 위한 Content-Type 설정
        }
    })
        .then(response => {
            if (response.ok) {
                console.log("HERE");
            } else {
                console.error(`업데이트 실패: ${response.status} - ${response.statusText}`);
            }
        })
        .catch(error => {
            console.error('오류 발생:', error);
        });
}