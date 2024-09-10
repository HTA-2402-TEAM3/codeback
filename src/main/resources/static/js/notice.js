function getNotification() {

    fetch('/api/notification', {
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
            console.log(data);
            const notificationList = document.getElementById('notificationList');

            data.forEach(notification => {
                const listItem = document.createElement('li');

                const deleteButton = document.createElement('button');
                let notificationID = notification.id;

                deleteButton.textContent = '삭제'; // 버튼 텍스트 설정
                // 버튼 클릭 이벤트 리스너 추가
                console.log(notificationID);
                deleteButton.addEventListener('click', function () {
                    // 클릭한 버튼의 부모 요소인 li 삭제
                    deleteNotice(notificationID);
                    listItem.remove();
                });

                const fadeButton = document.createElement('button');
                fadeButton.textContent = '읽음'; // 버튼 텍스트 설정
                fadeButton.addEventListener('click', function () {
                    console.log("success");
                    readNotice(notificationID);
                    listItem.classList.add('blurred')
                });


                //날짜 파싱
                const isoDate = `${notification.createDate}`;
                // Date 객체로 변환
                const date = new Date(isoDate);

// 날짜와 시간을 사용자의 로케일에 맞춰 출력
                const options = {
                    year: 'numeric',
                    month: 'long',
                    day: 'numeric',
                    hour: '2-digit',
                    minute: '2-digit',
                    second: '2-digit',
                    hour12: true // 12시간 형식 사용
                };

                const formattedDate = date.toLocaleString('ko-KR', options).replace(/GMT.*$/, '').trim();

                listItem.textContent = `${formattedDate} : ${notification.message}`;
                // li 요소에 버튼 추가
                listItem.appendChild(deleteButton);
                listItem.appendChild(fadeButton);
                notificationList.appendChild(listItem);
            });
        })
        .catch(error => console.error('Error fetching notifications:', error));
}

function deleteNotice(id) {
    console.log(id);

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