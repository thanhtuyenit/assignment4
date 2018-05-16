package tuyen.novahub.assignment4.common;

public class Define {
	 int bookCount = 1;
	 int buttonToShow = 5;
	 int initialPage = 0;
	 int initialPageSize = 5; 
	 int[] pageSize = { 5, 10, 20, 50 };
	
	public Define() {
		super();
	}

	public int getBookCount() {
		return bookCount;
	}

	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}

	public int getButtonToShow() {
		return buttonToShow;
	}

	public void setButtonToShow(int buttonToShow) {
		this.buttonToShow = buttonToShow;
	}

	public int getInitialPage() {
		return initialPage;
	}

	public void setInitialPage(int initialPage) {
		this.initialPage = initialPage;
	}

	public int getInitialPageSize() {
		return initialPageSize;
	}

	public void setInitialPageSize(int initialPageSize) {
		this.initialPageSize = initialPageSize;
	}

	public int[] getPageSize() {
		return pageSize;
	}

	public void setPageSize(int[] pageSize) {
		this.pageSize = pageSize;
	}

	
	
}
