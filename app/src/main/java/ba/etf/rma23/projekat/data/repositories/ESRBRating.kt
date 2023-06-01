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
    AO(12);

    companion object{
        fun getGameRating(value: Int): ESRBRating? {
            return ESRBRating.values().find { it.value == value }
        }
    }

}