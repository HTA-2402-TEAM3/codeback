const imgContent = document.querySelector('.image-upload');
const fileInput = document.getElementById('fileInput');
const imageContainer = document.querySelector('.image-container');

let formData = new FormData();

imgContent.addEventListener("dragover", dragOver);
imgContent.addEventListener("dragleave", dragOver);
imgContent.addEventListener("drop", uploadFiles);
imgContent.addEventListener("click", () => fileInput.click());
fileInput.addEventListener("change", handleFiles);

function dragOver(e) {
    e.stopPropagation();
    e.preventDefault();

    if (e.type === "dragover") {
        e.target.style.backgroundColor = "black";
        e.target.style.outlineOffset = "-20px";
    } else {
        e.target.style.backgroundColor = "gray";
        e.target.style.outlineOffset = "-10px";
    }
}

function uploadFiles(e) {
    e.stopPropagation();
    e.preventDefault();
    dragOver(e); // 드래그 종료 시 스타일 적용

    const dataTransfer = e.dataTransfer || e.originalEvent.dataTransfer;
    const files = dataTransfer.files;

    handleFiles({ target: { files } });
}

let files;

function handleFiles(event) {
    files = event.target.files;
    const fileNames = [];

    if (files.length > 0) {
        for (let i = 0; i < files.length; i++) {
            fileNames.push(files[i].name);
            if (files[i].type.match(/image.*/)) {
                const url = window.URL.createObjectURL(files[i]);
                const imgWrapper = document.createElement('div');
                imgWrapper.style.position = 'relative';
                imgWrapper.style.display = 'inline-block';

                const img = document.createElement('img');
                img.src = url;
                img.className = "appendImg";
                img.id = i+"img";
                img.onclick = () => deleteImg(i);
                img.style.width = '180px'; // 이미지 크기 조정
                img.style.height = '180px'; // 비율 유지
                img.style.objectFit = 'cover'; // 이미지 잘림 방지

                imageContainer.appendChild(img);

                formData.append('imageFiles',files[i]);
                console.log(img.id);
            } else {
                alert('이미지가 아닙니다.');
                return;
            }
        }
    }
    console.log("formData in uploadImages.js : ");
    for (const [key, value] of formData.entries()) {
        console.log(`${key}: ${value.name || value}`);
    }
    console.log(fileNames);
}
function deleteImg(index) {
    console.log(index);
    const fileName = files[index].name;

    if(projectReviewId !== null && projectReviewId !== ''){
        console.log('delete:'+fileName);
    }else {
        formData.delete('imageFiles', fileName);
    }

    const imgElement = document.getElementById(index+"img");
    imgElement.remove();
}

