$(document).ready(function()
{

  var content = $("#content");
  var friendsPanel = $("#friendspanel");
  var notificationPanel = $("#notificationpanel");

  {
    content.load("userInformation.jsp");
//    friendsPanel.load("friendsPanel.jsp");
  };  

  $("a.navlink").on("click", function(e)
  {
    e.preventDefault();
    var linkurl     = $(this).attr("href");
    var imgloader   = '<center style="margin-top: 200px;"><img src="img/loader.gif" alt="loading..." /></center>';
    var linkhtmlurl = linkurl.substring(1, linkurl.length);

    content.load(imgloader);
    $("#defaultpanel").panel("close");

    setTimeout(function() 
    { content.load(linkhtmlurl, function() 
      { /* no callback */ });
    }, 500);
  });
  
  $("a.navlink-home").on("click", function(e)
      {
        e.preventDefault();
        var linkurl     = $(this).attr("href");
        var linkhtmlurl = linkurl.substring(1, linkurl.length);

        $("#defaultpanel").panel("close");

        window.location = linkhtmlurl;
      });
  
  $("#logout").on("click", function()
  {
    window.location = "logout";
  });
  
  friendsPanel.panel({
    beforeopen: function() 
    {
      //To refresh
      friendsPanel.load("friendsPanel.jsp",function(){
        friendsPanel.trigger('create');
      });
    }
  });
  
  notificationPanel.panel({
    beforeopen: function() 
    {
      //To refresh
      notificationPanel.load("notificationPanel.jsp",function(){
        notificationPanel.trigger('create');
      });
    }
  });

});