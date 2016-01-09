package todo.model;

public class Board {
	private int boardId;
	private String listName;
	private String userId;
	
	public Board(String listName, String userId) {
		this(0, listName, userId);
	 }
	
	public Board(int boardId, String listName, String userId) {
		this.boardId = boardId;
		this.listName = listName;
		this.userId = userId;
	}

	public String getListName(){
		return listName;
	}
	
	public int getBoardId() {
		return boardId;
	}
}
