package step2;

import java.util.List;

public class Lotto {
    private static final Money PRICE = Money.of(1000);
    private static final CreateLottoNumbersStrategy DEFAULT_CREATE_NUMBER_STRATEGY = new CreateRandomNumbersStrategy();
    private final List<LottoNumber> numbers;
    private static final int LOTTO_NUMBERS_LENGTH = 6;
    
    private Lotto(final List<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    public static Lotto of(final CreateLottoNumbersStrategy strategy) {
        return new Lotto(strategy.create());
    }

    public static Lotto of() {
        return of(DEFAULT_CREATE_NUMBER_STRATEGY);
    }

    public static Money getPrice() {
        return PRICE;
    }

    public int countHitNumber(final List<LottoNumber> numbers) {
        return (int) this.numbers.stream()
                .filter(numbers::contains).count();
    }

    public static int getLottoNumbersLength() {
        return LOTTO_NUMBERS_LENGTH;
    }
}
