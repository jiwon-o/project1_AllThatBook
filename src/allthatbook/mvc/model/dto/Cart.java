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
		builder.append("장바구니번호: ");
		builder.append(cartId);
		builder.append(", 회원번호");
		builder.append(userNo);
		if(cartDetailList != null) {
			builder.append("\n<<<<<<장바구니 상세>>>>>>");
			for (CartDetail cartDetail : cartDetailList) {
				builder.append("\n");
				builder.append(cartDetail);
			}
		}
		return builder.toString();
	}
}
