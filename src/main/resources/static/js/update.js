// (1) 회원정보 수정
function update(userId, event) {
	event.preventDefault();
 console.log(userId);


	let data = $("#profileUpdate").serialize();
	console.log(data);

	$.ajax({
		type:"put",
		url:`/api/user/${userId}`,
		data:data,
		//contentType:"application/x-www-form-urlencoded: charset=utf-8",
		dataType:"json"
		
	}).done(res=>{ // httpstatus code기 200번대 
		console.log("성공 ! ")
		console.log(res)
		//location.href = `/user/${userId}`
	}).fail(err=>{ // httpstatus code가 200번대가 아닐 
		
		console.log("실패!! ")
		console.log(err);
	})
}
