//코드 재사용을 위한 유효성 검사 함수 정의
const validations = {
    // 최소/최대 글자 수 제한 유효성 검사
    length: (min, max) => (value) => value.length >= min && value.length <= max,
	
	// 최대 글자 수 제한 유효성 검사
	lengthMax: (max) => (value) => value.length <= max,

    // 정규식을 통한 특정 문자열 존재 유무 체크
    regex: (pattern) => (value) => pattern.test(value),

    // 빈 문자열 체크
    nonEmpty: () => (value) => value.trim().length > 0,

    // 휴대폰 번호를 위한 숫자 Only 체크
    isNumeric: () => (value) => /^[0-9]*$/.test(value),
};


//회원가입 입력 항목별로 유효성 검사 설정
const ruleSet = {
	//아이디 검사
    username: [
        { test: validations.length(3, 15), errorKey: 'length' },
        { test: validations.regex(/^[a-zA-Z\d_]+$/), errorKey: 'format' },
    ],
	//비밀번호 검사
    password: [
        { test: validations.nonEmpty(), errorKey: 'empty' },
        { test: validations.length(8, 30), errorKey: 'length' },
        { test: validations.regex(/[A-Za-z]/), errorKey: 'letter' },
        { test: validations.regex(/[\d]/), errorKey: 'number' },
        { test: validations.regex(/[!@#$%^&*_]/), errorKey: 'special' },
        { test: validations.regex(/[A-Za-z\d!@#$%^&*_]/), errorKey: 'format' },
    ],
	//이메일 검사
    email: [
        { test: validations.length(7, 30), errorKey: 'length' },
        { test: validations.regex(/^[0-9a-zA-Z]+([0-9a-zA-Z]*[-._+])*[0-9a-zA-Z]+@[0-9a-zA-Z]+([-.][0-9a-zA-Z]+)*([0-9a-zA-Z]*[.])[a-zA-Z]{2,6}$/), errorKey: 'format' },
    ],
	//휴대폰 번호 검사
    telephone: [
        { test: validations.isNumeric(), errorKey: 'number' },
    ],
	//이름 검사
    name: [
        { test: validations.length(3, 30), errorKey: 'length' },
        { test: validations.regex(/^[\x80-\uFFFF\w ]+$/), errorKey: 'format' },
    ],
	//별명 검사
    nickname: [
        { test: validations.length(3, 30), errorKey: 'length' },
        { test: validations.regex(/^[\x80-\uFFFF\w !@#$%^&*]+$/), errorKey: 'format' },
    ],
	//상세 주소 검사
    address: [
        { test: validations.lengthMax(30), errorKey: 'length' }
    ]
};


/**
 * 각 입력 항목별 유효성 검사 규칙 바인딩
 * @param {string} inputId - input 태그의 ID 속성 값
 * @param {string} messageId - input 태그의 입력 값의 따라 메세지가 나올 span 태그의 ID 속성값
 * @param {Array} ruleSet - 각 input 태그에 따른 유효성 검사 규칙들
 * @param {Array} errorSet - 각 intput 태그에 따른 유효성 검사 규칙들에 대한 에러 메시지  
 * @param {string} buttonId - 아이디 중복검사나 가입하기 등 사용자가 누를 수 있는 버튼 ID 값
 */


function setupInputValidation(inputId, messageId, ruleSet, errorSet, buttonId) {
    const inputElement = document.getElementById(inputId);
    const messageElement = document.getElementById(messageId);
	const buttonElement = document.getElementById(buttonId);

    inputElement.addEventListener('input', function () {
        const inputValue = inputElement.value;
		let isInputValid = true;

        for (let rule of ruleSet) {
            if (!rule.test(inputValue)) {
                messageElement.textContent = errorSet[rule.errorKey]; //유효성 검사가 실패할 시 출력될 메시지 설정
                messageElement.classList.add('active'); //유효성 검사를 실패하면 span 태그의 class 속성에 active를 추가 (active 태그는 span 태그 메시지 내용을 출력해줌)
				isInputValid = false;
                break;
            }
        }

		if (isInputValid) {
				// 모든 유효성 검사를 통과하면 -> span 태그의 메시지를 제거
				messageElement.textContent = '';
				messageElement.classList.remove('active');
			
				
		       } 
				// Disable the button if the span has the 'active' class
				buttonElement.disabled = messageElement.classList.contains('active');
				

    });
}

// 각 입력 항목에 맞는 input 태그, span 태그, 유효성 검사 규칙, 유효성 검사 메시지 바인딩
setupInputValidation('username', 'username-check', ruleSet.username, errorSet.username, 'checkUsername'); //아이디
//setupInputValidation('password', 'password-check', validationRules.password); //비밀번호
setupInputValidation('email', 'email-check', ruleSet.email, errorSet.email); //아이디
//setupInputValidation('phone2', 'phone2-check', validationRules.phone2); //번호 입력칸 2
//setupInputValidation('phone3', 'phone3-check', validationRules.phone3); //번호 입력칸 3
setupInputValidation('name', 'name-check', ruleSet.name, errorSet.name); //이름
setupInputValidation('nickname', 'nickname-check', ruleSet.nickname, errorSet.nickname); //별명
setupInputValidation('addressDetail', 'addressDetail-check', ruleSet.address, errorSet.address); //상세주소


//아이디 중복 검사
document.getElementById('checkUsername').addEventListener('click', function () {
    const usernameInput = document.getElementById('username').value;
    const usernameCheckMessage = document.getElementById('username-check');

    // 아이디 중복 검사 메시지 제거
    usernameCheckMessage.textContent = '';
    usernameCheckMessage.classList.remove('active', 'success');
	

    // Perform the Ajax request
    fetch(`/checkusername?username=${usernameInput}`)
        .then(response => response.json())
        .then(isAvailable => {
            if (isAvailable) {
                usernameCheckMessage.textContent = 'username is available.';
                usernameCheckMessage.classList.add('active', 'success');
            } else {
                usernameCheckMessage.textContent = 'username is already taken.';
                usernameCheckMessage.classList.add('active');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            usernameCheckMessage.textContent = 'Error checking username availability.';
			usernameCheckMessage.classList.add('active');
        });
});

// Password Validity Check
const passwordInput = document.getElementById('password');
const passwordCheckInput = document.getElementById('passwordCheck');
const passwordValiditySpan = document.getElementById('password-check');
const passwordMatchSpan = document.getElementById('passwordCheck-check');

passwordInput.addEventListener('input', function () {
    const password = passwordInput.value;

    // Regular expressions for each character group
    const hasEnglish = /[A-Za-z]/.test(password); // Checks for English letters
    const hasNumber = /[0-9]/.test(password); // Checks for numbers
    const hasSpecial = /[!@#$%^&*]/.test(password); // Checks for special characters
    const isValidLength = password.length >= 8 && password.length <= 30; // Checks for length
	const isEmpty = password.length == 0; //check for empty input

    // Update validity message
    if 	(isEmpty) {
		passwordValiditySpan.textContent = ''; // Empty password
	    passwordValiditySpan.classList.remove('active'); // Hide
	} else if (!isValidLength) {
        passwordValiditySpan.textContent = 'Password must be 8-30 characters.';
        passwordValiditySpan.classList.add('active'); // Make visible
    } else if (!hasEnglish) {
        passwordValiditySpan.textContent = 'Password must include at least one English letter.';
        passwordValiditySpan.classList.add('active');
    } else if (!hasNumber) {
        passwordValiditySpan.textContent = 'Password must include at least one number.';
        passwordValiditySpan.classList.add('active');
    } else if (!hasSpecial) {
        passwordValiditySpan.textContent = 'Password must include at least one special character (!@#$%^&*).';
        passwordValiditySpan.classList.add('active');
    } else {
        passwordValiditySpan.textContent = ''; // Valid password
        passwordValiditySpan.classList.remove('active'); // Hide
    }
});

passwordCheckInput.addEventListener('input', function () {
    const isMatch = passwordInput.value === passwordCheckInput.value;
    passwordMatchSpan.textContent = isMatch ? '' : 'Passwords do not match.';
    if (!isMatch) {
        passwordMatchSpan.classList.add('active');
    } else {
        passwordMatchSpan.classList.remove('active');
    }
});

// Phone Number Validation
const phoneInputs = document.querySelectorAll('.phone-input');

function validatePhoneNumber(input) {
    const isCellValid = /^[0-9]*$/.test(input.value); // Check for numbers only
    const errorMessageSpan = input.parentNode.querySelector('.error-message'); // Get the sibling span for the input

    if (!isCellValid) {
        errorMessageSpan.textContent = 'Only numbers are allowed.';
        errorMessageSpan.classList.add('active');
        input.value = input.value.replace(/[^0-9]/g, ''); // Remove invalid characters
    } else {
        errorMessageSpan.textContent = '';
        errorMessageSpan.classList.remove('active');
    }
}

phoneInputs.forEach((input, index) => {
    input.addEventListener('input', function () {
        validatePhoneNumber(input);

        if (input.value.length === input.maxLength && phoneInputs[index + 1]) {
            phoneInputs[index + 1].focus();
        }
    });

    input.addEventListener('paste', function (event) {
        const pasteData = (event.clipboardData || window.clipboardData).getData('text');
        if (!/^[0-9]*$/.test(pasteData)) {
            event.preventDefault();
        }
    });
});

// Address API Integration (Daum Postcode)
function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            let addr = ''; // Address variable
            let extraAddr = ''; // Extra address variable

            if (data.userSelectedType === 'R') {
                addr = data.roadAddress;
            } else {
                addr = data.jibunAddress;
            }

            if (data.userSelectedType === 'R') {
                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                    extraAddr += data.bname;
                }
                if (data.buildingName !== '' && data.apartment === 'Y') {
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                if (extraAddr !== '') {
                    extraAddr = ' (' + extraAddr + ')';
                }
                document.getElementById('addressExtra').value = extraAddr;
            } else {
                document.getElementById('addressExtra').value = '';
            }

            document.getElementById('zipcode').value = data.zonecode;
            document.getElementById('address').value = addr;
            document.getElementById('addressDetail').focus();
        }
    }).open();
}

function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("addressExtra").value = extraAddr;
            
            } else {
                document.getElementById("addressExtra").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('zipcode').value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("addressDetail").focus();
        }
    }).open();
}


