package todo.model;

public class TaskCategory {
	private int categoryId;
	private int articleId;
	
//	public TaskCategory(int categoryId) {
//		this(0, categoryId);
//	 }
	
	public TaskCategory(int articleId, int categorydId) {
		this.articleId = articleId;
		this.categoryId = categorydId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	
	
}
