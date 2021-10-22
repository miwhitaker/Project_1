/**
 * 
 */


function mapResponse(request) {
	let tableBody = document.getElementById("tbody")
	for(i of request) {
		let row = document.createElement("tr")
		let row1 = document.createElement("td")
		row1.innerHTML = i.id;
		row.appendChild(row1);
		let row2 = document.createElement("td")
		row2.innerHTML = i.description;
		row.appendChild(row2);
		let row3 = document.createElement("td")
		row3.innerHTML = i.typeName;
		row.appendChild(row3);
		let row4 = document.createElement("td")
		row4.innerHTML = i.amount;
		row.appendChild(row4);
		let row5 = document.createElement("td")
		row5.innerHTML = i.submittedDate;
		row.appendChild(row5);
		let row6 = document.createElement("td")
		row6.innerHTML = i.author;
		row.appendChild(row6);
		let row7 = document.createElement("td")
		row7.innerHTML = i.statusName;
		row.appendChild(row7);
		let row8 = document.createElement("td")
		row8.innerHTML = i.resolvedDate;
		row.appendChild(row8);
		let row9 = document.createElement("td")
		row9.innerHTML = i.resolver;
		row.appendChild(row9);
		tableBody.appendChild(row);
	}
	
}

function getRequests() {
	let xhttp =  new XMLHttpRequest();
	let url = '/Project_1/JSONServlet/getadmin.json'
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState==4 && xhttp.status==200){
			let response = JSON.parse(xhttp.responseText);
			console.log(response);
			mapResponse(response)
		}
	}
	xhttp.open('GET', url);
	xhttp.send();
}

async function approveRequests() {
	var params = `statusChange=${document.querySelector('#status').value}&requestId=${document.querySelector('#reqId').value}`;
	(async () => {
  		const rawResponse = await fetch('/Project_1/JSONServlet/changestatus.json', {
    		method: 'POST',
   			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
	    	body: params
	  });
		await rawResponse.text();
		
	clearHtml();
	getRequests();
	
	})();
	
}

function clearHtml() {
	document.getElementById('tbody').innerHTML = '';
	document.getElementById("reqId").value = '';
}

getRequests();