function getMemberInfo(reviewId) {
    let email = '';
    fetch(`/api/member/info`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log("data ::::", data.email);
            email = data.email;
            update(email);
        })
        .catch(error => {
            console.error('Fetch error:', error);
        });
    return email;
}

function update(userEmail) {

}

function deleteCodeReview(id) {
    if(confirm("정말 삭제하시겠습니까?")) {
        fetch(`/api/review/delete/${id}`, {
            method: 'DELETE'
        })
            .then(resp => {
                if(resp.ok) {
                    alert("삭제되었습니다.");
                    window.location.href = '/review/';
                } else {
                    throw new Error("err");
                }
            }).catch(error => {
            console.error(error);
        })
    }
}