package lotto.domain.lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static lotto.domain.lotto.LottoTicketGroupFactory.COUNT_MUST_NOT_BE_NEGATIVE;


class LottoTicketGroupFactoryTest {
    @DisplayName("Lottos 생성")
    @Nested
    class Create {
        @DisplayName("0 개 생성")
        @Test
        void zero() {
            // given
            final int count = 0;

            // when
            final LottoTicketGroup lottoTicketGroup = LottoTicketGroupFactory.create(count);

            // then
            assertThat(lottoTicketGroup).isEqualTo(LottoTicketGroup.EMPTY);
            assertThat(lottoTicketGroup.size()).isZero();
        }

        @DisplayName("1 개 생성")
        @Test
        void one() {
            // given
            final int count = 1;

            // when
            final LottoTicketGroup lottoTicketGroup = LottoTicketGroupFactory.create(count);

            // then
            assertThat(lottoTicketGroup).isNotNull();
            assertThat(lottoTicketGroup.size()).isOne();
        }

        @DisplayName("음수의 경우 예외 throw")
        @Test
        void throw_exception() {
            // given
            final int count = -1;

            // when
            final Throwable thrown = catchThrowable(() -> {
                LottoTicketGroupFactory.create(count);
            });

            // then
            Assertions.assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(COUNT_MUST_NOT_BE_NEGATIVE);
        }
    }
}