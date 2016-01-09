document.getElementById("add_btn").addEventListener("click", createAddDiv);

function createAddDiv() {
	document.getElementById("add_list_con").style.display = "block";
	document.getElementById("back_con").style.webkitFilter = "blur(5px)";; 
	
}


// var newDiv = document.createElement("div");
// 	newDiv.className = "addList";
// 	newDiv.style.width = "400px";
//     newDiv.style.height = "200px";
//     newDiv.style.backgroundColor ="orange";
    
//     newDiv.setAttribute("type", "text");
// 	document.getElementById("add_btn").appendChild(newDiv); 

// 	var x = document.createElement("INPUT");
//     x.setAttribute("type", "submit");
//     document.getElementById("add_btn").appendChild(x);