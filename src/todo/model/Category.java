package todo.model;

public class Category {
	private int categoryId;
	private String categoryName;
	private String color;
	private int boardId;
	
	public Category(String categoryName, String color, int boardId) {
		this(0, categoryName, color, boardId);
	 }
	
	public Category(int categorydId, String categoryName, String color, int boardId) {
		this.categoryId = categorydId;
		this.categoryName = categoryName;
		this.color = color;
		this.boardId = boardId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

}
