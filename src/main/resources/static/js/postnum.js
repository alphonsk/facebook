//$(document).ready( function () {
$(document).load(function () {

var myRoomNumber;

$('#rooms li a').click(function() {
   myRoomNumber = $(this).attr('dataid');
   console.log(myRoomNumber);
});

$('#myModal').on('show.bs.modal', function (e) {
    $(this).find('.roomNumber').text(myRoomNumber);
});

    var postnum;
    $('#reply-modal div button').click(function() {
        postnum = $(this).attr('id');
        console.log('CLICKED');
    });

//    $('#add-comment').click(function() {
//       post-id = $(this).attr('post-id');
//    });
//
//    $('#add-comment').on('show.bs.modal', function (e) {
//        $(this).find('.roomNumber').text(post-id);
//    });

});