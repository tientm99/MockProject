document.addEventListener("DOMContentLoaded", function() {
	var menuButton = document.getElementById("menu");
	var menuList = document.getElementById("menuList");
	menuButton.addEventListener("click", function() {
		if (menuList.style.display === "none") {
			menuList.style.display = "block";
		} else {
			menuList.style.display = "none";
		}
	});
});

//$(document).ready(function(){//
	//$(".list").click(function(){
		//$.get("employeeList.jsp", function(data){
          //  $("#content").html(data);
//        });
	//})
//})

//$(document).ready(function(){
	//$(".add").click(function(){
		//$.get("employeeAdd.jsp", function(data){
          //  $("#content").html(data);
       // });
	//})
//})
