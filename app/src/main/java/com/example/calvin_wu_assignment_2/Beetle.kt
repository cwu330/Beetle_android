package com.example.calvin_wu_assignment_2

data class Beetle(
    var body: Int = 0,
    var head: Int = 0,
    var legs: Int = 0,
    var antennae: Int = 0,
    var eyes: Int = 0,
    var tail: Int = 0
) {
    fun isComplete(): Boolean {
        return body == 1 && head == 1 && legs == 6 &&
                antennae == 2 && eyes == 2 && tail == 1
    }

    fun applyRoll(roll: Int): String {
        return when (roll) {
            6 -> if (body == 0) {
                body = 1
                "Body added!"
            } else "Already has body."

            5 -> if (body == 1 && head == 0) {
                head = 1
                "Head added!"
            } else if (body == 0) {
                "Need body before head."
            } else "Already has head."

            4 -> if (body == 1 && legs < 6) {
                legs++
                "Leg $legs added!"
            } else if (body == 0) {
                "Need body before legs."
            } else "All legs added."

            3 -> if (head == 1 && antennae < 2) {
                antennae++
                "Antenna $antennae added!"
            } else if (head == 0) {
                "Need head before antennae."
            } else "All antennae added."

            2 -> if (head == 1 && eyes < 2) {
                eyes++
                "Eye $eyes added!"
            } else if (head == 0) {
                "Need head before eyes."
            } else "All eyes added."

            1 -> if (body == 1 && tail == 0) {
                tail = 1
                "Tail added!"
            } else if (body == 0) {
                "Need body before tail."
            } else "Already has tail."

            else -> "Invalid roll."
        }
    }

    fun reset() {
        body = 0
        head = 0
        legs = 0
        antennae = 0
        eyes = 0
        tail = 0
    }
}
