package nl.bsn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BsnValidatorTest {

    private final BsnValidator validator = new BsnValidator(); // exists now so the tests should pass

    @Nested
    @DisplayName("Valid BSNs")
    class ValidCases {
        @ParameterizedTest
        @DisplayName("Accepts known valid BSNs (11-proef, 9 digits)")
        @ValueSource(strings = {"286497153", "459980889", "808026598"})
        void validSamplesPass(String bsn) {
            assertTrue(validator.isValid(bsn));
        }

        @ParameterizedTest
        @DisplayName("8-digit BSNs are validated with an implied leading zero")
        @ValueSource(strings = {"72124581"})
            // This should be 072124581 and valid!
        void eightDigitInputsPass(String eightDigits) {
            assertTrue(validator.isValid(eightDigits));
        }
    }

    @Nested
    @DisplayName("Invalid inputs")
    class InvalidCases {
        @ParameterizedTest
        @DisplayName("Rejects bad formats and lengths")
        @ValueSource(strings = {"", "   ", "abc^%$gdebhe", "123-456-789","1234567890"})
        void badFormatsFail(String bsn) {
            assertFalse(validator.isValid(bsn));
        }

        @ParameterizedTest
        @DisplayName("Rejects numbers that fail the 11-proef")
        @ValueSource(strings = {"111111111", "123456789", "000000001"})
        void checksumFails(String bsn) {
            assertFalse(validator.isValid(bsn));
        }

        @ParameterizedTest
        @DisplayName("All zeros are invalid (8 or 9 digits)")
        @ValueSource(strings = {"00000000", "000000000"})
        void zerosAreInvalid(String bsn) {
            assertFalse(validator.isValid(bsn));
        }
    }
}
