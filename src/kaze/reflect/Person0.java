package kaze.reflect;
public class Person0 {
    private String name;
    private int age;
    
    public Person0(String name,int age){
        this.name = name;
        this.age = age;
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
    public String toString() {
          return "Person0 [name=" + name + ", age=" + age + "]";
    }
	
	public void add()  {
           System.out.println("add不带参数的方法");
    }
	
	public void addWithParameters(String name, int age) {
            System.out.println("add带参数方法" + name + age);
    }
}