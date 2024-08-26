function selectOnlyOne(checkbox) {
    // 모든 체크박스를 가져옵니다.
    const checkboxes = document.querySelectorAll('input[type="checkbox"]');

    // 현재 체크박스가 체크되면 나머지를 해제합니다.
    if (checkbox.checked) {
        checkboxes.forEach((cb) => {
            if (cb !== checkbox) {
                cb.checked = false;
            }
        });
        fetchData(checkbox.value);
    } else {
        fetchAllData();
    }
    console.log(`선택된 체크박스의 값: ${checkbox.checked ? checkbox.value : '없음'}`);
}

function fetchData(value) {
    $.ajax({
        url: `/api/review/${value}`,
        method: 'GET',
        success: function (data) {
            console.log("data ::::" , data);
            renderReviews(data.reviews);
        },
        error: function (error) {
            console.error(error);
        }
    });
}

function fetchAllData() {
    $.ajax({
        url: `/review/list`,
        method: 'GET',
        success: function (data) {
            console.log("allData: ",data);
        },
        error: function (error) {
            console.error(error);
        }
    });
}

function renderReviews(reviews) {
    const container = $('#reviewsContainer');
    container.empty();

    reviews.forEach(review => {
        const reviewElement = `  <a href="/review/${review.id}" class="box" style="padding-bottom: 0;">
                <div>
                    <ul style="margin-bottom: 30px;">
                        <li>
                            <h3 id="${review.id}" name="title" style="margin-bottom: 15px;">
                                ${review.title}
                            </h3>
                        </li>
                        <li>
                            <button class="button special disabled icon">${review.codeLanguageName}</button>
                        </li>
                    </ul>
                    <ul style="margin-top: 30px;">
                        <li>
                            <h5 id="writer1" name="writer" style="margin: 0; font-weight: bold;">
                                ${review.member}
                            </h5>
                            <h5 id="date1" name="date" style="margin-top: 0;">
                                ${review.createDate}
                            </h5>
                        </li>
                        <li>
                            <div>
                                <icon class="icon fa-thumbs-up">${review.preferenceCnt}</icon>
                                <icon class="icon fa-comment">${review.codeReviewComments}</icon>
                            </div>
                        </li>
                    </ul>
                </div>
            </a>`;
        container.append(reviewElement);
    });
}
