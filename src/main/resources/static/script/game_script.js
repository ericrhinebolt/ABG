const gameTitle = document.querySelector(".gameTitle");
const gameId = document.querySelector(".gameId");
const gameSummary = document.querySelector(".gameSummary");
const gameReleaseDate = document.querySelector(".gameReleaseDate");
const gamePrice = document.querySelector(".gamePrice");
const gameWebsite = document.querySelector(".gameWebsite");
const gameDescription = document.querySelector(".gameDescription");
const gameRequirementsMin = document.querySelector(".gameRequirementsMin");
const gameRequirementsRec = document.querySelector(".gameRequirementsRec");
const gameDev = document.querySelector(".gameDev");
const gamePub = document.querySelector(".gamePub");
const gameLegal = document.querySelector(".gameLegal");
const newReview = document.getElementById("newReview");
const newFavorite = document.getElementById("newFavorite");
const favAppId = document.querySelector(".favAppId");
const newLink = document.createElement("a");
const hiddenAppId = document.getElementById("hiddenAppId");


let queryString = hiddenAppId.value;

// Javascript to fetch and populate game info on game info page

window.onload = function (){
    fetch(`/steamapi/${queryString}`, requestOptions)
        .then(response => response.json())
        .then(result => test(result))
        .catch(error => console.log('error', error));
}

function test(jsonResult) {
    const { name, steam_appid, detailed_description, about_the_game, short_description, website, legal_notice } = jsonResult[queryString].data;

    favAppId.value = steam_appid;
    gameTitle.innerHTML = name;
    gameId.innerHTML = "Steam ID: " + steam_appid;
    gameReleaseDate.innerHTML = "Release Date: " + jsonResult[queryString].data.release_date.date;
    gamePrice.innerHTML = "Price: " + jsonResult[queryString].data.price_overview.final_formatted + " " + jsonResult[queryString].data.price_overview.currency;
    newLink.setAttribute("href", website);
    newLink.setAttribute("class", "gameWebsiteLink");
    gameWebsite.appendChild(newLink);
    newLink.innerHTML = website;
    gameSummary.innerHTML = short_description;
    gameDescription.innerHTML = about_the_game;
    gameRequirementsMin.innerHTML = jsonResult[queryString].data.pc_requirements.minimum;
    gameRequirementsRec.innerHTML = jsonResult[queryString].data.pc_requirements.recommended;
    gameDev.innerHTML = "Developer: " + jsonResult[queryString].data.developers[0];
    gamePub.innerHTML = "Publisher: " + jsonResult[queryString].data.publishers[0];
    gameLegal.innerHTML = legal_notice;

    console.log(jsonResult[queryString].data.name);
}

// Event listeners for buttons to add favorites and review

newReview.addEventListener("click", function (){
    window.location.href = `../../reviews/new_review/${queryString}`
})

newFavorite.addEventListener("click", function (){
    window.location.href = "/add_favorite"
})
