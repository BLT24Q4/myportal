function checkEmail(event) {
	// 가입 폼 #check-email을 누르면 email 텍스트 상자의 value가 중복되었는지
	//	Ajax로 체크
	const frm = event.target.form;
	const url = event.target.getAttribute("data-target");
	
	const email = frm.email.value.trim();
	
	if (email.length === 0) {
		alert("이메일을 입력해 주세요");
		return;
	}
	
	fetch(`${url}?email=$email`)
	.then(response => {
		console.log(response)
		return response.json();
	})
	.then(data => {
		alert(data);
	})
	.catch(error => {
		console.error("Error:" + error);
	})
}

window.addEventListener("load", (event) => {
	document.getElementById("check-email")
		.addEventListener("click", checkEmail);
})