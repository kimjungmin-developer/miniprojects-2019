const commentApp = (function() {
    
    const CommentEvent = function() {
        const commentService = new CommentService();

        const saveComment = function() {
            const commentSaveBtn = document.getElementById('save-comment-btn')
            commentSaveBtn.addEventListener('click', commentService.save)
        }

        const prepareEditComment = function() {
            const commentSection = document.getElementById('comment-section')
            commentSection.addEventListener('click', commentService.prepareEdit)
        }

        const finalizeEditComment = function() {
            const commentSection = document.getElementById('comment-section')
            commentSection.addEventListener('click', commentService.finalizeEdit)
        }

        const deleteComment = function() {
            const commentSection = document.getElementById('comment-section')
            commentSection.addEventListener('click', commentService.deleteComment)
        }

        const init = function() {
            saveComment();
            prepareEditComment();
            finalizeEditComment();
            deleteComment();
        }

        return {
            init: init
        }
    }

    const CommentService = function() {
        const save = function() {
            console.log("inside comment.js/saveComment fucntion! hooray!")
            const body = {};
            body.contents = document.getElementById('comment-input').value;
            const dataBody = JSON.stringify(body);
            // api.saveComment(dataBody, wootubeCtx.util.getUrlParams().id)
            //     .then(response => response.json())
            //     .then(data => insertComment(data))
            insertComment(testData);
        }

        const insertComment = function (data) {
            const commentSection = document.getElementById('comment-section');
            const commentTemplate = ` 
        <li class="comment-item border bottom mrg-btm-30" id="${data.id}">
            <img class="thumb-img img-circle" src="https://avatars3.githubusercontent.com/u/50367798?v=4" alt="">
            <div class="info">
                <span href="" class="text-bold inline-block">${data.author.name}</span>
        
                <p class="width-80" id="contents-${data.id}"> ${data.contents}</p>
                <span class="text-bold inline-block"> 생성날짜:${data.createDate}</span>
                <button type="button" id="edit-btn-${data.id}" class="btn btn-danger">
                    <i class="ti-pencil text-dark font-size-16 pdd-horizontal-5"></i>
                </button>
                <button id="edit-confirm-btn-${data.id}" class="float-right pointer btn btn-icon" style="visibility:hidden">저장확인</button>
                <button type="button" id="delete-btn-${data.id}" class="btn btn-danger">
                    <i class="ti-trash text-dark font-size-16 pdd-horizontal-5"></i>
                </button>
                <input type="text" id="edit-text-${data.id}" style="visibility:hidden">
            </div>
        </li>
        `
            commentSection.insertAdjacentHTML("afterbegin", commentTemplate)
        }

        const prepareEdit = function(event) {
            console.log("inside prepareEdit()");
            console.log(event.target.classList);
            console.log(event.target.closest('i'));
            const itag = event.target.querySelector('i');
            console.log(itag)

            if(itag) {
                if(itag.classList.contains("ti-pencil")) {
                    console.log("inside prepareEdit() : inside if-statement")
                    const litag = event.target.closest('li');
                    const commentId = litag.id;
                    console.log(litag);
                    console.log(commentId);
                    const editConfirmBtn = document.getElementById('edit-confirm-btn-' + commentId);
                    const editText = document.getElementById('edit-text-' + commentId);
                    editConfirmBtn.setAttribute("style", "visibility:visible");
                    editText.setAttribute("style", "visibility:visible");
                    console.log("at the end of prepareEdit()")
                }
            }
        }

        const finalizeEdit = function(event) {
            console.log("inside finalizeEdit()");
            if(event.target.classList.contains('btn-icon')) {
                console.log('inside finalizeEdit : if-statement')
                const litag = event.target.closest('li');
                const commentId = litag.id;
                const body = {};
                body.contents = document.getElementById('edit-text-' + commentId).innerText;
                const dataBody = JSON.stringify(body);
                // api.editComment(dataBody, wootubeCtx.util.getUrlParams().id, commentId)
                // .then(response => response.json())
                // .then(json => updateTemplate(json, commentId));
                updateTemplate(testData2,1)
            }
        }

        const updateTemplate = function(json, commentId) {
            console.log(json)
            if (json.result) {
                // error
                alert(json.message);
                return false;
            }
            console.log(json)
            const contents = document.getElementById('contents-'+commentId);
            contents.innerText = json.contents;
        }

        const deleteComment = function(event) {
            const itag = event.target.querySelector('i');
            console.log("this is i-tag: " + itag)
            //console.log("this is i-tag-classList: " + itag.classList)
            if(itag) {
                if(itag.classList.contains('ti-trash')) {
                    console.log("inside deleteComment : if-statement")
                    const litag = event.target.closest('li');
                    const commentId = litag.id;
                    // api.deleteComment(wootubeCtx.util.getUrlParams().id, commentId)
                    // .then(response => response.json())
                    // .then(json => deleteCommentFromTemplate(json, commentId))
                    deleteCommentFromTemplate(testData2, 1);
                }
            }
        }

        const deleteCommentFromTemplate = function(json, commentId) {
            if (json.result) {
                // error
                alert(json.message);
                return false;
            }
            const litagToDelete = document.getElementById(''+commentId);
            litagToDelete.innerHTML = '';
        }

        return {
            save:save,
            prepareEdit:prepareEdit,
            finalizeEdit:finalizeEdit,
            deleteComment:deleteComment
        }
    }

    const init = function() {
        const commentEvent = new CommentEvent();
        commentEvent.init();
    }

    return {
        init:init
    }
})();

commentApp.init();