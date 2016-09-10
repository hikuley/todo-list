/**
 * Created by hikuley on 30/08/16.
 */

var newTodoModal = $('[data-remodal-id=newTodoModal]').remodal();
$(function () {

    $("#TODO, #INPROGRESS,#TEST,#DONE").sortable({
        connectWith: ".connectedSortable"
    }).disableSelection();

    $("#TODO, #INPROGRESS,#TEST,#DONE").droppable({
        drop: function (event, ui) {

            var id = $(ui.draggable[0]).attr("data-todo-id");
            var status = event.target.id;
            var request = {"id": id, "status": status};
            var settings = {
                "url": "/todo/updateStatus",
                "method": "POST",
                "headers": {
                    "content-type": "application/json"
                },
                "data": JSON.stringify(request)
            }
            $.ajax(settings);
        }
    });

});

