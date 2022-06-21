const content = document.querySelector(".content");
const gameSearch = document.getElementById("gameSearch");
const searchBtn = document.getElementById("searchBtn");
const searchBtn2 = document.getElementById("searchBtn2");
const gameSearch2 = document.getElementById("gameSearch2");
const searchBtn3 = document.getElementById("searchBtn3");
const gameSearch3 = document.getElementById("gameSearch3");
const searchBtn4 = document.getElementById("searchBtn4");
const gameSearch4 = document.getElementById("gameSearch4");
const alert = document.querySelector(".alert");
const gameLink = document.querySelectorAll(".gameRow")
console.log(gameLink)

let requestOptions = {
    method: 'GET',
    redirect: 'follow'
};

if (searchBtn != null) {
    searchBtn.addEventListener("click", function (event) {
        event.preventDefault();
        console.log(gameSearch.value)
        let searchValue = gameSearch.value;
        window.location.href = `./games/search_results/${searchValue}`
        // fetch(`/games/${searchValue}`, requestOptions)
        //     .then(response => response.json())
        //     .then(result => test(result))
        //     .catch(error => console.log('error', error));
    })
}

if (searchBtn2 != null) {
    searchBtn2.addEventListener("click", function (event) {
        event.preventDefault();
        console.log(gameSearch2.value)
        let searchValue2 = gameSearch2.value;
        window.location.href = `../../games/search_results/${searchValue2}`
    })
}

if (searchBtn3 != null) {
    searchBtn3.addEventListener("click", function (event) {
        event.preventDefault();
        console.log(gameSearch3.value)
        let searchValue3 = gameSearch3.value;
        window.location.href = `./reviews/search_results/${searchValue3}`
    })
}

if (searchBtn4 != null) {
    searchBtn4.addEventListener("click", function (event) {
        event.preventDefault();
        console.log(gameSearch4.value)
        let searchValue4 = gameSearch4.value;
        window.location.href = `../../reviews/search_results/${searchValue4}`
    })
}

if (alert != null && alert.innerHTML !== null){
    setTimeout(() => {
        alert.setAttribute("style", "opacity: 0; transition: all 0.5s;");
    }, 5000)
}

// fetch("/steamapi/Player", requestOptions)
//     .then(response => response.json())
//     .then(result => test(result))
//     .catch(error => console.log('error', error));

function test(jsonResult) {
    console.log(jsonResult);
}

if (gameLink != null) {
    gameLink.forEach((link) => {
        link.addEventListener("click", function () {
                console.log(link)
                // event.preventDefault();
                let linkValue = link.innerText.split("\t")[0]
                console.log(linkValue)
                window.location.href = `../../games/game_info/${linkValue}`

                // fetch(`/steamapi/${linkValue}`, requestOptions)
                //     .then(response => response.json())
                //     .then(result => test(result))
                //     .catch(error => console.log('error', error));
            }
        )
    })
}

//
// .addEventListener("click", function () {
//     console.log("IM HERE")
//     // event.preventDefault();
//     let linkValue = gameLink.innerText
//     console.log(linkValue)
//     fetch(`/steamapi/${linkValue}`, requestOptions)
//         .then(response => response.json())
//         .then(result => test(result))
//         .catch(error => console.log('error', error));