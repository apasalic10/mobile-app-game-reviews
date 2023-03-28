package com.example.rma23_19079_videogames

fun getAll() : List<Game> {
    return listOf(
        Game("Grand Theft Auto IV", "PlayStation 3",
            "29 April 2008", 9.1, "https://www.igrandtheftauto.com/img/content/640x0/3618.jpg", "No Rating Summary",
            "Rockstar North", "Rockstar Games", "Action-adventure", "Grand Theft Auto IV is an action-adventure game played from a third-person perspective.[2] Players complete missions—linear scenarios with set objectives—to progress through the story.",
            listOf(UserRating("niko123",1,9.0), UserRating("alme001",2,8.0),
            UserRating("tony55",3,6.0), UserRating("allen14",4,7.0),
            UserRating("mike",5,8.3)
            )
        ),
        Game("PUBG: Battlegrounds","PlayStation 5", "20 December 2017",
        9.0, "https://upload.wikimedia.org/wikipedia/en/thumb/9/9f/Pubgbattlegrounds.png/220px-Pubgbattlegrounds.png",
        "This is a multiplayer action game in which players can participate in \"last-man-standing\"-style shootouts while collecting supplies/weapons/armor around an island.",
        "PUBG Studios","Krafton\n" + "Microsoft Studios (Xbox One)\n" + "Tencent Games (mobile)",
        "Battle royale", "PUBG is a player versus player shooter game in which up to one hundred players fight in a battle royale, a type of large-scale last man standing deathmatch where players fight to remain the last alive",
            listOf(UserRating("niko123",1,9.0), UserRating("alme001",2,8.0),
                UserRating("tony55",3,6.0), UserRating("allen14",4,7.0),
                UserRating("blackMan",5,8.7))),
    )
}

fun getDetails(title : String) : Game? {
    for(g : Game in getAll()){
        if(g.title.equals(title)){
            return g;
        }
    }

    return null;
}