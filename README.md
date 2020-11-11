# 로또
## 진행 방법
* 로또 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## STEP1 문자열 덧셈 계산기
### 기능 정의
* 문자열 계산기는 다음과 같은 기능이 있다.
* 구분자를 기준으로 문자열을 분리할 수 있다.
        * 구분자1: 쉼표(,)
        * 구분자2: 콜론(:)
        * 커스텀 구분자: “//”와 “\n” 문자열 사이에 위치하는 문자를 사용한다.
* 구분자를 기준으로 분리된 각 숫자의 합을 반환한다.
* 예외 처리
    * null: 0을 반환
    * 공백문자: 0을 반환
    * 숫자 이외의 문자: RuntimeException throw
    * 음수 값: RuntimeException throw
    
## STEP2 로또 자동
### 기능 정의
* 로또의 특성은 다음과 같다.
    * 1장의 가격은 1000원이다.
    * 총 6자리이다.
    * 당첨금은 다음과 같다.
        * 3개 일치: 5,000원
        * 4개 일치: 50,000원
        * 5개 일치: 1,500,000원
        * 6개 일치: 2,000,000,000원
* 로또 구입 금액을 입력 받을 수 있다.
    * 다음 문구를 출력한다.
        * "구입금액을 입력해 주세요."
    * 로또 구입 금액을 입력 받는다.
        * 금액은 로또 가격 단위로 나누어 떨어짐을 보장한다.
* 금액 만큼 로또를 구매한다.
* "n개를 구매했습니다." 문구를 출력할 수 있다.
* 로또 번호 6개를 로또 구매 장 수 만큼 반복해서 출력할 수 있다.
* 구입 금액에 해당하는 로또를 발급한다.
* 지난 주 당첨 번호를 입력 받을 수 있다.
    * 다음 문구를 출력한다.
        * "지난 주 당첨 번호를 입력해 주세요."
    * 로또 번호를 입력 받는다.
        * 총 6자리 숫자를 입력 받는다.
* 다음을 출력할 수 있다.
    * 당첨 현황
    * 총 수익률
