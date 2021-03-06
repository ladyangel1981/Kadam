package XMLconfig;

import java.util.List;

public class Configuration {

	private String language;
	private List<String> questionType;
	private int timeout;
	private int maxQuestion;
	private String pathXML;
	private Integer countdown;

	public Configuration() {

	}

	public Configuration(String language, List<String> questionType, int timeout, int maxQuestion, String pathXML,
			Integer countdown) {
		super();
		this.language = language;
		this.questionType = questionType;
		this.timeout = timeout;
		this.maxQuestion = maxQuestion;
		this.pathXML = pathXML;
		this.countdown = countdown;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<String> getQuestionType() {
		return questionType;
	}

	public void setQuestionType(List<String> questionType) {
		this.questionType = questionType;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getMaxQuestion() {
		return maxQuestion;
	}

	public void setMaxQuestion(int maxQuestion) {
		this.maxQuestion = maxQuestion;
	}

	public String getPathXML() {
		return pathXML;
	}

	public void setPathXML(String pathXML) {
		this.pathXML = pathXML;
	}

	public Integer getCountdown() {
		return countdown;
	}

	public void setCountdown(Integer countdown) {
		this.countdown = countdown;
	}

	@Override
	public String toString() {
		return "Configuration [\n\tLanguage = " + language + ",\n\tQuestion Type = " + questionType + ",\n\tTimeout = "
				+ timeout + ",\n\tMax of questions = " + maxQuestion + ",\n\tPath XML = " + pathXML
				+ ",\n\tCountdown = " + countdown + "\n]";
	}

}