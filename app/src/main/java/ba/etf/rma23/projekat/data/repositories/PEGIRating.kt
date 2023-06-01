package ba.etf.rma23.projekat.data.repositories

enum class PEGIRating(val value: Int) {
    Three(1),
    Seven(2),
    Twelve(3),
    Sixteen(4),
    Eighteen(5);

    companion object{
        fun getGameRating(value: Int): PEGIRating? {
            return PEGIRating.values().find { it.value == value }
        }
    }
}