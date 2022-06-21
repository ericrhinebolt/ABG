const reviewTitle = document.querySelector(".reviewTitle");
const reviewAppId = document.querySelector(".reviewAppId");
const reviewTitleField = document.querySelector(".reviewTitleField");
const reviewAppIdField = document.querySelector(".reviewAppIdField");
const hiddenAppId = document.getElementById("hiddenAppId");
const updateReviewTitle = document.querySelector(".updateReviewTitle")

let queryString = hiddenAppId.value;

// let queryString = window.location.href;
// queryString = queryString.substring(41);

window.onload = function (){
    fetch(`/steamapi/${queryString}`, requestOptions)
        .then(response => response.json())
        .then(result => test(result))
        .catch(error => console.log('error', error));
}

function test(jsonResult) {
    const { name, steam_appid, detailed_description, about_the_game, short_description, website, legal_notice } = jsonResult[queryString].data;
    if(reviewTitleField != null) {
        reviewTitleField.value = name;
    }
    if(reviewAppIdField != null) {
        reviewAppIdField.value = queryString;
    }
    if(reviewTitle != null) {
        reviewTitle.innerHTML = name;
    }
    if(reviewAppId != null) {
        reviewAppId.innerHTML = queryString;
    }
    if(updateReviewTitle != null) {
        updateReviewTitle.innerHTML = name;
    }
}

