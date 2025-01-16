function checkEmail(event) {
	// 가입 폼 #check-email을 누르면 email 텍스트 상자의 value가 중복되었는지
	//	Ajax로 체크
	const obj = event.target;
	const target = obj.getAttribute("data-target");
	
	const frm = obj.form;
	const email = frm.email.value;
	
	if (email.trim().length === 0) {
		alert("이메일을 입력하세요.");
		frm.email.focus();
		return;
	}
	
	fetch(`${target}?email=${email}`)
	.then(response => {
		console.log(response);
		return response.json();
	})
	.then(data => {
		console.log(data);
	})
	.catch(error => {
		console.error(error);
	})
}

window.addEventListener("load", (event) => {
	document.getElementById("check-email")
		.addEventListener("click", checkEmail);
})