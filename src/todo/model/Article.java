package todo.model;

public class Article {
	private int articleId;
	private String content;
	private String doneDate;
	private boolean isDone;
	private int boardId;
	private String color;
	
//	public Article(String content, int boardId) {
//		this(0, content, null, (Boolean) null, boardId);
//	}
	
	public Article(String content, String doneDate, int boardId) {
		this(0, content, doneDate, (Boolean) null, boardId, null);
	}
	
	public Article(String content, String doneDate, boolean isDone, int boardId) {
		this(0, content, doneDate, isDone, boardId, null);
	}
	
	public Article(int articleId, String content, String doneDate, boolean isDone, int boardId, String color) {
		this.articleId = articleId;
		this.content = content;
		this.doneDate = doneDate;
		this.isDone = isDone;
		this.boardId = boardId;
		this.color = color;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDoneDate() {
		return doneDate;
	}

	public void setDoneDate(String doneDate) {
		this.doneDate = doneDate;
	}

	public boolean getIsDone() {
		return isDone;
	}

	public void setIsDone(boolean isDone) {
		this.isDone = isDone;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
