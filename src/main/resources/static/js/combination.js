document.querySelector('input[type="radio"]').checked = true;
	
// CSRF 토큰과 헤더 이름을 메타 태그에서 동적으로 가져옵니다.
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");


// 페이지 로딩 시 첫 번째 라디오 버튼에 해당하는 성분을 선택하여 처리
document.addEventListener('DOMContentLoaded', function() {
   
    let firstLabel = document.querySelector('label');
    let firstInput = document.querySelector('#' + firstLabel.getAttribute('for'));
    let firstIngredientSeq = firstInput.value;
    let firstIngredient = firstLabel.textContent.trim();

	
	let image = document.querySelector('#selectOneImg');
	image.src = '/img/ingredient/' + firstIngredient + '.png';
	
    // 첫 번째 라디오 버튼에 해당하는 성분을 표시
    selectIngredient(firstIngredientSeq, firstIngredient);
	
	document.querySelectorAll('.selectOne')[0].innerHTML = firstIngredient;
	document.querySelectorAll('.selectOne')[1].innerHTML = firstIngredient;
});



// 클릭된 label에 연결된 input 요소 가져오기
document.querySelectorAll('label').forEach(function (label) {
    label.addEventListener('click', function () {
        
		
        let input = document.querySelector('#' + label.getAttribute('for'));
        let ingredientSeq = input.value;
        let ingredient = label.textContent.trim();
		console.log(ingredient);
		let image = document.querySelector('#selectOneImg');
		image.src = '/img/ingredient/' + ingredient + '.png';
		
		document.querySelectorAll('.selectOne')[0].innerHTML = ingredient;
		document.querySelectorAll('.selectOne')[1].innerHTML = ingredient;

        // selectIngredient 함수 호출
        selectIngredient(ingredientSeq, ingredient);
    });
});


// 성분을 업데이트하는 함수
function selectIngredient(ingredientSeq, ingredient) {
    // UI 초기화
    resetUI();

    // AJAX 요청
    $.ajax({
        type: 'POST',
        url: '/combination/ajax',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            ingredientSeq: ingredientSeq,
            ingredient: ingredient
        }),
		beforeSend : function(xhr) {
            xhr.setRequestHeader(header, token);
        },
        success: function (response) {
            handleCombinations(response);
        },
        error: function (a, b, c) {
            console.log(a, b, c);
        }
    });
}

// UI 초기화 함수
function resetUI() {
    document.querySelector('#combinationBad').style.display = 'none';
    document.querySelector('#combinationGood').style.display = 'none';
    document.querySelector('#collapseOne').innerHTML = '';
    document.querySelector('#collapseTwo').innerHTML = '';

    
    document.querySelector('#buttonOne').innerHTML = '';
}

// 조합 처리 함수
function handleCombinations(response) {
    const { good, bad } = response;

    // good과 bad 배열이 모두 존재하는 경우
	if (good && good.length > 0 && bad && bad.length > 0) {
        displayCombination('#combinationBad', bad[0].functionalContent);
        displayCombination('#combinationGood', good[0].functionalContent);
        addCombinations(good, bad);
    }
    // bad만 있을 경우
    else if (bad && bad.length > 0) {
        displayCombination('#combinationBad', bad[0].functionalContent);
        badCombination(bad);
    }
    // good만 있을 경우
    else if (good && good.length > 0) {
        displayCombination('#combinationGood', good[0].functionalContent);
        goodCombination(good, []);
    }
}

// 동적으로 조합을 추가하는 함수
function addCombinations(goodArray, badArray) {
    let collapseOne = document.querySelector('#collapseOne');
    let collapseTwo = document.querySelector('#collapseTwo');

    // 'good' 조합 추가
    if (goodArray && goodArray.length > 0) {
        document.querySelector('#combinationGood').style.display = 'block';
        document.querySelector('#buttonOne').innerHTML = '같이 먹으면 좋은 조합';
        goodArray.forEach(function (item) {
            let goodItemHTML = `
                <div class="accordion-body">
                    <div class="good_combination_title">${item.name}</div>
                    <div class="good_combination_content">${item.reason}</div>
                    <a class="good_combination_link" href="${item.link}" target="_blank">출처</a>
                </div>
            `;
            let newElement = document.createElement('div');
            newElement.innerHTML = goodItemHTML;
            collapseOne.appendChild(newElement);
        });
    } else {
        console.log('No good combinations available.');
    }


    // 'bad' 조합 추가
    if (badArray && badArray.length > 0) {
        document.querySelector('#combinationBad').style.display = 'block';
        document.querySelector('#buttonTwo').innerHTML = '같이 먹으면 안좋은 조합';
        badArray.forEach(function (item) {
            let badItemHTML = `
                <div class="accordion-body">
                    <div class="bad_combination_title">${item.name}</div>
                    <div class="bad_combination_content">${item.reason}</div>
                    <a class="bad_combination_link" href="${item.link}" target="_blank">출처</a>
                </div>
            `;
            let newElement = document.createElement('div');
            newElement.innerHTML = badItemHTML;
            collapseTwo.appendChild(newElement);
        });
    } else {
        console.log('No bad combinations available.');
    }
	
}

// 동적으로 조합을 추가하는 함수
function badCombination(badArray) {
    let collapseOne = document.querySelector('#collapseOne');
    
	// 'good'&'bad' 조합 추가
	if (badArray && badArray.length > 0) {
		  document.querySelector('#combinationGood').style.display = 'block';
	      document.querySelector('#buttonOne').innerHTML = '같이 먹으면 안좋은 조합';
	      badArray.forEach(function (item) {
	          let badItemHTML = `
	              <div class="accordion-body">
	                  <div class="good_combination_title">${item.name}</div>
	                  <div class="good_combination_content">${item.reason}</div>
	                  <a class="good_combination_link" href="${item.link}" target="_blank">출처</a>
	              </div>
	          `;
	          let newElement = document.createElement('div');
	          newElement.innerHTML = badItemHTML;
	          collapseOne.appendChild(newElement);
	      });
		  
		  document.querySelector('#combinationBad').style.display = 'none';
		
	} else {
	    console.log('No bad combinations available.');
	}

}

function goodCombination(goodArray) {
	let collapseOne = document.querySelector('#collapseOne');
	

	// 'good' 조합 추가
	if (goodArray && goodArray.length > 0) {
	    document.querySelector('#combinationGood').style.display = 'block';
	    document.querySelector('#buttonOne').innerHTML = '같이 먹으면 좋은 조합';
	    goodArray.forEach(function (item) {
	        let goodItemHTML = `
	            <div class="accordion-body">
	                <div class="good_combination_title">${item.name}</div>
	                <div class="good_combination_content">${item.reason}</div>
	                <a class="good_combination_link" href="${item.link}" target="_blank">출처</a>
	            </div>
	        `;
	        let newElement = document.createElement('div');
	        newElement.innerHTML = goodItemHTML;
	        collapseOne.appendChild(newElement);
	    });
	} else {
	    console.log('No good combinations available.');
	}

}



// 조합 내용을 표시하는 함수
function displayCombination(elementId, content) {
    document.querySelector(elementId).style.display = 'block';
    document.querySelector('#ingredientContent').innerHTML = content;
}

