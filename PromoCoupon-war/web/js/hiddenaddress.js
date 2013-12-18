function display(){
	console.log("here");
	var disp = document.getElementsByClassName("hidden");
	if(document.getElementById("new").checked==true){
		for(var i=0;i<disp.length;i++){
			console.log();
			disp[i].style.display="table-row";
			}
		}else{
			for(var i=0;i<disp.length;i++){
				console.log(i);
				disp[i].style.display="none";
				}			
			}
}