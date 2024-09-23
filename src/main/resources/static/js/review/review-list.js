// let checkboxValue = '';
// let searchKeyword = '';
// let loginEmail = '';
// document.addEventListener('DOMContentLoaded', function () {
//     getMemberEmail();
// });

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
        checkboxValue = checkbox.value;
        searchData();
    } else {
        checkboxValue = '';
        fetchAllData(0);
    }

}

function renderPaging(totalPage) {
    const ul = document.getElementById('pagingUl');
    ul.innerHTML = '';

    let pageElements = `
        <li class="page-item">
            <a class="page-link" aria-label="Previous" onclick="fetchAllData(0); return false;">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>
    `;

    for (let page = 0; page < totalPage; page++) {
        pageElements += `
            <li class="page-item">
                <a class="page-link" onclick="fetchAllData(${page}); return false;">${page + 1}</a>
            </li>
        `;
    }

    pageElements += `
        <li class="page-item">
            <a class="page-link" onclick="fetchAllData(${totalPage - 1}); return false;">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
    `;

    ul.innerHTML += pageElements;
}

function fetchAllData(page) {
    let url;
    if (searchKeyword !== '' && checkboxValue === '') {
        // 키워드 o 체크박스 x
        url = `/api/review/search?search=${searchKeyword}&pageNum=${page}`
    } else if (searchKeyword === '' && checkboxValue !== '') {
        // 키워드 x 체크박스 o
        url = `/api/review/search?language=${checkboxValue}&pageNum=${page}`
    } else if(searchKeyword !== '' && checkboxValue !== ''){
        // 키워드 o 체크박스 o
        url = `/api/review/search?search=${searchKeyword}&language=${checkboxValue}&pageNum=${page}`
    } else {
        // 키워드 x 체크박스 x
        url = `/api/review/?pageNum=${page}`
    }

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            renderReviews(data.reviews);
            renderPaging(data.totalPage);
        })
        .catch(error => {
            console.error('Fetch error:', error);
        });
}

function formatDate(dateString) {
    const date = new Date(dateString);

    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1
    const day = String(date.getDate()).padStart(2, '0');
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');

    return `${year}-${month}-${day} ${hours}:${minutes}`;
}

function renderReviews(reviews) {
    const container = document.getElementById('reviewsContainer');
    container.innerHTML = '';

    reviews.forEach(review => {
        const formatedDate = formatDate(review.createDate);

        const reviewElement = document.createElement('a');
        reviewElement.href = `/review/${review.id}`;
        reviewElement.className = 'box';
        reviewElement.style.paddingBottom = '0';

        reviewElement.innerHTML = `
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
                        ${formatedDate}
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
    `;
        container.insertAdjacentElement('beforeend', reviewElement); // DOM 요소를 추가
    });
}


// ==========================================

function searchData() {
    searchKeyword = document.getElementById('search').value;

    let url;
    if (searchKeyword !== '' && checkboxValue === '') {
        url = `/api/review/search?search=${searchKeyword}`
    } else if (searchKeyword === '' && checkboxValue !== '') {
        url = `/api/review/search?language=${checkboxValue}`
    } else {
        url = `/api/review/search?search=${searchKeyword}&language=${checkboxValue}`
    }
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            renderReviews(data.reviews);
            renderPaging(data.totalPage);
        })
        .catch(error => {
            console.error('Fetch error:', error);
        });
}
