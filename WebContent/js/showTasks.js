document.getElementById("category_con").addEventListener("click", createCategoryDiv);
document.getElementById("hide_category").addEventListener("click", hideCategoryDiv);
document.getElementById("category_btn").addEventListener("click", showCategorySelectorDiv);

function createCategoryDiv() {
	document.getElementById("create_category_con").style.display = "block";
}

function hideCategoryDiv(e) {
	document.getElementById("create_category_con").style.visibility = "hidden";
}

function showCategorySelectorDiv () {
	var box = document.getElementById("category_select_box");
	if(box.style.display == "none") {
		box.style.display = "block";
	}
	else {
		box.style.display = "none";
	}
}

$(function() {
  $( "#testDatepicker" ).datepicker({
        showOn: "button", 
        buttonImage: "/img/ic_date_range_black_24dp_2x.png", 
        buttonImageOnly: true,
        dateFormat: 'yy-mm-dd', 
  });
});

