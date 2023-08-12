package Entity;

public class Seat {
	private int id;
	private int rowSeat;
	private int columnSeat;
	private int planeId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRowSeat() {
		return rowSeat;
	}
	public void setRowSeat(int rowSeat) {
		this.rowSeat = rowSeat;
	}
	public int getColumnSeat() {
		return columnSeat;
	}
	public void setColumnSeat(int columnSeat) {
		this.columnSeat = columnSeat;
	}
	public int getPlaneId() {
		return planeId;
	}
	public void setPlaneId(int planeId) {
		this.planeId = planeId;
	}
	public Seat(int id, int rowSeat, int columnSeat, int planeId) {
		super();
		this.id = id;
		this.rowSeat = rowSeat;
		this.columnSeat = columnSeat;
		this.planeId = planeId;
	}
	public Seat(int rowSeat, int columnSeat, int planeId) {
		super();
		this.rowSeat = rowSeat;
		this.columnSeat = columnSeat;
		this.planeId = planeId;
	}
	public Seat() {
		super();
	}

	
	
	
	
	
}
