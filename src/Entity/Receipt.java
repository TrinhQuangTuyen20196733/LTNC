package Entity;

public class Receipt {
	private int id;
	private int userId;
	private int payment;
	private int ticketId;
	private int serviceId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public Receipt(int id, int userId, int payment, int ticketId, int serviceId) {
		super();
		this.id = id;
		this.userId = userId;
		this.payment = payment;
		this.ticketId = ticketId;
		this.serviceId = serviceId;
	}
	public Receipt(int userId, int payment, int ticketId, int serviceId) {
		super();
		this.userId = userId;
		this.payment = payment;
		this.ticketId = ticketId;
		this.serviceId = serviceId;
	}
	public Receipt() {
		super();
	}
	
}
