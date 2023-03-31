package com.example.rma23_19079_videogames

import java.util.*

class GameData {
    companion object{
        fun getAll() : List<Game> {
            return listOf(
                Game("Grand Theft Auto IV", "PlayStation 3",
                    "29 April 2008", 9.1, "https://www.igrandtheftauto.com/img/content/640x0/3618.jpg", "No Rating Summary",
                    "Rockstar North", "Rockstar Games", "Action-adventure", "Grand Theft Auto IV is an action-adventure game played from a third-person perspective. Players complete missions—linear scenarios with set objectives—to progress through the story.",
                    listOf(UserRating("niko123",1,9.0), UserRating("alme001",2,8.0),
                        UserRating("tony55",3,6.0), UserRating("allen14",4,7.0),
                        UserRating("mike",5,8.3)
                    )
                ),
                Game("PUBG: Battlegrounds","PlayStation 5", "20 December 2017",
                    9.0, "https://upload.wikimedia.org/wikipedia/en/thumb/9/9f/Pubgbattlegrounds.png/220px-Pubgbattlegrounds.png",
                    "This is a multiplayer action game in which players can participate in last-man - standing - style shootouts while collecting supplies/weapons/armor around an island.",
                    "PUBG Studios","Krafton, " + " Microsoft Studios (Xbox One), " + " Tencent Games (mobile)",
                    "Battle royale", "PUBG is a player versus player shooter game in which up to one hundred players fight in a battle royale, a type of large - scale last man standing deathmatch where players fight to remain the last alive",
                    listOf(UserRating("niko123",6,9.0), UserRating("alme001",7,8.0),
                        UserRating("tony55",10,6.0), UserRating("allen14",11,7.0),
                        UserRating("blackMan",12,8.7))),
                Game("FIFA 23", "PlayStation 5, " +" Windows, " + "Xbox One", "30 September 2022",
                    9.5, "https://fifauteam.com/images/covers/fifa23/standard-cg.webp",
                    "This is a soccer simulation game in which players engage in realistic matches with teams across international leagues.",
                    "Electronic Arts", "EA Sports", "Sports", "FIFA 23 is a football video game published by Electronic Arts.",
                    listOf(UserReview("philipK0", 14,"This game is very interesting!"), UserRating("anelOsm",13,10.0),
                    UserRating("veca58",17,7.9),UserRating("allen14",16,8.5), UserRating("alme001",15,8.3))),
                Game("Assassin's Creed Valhalla","Playstation 5", "November 10, 2020", 8.9, "https://upload.wikimedia.org/wikipedia/en/f/ff/Assassin%27s_Creed_Valhalla_cover.jpg",
                    "This is an action-adventure game in which players assume the role of a Viking mercenary (Eivor) who travels through England to forge alliances, lead raids, and defeat rival clans/rulers.",
                    "Ubisoft Montreal", "Ubisoft", "Action role-playing", "Assassin's Creed Valhalla is a 2020 action role-playing video game developed by Ubisoft Montreal and published by Ubisoft.",
                    listOf()),
                Game("Red Dead Redemption 2", "PlayStation 4, " + "Xbox One, " + "Windows", "November 5, 2019", 8.7,
                    "https://upload.wikimedia.org/wikipedia/en/4/44/Red_Dead_Redemption_II.jpg", "This is an action-adventure game in which players assume the role of Arthur Morgan, a member of an outlaw gang on the run in 1899.",
                    "Rockstar Studios", "Rockstar Games", "Action-adventure", "Red Dead Redemption 2 is a 2019 action-adventure game developed and published by Rockstar Games.",
                    listOf()),
                Game("Rocket League", "PlayStation 4, " + "Xbox One, " + "Windows", "July 7, 2015", 8.3,
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/Rocket_League_coverart.jpg/330px-Rocket_League_coverart.jpg",
                    "This is an action-racing game in which players drive futuristic cars to play games of soccer.", "Psyonix", "Psyonix",
                    "Sports", "Rocket League is a vehicular soccer video game developed and published by Psyonix.", listOf()),
                Game("Forza Horizon 5", "Xbox One, " + "Windows", "9 November 2021", 7.5, "https://upload.wikimedia.org/wikipedia/en/8/86/Forza_Horizon_5_cover_art.jpg",
                    "This is a racing simulation game in which players assume the role of a veteran driver helping to expand the Horizon Festival to Mexico.",
                    "Playground Games", "Xbox Game Studios", "Racing", "Forza Horizon 5 is a 2021 racing video game developed by Playground Games and published by Xbox Game Studios.",
                    listOf()),
                Game("It Takes Two", "PlayStation 5, " + "Xbox One, " + "Windows", "March 26, 2021", 6.9,
                    "https://upload.wikimedia.org/wikipedia/en/a/aa/It_Takes_Two_cover_art.png", "This is a co-op adventure game in which players follow a divorcing couple through a fantasy adventure.",
                    "Hazelight Studios", "Electronic Arts", "Action-adventure", "It Takes Two is an action-adventure platform video game developed by Hazelight Studios and published by Electronic Arts.",
                    listOf()),
                Game("Hitman 3", "PlayStation 5, " + "Xbox One, " + "Windows", "20 January 2021", 6.1,
                    "https://upload.wikimedia.org/wikipedia/en/4/4b/Hitman_3_Packart.jpg", "This is a stealth action game in which players assume the role of an assassin (Agent 47) tasked with eliminating targets through a variety of missions.",
                    "IO Interactive", "IO Interactive", "Stealth", "Hitman 3 is a 2021 stealth game developed and published by IO Interactive.",
                    listOf()),
                Game("Spider-Man", "PlayStation 4, " + "Playstation 5, " + "Windows", "September 7, 2018", 6.0,
                    "https://upload.wikimedia.org/wikipedia/en/e/e1/Spider-Man_PS4_cover.jpg", "This is an action game in which players assume the role of Miles Morales/Spider-Man as he battles underground gangs and villains while attempting to save New York City from attacks.",
                    "Insomniac Games", "Sony Interactive", "Action-adventure", "Marvel's Spider-Man is a 2018 action-adventure game developed by Insomniac Games and published by Sony Interactive Entertainment.",
                    listOf())
            )
        }

        fun getDetails(title : String) : Game? {
            for(g : Game in getAll()){
                if(g.title.toLowerCase().equals(title.toLowerCase())){
                    return g;
                }
            }

            return null;
        }

        fun getImpressionsOfGame(title : String) : ArrayList<UserImpression> {
            var impressions  = ArrayList<UserImpression>()
            for(g : Game in getAll()){
                if(g.title.equals(title)){
                    for(impression : UserImpression in g.userImpressions){
                        impressions.add(impression)
                    }
                }
            }

            impressions.sortByDescending {
                it.timestamp
            }

            return impressions;
        }
    }
}