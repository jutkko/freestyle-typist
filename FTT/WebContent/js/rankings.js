$(document).ready(function(){
  $("a.navlink").on("click", function(e)
		  {
		    e.preventDefault();
		    var linkurl     = $(this).attr("href");
		    var imgloader   = '<center style="margin-top: 200px;"><img src="img/loader.gif" alt="loading..." /></center>';
		    var linkhtmlurl = linkurl.substring(1, linkurl.length);

		    $("#tableSection").load(imgloader);

		    setTimeout(function() 
		    { $("#tableSection").load(linkhtmlurl, function() 
		      { /* no callback */ });
		    }, 500);
		  });
});