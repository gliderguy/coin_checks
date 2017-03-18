import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoinChanger {
    public static final List<Integer> COIN_VALUES_IN_CENT = Arrays.asList(200, 100, 50, 20, 10, 5, 1);

    public List<Integer> change(int amountToChange) {
        List<Integer> coins = new ArrayList<>();

        int rest = amountToChange;
        for (Integer coinValue : COIN_VALUES_IN_CENT) {
            rest = addCoinsForValue(coinValue, rest, coins);
        }

        if (rest > 0) {
            throw new IllegalStateException("The rest should be zero at this point.");
        }
        return coins;
    }

    private int addCoinsForValue(int coinValue, int rest, List<Integer> coins) {
        while (rest >= coinValue) {
            coins.add(coinValue);
            rest -= coinValue;
        }
        return rest;
    }
}
