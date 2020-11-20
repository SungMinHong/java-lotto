package lotto.domain;

import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.LottoTicketGroup;
import lotto.domain.rank.LottoRankCalculator;
import lotto.dto.LottoStatisticsResult;
import lotto.dto.WinLotteryResult;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static util.Preconditions.checkArgument;

public class LottoGame {
    public static final String MONEY_MUST_GRATE_THEN_ZERO_TO_BUY_LOTTO = "money must grate then zero to buy lotto";
    private final Money money;
    private LottoTicketGroup lottoTicketGroup = LottoTicketGroup.EMPTY;

    private LottoGame(final Money money) {
        this.money = money;
    }

    public static LottoGame of(final int money) {

        return new LottoGame(Money.of(money));
    }

    public LottoTicketGroup buyLotto(final List<String> lottoNumberExpressionList) {
        checkArgument(money.isNotZero(), MONEY_MUST_GRATE_THEN_ZERO_TO_BUY_LOTTO);

        if (Objects.isNull(lottoNumberExpressionList) || lottoNumberExpressionList.size() == 0) {
            this.lottoTicketGroup = LottoStore.sell(money);
            return getLottoTicketGroup();
        }
        return buyLottoWithSlipMode(lottoNumberExpressionList);
    }

    private LottoTicketGroup buyLottoWithSlipMode(final List<String> lottoNumberExpressionList) {
        final LottoTicketGroup slipLottoTicketGroup = LottoTicketGroup.of(lottoNumberExpressionList.stream()
                .map(LottoTicket::of)
                .collect(Collectors.toList()));

        this.lottoTicketGroup = LottoStore.sell(money, slipLottoTicketGroup);
        return getLottoTicketGroup();
    }

    public LottoTicketGroup getLottoTicketGroup() {
        return lottoTicketGroup;
    }

    public LottoStatisticsResult getWinLotteryStatistics(final String winningNumberExpression, final int bonusNumber) {
        final LottoRankCalculator lottoRankCalculator = new LottoRankCalculator();
        final LottoTicket winningLotto = LottoTicket.of(winningNumberExpression);
        final WinLotteryResult result = lottoRankCalculator.calculateWinLotteryResult(lottoTicketGroup, winningLotto, LottoNumber.of(bonusNumber));
        return new LottoStatisticsResult(result, getProfit(result));
    }

    private double getProfit(final WinLotteryResult result) {
        final Money prize = result.getTotalPrizeMoney();
        return prize.divide(money);
    }
}
