package eventmodel;

import java.util.Optional;

public class Demo {
	public static void main(String[] args) {
		Optional<String> a = Optional.ofNullable("Abi");
		System.out.println(a.isPresent() ? a.get() : "zz");
	}
}
