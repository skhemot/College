package com.hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
//eljj
public class Book {

	private int id;
	private String bookName;
	private int price;
	private String type;
	
	public Book()  {
	
	}

	public Book(int id, String bookName, int price, String type) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.price = price;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bookName, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(bookName, other.bookName) && id == other.id;
	}
	
		public static void main(String[] args) {

			HashMap<Book, Integer> bookMap = new HashMap<>();
			Book b1 = new Book(1, "ABC", 150, "Learning");
			bookMap.put(b1, b1.getPrice());
			Book b2 = new Book(2, "XYZ", 180, "Historical");
			bookMap.put(b2, b2.getPrice());
			Book b3 = new Book(3, "PQR", 210, "Myth");
			bookMap.put(b3, b3.getPrice());
			Book b4 = new Book(5, "AB", 150, "Learning");
			bookMap.put(b4, b4.getPrice());

			for (Map.Entry bk : bookMap.entrySet()) {
				Book b =(Book) bk.getKey();
				System.out.println(b.getId()+" "+b.getBookName()+" "+b.getPrice()+" "+b.getType()+"-----"+bk.getValue());
				
			}
		
	}

	
}
