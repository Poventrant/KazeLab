package gson;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 2016-3-5 庞文全
 */
public class User implements java.io.Serializable {
	@JSONField(serialize=false)
	private Integer id;
	private String name;

	public User() {
	}

	public User(int id, String name) {
		this.id = id;
		this.name = name;
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", unique = true, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


}