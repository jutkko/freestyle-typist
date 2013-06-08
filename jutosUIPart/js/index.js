$(document).ready(function()
{


  var content = $("#content");

  {
    content.load("home.html");
  };

  $("a.navlink").on("click", function(e)
  {
    e.preventDefault();
    var linkurl     = $(this).attr("href");
    var imgloader   = '<center style="margin-top: 30px;"><img src="img/loader.gif" alt="loading..." /></center>';
    var linkhtmlurl = linkurl.substring(1, linkurl.length);

    content.html(imgloader);
    $("#defaultpanel").panel("close");

    setTimeout(function() 
    { content.load(linkhtmlurl, function() 
      { /* no callback */ }) 
    }, 500);
  });

});