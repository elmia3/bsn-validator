package nl.bsn;

public class BsnValidator {
    /**
     * Validates a Dutch BSN using the 11-proef with weights [9,8,7,6,5,4,3,2,-1].
     * Accepts 9 digits; accepts 8 digits by prepending a leading zero.
     */
    public boolean isValid(String input) {
        if (input == null) return false;

        String s = input.trim();
        if (s.isEmpty() || !s.chars().allMatch(Character::isDigit)) return false;

        if (s.length() == 8) {
            s = "0" + s;
        } else if (s.length() != 9) {
            return false;
        }

        // reject all zeros
        if (s.chars().allMatch(ch -> ch == '0')) return false;

        int[] weights = {9,8,7,6,5,4,3,2,-1};
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = s.charAt(i) - '0';
            sum += digit * weights[i];
        }
        return sum % 11 == 0;
    }
}