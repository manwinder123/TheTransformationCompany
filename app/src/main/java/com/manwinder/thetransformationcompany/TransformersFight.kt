package com.manwinder.thetransformationcompany

class TransformersFight(private val autobotsList : ArrayList<Transformer>, private val decepticonList: ArrayList<Transformer>) {

    var fightCount = 0
    var decepticonEliminationCount = 0
    var autobotEliminationCount = 0

    val autobotSurvivorsList = ArrayList<Transformer>()
    val decepticonSurvivorsList = ArrayList<Transformer>()

    init {
        sortListsByRank()
        fight()
    }

    private fun sortListsByRank() {
        val comparator = Comparator<Transformer> { p0, p1 ->
            when {
                p0?.rank!! < p1?.rank!! -> -1
                p0.rank > p1.rank -> 1
                else -> 0
            }
        }

        autobotsList.sortWith(comparator)
        decepticonList.sortWith(comparator)
    }

    private fun fight() {
        val longerList = when {
            autobotsList.size > decepticonList.size -> {
                autobotSurvivorsList.addAll(autobotsList.subList(decepticonList.size, autobotsList.size ))
                decepticonList.size
            }
            decepticonList.size > autobotsList.size -> {
                decepticonSurvivorsList.addAll(decepticonList.subList(autobotsList.size, decepticonList.size))
                autobotsList.size
            }
            else -> decepticonList.size
        }


        for (i in 0 until longerList) {
            val a = autobotsList[i]
            val d = decepticonList[i]

            fightCount += 1

            // Special cases
            if (a.name.toLowerCase() == "optimus prime" && d.name.toLowerCase() == "predaking" ||
                    d.name.toLowerCase() == "optimus prime" && a.name.toLowerCase() == "predaking") {
                autobotSurvivorsList.clear()
                decepticonSurvivorsList.clear()
                decepticonEliminationCount = 0
                autobotEliminationCount = 0
                break
            } else if (a.name.toLowerCase() == "optimus prime" || a.name.toLowerCase() == "predaking") {
                autobotSurvivorsList.add(a)
                autobotEliminationCount += 1
                continue
            } else if (d.name.toLowerCase() == "optimus prime" || d.name.toLowerCase() == "predaking") {
                decepticonSurvivorsList.add(d)
                decepticonEliminationCount += 1
                continue
            }

            // Courage & Strength elimination
            if ((d.courage - a.courage) >= 4 && (d.strength - a.strength) >= 3){
                decepticonSurvivorsList.add(d)
                decepticonEliminationCount += 1
            } else if ((a.courage - d.courage) >= 4 && (a.strength - d.strength) >= 3){
                autobotSurvivorsList.add(a)
                autobotEliminationCount += 1

            // Skill elimination
            } else if ((d.skill - a.skill) >= 3){
                decepticonSurvivorsList.add(d)
                decepticonEliminationCount += 1
            } else if ((a.skill- d.skill) >= 3){
                autobotSurvivorsList.add(a)
                autobotEliminationCount += 1
            }

            // Overall rating elimination
            else if (d.overallRating > a.overallRating) {
                decepticonSurvivorsList.add(d)
                decepticonEliminationCount += 1
            } else if (a.overallRating > d.overallRating){
                autobotSurvivorsList.add(a)
                autobotEliminationCount += 1
            } else if (a.overallRating == d.overallRating){

                // both eliminate each other
                autobotEliminationCount += 1
                decepticonEliminationCount += 1
            }
        }
    }

    fun getWinningTeamName(): String {
        return when {
            autobotEliminationCount > decepticonEliminationCount -> "Autobots"
            decepticonEliminationCount > autobotEliminationCount -> "Decepticons"
            else -> "Nobody"
        }
    }

    fun getWinningTeamMembers(): String {
        var teamMembers  = ""
        return when {
            autobotEliminationCount > decepticonEliminationCount -> {
                autobotsList.forEach {
                    teamMembers += "${it.name}, "
                }
                teamMembers.dropLast(2)
            }
            decepticonEliminationCount > autobotEliminationCount -> {
                decepticonList.forEach {
                    teamMembers += "${it.name}, "
                }
                teamMembers.dropLast(2)
            }
            else -> teamMembers
        }
    }

    fun getLosingTeamName(): String {
        return when {
            autobotEliminationCount < decepticonEliminationCount -> "Autobots"
            decepticonEliminationCount < autobotEliminationCount -> "Decepticons"
            else -> "Nobody"
        }
    }

    fun getLosingTeamMembersThatSurvived(): String {
        var teamMembers  = ""
        return when {
            autobotEliminationCount < decepticonEliminationCount -> {
                autobotSurvivorsList.forEach {
                    teamMembers += "${it.name}, "
                }
                teamMembers.dropLast(2)
            }
            decepticonEliminationCount > autobotEliminationCount -> {
                decepticonSurvivorsList.forEach {
                    teamMembers += "${it.name}, "
                }
                teamMembers.dropLast(2)
            }
            else -> teamMembers
        }
    }
}