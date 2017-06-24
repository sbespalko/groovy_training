import org.junit.Test

class MoneyTest {
    @Test
    void plus() throws Exception {
        Money m1 = new Money(30, 'rub')
        Money m2 = new Money(20, 'eur')
        println m1 + m2
    }

    @Test
    void getRate() {
        println Money.getRate('eur','rub')
        println Money.getRate('usd','rub')
    }

}