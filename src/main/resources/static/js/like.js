function changePreference(entityID, type) {

    console.log(entityID);
    console.log(type);

    const likeType = {
        type : type
    };

    fetch(`/like/${entityID}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(likeType)
    })
        .then((response) => {
            if (!response.ok) {
                return new Error("Preference Fail")
            }

            return response.json();
        })
        .then((data) => {

            console.log(data);
            const countTag = document.getElementById('count_' + data.entityID);

            let count = parseInt(countTag.textContent);

            if (data.likeSign) {
                count += 1;
            } else {
                count -= 1;
            }

            countTag.textContent = count;

        })
        .catch((error) => {
            console.error(error.message);
        })
    ;
}

function reviewLike(entityID) {
    changePreference(entityID, "codeReview");
}

function commentLike(entityID) {
    changePreference(entityID, "codeReviewComment");
}