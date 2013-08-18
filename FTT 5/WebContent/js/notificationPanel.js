$(document).ready(function()
{
  var notificationPanel = $("#notificationpanel");
  
  $("a.link").on("click", function(e)
      {
        e.preventDefault();
        $("#notificationpanel").panel("close");
        var linkurl     = $(this).attr("href");
        var linkhtmlurl = linkurl.substring(1, linkurl.length);
        $("#content").load(linkhtmlurl);
      });
  
  $("#confirmFriend").submit(function()
  {
    $.ajax({
      url: "confirmFriend",
      type: "POST",
      dataType: "json",
      data: $("#confirmFriend").serialize(),
      async: false,
      success: function(data){
        if(!data.isValid)
        {
          alert("Could not accept the friend!");
        }
      }
    });
    
    notificationPanel.load("notificationPanel.jsp",function(){
      notificationPanel.trigger("create");
    });
    return false;
  });
});