package com.mysite.sbb.answer;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerService {

	private final AnswerRepository ar;
	
	public Answer create(Question question, String content, SiteUser author) {
		Answer answer = new Answer();
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question);
		answer.setAuthor(author); //글쓴이 데이터 저장
		ar.save(answer);
		return answer;
	}
	//답변 조회
	public Answer getAnswer(Integer id) {
		Optional<Answer> answer = ar.findById(id);
		if(answer.isPresent()) {
			return answer.get();
		} else {
			throw new DataNotFoundException("answer not found");
		}
	}
	
	//답변 수정
	public void modify(Answer answer, String content) {
		answer.setContent(content);
		answer.setModifyDate(LocalDateTime.now());
		ar.save(answer);
	}
	
	//답변 삭제
	public void delete(Answer answer) {
		ar.delete(answer);
	}
	
	//답변 추천인 저장 vote 메서드 추
	public void vote(Answer answer, SiteUser siteUser) {
		answer.getVoter().add(siteUser);
		ar.save(answer);
	}
}
