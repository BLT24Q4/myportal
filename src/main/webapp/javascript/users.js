function checkEmail(event) {
	// 가입 폼 #check-email을 누르면 email 텍스트 상자의 value가 중복되었는지
	//	Ajax로 체크
	alert("이메일 체크 이벤트");
}

window.addEventListener("load", (event) => {
	document.getElementById("check-email")
		.addEventListener("click", checkEmail);
})