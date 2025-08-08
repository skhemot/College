package StudentDiary.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "StudentTable")
public class StudentEntity {

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
	private long id;
	@Column(name = "NAME", length = 100)
	private String name;
	@Column(name = "surname", length = 100)
	private String surname;
	@Column(name = "Address", length = 100)
	private String Address;

	public StudentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentEntity(long id, String name, String surname, String address) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		Address = address;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

}
