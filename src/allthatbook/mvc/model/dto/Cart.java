package allthatbook.mvc.model.dto;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private int cartId;
	private int userNo;
	
	private List<CartDetail> cartDetailList = new ArrayList<CartDetail>();
	
	public Cart() {}

	public Cart(int cartId, int userNo) {
		super();
		this.cartId = cartId;
		this.userNo = userNo;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
	
	public List<CartDetail> getCartDetailList() {
		return cartDetailList;
	}

	public void setCartDetailList(List<CartDetail> cartDetailList) {
		this.cartDetailList = cartDetailList;
	}

	public void addCartDetail(CartDetail cartDetail) {
		cartDetailList.add(cartDetail);
	}
	
	public void removeCartDetail(CartDetail cartDetail) {
		cartDetailList.remove(cartDetail);
	}
	
	public boolean chkCartDuplicate(int bookNo) {
		for (CartDetail cartDetail : cartDetailList) {
			if (cartDetail.getBookNo() == bookNo) return true;
		}
		return false;
	}
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cart [cartId=");
		builder.append(cartId);
		builder.append(", userNo=");
		builder.append(userNo);
		builder.append(", cartDetailList=");
		builder.append(cartDetailList);
		builder.append("]");
		return builder.toString();
	}

	
	
}
