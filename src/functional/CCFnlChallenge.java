package functional;

import java.util.ArrayList;
import java.util.List;

public class CCFnlChallenge {
	public static void main(final String[] args) {
		main1(null);
		main2(null);
	}


	public static void main1(final String[] args) {
		final List<Contact> contacts = new ArrayList<>();
		contacts.add(new Contact("Deepu", "9876543211", "Address1"));
		contacts.add(new Contact("Akhil", "9876543234", "Address1"));
		contacts.add(new Contact("Binu", "9876543264", "Address1"));

		contacts.stream().sorted((o1, o2) -> o1.name.compareTo(o2.name)).forEach(o -> System.out.println(o.name));
	}

	public static void main2(final String[] args) {
		final List<Contact> contacts = new ArrayList<>();
		contacts.add(new Contact("Deepu", "9876543211", "Address1"));
		contacts.add(new Contact("Akhil", "9876543234", "Address1"));
		contacts.add(new Contact("Binu", "9876543264", "Address1"));
		contacts.sort((c1, c2) -> c1.name.compareTo(c2.name));
		for(final Contact eachContact : contacts) {
			System.out.println(eachContact.name);
		}
	}
}

//record Contact(String name, String number, String address)   { }
class Contact {
	String name;
	String number;
	String address;
	public Contact(final String name, final String number, final String address) {
		this.name = name;
		this.number = number;
		this.address = address;
	}
}
