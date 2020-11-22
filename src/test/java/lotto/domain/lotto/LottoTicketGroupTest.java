package lotto.domain.lotto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lotto.domain.lotto.LottoTicketMockFactory.createLottoTicketGroup;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class LottoTicketGroupTest {
    @Test
    void of_return_empty_obj_when_receive_null() {
        // when
        final LottoTicketGroup result = LottoTicketGroup.of(null);

        // then
        assertThat(result).isEqualTo(LottoTicketGroup.EMPTY);
    }

    @Test
    void size() {
        // given
        final List<LottoTicket> lottoTicketList = LottoTicketMockFactory.createFourTeenLotto();
        final LottoTicketGroup lottoTicketGroup = LottoTicketGroup.of(lottoTicketList);

        // when
        final int result = lottoTicketGroup.size();

        // then
        assertThat(result).isEqualTo(lottoTicketList.size());
    }

    @Test
    void combine_return_combined_LottoTicketGroup() {
        // given
        final LottoTicketGroup lottoTicketGroup1 = createLottoTicketGroup(Arrays.asList(1, 2, 3, 4, 5, 6));
        final LottoTicketGroup lottoTicketGroup2 = createLottoTicketGroup(Arrays.asList(7, 8, 9, 10, 11, 12));
        final List<LottoTicket> allLottoTickets = new ArrayList<>(lottoTicketGroup1.get());
        allLottoTickets.addAll(lottoTicketGroup2.get());

        // when
        final LottoTicketGroup result = LottoTicketGroup.combine(lottoTicketGroup1, lottoTicketGroup2);

        // then
        assertThat(result.get()).containsAll(allLottoTickets);
    }

    @Test
    void combine_when_not_empty_list_combine_empty_list() {
        // given
        final LottoTicketGroup lottoTicketGroup = createLottoTicketGroup(Arrays.asList(1, 2, 3, 4, 5, 6));
        final List<LottoTicket> allLottoTickets = new ArrayList<>(lottoTicketGroup.get());

        // when
        final LottoTicketGroup result = LottoTicketGroup.combine(lottoTicketGroup, LottoTicketGroup.EMPTY);

        // then
        assertThat(result.get()).containsAll(allLottoTickets);
    }

    @Test
    void combine_when_empty_list_combine_empty_list() {
        // given
        final List<LottoTicket> emptyList = new ArrayList<>(LottoTicketGroup.EMPTY.get());

        // when
        final LottoTicketGroup result = LottoTicketGroup.combine(LottoTicketGroup.EMPTY, LottoTicketGroup.EMPTY);

        // then
        assertThat(result.get()).containsAll(emptyList);
    }

    @Test
    void combine_return_empty_obj_when_receive_null() {
        // when
        final LottoTicketGroup result = LottoTicketGroup.combine(null);

        // then
        assertThat(result).isEqualTo(LottoTicketGroup.EMPTY);
    }
}