package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.user.SiteUser;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository qr;

	public Page<Question> getList(int page){
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));
		Pageable pg = PageRequest.of(page, 10, Sort.by(sorts));
		return qr.findAll(pg);
	}
	
	public List<Question> getList() {
		return qr.findAll();
	}

	public Question getQuestion(Integer id) {
		Optional<Question> q = qr.findById(id);
		if (q.isPresent()) {
			return q.get();
		} else {
			throw new DataNotFoundException("question not found");
		}
	}

	public void create(String subject, String content, SiteUser user) {
		Question q = new Question();
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		q.setAuthor(user); //question 데이터 생성
		qr.save(q);
		}
	
	//수정 기능
	public void modify(Question question, String subject, String content) {
		question.setSubject(subject);
		question.setContent(content);
		question.setModifyDate(LocalDateTime.now());
		qr.save(question);
	}
	
	//삭제 기능
	public void delete(Question question) {
		qr.delete(question);
	}
	
	//질문 엔티티에 추천인 저장 vote 메서드 추가
	public void vote(Question question, SiteUser siteUser) {
		question.getVoter().add(siteUser);
		qr.save(question);
	}
}
