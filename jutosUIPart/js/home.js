$(document).ready(function()
{
  // Disabling enter to submit
  $('form input').keydown(function(event)
  {
    if(event.keyCode == 13) 
    {
      event.preventDefault();
      return false;
    }
  });

  var gameBox     = $("#gameBox");
  var resultSection = $("#resultSection");
  var playMode    = $("#playMode");

  gameBox.hide();
  resultSection.hide();

  $("#practiceMode").click(function()
  {
    playMode.hide();
    gameBox.show();
  });

  // Game part
  var textBox      = $("#textbox");
  var submitButton   = $("#submitButton");
  var resultMessage = $("#resultMessage");
  var timer;
  // var checkedCorrect;

  // Bind enter key to submit
  textBox.keyup(function(event)
  {
    if (event.keyCode == 13) 
    {
      check();
    }
  });

  textBox.click(function()
  {
    if (!timer)
      timer = new Date().getTime();
  });

  submitButton.click(check);

  function check()
  {
    var text = "some text";
    var answer = document.getElementById("textbox").value;
    if(answer == text)
    {
      // checkedCorrect = true;
      submitButton.attr("disabled", true);
      textBox.attr("disabled", true);
      resultMessage.text("Congratulations, you have just finished a game of our WebApp! Here is some data:");
      var time = new Date().getTime() - timer;
      var table = document.getElementById('resultTable');
      table.rows[0].cells[1].innerHTML = time / 1000 + "seconds";
      table.rows[1].cells[1].innerHTML = "0";
      table.rows[2].cells[1].innerHTML = "0"+ "%";
    } else
    {
      resultMessage.text("Oops, please check your input again!");
    }
    resultSection.show();
  }

});