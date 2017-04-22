<!DOCTYPE html>
<#import "/spring.ftl" as spring />
<html>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


<body>

<div style="width: 50%;margin: auto;">

<div style="height: 100px"></div>

<div style="float: right;"> <a href="<@spring.url '/logout'/>"> Logout </a> </div>

<p>
<h2>Welcome <i>${user.username}</i> to the store!</h2>
<p>
You have bought ${user.bought} units of delicious Spam, what is not surprising as this is the best spam in the world!
<p>
Your credit card ${user.creditCard} will be charged only tomorrow, so why not to buy some more today? 
<p>

<img width="256" alt="Spam can" src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/09/Spam_can.png/512px-Spam_can.png"/>
<form action="<@spring.url '/buy'/>" method="GET">
  Amount: <input style="width: 100px" type="number" min="1" max="100" value="1" name="amount">  <input type="submit" value="Buy!">
</form> 
<p>



<p>

<form action="<@spring.url '/comment'/>" method="POST">
  What to tell something about our delicious spam? <br>
  <input style="width: 600px" type="text" name="content">  <input type="submit" value="Comment">
</form> 
<p>

<#list comments as userComment>
    <div>
    <i>${userComment.user}</i> said that
    ${userComment.comment}
    </div>
    <br>
</#list>

</div>
</body>
</html>