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
		//	ex. {result: 'success', exists: false}
		if (data.exists) {
			alert("이미 사용중인 이메일입니다.");
			frm.emailCheck.value="n";
			throw new Error("중복된 이메일입니다.");
		} else {
			alert("사용 가능한 이메일입니다.")
			frm.emailCheck.value="y";
		}
	})
	.catch(error => {
		console.error(error);
	})
}

window.addEventListener("load", (event) => {
	document.getElementById("check-email")
		.addEventListener("click", checkEmail);
		
	document.getElementById("join-form")
		.addEventListener("submit", event => {
		//	기본 이벤트 제거
		event.preventDefault();
		const frm = event.target;
		
		/*
		if (frm.name.value.trim().length === 0) {
			alert("이름을 입력하세요.");
			frm.name.focus();
			return;
		}
		
		if (frm.email.value.trim().length === 0) {
			alert("이메일을 입력하세요.");
			frm.email.focus();
			return;
		}
		
		if (frm.password.value.trim().length === 0) {
			alert("비밀번호를 입력하세요.");
			frm.password.focus();
			return;
		}
		*/
		
		if (frm.emailCheck.value !== 'y') {
			alert('이메일 중복 확인을 해 주세요!');
			return;
		}
		
		frm.submit();
	});
})