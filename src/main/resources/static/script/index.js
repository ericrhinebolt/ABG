const heroLogin = document.querySelector(".heroLogin");
const heroRegister = document.querySelector(".heroRegister");
const newsAppId = document.querySelectorAll(".newsAppId");
const newsDate = document.querySelectorAll(".newsDate");
const newsGameName = document.querySelectorAll(".newsGameName")
const newsTitle = document.querySelectorAll(".newsTitle");
const newsContent = document.querySelectorAll(".newsContent");
const newLink = document.createElement("a");
const newsLink = document.querySelectorAll(".newsLink")
const favImg1 = document.querySelectorAll(".favImg1");
const favImg2 = document.querySelectorAll(".favImg2");
const favImg3 = document.querySelectorAll(".favImg3");
const favImgLink1 = document.querySelectorAll(".favImgLink1")
const favImgLink2 = document.querySelectorAll(".favImgLink2")
const favImgLink3 = document.querySelectorAll(".favImgLink3")
const imgDiv = document.querySelectorAll(".imgDiv");

let requestOptions = {
    method: 'GET',
    redirect: 'follow'
};

window.onload = function () {
    // Loop to get news from Steam News API for every game in favorites list and populate index when user is signed in
    for (let i = 0; i < newsAppId.length; i++) {
        let newsAppIdvalue = newsAppId[i].value;
        console.log(newsAppId);
        console.log(newsAppIdvalue)
        fetch(`/steamapi/news/${newsAppIdvalue}`, requestOptions)
            .then(response => response.json())
            .then(result => assign(result))
            .catch(error => console.log('error', error));

        // API call for news
        function assign(jsonResult) {
            const {appid} = jsonResult.appnews;
            let title, url, contents, date;
            if(jsonResult.appnews.newsitems[0]) {
                date = jsonResult.appnews.newsitems[0].date;
                title = jsonResult.appnews.newsitems[0].title;
                url = jsonResult.appnews.newsitems[0].url;
                contents = jsonResult.appnews.newsitems[0].contents;
            }
            console.log(title)
            // newsParent.appendChild(newDateDiv);
            newsDate[i].innerHTML = new Date(date * 1000);

            // API call for screenshots
            fetch(`/steamapi/${appid}`, requestOptions)
                .then(response => response.json())
                .then(result => subAssign(result))
                .catch(error => console.log('error', error))

            // Assinging values of screenshot call
            function subAssign(jsonResult) {
                // newsParent.appendChild(newGameNameDiv);
                console.log(i);
                newsGameName[i].innerHTML = jsonResult[appid].data.name;
                favImg1[i].setAttribute("src", jsonResult[appid].data.screenshots[0]?.path_thumbnail)
                favImg2[i].setAttribute("src", jsonResult[appid].data.screenshots[1]?.path_thumbnail)
                favImg3[i].setAttribute("src", jsonResult[appid].data.screenshots[2]?.path_thumbnail)
                favImgLink1[i].setAttribute("href", jsonResult[appid].data.screenshots[0]?.path_full)
                favImgLink2[i].setAttribute("href", jsonResult[appid].data.screenshots[1]?.path_full)
                favImgLink3[i].setAttribute("href", jsonResult[appid].data.screenshots[2]?.path_full)
                imgDiv[i].setAttribute("style", "grid-row:" + (i + 2) + "/ " + (i + 3));
            }

            // Assigns news info
            // newsParent.appendChild(newTitleDiv);
            newsTitle[i].innerHTML = title;
            // newsParent.appendChild(newContentsDiv)
            newsContent[i].innerHTML = contents;
            newsLink[i].setAttribute("href", url);
            newsLink[i].innerHTML = "Click here for more information"
            // newsContent[i].lastChild.innerHTML("For more information, click here " + url);


        }
    }
}


if (heroLogin != null) {
    heroLogin.addEventListener("click", function (event) {
        event.preventDefault();
        window.location.href = "/login";
    })
    heroRegister.addEventListener("click", function (event) {
        event.preventDefault();
        window.location.href = "/register";
    })
}