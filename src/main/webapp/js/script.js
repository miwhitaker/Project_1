/**
 * Javascript file for index.html page
 */
console.log("JS is connected")


window.onload = () => {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState == 4 && xhttp.status==200){
			
			
			//document.querySelector("#my-requests")
			
			document.getElementById("login-header").innerText=`Welcome ${user.name}`;
		}
	}
	
	xhttp.open("GET", "http://localhost:8080/ExpenseController/getUserAccount.json");
	xhttp.send();
}
