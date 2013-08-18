$(document).ready(function()
{

  var content = $("#content");

  $("button.link").on("click", function(e)
  {
    $("#welcomeMessage").hide();
    e.preventDefault();
    var linkurl     = $(this).attr("href");
    var linkhtmlurl = linkurl.substring(1, linkurl.length);
    var imgloader   = '<center style="margin-top: 200px;"><img src="img/loader.gif" alt="loading..." /></center>';
    
    content.html(imgloader);
    $("#defaultpanel").panel("close");

    setTimeout(function() 
    { content.load(linkhtmlurl, function() 
      { /* no callback */ });
    }, 500);
  });
  
  $("button.link-redirect").on("click", function(e)
      {
        e.preventDefault();
        var linkurl     = $(this).attr("href");
        var linkhtmlurl = linkurl.substring(1, linkurl.length);

        window.location = linkhtmlurl;

//        setTimeout(function() 
//        { content.load(linkhtmlurl, function() 
//          { /* no callback */ });
//        }, 500);
      });


});