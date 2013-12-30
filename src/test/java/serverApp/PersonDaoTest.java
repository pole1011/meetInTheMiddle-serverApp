package serverApp;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.meetInTheMiddle.serverApp.dao.person.PersonMockDao;
import com.meetInTheMiddle.serverApp.domain.person.Person;

public class PersonDaoTest {
	PersonMockDao dao = new PersonMockDao();
	List<Person> person = new ArrayList<Person>();
	Person p1;
	String firstName= "Felix";
	String lastName= "Albert";
//	Date birthday = new Date(12-12-2012);
	String phone = "12345123";
	String email = "felix@albert.de";
	Long kontaktliste = 1L;
	String password = "test";
	String interests = "testen";
	@Test
	public void testCreatePerson(){
		p1 = new Person(firstName,lastName,null,phone,email,kontaktliste,password,interests);
//		dao.create(firstName, lastName, birthday, phone, email, kontaktliste, password, interests);
		dao.create(p1);
		person = dao.selectAll();
		for(int i = 0; i< person.size(); i++){
			System.out.println(person.get(i).getFirstName());
			if(person.get(i).getEmail().equals(email)){
				System.out.println(person.get(i));
				System.out.println(p1);
				System.out.println(person.get(i).getEmail());
				System.out.println(p1.getEmail());
//				assertTrue(person.get(i).getEmail() == p1.getEmail());
			}
		}
	}
}
