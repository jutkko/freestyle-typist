$(document).ready(function(){
//$("button.link").on("click", function(e)
//    {
//  alert("kk");
//      e.preventDefault();
//      location.reload();
//    });
$("button.link").on("click", function(e)
{
  e.preventDefault();
  var linkurl     = $(this).attr("href");
  var linkhtmlurl = linkurl.substring(1, linkurl.length);

  window.location = linkhtmlurl;
});
});