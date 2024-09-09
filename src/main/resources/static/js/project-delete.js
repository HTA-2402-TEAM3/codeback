function deleteProjectReview(id){
    fetch(`/api//project/${id}`, {
        method: 'DELETE',
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // 응답을 JSON으로 처리 (필요시)
        })
        .then(data => {
            console.log('성공적으로 삭제되었습니다:', data);
        })
        .catch(error => {
            console.error('삭제 중 오류 발생:', error);
        });
}