// (1) 회원정보 수정
function update(userId, event) {
	event.preventDefault();
 console.log(userId);
 console.log(event);


	let data = $("#profileUpdate").serialize();
	console.log(data);
	
	$.ajax({
		type:"put",
		url:`/api/user/${userId}`,
		data:data,
		//contentType:"application/x-www-form-urlencoded: charset=utf-8",
		dataType:"json"
		
	}).done(res=>{
		console.log("성공 ! ")
		location.href = `/user/${userId}`
	}).fail(err=>{
		
		console.log("실패!! ")
	})
}
