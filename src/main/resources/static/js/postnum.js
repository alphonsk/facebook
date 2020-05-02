$(document).ready(function() {

    $('#add-comment').on('show.bs.modal', function(e) {
        var postid = $(e.relatedTarget).data('id');
        $(this).find('#post').val(postid);
    });



});

//    alert("I'm active");
//     $("selector").on("event", "delegateselector", function(){
//        // some code...
//      });

//    $('div.roomdiv').on('show.bs.modal','#myModal', function (e) {
//        var getIdFromRow = $(e.relatedTarget).data('id');
//        $("#buyghc").val(getIdFromRow);
//    });

//    $('#add-comment').on('show.bs.modal', function(e) {
//        var x = $(e.relatedTarget).data('id');
//        $(this).find('.roomNumber').text(x);
//    });