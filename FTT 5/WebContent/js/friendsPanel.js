$(document).ready(function()
{ 
  var friendsPanel = $("#friendspanel");
  

  $("button.link").on("click", function(e)
      {
        e.preventDefault();
        var linkurl     = $(this).attr("href");
        var linkhtmlurl = linkurl.substring(1, linkurl.length);
        $("#content").load(linkhtmlurl);
      });
  
  $("#addFriend").submit(function()
  {
    $.ajax({
      url: "addFriend",
      type: "POST",
      dataType: "json",
      data: $("#addFriend").serialize(),
      success: function(data){
        if(data.isValid)
        {
          $("#addFriendAlert").html('<div class="alert alert-success fade in"> <strong>Congrats!</strong> Your request was sent! </div>');
        } else
        {
          $("#addFriendAlert").html('<div class="alert alert-error fade in"><strong>Warning!</strong> '+ data.message + ' </div>');
        }
      }
    });
      friendsPanel.load("friendsPanel.jsp",function(){
      friendsPanel.trigger("create");
    });
    return false;
  }); 
  
  $('#mform').submit(function() {
    var json = new Array();
    $("#friendspanel").panel("close");

    $('#mform .btn.active').each(function() {
       json.push($(this).val());
     });
    $.ajax({
      url: "challengeFriend",
      type: "POST",
      dataType: "json",
      data: {json : json},
      async: false,
      success: function(data){
        alert("challenged successfully");
      }
    });
    friendsPanel.load("friendsPanel.jsp",function(){
      friendsPanel.trigger("create");
    });
    return false;
  });
});