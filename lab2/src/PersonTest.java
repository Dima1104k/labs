import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class PersonTest {

    @Test
    public void testEqualsAndHashCode() {
        EqualsVerifier.forClass(Person.class)
                .usingGetClass()
                .verify();
    }
}