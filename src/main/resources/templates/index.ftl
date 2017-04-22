<!DOCTYPE html>

<#import "/spring.ftl" as spring />

<html>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


<body>

<center>


<p>
<p>
<h2>Login to our store</h2>
<p>
<form action="<@spring.url '/login'/>" method="POST">
  Username: <input type="text" name="user">
  <br><br>
  Password: <input type="password" name="pass">
  <br><br>
  <input type="submit" value="Login!">
</form> 


<#if error??>
<span style="color:rgb(220, 0, 0)"> ${error} </span>
	<#if errorDetail??>
	<br>
	<!-- debugging code, rmove it for prod! -->
	<span style="font-size: 75%; color: white"> ${errorDetail} </span>
	</#if>
<br><br>
</#if>

</center>
	
	
	
</body>
</html>