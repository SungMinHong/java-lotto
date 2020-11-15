package lotto.domain.lotto;

import lotto.domain.Money;
import util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static util.Preconditions.checkArgument;

public class Lotto {
    private static final int LOTTO_NUMBERS_LENGTH = 6;
    private static final Money PRICE = Money.of(1000);
    private static final CreateLottoNumbersStrategy DEFAULT_CREATE_NUMBER_STRATEGY = new CreateRandomNumbersStrategy();
    private static final CreateLottoNumberStrategy DEFAULT_CREATE_BONUS_NUMBER_STRATEGY = new CreateRandomNumberStrategy();
    public static final String LOTTO_NUMBER_MUST_NOT_BE_NULL = "lotto number must not be null";
    public static final String LOTTO_NUMBER_SIZE_NOT_VALID = "lotto number size must be " + LOTTO_NUMBERS_LENGTH;
    public static final String LOTTO_NUMBER_MUST_NOT_BE_BLANK = "lotto number must not be blank";
    public static final String LOTTO_NUMBER_MUST_NOT_BE_DUPLICATED = "lotto number must not be duplicated";
    private static final String SEPARATOR = ", ";

    private final List<LottoNumber> numbers;
    private final LottoNumber bonusNumber;

    private Lotto(final List<LottoNumber> numbers, final LottoNumber bonusNumber) {
        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    public static Lotto of(final List<LottoNumber> numbers, final LottoNumber bonusNumber) {
        checkArgument(numbers != null, LOTTO_NUMBER_MUST_NOT_BE_NULL);
        checkArgument(numbers.size() == LOTTO_NUMBERS_LENGTH, LOTTO_NUMBER_SIZE_NOT_VALID);
        checkArgument(hasNotDuplicates(numbers), LOTTO_NUMBER_MUST_NOT_BE_DUPLICATED);
        numbers.sort(Comparator.comparing(LottoNumber::getValue));
        return new Lotto(numbers, bonusNumber);
    }

    public static Lotto of(final CreateLottoNumbersStrategy lottoNumbersStrategy, final CreateLottoNumberStrategy bonusNumberStrategy) {
        return of(lottoNumbersStrategy.create(), bonusNumberStrategy.create());
    }

    public static Lotto of() {
        return of(DEFAULT_CREATE_NUMBER_STRATEGY, DEFAULT_CREATE_BONUS_NUMBER_STRATEGY);
    }

    public static Lotto of(final String lottoNumberExpression, final int bonusNumber) {
        checkArgument(StringUtils.isNotBlank(lottoNumberExpression), LOTTO_NUMBER_MUST_NOT_BE_BLANK);
        final List<LottoNumber> lottoNumbers = Arrays.stream(lottoNumberExpression.split(SEPARATOR))
                .map(Integer::parseInt)
                .map(LottoNumber::of)
                .collect(Collectors.toList());
        return of(lottoNumbers, LottoNumber.of(bonusNumber));
    }

    public static boolean hasNotDuplicates(final List<LottoNumber> numbers) {
        return !hasDuplicates(numbers);
    }

    public static boolean hasDuplicates(final List<LottoNumber> numbers) {
        return numbers.stream()
                .map(LottoNumber::getValue)
                .distinct()
                .count() != numbers.size();
    }

    public int countHitNumber(final Lotto lotto) {
        return (int) this.numbers.stream()
                .filter(lotto.numbers::contains).count();
    }
    
    public boolean isMatchBonus(final Lotto lotto) {
        return this.bonusNumber.equals(lotto.bonusNumber);
    }

    public static Money getPrice() {
        return PRICE;
    }

    public static int getLottoNumbersLength() {
        return LOTTO_NUMBERS_LENGTH;
    }

    public List<Integer> getLottoNumber() {
        return numbers.stream()
                .map(LottoNumber::getValue)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }
}
