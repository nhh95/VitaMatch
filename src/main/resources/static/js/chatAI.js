const img = document.querySelector('#chatAIImg');
const chatting = document.querySelector('.chatAI');
const aI_chatting = document.querySelector('.AI_chatting');
const chatting_box = document.querySelector('.chatAI_Box');
const query_Box = document.querySelector('.query_Box');
const chatInner = document.querySelector('.chatting_inner');

const btn = document.querySelector('#ai_btn');
const user_write = document.querySelector('.ai_text');

let lastAI = document.querySelectorAll('.answer_AI_Box').length > 0 
	                    ? document.querySelectorAll('.answer_AI_Box')[document.querySelectorAll('.answer_AI_Box').length - 1]
	                    : null;

const lastUser = document.querySelectorAll('.query_User_Box').length > 0 
                    ? document.querySelectorAll('.query_User_Box')[document.querySelectorAll('.answer_AI_Box').length - 1]
                    : null;

// CSRF 토큰과 헤더 이름을 메타 태그에서 동적으로 가져옵니다.
let csrfToken = $("meta[name='_csrf']").attr("content");
let csrfHeader = $("meta[name='_csrf_header']").attr("content");

img.addEventListener('click', function() {
	img.style.display = 'none';
	chatting.style.display = 'block';
	chatting_box.style.display = 'block';

	let AI_HTML = `
		<div class="answer_AI_Box">
			<img src="/img/chatAI/chatAI.png" class="chatAI_Img">
			<div class="answer_AI"><span>영양제와 관련된 궁금증을 알려주세요.</span></div>
		</div>
   `;
   
   let newElement = document.createElement('div');
   newElement.innerHTML = AI_HTML;
   query_Box.appendChild(newElement);
   scrollToBottom();
	if(chatting.style.display == 'block'){
   		window.addEventListener('scroll', function() {
   		chatplace('.chatAI');  // img 요소를 매개변수로 전달
   		});
		
		btn.addEventListener('click', function(){
			user_bth();
		});
		
		// Enter 키 입력 이벤트 (keyCode 13은 Enter 키)
		user_write.addEventListener('keydown', function(event) {
		    if (event.key == 'Enter') {  // Enter 키가 눌리면
		        event.preventDefault();  // 기본 Enter 동작 방지 (예: 줄바꿈)
		        user_bth();
		    }
		});
		
   	} 
	

});

function user_bth(){
	lastAI = document.querySelectorAll('.answer_AI_Box').length > 0 
		                    ? document.querySelectorAll('.answer_AI_Box')[document.querySelectorAll('.answer_AI_Box').length - 1]
		                    : null;
						
	if(user_write.value.trim().length > 0) {
		let AI_HTML = `
			<div class="query_User_Box">
	         	<div class="query_User"><span>${user_write.value}</span></div>
	         	<img src="/img/chatAI/chatAIUser.png"  class="chatAIUser_Img">
	     	</div>
		`;
	
		let newElement = document.createElement('div');
		newElement.innerHTML = AI_HTML;
		query_Box.appendChild(newElement);
		
		// AJAX 요청
		$.ajax({
		    type: 'POST',
		    url: '/api/send',
		    //dataType: 'json',
		    contentType: 'application/json',
		    data: JSON.stringify({
		        message : user_write.value
		    }),
			beforeSend : function(xhr) {
				xhr.setRequestHeader(csrfHeader, csrfToken);
		    },
		    success: function (response) {
			
				let AI_HTML = `
						<div class="answer_AI_Box">
							<img src="/img/chatAI/chatAI.png" class="chatAI_Img">
							<div class="answer_AI"><span>${response}</span></div>
						</div>
				   `;
				   
				   let newElement = document.createElement('div');
				   newElement.innerHTML = AI_HTML;
				   query_Box.appendChild(newElement);
				   scrollToBottom();
		    },
		    error: function (a, b, c) {
		        console.log(a, b, c);
		    }
		});
		
		user_write.value = ''; 
		scrollToBottom();
	}
}



















// 스크롤 이벤트에 따라 imgplace 함수 호출
function itemplace(item) {
   	const parent = document.querySelector('.container');
    const place = document.querySelector(item);
    const footer = document.querySelector('.site-footer');
    
    let parentRect = parent.getBoundingClientRect();  // 부모 요소의 위치 정보
    let footerRect = footer.getBoundingClientRect();  // footer 요소의 위치 정보
    
    // 부모 요소의 높이
    let parentHeight = parentRect.height;

    // footer의 위쪽 위치 (스크롤된 후, footer 위로는 내려가지 않도록 제한)
    let footerTop = footerRect.top - 700;  // 조정된 footer 위치 (스틱되는 효과)
	
    // 스크롤을 기준으로 이미지의 bottom 위치 계산
    let newBottom = parentHeight - window.scrollY + 10;

	if(chatting.style.display = 'block'){
		
	}
	
	
    // footer와 겹치지 않도록 제한
    if (footerTop < 0) {
        newBottom = parentHeight - window.scrollY - 10;  // footer에 10px 여유를 두고 위치 설정
    	return;
	}

    // 지정된 img 요소의 bottom 위치 조정
    place.style.bottom = newBottom + "px";
}


function chatplace(item) {
   	const parent = document.querySelector('.container');
    const place = document.querySelector(item);
    const footer = document.querySelector('.site-footer');
    
    let parentRect = parent.getBoundingClientRect();  // 부모 요소의 위치 정보
    let footerRect = footer.getBoundingClientRect();  // footer 요소의 위치 정보
    
    // 부모 요소의 높이
    let parentHeight = parentRect.height;

    // footer의 위쪽 위치 (스크롤된 후, footer 위로는 내려가지 않도록 제한)
    let footerTop = footerRect.top - 700;  // 조정된 footer 위치 (스틱되는 효과)
	
    // 스크롤을 기준으로 이미지의 bottom 위치 계산
    let newBottom = parentHeight - window.scrollY + 10;

	if (window.scrollY == 0) {
       	window.scrollTo({
        	top: window.scrollY + 470,  // 현재 위치에서 470px 아래로 스크롤
           	behavior: 'smooth'  // 부드럽게 스크롤되도록 설정
       	});
   	}
	
	newBottom = parentHeight - window.scrollY + 560;
	
    // footer와 겹치지 않도록 제한
    if (footerTop < 0) {
        newBottom = parentHeight - window.scrollY - 10;  // footer에 10px 여유를 두고 위치 설정
    	return;
	}

    // 지정된 img 요소의 bottom 위치 조정
    place.style.bottom = newBottom + "px";
}


// 스크롤 이벤트에 따라 imgplace 함수 호출
window.addEventListener('scroll', function() {
    itemplace('#chatAIImg');  // img 요소를 매개변수로 전달
});

function scrollToBottom() {
    aI_chatting.scrollTop = aI_chatting.scrollHeight;
}

// MutationObserver 설정



// 닫기 버튼 누르면 없어지기

document.querySelector('.close img').addEventListener('click', function() {
	img.style.display = 'block';
	chatting.style.display = 'none';
	chatting_box.style.display = 'none';
	/*
	const first_AI = document.querySelector('.first');
	if(first_AI){
		first_AI.remove();
	}
	*/
});

