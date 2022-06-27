const gameFavoriteTitle = document.querySelector(".gameFavoriteTitle");
const gameAppId = document.querySelector(".gameAppId");

let queryString = gameAppId.innerHTML;

// Javascript to fetch and populate game title div on favorites page

window.onload = function (){
    fetch(`/steamapi/${queryString}`, requestOptions)
        .then(response => response.json())
        .then(result => test(result))
        .catch(error => console.log('error', error));
}

function test(jsonResult) {
    const {
        name,
        steam_appid,
        detailed_description,
        about_the_game,
        short_description,
        website,
        legal_notice
    } = jsonResult[queryString].data;

    gameFavoriteTitle.innerHTML = name;
}