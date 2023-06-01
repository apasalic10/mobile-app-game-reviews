package ba.etf.rma23.projekat.data.repositories

enum class AgeRating(val value: Int) {
    Three(3),
    Seven(7),
    Twelve(12),
    Sixteen(16),
    Eighteen(18),
    RP(-1),
    EC(3),
    E(6),
    E10(10),
    T(13),
    M(17),
    AO(18);
}