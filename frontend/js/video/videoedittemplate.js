addVideoEditTemplate(json);

function addVideoEditTemplate(json) {
    const videoEditTemplate = 
    `
    <div class="container-fluid max-width-lg">
                    <div class="card-block">
                        <div class="row">
                            <div class="col-md-7">
                                <h4 class="card-title">Video 수정</h4>
                                <form class="form-horizontal mrg-top-40 pdd-right-30">
                                    <div class="form-group row">
                                        <label for="form-1-1" class="col-md-2 control-label">YOUTUBE ID</label>
                                        <div class="col-md-10">
                                            <input type="text" class="form-control" id="youtube-id" placeholder="YOUTUBE ID를 입력하세요">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="form-1-2" class="col-md-2 control-label">제목</label>
                                        <div class="col-md-10">
                                            <input type="text" class="form-control" id="title"
                                                placeholder="제목을 입력하세요">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="form-1-5" class="col-md-2 control-label">내용</label>
                                        <div class="col-md-10">
                                            <textarea class="form-control" rows="3" id="contents"></textarea>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <button id="save-btn" class="btn btn-danger">Danger</button>
                </div>
    `
    const editArea = document.getElementById('edit-area');
    editArea.insertAdjacentHTML('beforeend', videoEditTemplate);
}