package com.test.nutri.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.nutri.repository.NewsRepository;

/**
 * {@link NewsService} 클래스의 메서드 성능을 테스트하는 클래스입니다. 주어진 두 메서드의 실행 시간을 측정하여 성능 개선
 * 여부를 비교합니다.
 * 
 * @author chimy2
 */
@SpringBootTest
public class NewsServiceTest {

	/**
	 * {@link NewsService} 클래스의 인스턴스. Spring의 의존성 주입을 통해 자동으로 주입됩니다.
	 */
	@Autowired
	private NewsService service;
	
	@Autowired
	private NewsRepository repository;

	/**
	 * {@link NewsService}의 두 메서드(`updateLatestNews`와 `updateLatestNewsBinary`)의 평균
	 * 실행 시간을 비교하는 테스트 메서드입니다. 각 메서드에 대해 20번의 실행 시간을 측정하고, 평균 실행 시간을 출력합니다. 성능 향상
	 * 여부를 비교하는 용도로 사용됩니다.
	 */
	@Test
	public void checkTime() {
		int rotateCount = 5;
		long beforeAvg = 0;
		long afterAvg = 0;

		long newsCount = repository.count();
		
		if(newsCount == 0) {
			service.insertAllNews();
		}
		
		for (int i = 0; i < rotateCount; i++) {
			long startTime = System.nanoTime();
			service.updateLatestNews();

			long endTime = System.nanoTime();

			beforeAvg += endTime - startTime;
		}

		beforeAvg /= rotateCount;

		for (int i = 0; i < rotateCount; i++) {
			long startTime = System.nanoTime();

			service.updateLatestNewsBinary();

			long endTime = System.nanoTime();

			afterAvg += endTime - startTime;
		}

		afterAvg /= rotateCount;

		System.out.printf("이전 메서드 평균 시간: %dns, %dms, %dsec\n", beforeAvg, (int) (beforeAvg / 1_000_000.0),
				(int) (beforeAvg / 1_000_000_000.0));
		System.out.printf("개선 메서드 평균 시간: %dns, %dms, %dsec\n", afterAvg, (int) (afterAvg / 1_000_000.0),
				(int) (afterAvg / 1_000_000_000.0));
//		이전 메서드 평균 시간: 1031453593ns, 1031ms, 1sec
//		개선 메서드 평균 시간: 103157846ns, 103ms, 0sec

		assertEquals(true, beforeAvg > afterAvg);
	}
}
