$(function(){
	$("#multipartFile").on('change',function () {
      ext = $(this).val().split('.').pop().toLowerCase();

      if($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
         resetFormElement($(this)); //폼 초기화
         swal({
				title : " ",
				text: "이미지 파일이 아닙니다! (gif, png, jpg, jpeg 만 업로드 가능)",
				type : "error"
			});
      } else {
         file = $('#multipartFile').prop("files")[0];
         blobURL = window.URL.createObjectURL(file);
         $('#imagePreview').attr('src', blobURL);
      }
   })
})
function resetFormElement(e) {
      e.wrap('<form>').closest('form').get(0).reset();
      e.unwrap();
   }