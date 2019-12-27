	$("button").hide();
	$("#loginState").hide();
	$(document).ready(function () {
		
		$(".rankBtn").show();
		$(".list").hide();
		
		$("#todayBtn").click(function(){
			$(".list").hide();
			$("#today").show();
		});
		$("#topBtn").click(function(){
			$(".list").hide();
			$("#top").show();
		});
		$("#tomonthBtn").click(function(){
			$(".list").hide();
			$("#tomonth").show();
		});
		$("#myRankBtn").click(function(){
			$(".list").hide();
			$("#myRank").show();
		});

		
		//sessionInfo값을 동기요청
		let loginState = null;
		$.ajax({
			url : "/puzzle/GetSessionInfo",
			method : "POST",
			async : false, //false 동기, true 비동기
			success : function(json) {
				loginState = json;
			}
		});
		
		//상단 로그인 id표시
		$("#loginState").text(loginState+"님 접속중");
		
		$.ajax({
			url : "/puzzle/GetRank",
			method : "POST",
			data : {"memberId" : loginState},
			success : function(json) {
				//console.log(json);
				$.each(json, function(i) {
					if(json[i] == null) {
						return;
					}
					if(i<10) {
						$("#today").append("<li>" + json[i].count + "회 " + json[i].timer + "초 " + json[i].member.memberId + "</li>");
					}
					if(i>=10 && i<20) {
						$("#tomonth").append("<li>" + json[i].count + "회 " + json[i].timer + "초 " + json[i].member.memberId + "</li>");
					}
					if(i>=20 && i<30) {
						$("#top").append("<li>" + json[i].count + "회 " + json[i].timer + "초 " + json[i].member.memberId + "</li>");
					}
					if(i>=30 && i<40) {
						$("#myRank").append("<li>" + json[i].count + "회 " + json[i].timer + "초 " + json[i].member.memberId + "</li>");
					}
				});
			}
		});
		
		if (loginState != null) {
			$("#logoutBtn").show();
			$("#startBtn").show();
			$("#loginState").show();
			$("#updateBtn").show();
			$("#myRankBtn").show();
		}
		else {
			$("#loginBtn").show();
			$("#joinBtn").show();
		}
		$("#loginBtn").click(function(){
			location.href = "/puzzle/login.html";
		});
		$("#joinBtn").click(function(){
			location.href = "/puzzle/join.html";
		});
		$("#startBtn").click(function(){
			location.href = "/puzzle/puzzle.html";
		});
		$("#updateBtn").click(function(){
			location.href = "/puzzle/update.html";
		});
		$("#homeBtn").click(function(){
			location.href = "/puzzle/index.html";
		});
		$("#logoutBtn").click(function(){
			$.ajax({
				url : "/puzzle/Logout",
				method : "POST",
				success : function(){
					location.href = "/puzzle/index.html";
				}
			});
		});
	});