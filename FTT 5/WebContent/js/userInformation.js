$(document).ready(function()
{

  var notificationPanel = $("#notificationpanel");
  
  notificationPanel.panel({
    beforeopen: function() 
    {
      //To refresh
      notificationPanel.load("notificationPanel.jsp",function(){
        notificationPanel.trigger('create');
      });
    }
  });

  $("#back-friendspanel").on("click", function(){
    alert("sss");
    notificationPanel.load("notificationPanel.jsp");
  });
});