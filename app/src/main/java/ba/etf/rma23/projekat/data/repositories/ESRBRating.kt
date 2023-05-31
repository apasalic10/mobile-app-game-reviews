package ba.etf.rma23.projekat.data.repositories

enum class ESRBRating(val value: Int) {
    Three(1),
    Seven(2),
    Twelve(3),
    Sixteen(4),
    Eighteen(5),
    RP(6),
    EC(7),
    E(8),
    E10(9),
    T(10),
    M(11),
    AO(12),
    CERO_A(13),
    CERO_B(14),
    CERO_C(15),
    CERO_D(16),
    CERO_Z(17),
    USK_0(18),
    USK_6(19),
    USK_12(20),
    USK_16(21),
    USK_18(22),
    GRAC_ALL(23),
    GRAC_Twelve(24),
    GRAC_Fifteen(25),
    GRAC_Eighteen(26),
    GRAC_TESTING(27),
    CLASS_IND_L(28),
    CLASS_IND_Ten(29),
    CLASS_IND_Twelve(30),
    CLASS_IND_Fourteen(31),
    CLASS_IND_Sixteen(32),
    CLASS_IND_Eighteen(33),
    ACB_G(34),
    ACB_PG(35),
    ACB_M(36),
    ACB_MA15(37),
    ACB_R18(38),
    ACB_RC(39);

    companion object{
        fun getGameRating(value: Int): ESRBRating? {
            return ESRBRating.values().find { it.value == value }
        }
    }

}