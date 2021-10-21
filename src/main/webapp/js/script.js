/**
 * Javascript file for index.html page
 */
console.log("JS is connected")

let visible = false;
window.onload = function sendNewRequest() {
	
}

function mapResponse(request) {
	
}

function getRequests() {
	let xhttp =  new XMLHttpRequest();
	let url = '/Project_1/JSONServlet/requests.json'
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState==4 && xhttp.status==200){
			let response = JSON.parse(xhttp.responseText);
			//mapResponse(response);
			console.log(response);

		}
	}
	xhttp.open('GET', url);
	xhttp.send();
}

getRequests();