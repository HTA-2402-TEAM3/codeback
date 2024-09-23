function deleteProjectReview(id){
    if(confirm("게시글을 삭제하시겠습니까?")) {
        fetch(`/api/project/${id}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json(); // 응답을 JSON으로 처리 (필요시)
            })
            .then(data => {
                alert(data.message);
                window.location.href = '/project/';
            })
            .catch(error => {
                console.error('삭제 중 오류 발생:', error);
            });
    }
}