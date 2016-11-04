package de.novatec.quickcheck.coinchanger;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

@RunWith(JUnitQuickcheck.class)
public class CoinChangerTest {

    /** The class under test. */
    private CoinChanger cut;

    @Before
    public void init() {
        cut = new CoinChanger();
    }

    @Property
    public void sumOfCoinsEqualsAmount(@InRange(min = "0", max = "500") int amountToChange) {
        List<Integer> coins = cut.change(amountToChange);

        int sum = coins.stream().mapToInt(i -> i).sum();
        assertThat(sum).isEqualTo(amountToChange);
    }

    @Property
    public void coinsAreOrderedByDescendingValue(@InRange(min = "0", max = "500") int amountToChange) {
        List<Integer> coins = cut.change(amountToChange);

        assertThat(coins).isSortedAccordingTo((o1, o2) -> o2.compareTo(o1));
    }

    @Property
    public void numberOfCoinsIsMinimal(@InRange(min = "0", max = "500") int amountToChange) {
        List<Integer> coins = cut.change(amountToChange);

        for (Integer coinValue : CoinChanger.COIN_VALUES_IN_CENT) {
            int sumOfSmallerCoinValues = coins.stream().filter(i -> i < coinValue).mapToInt(i -> i).sum();
            assertThat(sumOfSmallerCoinValues).isLessThan(coinValue);
        }
    }
}
