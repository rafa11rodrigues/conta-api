package conta.api.verifier;

public class Verifier {

	public static void validarString(String value, String message) {
		if (value == null || value.isEmpty()) {
			throw new RuntimeException(message);
		}
	}
	
	public static void valorPositivo(Double valor, String message) {
		if(valor == null || valor <= 0) {
			throw new RuntimeException(message);
		}
	}
}
