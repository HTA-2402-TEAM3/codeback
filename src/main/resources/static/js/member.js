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
            submit(email)
        })
        .catch(error => {
            console.error('Fetch error:', error);
        });
}