const addVideoCardTemplate = function (data, number, categorie) {
    const dateDifference = wootubeCtx.util.calculateDate(data[number].createDate)
    const videoCardTemplate = 
    `<div class="col-lg-2 padding-2">
        <div class="card bg-transparent no-border">
            <div class="card-media">
                <a href="video-detail.html?id=${data[number].id}"> 
                    <img class="img-responsive" src="http://img.youtube.com/vi/${data[number].youtubeId}/0.jpg" alt="">
                </a>
            </div>
            <div class="card-block padding-10">
                <h5 class="mrg-btm-10 no-mrg-top text-bold font-size-14 ls-0">${data[number].title}</h5>
                <span class="font-size-13">${data[number].creator.name}</span>
                <div class="font-size-13">
                <span>조회수 ${data[number].viewCount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}</span>
                <span> · </span>
                <span>${dateDifference}</span>
                </div>
            </div>
        </div>
    </div>`
    const videoCards = document.getElementById(categorie)
    videoCards.insertAdjacentHTML('beforeend', videoCardTemplate)
}

const addVideoCardTemplates = function (data, categorie) {
    for(let i = 0; i < Math.min(wootubeCtx.constants.videoPageSize, data.length); i++) {
        addVideoCardTemplate(data, i, categorie)
    }
}

const addChannelVideoCardTemplates = function (data, categorie) {
    for(let i = 0; i < data.length; i++) {
        addVideoCardTemplate(data, i, categorie)
    }
}

