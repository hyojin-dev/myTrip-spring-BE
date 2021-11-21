// 리뷰 작성
function save() {
    let userReview = {
        "title": $('#title').val(),
        "place": $('#place').val(),
        "review": $('#review').val(),
        // "photoFile": $('#file')[0].files[0]
    }
    // console.log(typeof $('#file')[0].files[0])





    $.ajax({
        type: "POST",
        url: "/userReview",
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(userReview),
        success: function (response) {
            console.log(response);
            // console.log(response["photoFile"]);
            alert("포스팅 업데이트 성공!");
            // window.location.reload();
        }
    });
}

// 파일 업로더 js

function readURL(input) {
    if (input.files && input.files[0]) {

        var reader = new FileReader();

        reader.onload = function (e) {
            $('.image-upload-wrap').hide();

            $('.file-upload-image').attr('src', e.target.result);
            $('.file-upload-content').show();

            $('.image-title').html(input.files[0].name);
        };

        reader.readAsDataURL(input.files[0]);

    } else {
        removeUpload();
    }
}

function removeUpload() {
    $('.file-upload-input').replaceWith($('.file-upload-input').clone());
    $('.file-upload-content').hide();
    $('.image-upload-wrap').show();
}

$('.image-upload-wrap').bind('dragover', function () {
    $('.image-upload-wrap').addClass('image-dropping');
});
$('.image-upload-wrap').bind('dragleave', function () {
    $('.image-upload-wrap').removeClass('image-dropping');
});