package ru.job4j.early;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PasswordValidatorTest {

    @Test
    void validateEmptyPassword() {
        assertThatThrownBy(() -> PasswordValidator.validate(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("The password does not contain any characters");
    }

    @Test
    void validateMinLength() {
        assertThatThrownBy(() -> PasswordValidator.validate("1AdMin#"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Number of characters less than 8");
    }

    @Test
    void validateMaxLength() {
        assertThatThrownBy(() -> PasswordValidator.validate("1AdMin#qwertyPASSWORD12345userQWERTY#@"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("The number of characters is more than 32");
    }

    @Test
    void validateNoDigit() {
        assertThatThrownBy(() -> PasswordValidator.validate("gfdhtfdxfhtdht"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("The password must contain numbers");
    }

    @Test
    void validateWithANumberThenNoRegisterUpper() {
        assertThatThrownBy(() -> PasswordValidator.validate("gf3dxfhtd7ht"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("The password must contain uppercase");
    }

    @Test
    void validateNoRegisterUpper() {
        assertThatThrownBy(() -> PasswordValidator.validate("gf3f3td7ht#"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("The password must contain uppercase");
    }

    @Test
    void validateNoRegisterLower() {
        assertThatThrownBy(() -> PasswordValidator.validate("AS$HHJJ4HHH"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("The password must contain lowercase");
    }

    @Test
    void validateNoSymbol() {
        assertThatThrownBy(() -> PasswordValidator.validate("ASbHJJ4jhg"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("The password does not contain any characters");
    }

    @Test
    void validateNoSubstring() {
        assertThatThrownBy(() -> PasswordValidator.validate("A5bHJ UsEr 5*"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("The password contains a forbidden substring");
        assertThatThrownBy(() -> PasswordValidator.validate("A5b paSSworD 5*"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("The password contains a forbidden substring");
        assertThatThrownBy(() -> PasswordValidator.validate("AdMIn 32@"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("The password contains a forbidden substring");
        assertThatThrownBy(() -> PasswordValidator.validate("A5bHJ 12345 *"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("The password contains a forbidden substring");
        assertThatThrownBy(() -> PasswordValidator.validate("A5bHJ Qwerty *"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("The password contains a forbidden substring");
    }

    @Test
    void checkingTheCorrectPasswordOne() {
        assertThat(PasswordValidator.validate("A1$hJJ4H32Rus")).isEqualTo("A1$hJJ4H32Rus");
        assertThat(PasswordValidator.validate("QWE123rty45#Us*Er")).isEqualTo("QWE123rty45#Us*Er");
        assertThat(PasswordValidator.validate("QweRru12346UsiR%")).isEqualTo("QweRru12346UsiR%");
        assertThatThrownBy(() -> PasswordValidator.validate("12345#RuS"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("The password contains a forbidden substring");
    }
}