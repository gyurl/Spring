package com.mysite.sbb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import com.mysite.sbb.question.QuestionService;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionService qs;

	@Test
	void testQuestionService() {
		for (int i = 1; i <= 300; i++) {
			String subject = String.format("테스트 샘플 데이터:[%03d]", i);
			String content = "내용 없음";
			qs.create(subject, content, null);
		}
	}

	@Autowired
	private QuestionRepository qr;

	@Autowired
	private AnswerRepository ar;

	// @Test
	@Transactional
	void testGetAnswerList() {
		Optional<Question> oq = qr.findById(2);
		if (oq.isPresent()) {
			Question q = oq.get();
			List<Answer> answerList = q.getAnswerList();

			System.out.println(answerList.size());
			System.out.println(answerList.get(0).getContent());
		}
	}

	// @Test
	void testAnswerSave() {
		Optional<Question> oq = qr.findById(2);
		if (oq.isPresent()) {
			Question q = oq.get();

			Answer a = new Answer();
			a.setContent("네 자동으로 생성됩니다.");
			a.setQuestion(q);
			a.setCreateDate(LocalDateTime.now());
			ar.save(a);
		}
	}

	// @Test
	void testDelete() {
		System.out.println(qr.count());
		Optional<Question> oq = qr.findById(1);
		if (oq.isPresent()) {
			Question q = oq.get();
			qr.delete(q);
			System.out.println(qr.count());
		}
	}

	// @Test
	void testUpdate() {
		Optional<Question> oq = qr.findById(1);
		if (oq.isPresent()) {
			Question q = oq.get();
			q.setSubject("수정된 제목");
			q.setContent("수정된 내용");
			qr.save(q);
		}
	}

	// @Test
	void testSubjectLike() {
		List<Question> qList = qr.findBySubjectLike("sbb%");
		Question q = qList.get(0);
		System.out.println(q.getSubject());

	}

	// @Test
	void testFindBySandC() {
		// Question q =qr.findBySubjectAndContent("sbb가 무엇인가요", "sbb에 대해서 알고 싶습니다.");
		Question q = qr.findBySubjectOrContent("sbb가 무엇인가요", "sbb에 대해서 알고 싶습니다.");
		System.out.println(q.getId());
	}

	// @Test
	void testFindByContent() {
		Question q = qr.findByContent("sbb에 대해서 알고 싶습니다.");
		System.out.println(q.getId());
		assertEquals(1, q.getId());
	}

	// @Test
	void testFindBySubject() {
		Question q = qr.findBySubject("sbb가 무엇인가요?");
		assertEquals(1, q.getId());
	}

	// @Test
	void testFindById() {
		Optional<Question> oq = qr.findById(1);
		if (oq.isPresent()) {
			Question q = oq.get();
			System.out.println(q.getSubject());
			assertEquals("sbb가 무엇인가요?", q.getSubject());
		} else {
			System.out.println("찾을 수 없음.");
		}

	}

	// @Test
	void testFindAll() {
		List<Question> all = qr.findAll();
		assertEquals(2, all.size());

		Question q = all.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
		System.out.println(q.getSubject());
	}

	@Test
	void testInsert() {
		Question q1 = new Question();
		q1.setSubject("질문 1번");
		q1.setContent("테스트에 대해서 알고 싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		qr.save(q1);

		Question q2 = new Question();
		q2.setSubject("질문 2번.");
		q2.setContent("id는 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		qr.save(q2);

	}

	// @Test
	void testExist() {
		System.out.println(qr);
	}

}
