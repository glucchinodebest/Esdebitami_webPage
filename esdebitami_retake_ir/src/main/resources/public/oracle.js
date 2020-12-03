var start = 0;

function ask() {

	var a = document.getElementById("ask");

	if (a.innerHTML == 'ask') {
		
		document.getElementById("output").innerHTML = "";

		var question = document.getElementById("question").value;

		if (question != '') {

			document.getElementById("ask").innerHTML = "searching...";

			var template = document.getElementById("template");
			var answers = document.getElementById("answers");
			
			template = template.options[template.selectedIndex].value;	
			answers = answers.options[answers.selectedIndex].value;

			question = encodeURIComponent(question);
			
			var url = `http://localhost:8045/vvas-ir/templates/${template}/answers?count=${answers}&question=${question}&language=IT`
			var xmlHttp = new XMLHttpRequest();
			xmlHttp.addEventListener('load', complete, false);
			xmlHttp.open("GET", url, true);
			start=new Date().getTime();
			xmlHttp.send();
		}
	}
}

function complete(e) {

	var end = new Date().getTime();
	var response = JSON.parse(e.target.responseText);
	var output = document.getElementById("output");

	for (var i = 0; i < response.length; i++) {
		var r=response[i];
		var p = document.createElement("p");
		var link = document.createElement('a');
		link.appendChild(document.createTextNode(r.content));
		link.href = r.uri;
		link.setAttribute("target", "_blank");
		p.appendChild(document.createTextNode('('+ r.confidence.toFixed(2) + ') '));
		p.appendChild(document.createTextNode(r.reference+': '));
		p.appendChild(link);
		output.appendChild(p);
	}

	var time=document.createElement("small");
	time.appendChild(document.createTextNode(((end-start)/1000).toFixed(2)+' seconds'));
	output.appendChild(time);
	
	document.getElementById("ask").innerHTML = "ask";
}