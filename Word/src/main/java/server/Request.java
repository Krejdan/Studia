package server;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import tables.Car;
import tables.ExamCategory;
import tables.ExamResult;
import tables.ExamTask;
import tables.ExamTaskResult;
import tables.ExaminationCard;
import tables.Question;
import tables.Term;
import tables.TheoreticalExam;
import tables.User;

public class Request implements Serializable {

	private static final long serialVersionUID = 7452698369112838545L;
	
	private RequestType type;
	private LocalDate date;
	private User user;
	private ExamResult examResult = new ExamResult();
	private ExaminationCard examinationCard;
	private TheoreticalExam theoreticalExam;
	private Term term;
	private String mail;
	private String password;
	private String message;
	private Boolean result;
	private List<Question> questions = new ArrayList<>();
	private List<String> answers = new ArrayList<>();
	private int points;
	private List<ExamTaskResult> examTaskResults = new ArrayList<>();
	private boolean canOrderPractical = false;
	private List<Term> termsList = new ArrayList<>();
	private ExamCategory category;
	private Car car;
	
	public RequestType getType() {
		return type;
	}
	public void setType(RequestType type) {
		this.type = type;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ExamResult getExamResult() {
		return examResult;
	}
	public void setExamResult(ExamResult examResult) {
		this.examResult = examResult;
	}
	public ExaminationCard getExaminationCard() {
		return examinationCard;
	}
	public void setExaminationCard(ExaminationCard examinationCard) {
		this.examinationCard = examinationCard;
	}
	public TheoreticalExam getTheoreticalExam() {
		return theoreticalExam;
	}
	public void setTheoreticalExam(TheoreticalExam theoreticalExam) {
		this.theoreticalExam = theoreticalExam;
	}
	public Term getTerm() {
		return term;
	}
	public void setTerm(Term term) {
		this.term = term;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public List<String> getAnswers() {
		return answers;
	}
	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	@Override
	public String toString() {
		return "Request [type=" + type + ", date=" + date + ", user=" + user + ", examResult=" + examResult
				+ ", examinationCard=" + examinationCard + ", term=" + term + ", mail=" + mail + ", password="
				+ password + "]";
	}
	public List<ExamTaskResult> getExamTaskResults() {
		return examTaskResults;
	}
	public void setExamTaskResults(List<ExamTaskResult> examTaskResults) {
		this.examTaskResults = examTaskResults;
	}
	public boolean isCanOrderPractical() {
		return canOrderPractical;
	}
	public void setCanOrderPractical(boolean canOrderPractical) {
		this.canOrderPractical = canOrderPractical;
	}
	public List<Term> getTermsList() {
		return termsList;
	}
	public void setTermsList(List<Term> termsList) {
		this.termsList = termsList;
	}
	public ExamCategory getCategory() {
		return category;
	}
	public void setCategory(ExamCategory category) {
		this.category = category;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	
	
	
}
