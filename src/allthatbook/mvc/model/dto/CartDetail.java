package allthatbook.mvc.model.dto;

public class CartDetail {
	
	private int cartId;   //fk
	private int bookNo;   //fk
	
	
	public CartDetail() {}


	public CartDetail(int cartId, int bookNo) {
		super();
		this.cartId = cartId;
		this.bookNo = bookNo;
	}


	public int getCartId() {
		return cartId;
	}


	public void setCartId(int cartId) {
		this.cartId = cartId;
	}


	public int getBookNo() {
		return bookNo;
	}


	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CartDetail [cartId=");
		builder.append(cartId);
		builder.append(", bookNo=");
		builder.append(bookNo);
		builder.append("]");
		return builder.toString();
	}
	
}
