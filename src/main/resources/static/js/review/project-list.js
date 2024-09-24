let searchText = '';
let findWithTag = '';

document.addEventListener('DOMContentLoaded', function () {
    const urlParams = new URLSearchParams(window.location.search);
    findWithTag = urlParams.get('tag');
});

function fetchAllProjects(page) {
    if(findWithTag === null) {
        findWithTag = '';
    }

    let url;
    if(searchText==='' && findWithTag==='') {
        url = `/api/project/?pageNum=${page}`;
    } else if(searchText===''&&findWithTag!=='') {
        url = `/api/project/search?tag=${findWithTag}&pageNum=${page}`
    } else {
        url = `/api/project/search?search=${searchText}&pageNum=${page}`
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

function renderReviews(reviews) {
    const container = document.getElementById('project-box');
    container.innerHTML = '';

    reviews.forEach(review => {
        const boxDiv = document.createElement('div');
        boxDiv.className = 'boxes';

        const formatDate = formatted(review.createDate);
        const reviewElement = document.createElement('a');
        reviewElement.href = `/project/${review.id}`;
        reviewElement.className = 'box';

        reviewElement.innerHTML = `
    <div>
        <img src="${review.projectReviewThumbnails ? review.projectReviewThumbnails : '/images/default.png'}" alt="Pic 01" class="project-img"/>
        <h4>${review.title}</h4>
        <div class="writer-info">
            <p>${review.member}</p>
            <p>${formatDate}</p>
        </div>
        <div class="tag-info">
             <p>${review.projectReviewTags.map(tag => '#' + tag).join(' ')}</p>
            <div>
                <icon class="icon fa-thumbs-up">${review.preferenceCnt}</icon>
                <icon class="icon fa-comment">${review.projectReviewComments}</icon>
            </div>
        </div>
    </div>
`;
        boxDiv.insertAdjacentElement('beforeend', reviewElement);
        container.appendChild(boxDiv);
    });
}

function formatted(dateString) {
    const date = new Date(dateString);

    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1
    const day = String(date.getDate()).padStart(2, '0');
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');

    return `${year}-${month}-${day} ${hours}:${minutes}`;
}

function renderPaging(totalPage) {
    const ul = document.getElementById('pagingUl');
    ul.innerHTML = '';

    let pageElements = `
        <li class="page-item">
            <a class="page-link" aria-label="Previous" onclick="fetchAllProjects(0); return false;">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>
    `;

    for (let page = 0; page < totalPage; page++) {
        pageElements += `
            <li class="page-item">
                <a class="page-link" onclick="fetchAllProjects(${page}); return false;">${page + 1}</a>
            </li>
        `;
    }

    pageElements += `
        <li class="page-item">
            <a class="page-link" onclick="fetchAllProjects(${totalPage - 1}); return false;">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
    `;

    ul.innerHTML += pageElements;
}

function searchProjects() {
    searchText = document.getElementById('search').value;

        fetch(`/api/project/search?search=${searchText}`)
            .then(resp => {
                if(!resp.ok) {
                    throw new Error('Network response was not ok');
                }
                return resp.json();
            })
            .then(data => {
                renderReviews(data.reviews);
                renderPaging(data.totalPage);
            }).catch(err => {
                console.error("Fetch error : ",err);
        });

}