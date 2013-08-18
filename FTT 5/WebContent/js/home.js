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
  var resultTable = $("#resultTable");
  var resultMessage = $("#resultMessage");
  var playMode    = $("#playMode");

  var source = $("#source");
  
  resultTable.hide();
  source.hide();
  
    playMode.hide();
    gameBox.show();
    startCountDown(3, 1000, start);

  // Game part
  var textBox      = $("#textbox");
 // var submitButton   = $("#submitButton");
  var timer;
  var speedCounter; 
  
  var bar = $("#progressBar");
  
  var entered = 0;
  var correct = 0;
  var incorrect = 0;
  var spaces = 0;
  var currSpaces = 0;
  
  var correctChars = 0;
  var currCorrectChars = 0;
  
  var limit = 25;
  var nextChar = 0;
  var line = "";
  nextLine();
  
  //alert(m);
  function nextLine() 
  {
  if(nextChar + limit>= p.length) 
  {
    trimLine(p.length);
    nextChar = p.length;
    return;
  }
    for(var i = nextChar + limit ; i > nextChar; i--)
    {
      if(p.charAt(i) == ' ') 
      {
        trimLine(i+1);
        nextChar = i+1;
        return;
      } 
    }
  };
  
  //get rid of white spaces at the start of line until given index 
  function trimLine(end) 
  {
    for(var j = nextChar ; j < end ; j++)
    {
      if(p.charAt(j) != ' ')
      {
        line = p.substring(j, end);
          return;
      }
    }
  };

  // Bind enter key to submit
  textBox.keyup(function(event)
  {
  var answer = document.getElementById("textbox").value;
  if(answer.length > entered) {
    if(answer.charAt(entered)==p.charAt(entered)) {
      correct++;
    }
    else {
      incorrect++;
    }
    entered++;
  }
  
  if(event.keyCode == 8 || event.keyCode == 46) {
    entered--;
  }

    check();
  });

 // submitButton.click(check);
  
  function check()
  {
    var answer = document.getElementById("textbox").value;
    
    spaces = currSpaces;
    correctChars = currCorrectChars;
    var highlightStartTag = "<font style='color:red; background-color:white;'>";
  var highlightEndTag = "</font>";
  var newText = "";
  // alert(nextChar + " " + line.length);
  var lineStart = nextChar - line.length;
  var i;
  
  
    for(i = lineStart; i < nextChar; i++) 
    {
      if(answer.charAt(i-lineStart) != p.charAt(i))
      {
        //user typed wrong
         newText = p.substring(lineStart, i) + highlightStartTag + 
       p.substring(i, i+1) + highlightEndTag +
       p.substring(i+1, nextChar);
         document.getElementById("theText").innerHTML = newText;
         
          if(i - lineStart < answer.length)
          {
            resultMessage.text("Oops, please check your input again!");
          }
          else
          {
            resultMessage.text("");
          }
          
          var progressPercentage = (i-lineStart + currCorrectChars)/chars*100;
          On.socket.send(progressPercentage);
  //        alert(progressPercentage);
          bar.width(progressPercentage + "%");
          var time = new Date().getTime() - timer;
          bar.text(Math.round(spaces / (time/1000/60)) + " wpm");
          bar.css("color", "black");
          //updateOpponentProgress(progressPercentage);
          return;
      }
      
      if(answer.charAt(i-lineStart) == ' ')
      {
        spaces++;
      }
    }
    
    if(nextChar>=p.length)
    {
      spaces++;
     bar.width(100*12);
      var time = new Date().getTime() - timer;
       bar.text(Math.round(spaces / (time/1000/60)) + " wpm");
         document.getElementById("theText").innerHTML = p;
       // submitButton.attr("disabled", true);
        textBox.attr("disabled", true);
        clearInterval(speedCounter);
      //  document.getElementById("speed").innerHTML = "  ";
//        resultMessage.text("Congratulations, you have just finished a game of our WebApp! Here is some data:");
        resultMessage.text("You won easily !!! Your opponent was miles away !!!");
        var time = new Date().getTime() - timer;
        On.socket.send(100);
        var table = document.getElementById('resultTable');
        table.rows[0].cells[1].innerHTML = Math.round(time / 1000) + " seconds";
        table.rows[1].cells[1].innerHTML = Math.round(words / (time/1000/60));
       //table.rows[2].cells[1].innerHTML = correct/(correct+incorrect)*100 + "%";
        resultTable.show();
        source.show();
        $("#panelshiding").show();
    }
    else
    {
       currSpaces = spaces;
       currCorrectChars += line.length;
       document.getElementById("textbox").value = "";
       nextLine();
       document.getElementById("theText").innerHTML = line;
    }
    
   };
  
  function startCountDown(i, p, f) {
  // store parameters
  var pause = p;
  var fn = f;
  // make reference to div
  var countDownObj = document.getElementById("countDown");
  if (countDownObj == null) {
    // error
    alert("div not found, check your id");
    return;
  }
  
  countDownObj.count = function(i) {
    if (i == -1) {
      // execute function
      countDownObj.innerHTML = "";
      fn();
      // stop
      return;
    }
    if (i == 0) {
      countDownObj.innerHTML = "Go!!!";
    }
    else {
        // write out count
        countDownObj.innerHTML = i;
    }
    countDownObj.style.fontSize = "80px";
    setTimeout(function() {
      // repeat
      countDownObj.count(i - 1);
    },
    pause
    );
  };
  
  // set it going
  countDownObj.count(i);
  }

  function start() {
    //start the timer and show the first line
      if (!timer)
      {
        timer = new Date().getTime();
        document.getElementById("theText").innerHTML = line;
      }
      textBox.attr("disabled", false);
    //  submitButton.attr("disabled", false);
      $("#panelshiding").hide();
      textBox.focus();
      document.getElementById("header").innerHTML = "";
      speedCounter = setInterval(updateSpeed, 1000);
  }
  
  function updateOpponentProgress(progressPercentageOpponent) {
    $("#progressBarOpponent").width(progressPercentageOpponent + "%");
  }
  
  function updateOpponentProgresss() {
   alert("no");
  }

  
 function updateSpeed()
  {
   var time = new Date().getTime() - timer;
   bar.text(Math.round(spaces / (time/1000/60)) + " wpm");
   bar.css("color", "black");
  }

    
//  function progressBar()
//  {
//    
//
//    var progress = setInterval(function() 
//    {
//    var $bar = $('.bar');
//   
//    if ($bar.width()==400) {
//        clearInterval(progress);
//        $('.progress').removeClass('active');
//    } else {
//        $bar.width($bar.width()+40);
//    }
//    $bar.text($bar.width()/4 + "%");
//    }, 800);
//
//  }

});
